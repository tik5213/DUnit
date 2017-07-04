package top.ftas.dunit.sample.test.simple;

import top.ftas.dunit.annotation.DUnit;
import top.ftas.dunit.core.AbstractDisplayUnit;
import top.ftas.dunit.util.LogUtil;
import top.ftas.dunit.util.ThreadModel;

/**
 * Created by tik on 17/6/27.
 */

@DUnit(
		threadMode = ThreadModel.NEW_THREAD
)
public class SimpleNewThreadDisplayUnit extends AbstractDisplayUnit{
	@Override
	public void callUnit() {
		final Thread unitThread = Thread.currentThread();
		mActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				LogUtil.toast(mActivity,"你好，SimpleNewThreadDisplayUnit。DisplayUnit所在线程：" + unitThread);
			}
		});
	}
}
