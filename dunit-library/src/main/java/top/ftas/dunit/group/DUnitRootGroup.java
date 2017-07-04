package top.ftas.dunit.group;

import top.ftas.dunit.annotation.DUnitGroup;
import top.ftas.dunit.util.DUnitConstant;

/**
 * Created by tik on 17/6/29.
 * 根分组
 */

@DUnitGroup
public class DUnitRootGroup implements DUnitGroupInterface{

	@Override
	public String getName() {
		return getClass().getSimpleName();
	}

	@Override
	public int getPriority() {
		return DUnitConstant.Sys.DEFAULT_VALUE_INT;
	}

	@Override
	public Class<? extends DUnitGroupInterface> getGroup() {
		if (getClass() == DUnitRootGroup.class){
			return DUnitRootGroup.class;
		}else {
			return (Class<? extends DUnitGroupInterface>) getClass().getSuperclass();
		}
	}
}
