package top.ftas.dunit.thread;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by tik on 17/6/28.
 */

public class DUnitThreadManager {
	private Handler mMainHandler;
	private Executor mIOExecutor;

	public static class SingletonHolder {
		private static final DUnitThreadManager instance = new DUnitThreadManager();
	}

	private DUnitThreadManager() {
		mMainHandler = new Handler(Looper.getMainLooper());
		mIOExecutor = createIOPoolExecutor();
	}

	private ThreadPoolExecutor createIOPoolExecutor(){
		//IO线程工厂类
		ThreadFactory threadFactory = new ThreadFactory() {
			@Override
			public Thread newThread(@NonNull Runnable runnable) {
				Thread thread = new Thread(runnable);
				thread.setName("dunit-io");
				return thread;
			}
		};

		//创建一个任务拒绝策略
		//直接忽略新进的任务
		RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.DiscardPolicy();
		//创建一个最大线程数为3的线程池
		ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1, 3, 3, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(10),threadFactory,rejectedExecutionHandler);
		//当核心线程空闲时，允许杀死核心线程
		poolExecutor.allowCoreThreadTimeOut(true);
		return poolExecutor;
	}

	public static final DUnitThreadManager getInstance() {
		return SingletonHolder.instance;
	}

	public void postCurrent(Runnable runnable){
		runnable.run();
	}

	public void postMain(Runnable runnable) {
		mMainHandler.post(runnable);
	}

	public void postNew(Runnable runnable) {
		new Thread(runnable).start();
	}

	public void postIO(Runnable runnable) {
		mIOExecutor.execute(runnable);
	}


}
