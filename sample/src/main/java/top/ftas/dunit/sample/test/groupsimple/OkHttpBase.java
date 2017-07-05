package top.ftas.dunit.sample.test.groupsimple;

import top.ftas.dunit.annotation.DUnit;
import top.ftas.dunit.annotation.DUnitHidden;
import top.ftas.dunit.core.AbstractDisplayUnit;
import top.ftas.dunit.sample.group.HttpGroup;

/**
 * Created by tik on 17/7/5.
 */

@DUnitHidden
@DUnit(group = HttpGroup.OkHttpGroup.class)
public class OkHttpBase extends AbstractDisplayUnit {
	@Override
	public void callUnit() {
	}
}
