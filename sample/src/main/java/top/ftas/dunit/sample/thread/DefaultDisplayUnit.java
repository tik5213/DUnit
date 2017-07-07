package top.ftas.dunit.sample.thread;

import top.ftas.dunit.annotation.DUnit;
import top.ftas.dunit.core.AbstractDisplayUnit;
import top.ftas.dunit.sample.AllGroup;
import top.ftas.dunit.util.LogUtil;
import top.ftas.dunit.util.ThreadModel;

/**
 * Created by tik on 17/6/27.
 */

@DUnit(group = AllGroup.ThreadDispayGroup.class)
public class DefaultDisplayUnit extends AbstractDisplayUnit{
	@Override
	public void callUnit() {
		mMessageHelper.appendLine("当前线程：" + Thread.currentThread());
	}
}
