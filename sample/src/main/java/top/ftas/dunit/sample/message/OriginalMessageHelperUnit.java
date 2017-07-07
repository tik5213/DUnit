package top.ftas.dunit.sample.message;

import top.ftas.dunit.annotation.DUnit;
import top.ftas.dunit.core.AbstractDisplayUnit;
import top.ftas.dunit.sample.AllGroup;

/**
 * Created by tik on 17/7/7.
 */

@DUnit(group = AllGroup.MessageHelperGroup.class,name = "不加工")
public class OriginalMessageHelperUnit extends AbstractDisplayUnit{
	@Override
	public void callUnit() {
		mMessageHelper.printLine("开始");
		mMessageHelper.appendLine("中间");
		mMessageHelper.append("结束");
	}
}
