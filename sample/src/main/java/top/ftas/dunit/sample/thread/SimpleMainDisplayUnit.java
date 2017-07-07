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
		threadMode = ThreadModel.MAIN,
		name = "Main线程运行",
		priority = PrioritySet.SimpleMainDisplayUnit,
		group = AllGroup.ThreadDispayGroup.class
)
public class SimpleMainDisplayUnit extends AbstractDisplayUnit{
	@Override
	public void callUnit() {
		Thread unitThread = Thread.currentThread();
		LogUtil.toast(mActivity,"Hello，SimpleMainDisplayUnit。DisplayUnit所在线程：" + unitThread);
	}
}
