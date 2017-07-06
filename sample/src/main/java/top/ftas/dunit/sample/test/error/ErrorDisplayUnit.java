package top.ftas.dunit.sample.test.error;

import top.ftas.dunit.core.AbstractDisplayUnit;

/**
 * Created by tik on 17/7/6.
 */

public class ErrorDisplayUnit extends AbstractDisplayUnit{
	@Override
	public void callUnit() {
		mMessageHelper.printLine("ErrorDisplayUnit执行中....");
		throw new RuntimeException("ErrorDisplayUnit执行错误");
	}
}
