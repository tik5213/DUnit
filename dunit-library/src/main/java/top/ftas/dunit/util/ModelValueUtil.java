package top.ftas.dunit.util;

import android.util.Log;

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
	public static int getIntValue(DUnitBaseModel model,int annotationValue, int tmpValue){
		annotationValue = annotationValue == DEFAULT_VALUE_INT ? tmpValue : annotationValue;
		tmpValue = tmpValue == DEFAULT_VALUE_INT ? annotationValue : tmpValue;
		return model.isDirectlyAnnotated() ? annotationValue : tmpValue;
	}

	public static String getStringValue(DUnitBaseModel model,String annotationValue,String tmpValue){
		if (DEFAULT_VALUE_STRING.equals(annotationValue)) annotationValue = tmpValue;
		if (DEFAULT_VALUE_STRING.equals(tmpValue)) tmpValue = annotationValue;
		return model.isDirectlyAnnotated() ? annotationValue : tmpValue;
	}

	public static Class<? extends DUnitGroupInterface> getGroupGroup(DUnitBaseModel unitGroupModel, String annotationGroupName, Class<? extends DUnitGroupInterface> tmpGroup){
		try {
			String tmpGroupName = tmpGroup.getName();
			if (DEFAULT_VALUE_GROUP_NAME.equals(annotationGroupName) && DEFAULT_VALUE_GROUP_NAME.equals(tmpGroupName)){
				return DUnitRootGroup.class;
			}else {
				if (unitGroupModel.isDirectlyAnnotated()){
					if (DEFAULT_VALUE_GROUP_NAME.equals(annotationGroupName)){
						return tmpGroup;
					}else {
						Class<? extends DUnitGroupInterface> group = (Class<? extends DUnitGroupInterface>) Class.forName(annotationGroupName);
						return group;
					}
				}else {
					if (DEFAULT_VALUE_GROUP_NAME.equals(tmpGroupName)){
						Class<? extends DUnitGroupInterface> group = (Class<? extends DUnitGroupInterface>) Class.forName(annotationGroupName);
						return group;
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

	public static Class<? extends DUnitGroupInterface> getUnitGroup(DUnitBaseModel unitModel){
		try {
			String groupClassName = unitModel.getGroupClassName();
//			Log.i("test","---------> groupClassName:" + groupClassName + " unitModel:" + unitModel);
			if (!DEFAULT_VALUE_GROUP_NAME.equals(groupClassName)) {
				Class<? extends DUnitGroupInterface> group = (Class<? extends DUnitGroupInterface>) Class.forName(groupClassName);
				return group;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return DUnitRootGroup.class;
	}


}
