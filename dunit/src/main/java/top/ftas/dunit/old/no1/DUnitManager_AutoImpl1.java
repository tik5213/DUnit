//package top.ftas.dunit.old.no1;
//
//import com.google.gson.Gson;
//
//import java.lang.reflect.ParameterizedType;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import top.ftas.dunit.core.DUnitManager;
//import top.ftas.dunit.group.DUnitGroupInterface;
//import top.ftas.dunit.group.DUnitRootGroup;
//import top.ftas.dunit.model.DUnitBaseModel;
//import top.ftas.dunit.model.DUnitGroupModel;
//import top.ftas.dunit.model.DUnitModel;
//import top.ftas.dunit.util.DUnitConstant;
//import top.ftas.dunit.util.GenericTypesUtil;
//import top.ftas.dunit.util.ModelValueUtil;
//
//@Deprecated
//final class DUnitManager_AutoImpl1 extends DUnitManager {
//    @Override
//    protected HashMap<Class<? extends DUnitGroupInterface>, ArrayList<DUnitBaseModel>> createModelMap(ArrayList<DUnitGroupModel> unitGroupModels,
//                                                                                                      ArrayList<DUnitModel> unitModels) {
//        HashMap<Class<? extends DUnitGroupInterface>, ArrayList<DUnitBaseModel>> modelMap = new HashMap<>();
//        modelMap.put(DUnitRootGroup.class, new ArrayList<DUnitBaseModel>());
//        ;
//        for (DUnitGroupModel unitGroupModel : unitGroupModels) {
//            ;
//            //将每个DUnitGroupModel根据Class添加到HashMap中;
//            modelMap.put(unitGroupModel.getOriginal(), new ArrayList<DUnitBaseModel>());
//        }
//        ;
//        //根据所属组，将所有组分类;
//        for (DUnitGroupModel unitGroupModel : unitGroupModels) {
//            Class<? extends DUnitGroupInterface> group = unitGroupModel.getGroup();
//            if (group == unitGroupModel.getOriginal()) continue;
//            modelMap.get(group).add(unitGroupModel);
//        }
//        ;
//        //根据所属组，将所有Unit分类;
//        for (DUnitModel unitModel : unitModels) {
//            Class<? extends DUnitGroupInterface> group = unitModel.getGroup();
//            modelMap.get(group).add(unitModel);
//        }
//        ;
//        return modelMap;
//    }
//
//    @Override
//    protected ArrayList<DUnitModel> initUnitModels() {
//        Gson gson = new Gson();
//        ParameterizedType type = GenericTypesUtil.type(ArrayList.class, DUnitModel.class);
//        ArrayList<DUnitModel> unitModels = gson.fromJson("[{\"threadMode\":-9999,\"unitType\":\"DEFAULT_VALUE_STRING\",\"isDirectlyAnnotated\":true,\"isHidden\":false,\"priority\":-9999,\"name\":\"DEFAULT_VALUE_STRING\",\"originalClassName\":\"top.ftas.dunit.sample_simple.MainActivity\",\"groupClassName\":\"top.ftas.dunit.util.DUnitConstant$Sys$DEFAULT_VALUE_GROUP\"},{\"threadMode\":-9999,\"unitType\":\"DEFAULT_VALUE_STRING\",\"isDirectlyAnnotated\":true,\"isHidden\":true,\"priority\":-9999,\"name\":\"DEFAULT_VALUE_STRING\",\"originalClassName\":\"top.ftas.dunit.sample_simple.TestUnit\",\"groupClassName\":\"top.ftas.dunit.util.DUnitConstant$Sys$DEFAULT_VALUE_GROUP\"}]", type);
//        for (DUnitModel unitModel : unitModels) {
//            ;
//            try {
//                ;
//                //Original;
//                Class<?> unitGroupClass = Class.forName(unitModel.getOriginalClassName());
//                unitModel.setOriginal(unitGroupClass);
//                //Name;
//                if (DUnitConstant.Sys.DEFAULT_VALUE_STRING.equals(unitModel.getName())) {
//                    unitModel.setName(unitGroupClass.getSimpleName());
//                }
//                ;
//                //UnitType;
//                ModelValueUtil.setUnitUnitTypeDefaultValue(unitModel);
//                //ThreadModel;
//                ModelValueUtil.setUnitThreadModelDefaultValue(unitModel);
//                //Group;
//                unitModel.setGroup(ModelValueUtil.getUnitGroup(unitModel));
//            } catch (Exception e) {
//                ;
//                e.printStackTrace();
//            }
//            ;
//        }
//        ;
//        return unitModels;
//    }
//
//    @Override
//    protected ArrayList<DUnitGroupModel> initUnitGroupModels() {
//        Gson gson = new Gson();
//        ParameterizedType type = GenericTypesUtil.type(ArrayList.class, DUnitGroupModel.class);
//        ArrayList<DUnitGroupModel> unitGroupModels = gson.fromJson("[{\"isDirectlyAnnotated\":false,\"isHidden\":false,\"priority\":-9999,\"name\":\"DEFAULT_VALUE_STRING\",\"originalClassName\":\"top.ftas.dunit.sample_simple.TestGroup\",\"groupClassName\":\"top.ftas.dunit.util.DUnitConstant$Sys$DEFAULT_VALUE_GROUP\"}]", type);
//        for (DUnitGroupModel unitGroupModel : unitGroupModels) {
//            ;
//            try {
//                ;
//                //Original;
//                Class<?> unitGroupClass = Class.forName(unitGroupModel.getOriginalClassName());
//                unitGroupModel.setOriginal(unitGroupClass);
//                //DUnitGroupInterface;
//                DUnitGroupInterface tmpUnitGroupInterface = (DUnitGroupInterface) unitGroupClass.newInstance();
//                //Name;
//                unitGroupModel.setName(ModelValueUtil.getStringValue(unitGroupModel, unitGroupModel.getName(), tmpUnitGroupInterface.getName()));
//                //Priority;
//                unitGroupModel.setPriority(ModelValueUtil.getIntValue(unitGroupModel, unitGroupModel.getPriority(), tmpUnitGroupInterface.getPriority()));
//                //Group;
//                unitGroupModel.setGroup(ModelValueUtil.getGroupGroup(unitGroupModel, unitGroupModel.getGroupClassName(), tmpUnitGroupInterface.getGroup()));
//            } catch (Exception e) {
//                ;
//                e.printStackTrace();
//            }
//            ;
//        }
//        ;
//        return unitGroupModels;
//    }
//}
