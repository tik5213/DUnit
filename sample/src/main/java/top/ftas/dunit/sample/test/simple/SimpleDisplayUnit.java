package top.ftas.dunit.sample.test.simple;

import android.widget.Toast;

import top.ftas.dunit.annotation.DUnit;
import top.ftas.dunit.core.AbstractDisplayUnit;
import top.ftas.dunit.sample.group.HttpGroup;
import top.ftas.dunit.thread.DUnitThreadManager;
import top.ftas.dunit.util.LogUtil;
import top.ftas.dunit.util.ThreadModel;

/**
 * Created by tik on 17/6/27.
 */

@DUnit(
		threadMode = ThreadModel.MAIN,
		name = "简单的Toast使用展示",
		priority = 1
)
public class SimpleDisplayUnit extends AbstractDisplayUnit{
	@Override
	public void callUnit() {
		Thread unitThread = Thread.currentThread();
		LogUtil.toast(mActivity,"Hello，SimpleDisplayUnit。DisplayUnit所在线程：" + unitThread);
	}
}
