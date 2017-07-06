package top.ftas.dunit.sample.test.groupsimple;

import top.ftas.dunit.annotation.DUnit;
import top.ftas.dunit.core.AbstractDisplayUnit;
import top.ftas.dunit.group.DUnitRootGroup;
import top.ftas.dunit.sample.group.HttpGroup;

/**
 * Created by tik on 17/7/4.
 */

@DUnit(name = "测试Okhttp的Get方法")
public class OkhttpGetMethodUnit extends OkHttpBase {
	@Override
	public void callUnit() {
//		mMessageHelper.print("我是MessageHelper!");
//		mMessageHelper.print("我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!");
		mMessageHelper.print("我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!我是MessageHelper!");
	}
}
