package top.ftas.dunit.compiler;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

import top.ftas.dunit.group.DUnitGroupInterface;
import top.ftas.dunit.group.DUnitRootGroup;
import top.ftas.dunit.model.DUnitBaseModel;
import top.ftas.dunit.model.DUnitGroupModel;
import top.ftas.dunit.model.DUnitModel;
import top.ftas.dunit.util.DUnitConstant;
import top.ftas.dunit.util.GenericTypesUtil;
import top.ftas.dunit.util.ModelValueUtil;

import static com.squareup.javapoet.ClassName.bestGuess;

/**
 * Created by tik on 17/7/2.
 */

public class InitMethodUtil {

	public static abstract class DoOther{
		public abstract void process(MethodSpec.Builder builder);
	}

	public static MethodSpec.Builder processInitMethodSpecBuilder(MethodSpec.Builder initMethodBuild, DoOther doOther, String modelListVariableName, String tmpModelVariableName, Class<? extends DUnitBaseModel> modelType){
		String tmpStr;

		//处理DUnitModel
		//for (DUnitModel unitModel: unitModels) {
		tmpStr = String.format("for ($T %s: %s) {",tmpModelVariableName,modelListVariableName);
		initMethodBuild.addStatement(tmpStr, modelType);
		//try {
		initMethodBuild.addStatement("try {");
		////Original
		initMethodBuild.addStatement("//Original");
		//Class<?> unitGroupClass = Class.forName(unitModel.getOriginalClassName());
		tmpStr = String.format("$T<?> unitGroupClass = $T.forName(%s.getOriginalClassName())",tmpModelVariableName);
		initMethodBuild.addStatement(tmpStr, Class.class, Class.class);
		//unitModel.setOriginal(unitGroupClass);
		tmpStr = String.format("%s.setOriginal(unitGroupClass)",tmpModelVariableName);
		initMethodBuild.addStatement(tmpStr);

		//DoOther
		if (doOther != null){
			doOther.process(initMethodBuild);
		}

		//}catch (Exception e){
		initMethodBuild.addStatement("}catch ($T e){", Exception.class);
		//e.printStackTrace();
		initMethodBuild.addStatement("e.printStackTrace()");
		//}
		initMethodBuild.addStatement("}");
		//}
		initMethodBuild.addStatement("}");

		//return unitModels;
		tmpStr = String.format("return %s", modelListVariableName);
		initMethodBuild.addStatement(tmpStr);

		return initMethodBuild;
	}

	/**
	 * 创建init方法，用于数据初始化
	 *
	 * @param methodName        方法名
	 * @param genericTypesClass ArrayList泛型的类型
	 * @param gsonDataString    ArrayList被Gson转换出的字符串
	 * @param modelListVariableName      GsonData被还原后的临时变量名
	 * @return 初始化方法的构造器
	 */
	public static MethodSpec.Builder createInitMethodSpecBuilder(String methodName, Class genericTypesClass, String gsonDataString, String modelListVariableName) {
		String tmpStr;

		//Override注解
		AnnotationSpec annotation = createOverrideAnnotation();

		//return type，返回值
		ParameterizedType returnType = GenericTypesUtil.type(ArrayList.class, genericTypesClass);

		//protected ArrayList<DUnitModel> initUnitModels()，函数声明
		MethodSpec.Builder initMethodBuilder = MethodSpec
				.methodBuilder(methodName)
				.addModifiers(Modifier.PROTECTED)
				.addAnnotation(annotation)
				.returns(returnType);

		//Gson gson = new Gson();
		initMethodBuilder.addStatement("$T gson = new $T()", Gson.class, Gson.class);
		//ParameterizedType type = GenericTypesUtil.type(ArrayList.class,DUnitModel.class);
		initMethodBuilder.addStatement("$T type = $T.type($T.class,$T.class)", ParameterizedType.class, GenericTypesUtil.class, ArrayList.class, genericTypesClass);
		//ArrayList<DUnitModel> unitModels = gson.fromJson(unitModelsString_c,type);
		tmpStr = String.format("$T<$T> %s = gson.fromJson($S,type)", modelListVariableName);
		initMethodBuilder.addStatement(tmpStr, ArrayList.class, genericTypesClass, gsonDataString);
		return initMethodBuilder;
	}

