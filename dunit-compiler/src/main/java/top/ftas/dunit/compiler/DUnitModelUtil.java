package top.ftas.dunit.compiler;

import com.google.common.base.Strings;
//import com.sun.tools.javac.code.Type;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.NestingKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Types;

import top.ftas.dunit.annotation.DUnit;
import top.ftas.dunit.annotation.DUnitGroup;
import top.ftas.dunit.annotation.DUnitHidden;
import top.ftas.dunit.model.DUnitGroupModel;
import top.ftas.dunit.model.DUnitModel;
import top.ftas.dunit.util.DUnitConstant;

/**
 * Created by tik on 17/6/30.
 */

public class DUnitModelUtil {
	//异常日志管理类
	private ErrorReporter mErrorReporter;
	//TypeUtils
	private Types mTypes;

	public DUnitModelUtil(ErrorReporter errorReporter, Types types) {
		mErrorReporter = errorReporter;
		mTypes = types;
	}

	public DUnitModel createParentUnitModel(Element type, DUnit dUnit) {
		TypeElement superElement = ElementUtil.getSuperElement(mTypes,type);
		DUnitModel unitModel;
		if (superElement != null && superElement.getAnnotation(DUnit.class) != null){
			unitModel = createParentUnitModel(superElement,superElement.getAnnotation(DUnit.class));
		}else {
			unitModel = createUnitModelWithDefaultValue();
		}

		//组装DUnitModel
		if (dUnit.threadMode() != DUnitConstant.Sys.DEFAULT_VALUE_THREAD_MODEL){
			unitModel.setThreadMode(dUnit.threadMode());
		}
		if (dUnit.priority() != DUnitConstant.Sys.DEFAULT_VALUE_INT){
			unitModel.setPriority(dUnit.priority());
		}

		//GroupClassName
		String groupClassName = getUnitModelGroupClassName(dUnit);
		if (!DUnitConstant.Sys.DEFAULT_VALUE_GROUP_NAME.equals(groupClassName)){
			unitModel.setGroupClassName(groupClassName);
		}

		//Name
		String name = dUnit.value();
		if (DUnitConstant.Sys.DEFAULT_VALUE_STRING.equals(name)){
			name = dUnit.name();
		}
		if (!DUnitConstant.Sys.DEFAULT_VALUE_STRING.equals(name)){
			unitModel.setName(name);
		}
		return unitModel;
	}

	/**
	 * 创建UnitModel
	 * @param type
	 * @param dUnit
	 */
	public DUnitModel createUnitModel(TypeElement type, DUnit dUnit) {
		//OriginalClassName
		String originalClassName = getRealClassName(type);
		//组装DUnitModel
		DUnitModel unitModel;
		Element parentElement = ElementUtil.getSuperElement(mTypes,type);
		if (parentElement != null && parentElement.getAnnotation(DUnit.class) != null){
			unitModel = createParentUnitModel(parentElement,parentElement.getAnnotation(DUnit.class));
		}else {
			unitModel = createUnitModelWithDefaultValue();
		}
		if (dUnit.threadMode() != DUnitConstant.Sys.DEFAULT_VALUE_THREAD_MODEL){
			unitModel.setThreadMode(dUnit.threadMode());
		}
		if (dUnit.priority() != DUnitConstant.Sys.DEFAULT_VALUE_INT){
			unitModel.setPriority(dUnit.priority());
		}
		unitModel.setOriginalClassName(originalClassName);

		//DirectlyAnnotated
		boolean isDirectlyAnnotated = directContainAnnotation(type,DUnitConstant.Sys.QUALIFIED_NAME_DUNIT);
		unitModel.setDirectlyAnnotated(isDirectlyAnnotated);

		//GroupClassName
		String groupClassName = getUnitModelGroupClassName(dUnit);
		if (!DUnitConstant.Sys.DEFAULT_VALUE_GROUP_NAME.equals(groupClassName)){
			unitModel.setGroupClassName(groupClassName);
		}

		//Name
		String name = dUnit.value();
		if (DUnitConstant.Sys.DEFAULT_VALUE_STRING.equals(name)){
			name = dUnit.name();
		}
		if (!DUnitConstant.Sys.DEFAULT_VALUE_STRING.equals(name)){
			unitModel.setName(name);
		}

		//DUnitHidden
		unitModel.setHidden(type.getAnnotation(DUnitHidden.class) != null);

		//Reporter
		mErrorReporter.print("find DUnit class : " + unitModel.getOriginalClassName());

		return unitModel;
	}

