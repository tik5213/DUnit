package top.ftas.dunit.sample.group.unit;

import top.ftas.dunit.annotation.DUnit;

/**
 * Created by tik on 17/7/4.
 */

@DUnit(name = "Post方法")
public class OkHttpPostMethodUnit extends OkHttpBase {
	@Override
	public void callUnit() {
		mMessageHelper.print("Post方法调用成功！");
	}
}
