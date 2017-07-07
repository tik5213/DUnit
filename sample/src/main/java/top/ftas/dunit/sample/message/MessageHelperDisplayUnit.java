package top.ftas.dunit.sample.message;

import top.ftas.dunit.annotation.DUnit;
import top.ftas.dunit.core.AbstractDisplayUnit;
import top.ftas.dunit.core.AbstractResultMessageHelperWrapper;
import top.ftas.dunit.core.ResultMessageHelper;
import top.ftas.dunit.sample.AllGroup;

/**
 * Created by tik on 17/7/6.
 */

@DUnit(group = AllGroup.MessageHelperGroup.class,name = "单独信息加工")
public class MessageHelperDisplayUnit extends AbstractDisplayUnit{

	@Override
	public ResultMessageHelper getMessageHelperWrapper(ResultMessageHelper messageHelper) {
		return new AbstractResultMessageHelperWrapper(messageHelper) {
			@Override
			public void print(String resultMessage) {
				mOriginalMessageHelper.print(resultMessage + " -->thread:" + Thread.currentThread().getName());
			}

			@Override
			public void printLine(String resultMessage) {
				mOriginalMessageHelper.printLine(resultMessage + " -->thread:" + Thread.currentThread().getName());
			}

			@Override
			public void append(String resultMessage) {
				mOriginalMessageHelper.append(resultMessage + " -->thread:" + Thread.currentThread().getName());
			}

			@Override
			public void appendLine(String resultMessage) {
				mOriginalMessageHelper.appendLine(resultMessage + " -->thread:" + Thread.currentThread().getName());
			}
		};
	}

	@Override
	public void callUnit() {
		mMessageHelper.printLine("开始");
		mMessageHelper.appendLine("中间");
		mMessageHelper.append("结束");
	}
}
