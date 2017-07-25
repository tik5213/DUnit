package top.ftas.dunit.sample_simple;

import android.app.Application;

/**
 * Created by tik on 17/7/25.
 */

public class ClientApplication extends Application{
	public static class MyCrashHandler implements Thread.UncaughtExceptionHandler{
		private Thread.UncaughtExceptionHandler mUncaughtExceptionHandler;

		public MyCrashHandler(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
			mUncaughtExceptionHandler = uncaughtExceptionHandler;
		}

		@Override
		public void uncaughtException(Thread t, Throwable e) {
			e.printStackTrace();
			mUncaughtExceptionHandler.uncaughtException(t,e);
		}
	}
	@Override
	public void onCreate() {
		super.onCreate();
		Thread.setDefaultUncaughtExceptionHandler(new MyCrashHandler(Thread.getDefaultUncaughtExceptionHandler()));
	}
}
