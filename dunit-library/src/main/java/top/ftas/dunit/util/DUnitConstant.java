package top.ftas.dunit.util;

import top.ftas.dunit.annotation.DUnit;
import top.ftas.dunit.annotation.DUnitGroup;
import top.ftas.dunit.group.DUnitGroupInterface;
import top.ftas.dunit.group.DUnitRootGroup;

/**
 * Created by tik on 17/6/28.
 */

public class DUnitConstant {
	public static class Sys{
		//DUnitManager
		public static final String DUNIT_MANAGER_SIMPLE_NAME_ = "DUnitManager";
		public static final String DUNIT_MANAGER_PKG = "top.ftas.dunit.core";
		public static final String DUNIT_MANAGER_CANONICAL_NAME = DUNIT_MANAGER_PKG + "." +  DUNIT_MANAGER_SIMPLE_NAME_;

		//DUnitManager_AutoImpl
		public static final String DUNIT_MANAGER_AUTO_IMPL_SIMPLE_NAME = "DUnitManager_AutoImpl1";
		public static final String DUNIT_MANAGER_AUTO_IMPL_PKG = DUNIT_MANAGER_PKG;
		public static final String DUNIT_MANAGER_AUTO_IMPL_CANONICAL_NAME = DUNIT_MANAGER_AUTO_IMPL_PKG + "." + DUNIT_MANAGER_AUTO_IMPL_SIMPLE_NAME;

		//top.ftas.dunit.annotation.DUnitGroup
		public static final String QUALIFIED_NAME_DUNIT_GROUP = DUnitGroup.class.getName().replace("$",".");
		public static final String QUALIFIED_NAME_DUNIT = DUnit.class.getName().replace("$",".");

		//DefaultValue
		public static final int DEFAULT_VALUE_INT = -1;
		public static final String DEFAULT_VALUE_STRING = "";
		public interface DEFAULT_VALUE_GROUP extends DUnitGroupInterface{}
		public static final String DEFAULT_VALUE_GROUP_NAME = DEFAULT_VALUE_GROUP.class.getName();
	}
}
