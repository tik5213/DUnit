package top.ftas.dunit.compiler;

import com.google.auto.service.AutoService;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.gson.Gson;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Types;

import top.ftas.dunit.annotation.DUnit;
import top.ftas.dunit.annotation.DUnitGroup;
import top.ftas.dunit.model.DUnitGroupModel;
import top.ftas.dunit.model.DUnitModel;
import top.ftas.dunit.util.DUnitConstant;

/**
 * Created by tik on 17/6/27.
 */

@AutoService(Processor.class)
public class DUnitProcessor extends AbstractProcessor {
	//TypeUtils
	private Types mTypes;
	//异常日志管理类
	private ErrorReporter mErrorReporter;
	//模型工具类
	private DUnitModelUtil mUnitModelUtil;

	private String mAutoImplClassName;

	@Override
	/**
	 * 被注解处理工具调用，参数ProcessingEnvironment 提供了Element，Filer，Messager等工具
	 */
	public synchronized void init(ProcessingEnvironment processingEnvironment) {
		super.init(processingEnvironment);
		mTypes = processingEnvironment.getTypeUtils();
		mErrorReporter = new ErrorReporter(processingEnvironment);
		mUnitModelUtil = new DUnitModelUtil(mErrorReporter, mTypes);
		mErrorReporter.print("init success.");

		String oldClassNameIntStr = System.getProperty("DUnitManager_AutoImpl_ClassNameInt");
		int classNameInt = 1;
		if (oldClassNameIntStr != null && !"".equals(oldClassNameIntStr)){
		    classNameInt = Integer.valueOf(oldClassNameIntStr) + 1;
		}
		if (classNameInt > DUnitConstant.Sys.DUNIT_MANAGER_MAX_AUTO_IMPLE_INT){
			throw new RuntimeException("DUnitManager_AutoImpl_ClassNameInt classNameInt大于" + DUnitConstant.Sys.DUNIT_MANAGER_MAX_AUTO_IMPLE_INT + " 过多的模块引用了 DUnit");
		}
		System.setProperty("DUnitManager_AutoImpl_ClassNameInt","" + classNameInt);
		mAutoImplClassName = DUnitConstant.Sys.DUNIT_MANAGER_AUTO_IMPL_SIMPLE_NAME + "_" + classNameInt;
	}


	/**
	 * 处理DUnit注解
	 */
	public MethodSpec processUnitElements(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
		//获取所有被DUnit注解的Element
		Collection<? extends Element> annotatedUnitElements = roundEnvironment.getElementsAnnotatedWith(DUnit.class);

//		查找所有element中类型为Type的Element
		List<TypeElement> originalElementList = ElementFilter.typesIn(annotatedUnitElements);
//		将普通集合变为不可变集合
		List<TypeElement> types = new ImmutableList.Builder<TypeElement>()
				.addAll(originalElementList)
				.build();

		if (types.size() > 0) {
			mErrorReporter.print("process DUnit start...");
			//用于存放所有DUnitModel
			ArrayList<DUnitModel> unitModels = new ArrayList<>();

			for (TypeElement type : types) {
				if (type.getKind() != ElementKind.CLASS) {
					mErrorReporter.abortWithError("@" + DUnit.class.getName() + " only applies to classes", type);
				}

				DUnit dUnit = type.getAnnotation(DUnit.class);
				if (dUnit != null) {
					DUnitModel unitModel = mUnitModelUtil.createUnitModel(type, dUnit);
					unitModels.add(unitModel);
				}
			}

			//将模型转换为字符串
			Gson gson = new Gson();
			//unitModels
			String unitModelsString = gson.toJson(unitModels);

			//创建initUnitModels方法
			MethodSpec initMethod_initUnitModels = InitMethodUtil.createMethodSpec_initUnitModels(unitModelsString);

			mErrorReporter.print("process DUnit end.");
			return initMethod_initUnitModels;
		} else {
			mErrorReporter.print("Waring! no DUnit found.");
		}
		return null;
	}

	/**
	 * 处理DUnitGroup注解
	 */
	public MethodSpec processUnitGroupElements(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
		//获取所有被DUnitGroup注解的Element
		Collection<? extends Element> annotatedUnitGroupElements = roundEnvironment.getElementsAnnotatedWith(DUnitGroup.class);

		//查找所有element中类型为Type的Element
		List<TypeElement> originalElementList = ElementFilter.typesIn(annotatedUnitGroupElements);
		//将普通集合变为不可变集合
		List<TypeElement> types = new ImmutableList.Builder<TypeElement>()
				.addAll(originalElementList)
				.build();

		if (types.size() > 0) {
			mErrorReporter.print("process DUnitGroup start...");
			//用于存放所有DunitGroupModel
			ArrayList<DUnitGroupModel> unitGroupModels = new ArrayList<>();

			for (TypeElement type : types) {
				if (type.getKind() != ElementKind.CLASS) {
					mErrorReporter.abortWithError("@" + DUnit.class.getName() + " only applies to classes", type);
				}

				DUnitGroup dUnitGroup = type.getAnnotation(DUnitGroup.class);

				DUnitGroupModel unitGroupModel = mUnitModelUtil.createUnitGroupModel(type, dUnitGroup);
				unitGroupModels.add(unitGroupModel);
			}

			//将模型转换为字符串
			Gson gson = new Gson();
			//unitGroupModels
			String unitGroupModelsString = gson.toJson(unitGroupModels);

			//创建initUnitGroupModels
			MethodSpec initMethod_initUnitGroupModels = InitMethodUtil.createMethodSpec_initUnitGroupModels(unitGroupModelsString);

			mErrorReporter.print("process DUnitGroup end.");
			return initMethod_initUnitGroupModels;
		} else {
			mErrorReporter.print("Waring! no DUnitGroup found.");
		}

		return null;
	}

