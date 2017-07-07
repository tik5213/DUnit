package top.ftas.dunit.sample.thread;

import top.ftas.dunit.annotation.DUnit;
import top.ftas.dunit.core.AbstractDisplayUnit;
import top.ftas.dunit.sample.AllGroup;
import top.ftas.dunit.util.LogUtil;
import top.ftas.dunit.util.ThreadModel;

/**
 * Created by tik on 17/6/27.
 */

@DUnit(
		name = "新建线程运行",
		threadMode = ThreadModel.NEW_THREAD,
		group = AllGroup.ThreadDispayGroup.class
)
public class SimpleNewThreadDisplayUnit extends AbstractDisplayUnit{
	@Override
	public void callUnit() {
		mMessageHelper.appendLine("当前线程：" + Thread.currentThread().getName());
		final Thread unitThread = Thread.currentThread();
		mActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				LogUtil.toast(mActivity,"你好，SimpleNewThreadDisplayUnit。\nDisplayUnit所在线程：" + unitThread + "\n当前线程：" + Thread.currentThread());
			}
		});
	}
}
