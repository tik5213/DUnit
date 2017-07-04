package top.ftas.dunit.sample.group;

import top.ftas.dunit.annotation.DUnitGroup;
import top.ftas.dunit.group.DUnitRootGroup;

/**
 * Created by tik on 17/6/29.
 */

@DUnitGroup("Http使用演示")
public class HttpGroup extends DUnitRootGroup{
	public static class RetrofitGroup extends HttpGroup{
		@Override
		public String getName() {
			return "RetrofitGroup的使用";
		}
	}

	@Override
	public int getPriority() {
		return 9999;
	}

	public static class OkHttpGroup extends HttpGroup{
		public static class OkHttpGroup001 extends OkHttpGroup{
		}

	}
}
