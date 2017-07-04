package top.ftas.dunit.sample.test.groupsimple;

import top.ftas.dunit.annotation.DUnit;
import top.ftas.dunit.core.AbstractDisplayUnit;
import top.ftas.dunit.sample.group.HttpGroup;

/**
 * Created by tik on 17/7/4.
 */

@DUnit(group = HttpGroup.OkHttpGroup.class,
		name = "测试Okhttp的Post方法"
)
public class OkhttpPostMethodUnit extends AbstractDisplayUnit {
	@Override
	public void callUnit() {

	}
}