	public static MethodSpec.Builder createMethodSpecBuilder_createModelMap(){
		String tmpStr;

		//Override注解
		AnnotationSpec annotation = createOverrideAnnotation();

		//return type，返回值
		Type type_Value = GenericTypesUtil.type(ArrayList.class,DUnitBaseModel.class);
		Type type_Key = GenericTypesUtil.getWildcardType(Class.class,DUnitGroupInterface.class);
		ParameterizedType returnType = GenericTypesUtil.type(HashMap.class, type_Key, type_Value);

		//参数
		Type type_unitGroupModels = GenericTypesUtil.type(ArrayList.class,DUnitGroupModel.class);
		ParameterSpec parameterSpec_unitGroupModels = ParameterSpec.builder(type_unitGroupModels,"unitGroupModels").build();
		Type type_unitModels = GenericTypesUtil.type(ArrayList.class,DUnitModel.class);
		ParameterSpec parameterSpec_unitModels = ParameterSpec.builder(type_unitModels,"unitModels").build();



		//函数声明
		//protected HashMap<Class<? extends DUnitGroupInterface>, ArrayList<DUnitBaseModel>> createModelMap(ArrayList<DUnitGroupModel> unitGroupModels, ArrayList<DUnitModel> unitModels) {
		MethodSpec.Builder methodBuilder = MethodSpec
				.methodBuilder("createModelMap")
				.addModifiers(Modifier.PROTECTED)
				.addParameter(parameterSpec_unitGroupModels)
				.addParameter(parameterSpec_unitModels)
				.addAnnotation(annotation)
				.returns(returnType);

		//函数体
		//HashMap<Class<? extends DUnitGroupInterface>,ArrayList<DUnitBaseModel>> modelMap = new HashMap<>();
		methodBuilder.addStatement("$T<$T<? extends $T>,$T<$T>> modelMap = new $T<>()",HashMap.class,Class.class,DUnitGroupInterface.class,ArrayList.class,DUnitBaseModel.class,HashMap.class);
		//modelMap.put(DUnitRootGroup.class,new ArrayList<DUnitBaseModel>());
		methodBuilder.addStatement("modelMap.put($T.class,new $T<$T>());",DUnitRootGroup.class,ArrayList.class,DUnitBaseModel.class);

		/***/
		//for (DUnitGroupModel unitGroupModel: unitGroupModels) {
		methodBuilder.addStatement("for ($T unitGroupModel: unitGroupModels) {",DUnitGroupModel.class);
		////将每个DUnitGroupModel根据Class添加到HashMap中
		methodBuilder.addStatement("//将每个DUnitGroupModel根据Class添加到HashMap中");
		//modelMap.put(unitGroupModel.getOriginal(),new ArrayList<DUnitBaseModel>());
		methodBuilder.addStatement("modelMap.put(unitGroupModel.getOriginal(),new $T<$T>())",ArrayList.class,DUnitBaseModel.class);
		//}
		methodBuilder.addStatement("}");

		/***/
		////根据所属组，将所有组分类
		methodBuilder.addStatement("//根据所属组，将所有组分类");
		//for (DUnitGroupModel unitGroupModel: unitGroupModels) {
		methodBuilder.addCode("for ($T unitGroupModel: unitGroupModels) {\n",DUnitGroupModel.class);
		//Class<? extends DUnitGroupInterface> group = unitGroupModel.getGroup();
		methodBuilder.addStatement("$T<? extends $T> group = unitGroupModel.getGroup()",Class.class,DUnitGroupInterface.class);
		//if (group == unitGroupModel.getOriginal()) continue;
		methodBuilder.addStatement("if (group == unitGroupModel.getOriginal()) continue");
		//modelMap.get(group).add(unitGroupModel);
		methodBuilder.addStatement("modelMap.get(group).add(unitGroupModel)");
		//}
		methodBuilder.addStatement("}");

		/***/
		////根据所属组，将所有Unit分类
		methodBuilder.addStatement("//根据所属组，将所有Unit分类");
		//for (DUnitModel unitModel: unitModels) {
		methodBuilder.addCode("for ($T unitModel: unitModels) {\n",DUnitModel.class);
		//Class<? extends DUnitGroupInterface> group = unitModel.getGroup();
		methodBuilder.addStatement("Class<? extends DUnitGroupInterface> group = unitModel.getGroup()",Class.class,DUnitGroupInterface.class);

		////test
//		methodBuilder.addStatement("if(modelMap.get(group) == null) {logTest(\"---》group:\" + group + \"  unitModel:\" + unitModel);continue; }");


		////test
//		methodBuilder.addCode("if(modelMap.get(group) == null) {\n");
//		ClassName className = ClassName.bestGuess("android.util.Log");
//		methodBuilder.addStatement("$T.i(\"test\",\"group:\" + group + \"   ->unitModel\" + unitModel)",className);
//		methodBuilder.addStatement("continue; }");

		//modelMap.get(group).add(unitModel);
		methodBuilder.addStatement("modelMap.get(group).add(unitModel)");
		//}
		methodBuilder.addStatement("}");
		//return modelMap;
		methodBuilder.addStatement("return modelMap");


		/***/
		return methodBuilder;

	}

