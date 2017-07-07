package top.ftas.dunit.sample.callback;

import top.ftas.dunit.annotation.DUnit;
import top.ftas.dunit.core.AbstractDisplayUnit;
import top.ftas.dunit.sample.AllGroup;

/**
 * Created by tik on 17/7/7.
 */

@DUnit(name = "异常演示",group = AllGroup.CallBackGroup.class)
public class ErrorCallBackUnit extends AbstractDisplayUnit{
	@Override
	public void onError(Throwable e) {
		super.onError(e);
	}

	@Override
	public void callUnit() {
		mMessageHelper.appendLine("callUnit调用中...");
		throw new RuntimeException("callUnit中的异常");
	}
}
