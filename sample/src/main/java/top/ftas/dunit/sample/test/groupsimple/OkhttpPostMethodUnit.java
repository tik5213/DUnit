package top.ftas.dunit.sample.test.groupsimple;

import top.ftas.dunit.annotation.DUnit;
import top.ftas.dunit.core.AbstractDisplayUnit;
import top.ftas.dunit.sample.group.HttpGroup;

/**
 * Created by tik on 17/7/4.
 */

@DUnit(name = "测试Okhttp的Post方法")
public class OkhttpPostMethodUnit extends OkHttpBase {
	@Override
	public void callUnit() {
		mMessageHelper.append("我是MessageHelper!");
	}
}
