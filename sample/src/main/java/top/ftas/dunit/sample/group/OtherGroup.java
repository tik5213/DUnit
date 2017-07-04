package top.ftas.dunit.sample.group;

import top.ftas.dunit.annotation.DUnitGroup;
import top.ftas.dunit.group.DUnitGroupInterface;
import top.ftas.dunit.group.DUnitRootGroup;

/**
 * Created by tik on 17/7/4.
 */

@DUnitGroup("其他分组")
public class OtherGroup extends HttpGroup{
	@Override
	public Class<? extends DUnitGroupInterface> getGroup() {
		return DUnitRootGroup.class;
	}
}
