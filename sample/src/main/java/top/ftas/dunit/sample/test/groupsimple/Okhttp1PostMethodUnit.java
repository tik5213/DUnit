package top.ftas.dunit.sample.test.groupsimple;

import top.ftas.dunit.annotation.DUnit;
import top.ftas.dunit.core.AbstractDisplayUnit;
import top.ftas.dunit.sample.group.HttpGroup;
import top.ftas.dunit.util.ThreadModel;

/**
 * Created by tik on 17/7/4.
 */

@DUnit(group = HttpGroup.OkHttpGroup.OkHttpGroup001.class,
		name = "测试Okhttp1的Post方法"
)
public class Okhttp1PostMethodUnit extends AbstractDisplayUnit {
	@Override
	public void callUnit() {

	}
}
