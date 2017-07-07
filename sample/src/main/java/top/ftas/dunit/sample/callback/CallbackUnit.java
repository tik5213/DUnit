package top.ftas.dunit.sample.callback;

import top.ftas.dunit.annotation.DUnit;
import top.ftas.dunit.core.AbstractDisplayUnit;
import top.ftas.dunit.sample.AllGroup;

/**
 * Created by tik on 17/7/7.
 */

@DUnit(name = "简单演示回调函数",group = AllGroup.CallBackGroup.class)
public class CallbackUnit extends AbstractDisplayUnit{
	@Override
	public void onPrepared() {
		mMessageHelper.hiddenAndClean();
		mMessageHelper.appendLine("onPrepared");
	}

	@Override
	public void onError(Throwable e) {
		super.onError(e);
	}

	@Override
	public void onCalled() {
		mMessageHelper.append("onCalled");
	}

	@Override
	public void callUnit() {
		mMessageHelper.appendLine("callUnit");
	}
}
