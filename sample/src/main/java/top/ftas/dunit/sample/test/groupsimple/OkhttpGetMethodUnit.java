package top.ftas.dunit.sample.test.groupsimple;

import top.ftas.dunit.annotation.DUnit;
import top.ftas.dunit.core.AbstractDisplayUnit;
import top.ftas.dunit.group.DUnitRootGroup;
import top.ftas.dunit.sample.group.HttpGroup;

/**
 * Created by tik on 17/7/4.
 */

@DUnit(name = "测试Okhttp的Get方法")
public class OkhttpGetMethodUnit extends OkHttpBase {
	@Override
	public void onPrepared() {
		mMessageHelper.hiddenAndClean();
		mMessageHelper.printLine("我已经准备好了");
	}

	@Override
	public void callUnit() {
		mMessageHelper.appendLine("我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!");
	}

	@Override
	public void onCalled() {
		mMessageHelper.append("调用已经结束了");
	}
}
