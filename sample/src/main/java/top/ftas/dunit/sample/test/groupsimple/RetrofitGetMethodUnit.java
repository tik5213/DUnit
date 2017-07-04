package top.ftas.dunit.sample.test.groupsimple;

import top.ftas.dunit.annotation.DUnit;
import top.ftas.dunit.core.AbstractDisplayUnit;
import top.ftas.dunit.sample.group.HttpGroup;

/**
 * Created by tik on 17/7/4.
 */

@DUnit(group = HttpGroup.RetrofitGroup.class,
		name = "测试Retrofit的Get方法"
)
public class RetrofitGetMethodUnit extends AbstractDisplayUnit {
	@Override
	public void callUnit() {

	}
}
