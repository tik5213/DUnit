package top.ftas.dunit.util;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;

import top.ftas.dunit.group.DUnitRootGroup;
import top.ftas.dunit.model.DUnitBaseModel;
import top.ftas.dunit.model.DUnitGroupModel;
import top.ftas.dunit.model.DUnitModel;

/**
 * Created by tik on 2018/3/26.
 */

public class DUnitManagerUtil {
    public static ArrayList<DUnitModel> initUnitModels(String unitGroupModelsString) {
        Gson gson = new Gson();
        ParameterizedType type = GenericTypesUtil.type(ArrayList.class,DUnitModel.class);
        ArrayList<DUnitModel> unitModels = gson.fromJson(unitGroupModelsString,type);

        for (DUnitModel unitModel: unitModels) {
            try {
                //Original
                Class<?> unitGroupClass = Class.forName(unitModel.getOriginalClassName());
                unitModel.setOriginal(unitGroupClass);

                //Name
                if (DUnitConstant.Sys.DEFAULT_VALUE_STRING.equals(unitModel.getName())){
                    unitModel.setName(unitGroupClass.getSimpleName());
                }

                //paramJson
                if (DUnitConstant.Sys.DEFAULT_VALUE_STRING.equals(unitModel.getParamJson())){
                    unitModel.setParamJson("{}");
                }

                //UnitType
                ModelValueUtil.setUnitUnitTypeDefaultValue(unitModel);

                //ThreadModel
                ModelValueUtil.setUnitThreadModelDefaultValue(unitModel);

                //Group
                unitModel.setGroup(ModelValueUtil.getUnitGroup(unitModel));

            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return unitModels;
    }

    public static ArrayList<DUnitGroupModel> initUnitGroupModels(String unitGroupModelsString) {
        Gson gson = new Gson();
        ParameterizedType type = GenericTypesUtil.type(ArrayList.class,DUnitGroupModel.class);
        ArrayList<DUnitGroupModel> unitGroupModels = gson.fromJson(unitGroupModelsString,type);

        for (DUnitGroupModel unitGroupModel : unitGroupModels) {
            try {
                //Original
                Class<?> unitGroupClass = Class.forName(unitGroupModel.getOriginalClassName());
                unitGroupModel.setOriginal(unitGroupClass);

                //DUnitGroupInterface
                Object groupObj = unitGroupClass.newInstance();

                //Name
                unitGroupModel.setName(ModelValueUtil.getNameValue(unitGroupModel,groupObj));

                //Priority
                unitGroupModel.setPriority(ModelValueUtil.getPriorityValue(unitGroupModel,groupObj));

                //Group
                unitGroupModel.setGroup(ModelValueUtil.getGroupGroup(unitGroupModel,groupObj));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return unitGroupModels;
    }

    public static HashMap<Class, ArrayList<DUnitBaseModel>> createModelMap(ArrayList<DUnitGroupModel> unitGroupModels, ArrayList<DUnitModel> unitModels) {
        HashMap<Class,ArrayList<DUnitBaseModel>> modelMap = new HashMap<>();
        modelMap.put(DUnitRootGroup.class,new ArrayList<DUnitBaseModel>());

        for (DUnitGroupModel unitGroupModel: unitGroupModels) {
            //将每个DUnitGroupModel根据Class添加到HashMap中
            modelMap.put(unitGroupModel.getOriginal(),new ArrayList<DUnitBaseModel>());
        }

        //根据所属组，将所有组分类
        for (DUnitGroupModel unitGroupModel: unitGroupModels) {
            Class group = unitGroupModel.getGroup();
            if (!modelMap.containsKey(group)){
                group = DUnitRootGroup.class;
            }
            modelMap.get(group).add(unitGroupModel);
        }


        //根据所属组，将所有Unit分类
        for (DUnitModel unitModel: unitModels) {
            Class group = unitModel.getGroup();
            modelMap.get(group).add(unitModel);
        }
        return modelMap;

    }
}
