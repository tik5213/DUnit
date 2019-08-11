package top.ftas.dunit.sample.message;

import top.ftas.dunit.annotation.DUnit;
import top.ftas.dunit.core.AbstractDisplayUnit;
import top.ftas.dunit.sample.AllGroup;
import top.ftas.dunit.util.MessageUtil;

/**
 * Created by tik on 17/7/6.
 */

@DUnit(group = AllGroup.MessageHelperGroup.class,name = "MessageUtil")
public class MessageUtilDisplayUnit extends AbstractDisplayUnit{
	@Override
	public void callUnit() {
		MessageUtil.out().printLine("MessageUtil 开始");
		MessageUtil.out().appendLine("MessageUtil 中间");
		MessageUtil.out().append("MessageUtil 结束");
	}
}
