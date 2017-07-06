package top.ftas.dunit.core;

import android.app.Activity;
import android.content.Context;

import top.ftas.dunit.annotation.DUnit;
import top.ftas.dunit.util.LogUtil;

/**
 * Created by tik on 17/6/28.
 */

@DUnit
public abstract class AbstractDisplayUnit implements Runnable{
	protected Activity mActivity;
	protected Context mContext;
	protected ResultMessageHelper mMessageHelper;

	public ResultMessageHelper getMessageHelper() {
		return mMessageHelper;
	}

	public void setMessageHelper(ResultMessageHelper messageHelper) {
		mMessageHelper = messageHelper;
	}

	public Activity getActivity() {
		return mActivity;
	}

	public void setActivity(Activity activity) {
		mActivity = activity;
	}

	public Context getContext() {
		return mContext;
	}

	public void setContext(Context context) {
		mContext = context;
	}

	public abstract void callUnit();

	@Override
	public void run() {
		callUnit();
	}

}
