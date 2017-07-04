package top.ftas.dunit.util;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by tik on 17/6/28.
 * 打印日志
 */

public class LogUtil {
	private static final String TAG = LogUtil.class.getSimpleName();

	public static void log(Activity activity, Throwable e) {
		e.printStackTrace();
		toast(activity,"异常：" + e.getMessage());
	}

	public static void log(String msg) {
		Log.i(TAG, msg);
	}

	public static void logInfo(String msg) {
		log(msg + "   thread:" + Thread.currentThread());
	}

	public static void toast(final Activity activity,final String msg) {
		Log.i(TAG, msg);
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(activity, msg, Toast.LENGTH_LONG).show();
			}
		});
	}

	public static void toastInfo(Activity activity, String msg) {
		toast(activity, msg + "   thread:" + Thread.currentThread());
	}


}
