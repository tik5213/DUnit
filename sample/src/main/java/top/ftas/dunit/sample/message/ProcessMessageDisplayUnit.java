package top.ftas.dunit.sample.message;

import top.ftas.dunit.annotation.DUnit;
import top.ftas.dunit.core.AbstractDisplayUnit;
import top.ftas.dunit.core.AbstractResultMessageHelperWrapper;
import top.ftas.dunit.core.ResultMessageHelper;
import top.ftas.dunit.sample.AllGroup;

/**
 * Created by tik on 17/7/6.
 */

@DUnit(group = AllGroup.MessageHelperGroup.class,name = "使用processMessage方法")
public class ProcessMessageDisplayUnit extends AbstractDisplayUnit{

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
