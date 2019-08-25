//package top.ftas.dunit.core;
//
//import com.google.gson.Gson;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import top.ftas.dunit.group.DUnitGroupInterface;
//import top.ftas.dunit.group.DUnitRootGroup;
//import top.ftas.dunit.model.DUnitBaseModel;
//import top.ftas.dunit.model.DUnitGroupModel;
//import top.ftas.dunit.model.DUnitModel;
//import top.ftas.dunit.util.DUnitManagerUtil;
//import top.ftas.dunit.util.ThreadModel;
//
///**
// * Created by tik on 17/6/28.
// */
//
//final class DUnitManager_AutoImpl extends DUnitManager{
//
//	@Override
//	protected ArrayList<DUnitModel> initUnitModels() {
//		//+++++++++++++++++++++++++compiler++++++++++++++++++++++++++++
//		DUnitModel unitModel_c = new DUnitModel();
//		unitModel_c.setOriginalClassName("SimpleDisplayUnit");
//		unitModel_c.setGroupClassName(DUnitRootGroup.class.getName());
//		unitModel_c.setName("SimpleDisplayUnit");
//		unitModel_c.setPriority(1);
//		unitModel_c.setThreadMode(ThreadModel.MAIN);
//		ArrayList<DUnitModel> unitModels_c = new ArrayList<>();
//		unitModels_c.add(unitModel_c);
//		Gson gson_c = new Gson();
//		String unitModelsString_c = gson_c.toJson(unitModels_c);
//
//
//		//++++++++++++++++++++++++++++++++++++++++++++++++++++++
//		return DUnitManagerUtil.initUnitModels(unitModelsString_c);
//	}
//
//	@Override
//	protected ArrayList<DUnitGroupModel> initUnitGroupModels() {
//		//+++++++++++++++++++++++++compiler++++++++++++++++++++++++++++
//		DUnitGroupModel unitGroupModel_c = new DUnitGroupModel();
//		unitGroupModel_c.setOriginalClassName("SimpleDisplayUnit");
//		unitGroupModel_c.setName("SimpleDisplayUnit");
//		unitGroupModel_c.setPriority(1);
//		ArrayList<DUnitGroupModel> unitGroupModels_c = new ArrayList<>();
//		unitGroupModels_c.add(unitGroupModel_c);
//		Gson gson_c = new Gson();
//		String unitGroupModelsString_c = gson_c.toJson(unitGroupModels_c);
//
//
//		//++++++++++++++++++++++++++++++++++++++++++++++++++++++
//		return DUnitManagerUtil.initUnitGroupModels(unitGroupModelsString_c);
//	}
//
//	@Override
//	protected HashMap<Class<? extends DUnitGroupInterface>, ArrayList<DUnitBaseModel>> createModelMap(ArrayList<DUnitGroupModel> unitGroupModels, ArrayList<DUnitModel> unitModels) {
//		return DUnitManagerUtil.createModelMap(unitGroupModels,unitModels);
//
//	}
//
//}
