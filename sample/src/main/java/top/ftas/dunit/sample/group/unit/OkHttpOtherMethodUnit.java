package top.ftas.dunit.sample.group.unit;

import top.ftas.dunit.annotation.DUnit;
import top.ftas.dunit.core.AbstractDisplayUnit;
import top.ftas.dunit.sample.group.GroupDemoGroup;

/**
 * Created by tik on 17/7/4.
 */

@DUnit(group = GroupDemoGroup.OkHttpGroup.OkHttpOtherGroup.class,
		name = "测试方法"
)
public class OkHttpOtherMethodUnit extends OkHttpBase {
	@Override
	public void callUnit() {
		mMessageHelper.print("测试成功");
	}
}
