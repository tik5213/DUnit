package top.ftas.dunit.core;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import top.ftas.dunit.group.DUnitGroupInterface;
import top.ftas.dunit.model.DUnitBaseModel;
import top.ftas.dunit.model.DUnitGroupModel;
import top.ftas.dunit.model.DUnitModel;
import top.ftas.dunit.util.DUnitConstant;
import top.ftas.dunit.util.LogUtil;

/**
 * Created by tik on 17/6/27.
 * DisplayUnit展示单元管理类
 */

public abstract class DUnitManager {
	private static final String TAG = DUnitManager.class.getSimpleName();
	private static DUnitManager sInstance;
	private ArrayList<DUnitModel> mUnitModels;
	private ArrayList<DUnitGroupModel> mUnitGroupModels;
	private HashMap<Class<? extends DUnitGroupInterface>, ArrayList<DUnitBaseModel>> mModelMap;

	protected abstract ArrayList<DUnitModel>  initUnitModels();
	protected abstract ArrayList<DUnitGroupModel> initUnitGroupModels();
	protected abstract HashMap<Class<? extends DUnitGroupInterface>,ArrayList<DUnitBaseModel>> createModelMap(ArrayList<DUnitGroupModel> unitGroupModels, ArrayList<DUnitModel> unitModels);

	protected DUnitManager(){
		mUnitGroupModels = initUnitGroupModels();
		mUnitModels = initUnitModels();
		mModelMap = createModelMap(mUnitGroupModels,mUnitModels);
	}

	public static DUnitManager getInstance() {
		if (sInstance == null){
			synchronized (DUnitManager.class){
				if (sInstance == null){
					try {
						Class<?> clazz = Class.forName(DUnitConstant.Sys.DUNIT_MANAGER_AUTO_IMPL_CANONICAL_NAME);
						sInstance = (DUnitManager) clazz.newInstance();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return sInstance;
	}

	public void showUnitModels(Activity activity){
		LogUtil.toast(activity,mUnitModels.toString());
	}

	public void showUnitGroupModels(Activity activity){
		LogUtil.toast(activity,mUnitGroupModels.toString());
	}

	public ArrayList<DUnitModel> getUnitModels() {
		return mUnitModels;
	}

	public ArrayList<DUnitGroupModel> getUnitGroupModels() {
		return mUnitGroupModels;
	}

	public HashMap<Class<? extends DUnitGroupInterface>, ArrayList<DUnitBaseModel>> getModelMap() {
		return mModelMap;
	}

	public void logTest(String msg){
		Log.i("test",msg);
	}

}