	public static AnnotationSpec createOverrideAnnotation(){
		return AnnotationSpec
				.builder(Override.class)
				.build();
	}


	/**
	 * 创建initUnitGroupModels
	 */
	public static MethodSpec createMethodSpec_initUnitGroupModels(String unitGroupModelsString) {
		String modelListVariableName = "unitGroupModels";
		final String tmpModelVariableName= "unitGroupModel";
		MethodSpec.Builder initMethodBuild = InitMethodUtil.createInitMethodSpecBuilder("initUnitGroupModels", DUnitGroupModel.class, unitGroupModelsString, modelListVariableName);

		//DoOther
		InitMethodUtil.DoOther doOther = new InitMethodUtil.DoOther() {
			@Override
			public void process(MethodSpec.Builder builder) {
				String tmpStr;

				////DUnitGroupInterface
				builder.addStatement("//DUnitGroupInterface");
				//DUnitGroupInterface tmpUnitGroupInterface = (DUnitGroupInterface) unitGroupClass.newInstance();
				builder.addStatement("$T tmpUnitGroupInterface = ($T) unitGroupClass.newInstance()",DUnitGroupInterface.class,DUnitGroupInterface.class);

				////Name
				builder.addStatement("//Name");
				//unitGroupModel.setName(ModelValueUtil.getStringValue(unitGroupModel,unitGroupModel.getName(),tmpUnitGroupInterface.getName()));
				builder.addStatement("unitGroupModel.setName($T.getStringValue(unitGroupModel,unitGroupModel.getName(),tmpUnitGroupInterface.getName()))",ModelValueUtil.class);

				////Priority
				builder.addStatement("//Priority");
				//unitGroupModel.setPriority(ModelValueUtil.getIntValue(unitGroupModel,unitGroupModel.getPriority(),tmpUnitGroupInterface.getPriority()));
				builder.addStatement("unitGroupModel.setPriority($T.getIntValue(unitGroupModel,unitGroupModel.getPriority(),tmpUnitGroupInterface.getPriority()))",ModelValueUtil.class);

				////Group
				builder.addStatement("//Group");
				//unitGroupModel.setGroup(ModelValueUtil.getGroupGroup(unitGroupModel,unitGroupModel.getGroupClassName(),tmpUnitGroupInterface.getGroup()));
				builder.addStatement("unitGroupModel.setGroup($T.getGroupGroup(unitGroupModel,unitGroupModel.getGroupClassName(),tmpUnitGroupInterface.getGroup()))",ModelValueUtil.class);
			}
		};


		InitMethodUtil.processInitMethodSpecBuilder(initMethodBuild,doOther,modelListVariableName,tmpModelVariableName,DUnitGroupModel.class);

		return initMethodBuild.build();
	}


	/**
	 * 创建initUnitModels方法
	 */
	public static MethodSpec createMethodSpec_initUnitModels(String unitModelsString) {
		String modelListVariableName = "unitModels";
		final String tmpModelVariableName= "unitModel";
		MethodSpec.Builder initMethodBuild = InitMethodUtil.createInitMethodSpecBuilder("initUnitModels", DUnitModel.class, unitModelsString, modelListVariableName);

		//DoOther
		DoOther doOther = new DoOther() {
			@Override
			public void process(MethodSpec.Builder builder) {
				String tmpStr;

				////Name
				builder.addStatement("//Name");
				//if (DUnitConstant.Sys.DEFAULT_VALUE_STRING.equals(unitModel.getName())){
				builder.addCode("if ($T.Sys.DEFAULT_VALUE_STRING.equals(unitModel.getName())){\n",DUnitConstant.class);
				//unitModel.setName(unitGroupClass.getSimpleName());
				builder.addStatement("unitModel.setName(unitGroupClass.getSimpleName())");
				//}
				builder.addStatement("}");

				////ThreadModel
				builder.addStatement("//ThreadModel");
				//ModelValueUtil.setUnitThreadModelDefaultValue(unitModel);
				builder.addStatement("$T.setUnitThreadModelDefaultValue(unitModel)",ModelValueUtil.class);

				////Group
				builder.addStatement("//Group");
				//unitModel.setGroup(ModelValueUtil.getUnitGroup(unitModel));
				builder.addStatement("unitModel.setGroup($T.getUnitGroup(unitModel))", ModelValueUtil.class);
			}
		};

		InitMethodUtil.processInitMethodSpecBuilder(initMethodBuild,doOther,modelListVariableName,tmpModelVariableName,DUnitModel.class);

		return initMethodBuild.build();
	}

}