	@Override
	/**
	 * 在这里扫描和处理你的注解并生成Java代码
	 */
	public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
		MethodSpec methodSpec_initUnitModels = processUnitElements(set, roundEnvironment);
		MethodSpec methodSpec_initUnitGroupModels = processUnitGroupElements(set, roundEnvironment);
		if (methodSpec_initUnitModels == null){
			mErrorReporter.reportWaring("methodSpec_initUnitModels is null.");
		}

		if (methodSpec_initUnitGroupModels == null){
			mErrorReporter.reportWaring("methodSpec_initUnitGroupModels is null.");
		}

		if (methodSpec_initUnitModels == null && methodSpec_initUnitGroupModels == null){
			mErrorReporter.reportWaring("methodSpec_initUnitModels && methodSpec_initUnitGroupModels is null.");
			return true;
		}

		MethodSpec methodSpec_createModelMap = InitMethodUtil.createMethodSpecBuilder_createModelMap().build();


		try {
			//声明类DUnitManager_AutoImpl1，并添加方法
			ClassName typeName = ClassName.bestGuess(DUnitConstant.Sys.DUNIT_MANAGER_CANONICAL_NAME);
			TypeSpec.Builder typeSpecBuild_DUnitManager_AutoImpl = TypeSpec
					.classBuilder(mAutoImplClassName)
					.addModifiers(Modifier.FINAL)
					.addModifiers(Modifier.PUBLIC)
					.superclass(typeName)
					.addMethod(methodSpec_createModelMap);
			if (methodSpec_initUnitModels != null){
				typeSpecBuild_DUnitManager_AutoImpl.addMethod(methodSpec_initUnitModels);
			}
			if (methodSpec_initUnitGroupModels != null){
				typeSpecBuild_DUnitManager_AutoImpl.addMethod(methodSpec_initUnitGroupModels);
			}
			TypeSpec typeSpec_DUnitManager_AutoImpl = typeSpecBuild_DUnitManager_AutoImpl.build();


			//创建Java文件
			JavaFile javaFile = JavaFile
					.builder(DUnitConstant.Sys.DUNIT_MANAGER_AUTO_IMPL_PKG, typeSpec_DUnitManager_AutoImpl)
					.addStaticImport(DUnitConstant.Sys.class,"DEFAULT_VALUE_GROUP_NAME")
					.build();
			javaFile.writeTo(processingEnv.getFiler());
		} catch (Exception e) {
			mErrorReporter.reportError("--------->" + e.getMessage());
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return true;
	}





	@Override
	/**
	 *  指定注解处理器是注册给那一个注解的，它是一个字符串的集合，意味着可以支持多个类型的注解，并且字符串是合法全名。
	 *  可用注解SupportedAnnotationTypes代替
	 */
	public Set<String> getSupportedAnnotationTypes() {
		//创建一个不可变集合
		return ImmutableSet.of(DUnit.class.getCanonicalName(), DUnitGroup.class.getCanonicalName());
	}

	@Override
	/**
	 * 指定Java版本
	 * 可用注解SupportedSourceVersion代替
	 */
	public SourceVersion getSupportedSourceVersion() {
		return SourceVersion.latestSupported();
	}

	/**
	 * 处理每一个TypeElement
	 *
	 * @param unitModels      保存DUnitModel
	 * @param unitGroupModels 保存DUnitGroupModel
	 * @param type            TypeElement
	 */
	private void processType(ArrayList<DUnitModel> unitModels, ArrayList<DUnitGroupModel> unitGroupModels, TypeElement type) {
		if (type.getKind() != ElementKind.CLASS) {
			mErrorReporter.abortWithError("@" + DUnit.class.getName() + " only applies to classes", type);
		}

		DUnit dUnit = type.getAnnotation(DUnit.class);
		DUnitGroup dUnitGroup = type.getAnnotation(DUnitGroup.class);

		if (dUnit != null) {
			DUnitModel unitModel = mUnitModelUtil.createUnitModel(type, dUnit);
			unitModels.add(unitModel);
		}

		if (dUnitGroup != null) {
			DUnitGroupModel unitGroupModel = mUnitModelUtil.createUnitGroupModel(type, dUnitGroup);
			unitGroupModels.add(unitGroupModel);
		}


	}


}
