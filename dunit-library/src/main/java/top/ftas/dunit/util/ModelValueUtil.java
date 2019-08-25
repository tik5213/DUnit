package top.ftas.dunit.util;

import top.ftas.dunit.group.DUnitGroupInterface;
import top.ftas.dunit.group.DUnitRootGroup;
import top.ftas.dunit.model.DUnitBaseModel;
import top.ftas.dunit.model.DUnitModel;

import static top.ftas.dunit.util.DUnitConstant.Sys.DEFAULT_VALUE_GROUP_NAME;
import static top.ftas.dunit.util.DUnitConstant.Sys.DEFAULT_VALUE_INT;
import static top.ftas.dunit.util.DUnitConstant.Sys.DEFAULT_VALUE_STRING;

/**
 * Created by tik on 17/7/4.
 */

public class ModelValueUtil {
	public static int getPriorityValue(DUnitBaseModel model,Object groupObj){
		int annotationValue = model.getPriority();
		int groupObjValue;
		if (groupObj instanceof DUnitGroupInterface){
			groupObjValue = ((DUnitGroupInterface) groupObj).getPriority();
		}else {
			groupObjValue = DEFAULT_VALUE_INT;
		}

		if (annotationValue == DEFAULT_VALUE_INT){
			return groupObjValue;
		}else if (groupObjValue == DEFAULT_VALUE_INT){
			return annotationValue;
		}
		return model.isDirectlyAnnotated() ? annotationValue : groupObjValue;
	}

	public static String getNameValue(DUnitBaseModel model,Object groupObj){
		String annotationValue = model.getName();
		String groupObjValue;
		if (groupObj instanceof DUnitGroupInterface){
			groupObjValue = ((DUnitGroupInterface) groupObj).getName();
		}else {
			groupObjValue = model.getOriginal().getSimpleName();
		}
		if (DEFAULT_VALUE_STRING.equals(annotationValue)){
			return groupObjValue;
		}else if (DEFAULT_VALUE_STRING.equals(groupObjValue)){
		    return annotationValue;
		}else {
			return model.isDirectlyAnnotated() ? annotationValue : groupObjValue;
		}
	}

	public static Class getGroupGroup(DUnitBaseModel unitGroupModel, Object groupObj){
		try {
			String annotationGroupName = unitGroupModel.getGroupClassName();
			Class tmpGroup;
			if (groupObj instanceof DUnitGroupInterface){
			    tmpGroup = ((DUnitGroupInterface) groupObj).getGroup();
			}else {
				tmpGroup = groupObj.getClass().getSuperclass();
			}

			String tmpGroupName = tmpGroup.getName();
			if (DEFAULT_VALUE_GROUP_NAME.equals(annotationGroupName) && DEFAULT_VALUE_GROUP_NAME.equals(tmpGroupName)){
				return DUnitRootGroup.class;
			}else {
				if (unitGroupModel.isDirectlyAnnotated()){
					if (DEFAULT_VALUE_GROUP_NAME.equals(annotationGroupName)){
						return tmpGroup;
					}else {
						return Class.forName(annotationGroupName);
					}
				}else {
					if (DEFAULT_VALUE_GROUP_NAME.equals(tmpGroupName)){
						return Class.forName(annotationGroupName);
					}else {
						return tmpGroup;
					}
				}
			}
		}catch (Exception e){
			return DUnitRootGroup.class;
		}
	}

	public static void setUnitThreadModelDefaultValue(DUnitModel dUnitModel){
		if (DUnitConstant.Sys.DEFAULT_VALUE_THREAD_MODEL == dUnitModel.getThreadMode()){
			dUnitModel.setThreadMode(ThreadModel.IO);
		}
	}

	public static void setUnitUnitTypeDefaultValue(DUnitModel dUnitModel){
		if (DUnitConstant.Sys.DEFAULT_VALUE_STRING.equals(dUnitModel.getUnitType())){
			dUnitModel.setUnitType(DUnitConstant.UnitType.AUTO);
		}
	}

	public static Class getUnitGroup(DUnitBaseModel unitModel){
		try {
			String groupClassName = unitModel.getGroupClassName();
			if (!DEFAULT_VALUE_GROUP_NAME.equals(groupClassName)) {
				Class group =  Class.forName(groupClassName);
				return group;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return DUnitRootGroup.class;
	}


}
