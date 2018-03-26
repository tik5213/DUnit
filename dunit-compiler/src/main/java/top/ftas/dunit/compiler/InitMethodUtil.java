package top.ftas.dunit.compiler;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import javax.lang.model.element.Modifier;

import top.ftas.dunit.group.DUnitGroupInterface;
import top.ftas.dunit.model.DUnitBaseModel;
import top.ftas.dunit.model.DUnitGroupModel;
import top.ftas.dunit.model.DUnitModel;
import top.ftas.dunit.util.DUnitManagerUtil;
import top.ftas.dunit.util.GenericTypesUtil;

/**
 * Created by tik on 17/7/2.
 */

public class InitMethodUtil {

	/**
	 * 创建init方法，用于数据初始化
	 * @return 初始化方法的构造器
	 */
	public static MethodSpec.Builder createInitMethodSpecBuilder(String methodName, Class genericTypesClass) {
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
		return initMethodBuilder;
	}

	public static MethodSpec.Builder createMethodSpecBuilder_createModelMap(){
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
		//return DUnitManagerUtil.createModelMap(unitGroupModels,unitModels);
		methodBuilder.addStatement("return $T.createModelMap(unitGroupModels,unitModels)",DUnitManagerUtil.class);
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
		MethodSpec.Builder initMethodBuilder = InitMethodUtil.createInitMethodSpecBuilder("initUnitGroupModels", DUnitGroupModel.class);

		//return DUnitManagerUtil.initUnitGroupModels(unitGroupModelsString_c);
		initMethodBuilder.addStatement("return $T.initUnitGroupModels($S)",DUnitManagerUtil.class,unitGroupModelsString);
		return initMethodBuilder.build();
	}


	/**
	 * 创建initUnitModels方法
	 */
	public static MethodSpec createMethodSpec_initUnitModels(String unitModelsString) {
		MethodSpec.Builder initMethodBuilder = InitMethodUtil.createInitMethodSpecBuilder("initUnitModels", DUnitModel.class);

		//return DUnitManagerUtil.initUnitModels(unitModelsString_c);
		initMethodBuilder.addStatement("return $T.initUnitModels($S)",DUnitManagerUtil.class,unitModelsString);
		return initMethodBuilder.build();
	}

}
