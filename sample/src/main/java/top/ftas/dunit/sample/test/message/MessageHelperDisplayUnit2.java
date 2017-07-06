package top.ftas.dunit.sample.test.message;

import top.ftas.dunit.core.AbstractDisplayUnit;
import top.ftas.dunit.core.AbstractResultMessageHelperWrapper;
import top.ftas.dunit.core.ResultMessageHelper;

/**
 * Created by tik on 17/7/6.
 */

public class MessageHelperDisplayUnit2 extends AbstractDisplayUnit{

	@Override
	public ResultMessageHelper getMessageHelperWrapper(ResultMessageHelper messageHelper) {
		return new AbstractResultMessageHelperWrapper(messageHelper) {
			@Override
			public String processMessage(String resultMessage) {
				return "加工后的消息：" + resultMessage;
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
