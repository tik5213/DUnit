package top.ftas.dunit.sample.test.simple;

import top.ftas.dunit.annotation.DUnit;
import top.ftas.dunit.core.AbstractDisplayUnit;
import top.ftas.dunit.util.LogUtil;
import top.ftas.dunit.util.ThreadModel;

/**
 * Created by tik on 17/6/27.
 */

public class DefaultDisplayUnit extends AbstractDisplayUnit{
	@Override
	public void callUnit() {
		Thread unitThread = Thread.currentThread();
		LogUtil.toast(mActivity,"默认值效果展示。DisplayUnit所在线程：" + unitThread);
	}
}
