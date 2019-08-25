package top.ftas.dunit.core;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import top.ftas.dunit.model.DUnitBaseModel;
import top.ftas.dunit.model.DUnitGroupModel;
import top.ftas.dunit.model.DUnitModel;
import top.ftas.dunit.util.ClassUtils;
import top.ftas.dunit.util.DUnitConstant;
import top.ftas.dunit.util.DUnitManagerUtil;
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
	private HashMap<Class, ArrayList<DUnitBaseModel>> mModelMap;

	protected abstract HashMap<Class, ArrayList<DUnitBaseModel>> createModelMap(ArrayList<DUnitGroupModel> unitGroupModels, ArrayList<DUnitModel> unitModels);

	protected  ArrayList<DUnitModel> initUnitModels(){
		return new ArrayList<>();
	}


	protected ArrayList<DUnitGroupModel> initUnitGroupModels() {
		return new ArrayList<>();
	}

	protected DUnitManager() {
		mUnitGroupModels = initUnitGroupModels();
		mUnitModels = initUnitModels();
		mModelMap = createModelMap(mUnitGroupModels, mUnitModels);
	}

	public static DUnitManager getInstance(Context context) {
		if (sInstance == null) {
			synchronized (DUnitManager.class) {
				if (sInstance == null) {
				    if (context == null){
				    	throw new IllegalArgumentException("DUnitManager getInstance context 为 null");
				    }
                    sInstance = createDUnitManager(context);
				}
			}
		}
		return sInstance;
	}

	public static ArrayList<DUnitGroupModel>  sDUnitGroupModels = new ArrayList<>();
	public static ArrayList<DUnitModel> sDUnitModels = new ArrayList<>();

	private static DUnitManager createDUnitManager(Context context){
		HashSet<DUnitGroupModel> dUnitGroupModels = new HashSet<>();
		HashSet<DUnitModel> dUnitModels = new HashSet<>();
		try {
			Set<String> routerMap = ClassUtils.getFileNameByPackageName(context,DUnitConstant.Sys.DUNIT_MANAGER_AUTO_IMPL_PKG);
			Iterator<String> allClassName = routerMap.iterator();
			while (allClassName.hasNext()){
				String autoImplClassName = allClassName.next();
				Class<?> clazz = Class.forName(autoImplClassName);
				DUnitManager dUnitManager = (DUnitManager) clazz.newInstance();
				ArrayList<DUnitGroupModel> dUnitGroupModelArrayList = dUnitManager.initUnitGroupModels();
				dUnitGroupModels.addAll(dUnitGroupModelArrayList);

				ArrayList<DUnitModel> dUnitModelArrayList = dUnitManager.initUnitModels();
				dUnitModels.addAll(dUnitModelArrayList);
			}

		} catch (Exception e) {
			Log.e("error",Log.getStackTraceString(e));
		}

		sDUnitGroupModels.addAll(dUnitGroupModels);
		sDUnitModels.addAll(dUnitModels);

		return new DUnitManager() {
			@Override
			protected HashMap<Class, ArrayList<DUnitBaseModel>> createModelMap(ArrayList<DUnitGroupModel> unitGroupModels, ArrayList<DUnitModel> unitModels) {
				return DUnitManagerUtil.createModelMap(unitGroupModels,unitModels);
			}

			@Override
			protected ArrayList<DUnitModel> initUnitModels() {
			    return sDUnitModels;
			}

			@Override
			protected ArrayList<DUnitGroupModel> initUnitGroupModels() {
			    return sDUnitGroupModels;
			}
		};

	}

	public void showUnitModels(Activity activity) {
		LogUtil.toast(activity, mUnitModels.toString());
	}

	public void showUnitGroupModels(Activity activity) {
		LogUtil.toast(activity, mUnitGroupModels.toString());
	}

	public ArrayList<DUnitModel> getUnitModels() {
		return mUnitModels;
	}

	public ArrayList<DUnitGroupModel> getUnitGroupModels() {
		return mUnitGroupModels;
	}

	public HashMap<Class, ArrayList<DUnitBaseModel>> getModelMap() {
		return mModelMap;
	}

	public void logTest(String msg) {
		Log.i("test", msg);
	}

}