	private DUnitModel createUnitModelWithDefaultValue() {
		DUnitModel unitModel = new DUnitModel();
		unitModel.setThreadMode(DUnitConstant.Sys.DEFAULT_VALUE_THREAD_MODEL);
		unitModel.setName(DUnitConstant.Sys.DEFAULT_VALUE_STRING);
		unitModel.setPriority(DUnitConstant.Sys.DEFAULT_VALUE_INT);
		unitModel.setGroupClassName(DUnitConstant.Sys.DEFAULT_VALUE_GROUP_NAME);
		return unitModel;
	}

	/**
	 * 创建UnitGroupModel
	 * @param type
	 * @param dUnitGroup
	 * @return
	 */
	public DUnitGroupModel createUnitGroupModel(TypeElement type, DUnitGroup dUnitGroup) {
		//OriginalClassName
		String originalClassName = getRealClassName(type);

		//组装DUnitGroupModel
		DUnitGroupModel unitGroupModel = new DUnitGroupModel();
		unitGroupModel.setOriginalClassName(originalClassName);
		unitGroupModel.setPriority(dUnitGroup.priority());

		//DirectlyAnnotated
		boolean isDirectlyAnnotated = directContainAnnotation(type,DUnitConstant.Sys.QUALIFIED_NAME_DUNIT_GROUP);
		unitGroupModel.setDirectlyAnnotated(isDirectlyAnnotated);

		//GroupClassName
		String groupClassName = getUnitGroupModelGroupClassName(dUnitGroup);
		unitGroupModel.setGroupClassName(groupClassName);

		//Name
		String name = dUnitGroup.value();
		if (Strings.isNullOrEmpty(name)){
			name = dUnitGroup.name();
		}
		unitGroupModel.setName(name);

		//DUnitHidden
		unitGroupModel.setHidden(type.getAnnotation(DUnitHidden.class) != null);

		//Reporter
		mErrorReporter.print("find DUnitGroup class : " + unitGroupModel.getOriginalClassName());
		return unitGroupModel;
	}

	/**
	 * 判断某个元素是否直接包含某注解
	 * @param typeElement  类型元素
	 * @param qualifiedName 注解的限定名
	 */
	public boolean directContainAnnotation(TypeElement typeElement,String qualifiedName){
		List<? extends AnnotationMirror> mirrors = typeElement.getAnnotationMirrors();
		if (mirrors.isEmpty()){
			return false;
		}
		String mirrorsString = mirrors.toString();
		return mirrorsString.contains(qualifiedName);
	}


