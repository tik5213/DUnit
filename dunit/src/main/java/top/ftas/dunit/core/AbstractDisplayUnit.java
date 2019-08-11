package top.ftas.dunit.core;

import android.app.Activity;
import android.content.Context;

import top.ftas.dunit.annotation.DUnit;
import top.ftas.dunit.util.MessageUtil;

/**
 * Created by tik on 17/6/28.
 */

@DUnit
public abstract class AbstractDisplayUnit implements Runnable{
	protected Activity mActivity;
	protected Context mContext;
	protected ResultMessageHelper mMessageHelper;

	public void onPrepared(){
	    mMessageHelper.clean();
	}
	public void onCalled(){}
	public void onError(Throwable e){
		mMessageHelper.appendLine("Error!!!" + e.getMessage());
		e.printStackTrace();
	}

	public ResultMessageHelper getMessageHelperWrapper(ResultMessageHelper messageHelper){
		return null;
	}

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
		try {
			MessageUtil.setResultMessageHelper(getMessageHelper());
			onPrepared();
			callUnit();
			onCalled();
		}catch (Throwable e){
			onError(e);
		}
	}

}
