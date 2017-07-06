package top.ftas.dunit.core;

import android.app.Activity;
import android.content.Context;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import top.ftas.dunit.annotation.DUnit;
import top.ftas.dunit.model.DUnitModel;
import top.ftas.dunit.util.LogUtil;

/**
 * Created by tik on 17/6/28.
 */

@DUnit
public abstract class AbstractDisplayUnit implements Runnable{
	protected Activity mActivity;
	protected Context mContext;
	protected ResultMessageHelper mMessageHelper;

	public void onPrepared(){}
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
			onPrepared();
			callUnit();
			onCalled();
		}catch (Throwable e){
			onError(e);
		}
	}

}
