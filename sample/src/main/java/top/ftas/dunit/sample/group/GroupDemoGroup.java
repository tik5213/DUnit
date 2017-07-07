package top.ftas.dunit.sample.group;

import top.ftas.dunit.annotation.DUnitGroup;
import top.ftas.dunit.group.DUnitGroupInterface;
import top.ftas.dunit.group.DUnitRootGroup;

/**
 * Created by tik on 17/6/29.
 */

@DUnitGroup("分组方式")
public class GroupDemoGroup extends DUnitRootGroup{

	@DUnitGroup("空的分组")
	public static class EmptyGroup extends GroupDemoGroup {
	}

	public static class RetrofitGroup extends GroupDemoGroup {
		@Override
		public String getName() {
			return "RetrofitGroup的使用";
		}
	}

	@DUnitGroup("OkHttp的使用")
	public static class OkHttpGroup extends GroupDemoGroup {

		@DUnitGroup("OkHttp的其他用法")
		public static class OkHttpOtherGroup extends OkHttpGroup {}
	}
}