	public void getGroupClassType(TypeElement type, DUnitGroup dUnitGroup) {
		if (dUnitGroup != null) {
			mErrorReporter.reportWaring("asType:" + type.asType() + "     |getNestingKind:" + type.getNestingKind() + "     |getAnnotation:" + type.getAnnotation(DUnitGroup.class) + "    |getAnnotationMirrors:" + type.getAnnotationMirrors());
			mErrorReporter.reportWaring("------------>" + getRealClassName(type));
			List<? extends AnnotationMirror> mirrors = type.getAnnotationMirrors();
			if (mirrors.isEmpty()){
				return;
			}
			AnnotationMirror mirror = mirrors.get(0);
			mErrorReporter.reportWaring("----->mirror:" + mirror);
			Map<? extends ExecutableElement, ? extends AnnotationValue> map = mirror.getElementValues();
			Set<? extends Map.Entry<? extends ExecutableElement, ? extends AnnotationValue>> entries = map.entrySet();
			for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry :
					entries) {
				ExecutableElement executableElement = entry.getKey();
				mErrorReporter.reportWaring("executableElement" + executableElement);
				AnnotationValue annotationValue = entry.getValue();
				Object object = annotationValue.getValue();
//				boolean isClassType = object instanceof Type.ClassType;
//				if (isClassType) {
//					Type.ClassType classType = (Type.ClassType) object;
//					mErrorReporter.reportWaring(classType.toString() + "  |  " + classType.getOriginalType() + "  |  " + classType.getKind() + "  |  " + classType.getReturnType() + "   |  " + classType.getUpperBound());
//				}
			}

		}
	}


	/*************当注解的参数为Class类型时，如果使用的apt技术，直接去获取Class会报异常，因为，此时jvm还没有运行，Class尚未加载*****************/
	public void getClassType(TypeElement type, DUnit dUnit) {
		if (dUnit != null){
//			mErrorReporter.reportWaring("asType:" + type.asType() + "     |getNestingKind:" + type.getNestingKind() + "     |getAnnotation:" + type.getAnnotation(DUnitGroup.class) + "    |getAnnotationMirrors:" + type.getAnnotationMirrors());
			List<? extends AnnotationMirror> mirrors = type.getAnnotationMirrors();
			AnnotationMirror mirror = mirrors.get(0);
			Map<? extends ExecutableElement, ? extends AnnotationValue> map = mirror.getElementValues();
			Set<? extends Map.Entry<? extends ExecutableElement, ? extends AnnotationValue>> entries = map.entrySet();
			for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry :
					entries) {
				ExecutableElement executableElement = entry.getKey();
				mErrorReporter.reportWaring("executableElement" + executableElement);
				AnnotationValue annotationValue = entry.getValue();
				Object object = annotationValue.getValue();
//				boolean isClassType = object instanceof Type.ClassType;
//				if (isClassType){
//					Type.ClassType classType = (Type.ClassType) object;
//					mErrorReporter.reportWaring(classType.toString() + "  |  " + classType.getOriginalType() + "  |  "  + classType.getKind() + "  |  "  + classType.getReturnType()  +  "   |  "  + classType.getUpperBound());
//				}
			}

		}
	}

	/**
	 * 获取TypeElement真实的类名
	 */
	public String getRealClassName(TypeElement typeElement) {
		NestingKind nestingKind = typeElement.getNestingKind();
		if (nestingKind.isNested()) {
			Element enclosingElement = typeElement.getEnclosingElement();
			if (enclosingElement.getKind() == ElementKind.CLASS || enclosingElement.getKind() == ElementKind.INTERFACE){
				String enclosingClassName = getRealClassName((TypeElement) enclosingElement);
				return enclosingClassName + "$" + typeElement.getSimpleName();
			}else {
				mErrorReporter.reportError("the type(" + enclosingElement.getKind()+ ") of enclosing element is not CLASS or INTERFACE.",typeElement);
				return null;
			}
		}else {
			return typeElement.getQualifiedName().toString();
		}
	}

	/**
	 * 获取DUnitGroup的group字段的类名
	 */
	public String getUnitGroupModelGroupClassName(DUnitGroup dUnitGroup){
		TypeMirror typeMirror = getUnitGroupModelGroupTypeMirror(dUnitGroup);
		Element element = mTypes.asElement(typeMirror);
		String className = getRealClassName((TypeElement) element);
		return className;
	}

	/**
	 * 获取DUnitGroup的group字段的TypeMirror
	 */
	public TypeMirror getUnitGroupModelGroupTypeMirror(DUnitGroup dUnitGroup){
		try {
			dUnitGroup.group();
		}catch (MirroredTypeException e){
			TypeMirror typeMirror = e.getTypeMirror();
			return typeMirror;
		}
		return null;
	}

	/**
	 * 获取DUnit的group字段的类名
	 */
	public String getUnitModelGroupClassName(DUnit dUnit){
		TypeMirror typeMirror = getUnitModelGroupTypeMirror(dUnit);
		Element element = mTypes.asElement(typeMirror);
		String className = getRealClassName((TypeElement) element);
		return className;
	}

	/**
	 * 获取DUnit的group字段的TypeMirror
	 */
	public TypeMirror getUnitModelGroupTypeMirror(DUnit dUnit){
		try {
			dUnit.group();
		}catch (MirroredTypeException e){
			TypeMirror typeMirror = e.getTypeMirror();
			return typeMirror;
		}
		return null;
	}
}
