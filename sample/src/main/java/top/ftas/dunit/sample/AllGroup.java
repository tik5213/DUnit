package top.ftas.dunit.sample;

import top.ftas.dunit.annotation.DUnitGroup;
import top.ftas.dunit.group.DUnitRootGroup;

/**
 * Created by tik on 17/7/7.
 */

public class AllGroup {
	@DUnitGroup(value = "简单演示")
	public static class ThreadDispayGroup extends DUnitRootGroup{}
	@DUnitGroup(value = "回调函数演示")
	public static class CallBackGroup extends DUnitRootGroup{}
	@DUnitGroup(value = "装饰MessageHelper")
	public static class MessageHelperGroup extends DUnitRootGroup{}
	@DUnitGroup(value = "嵌套演示")
	public static class OtherDisplayGroup extends DUnitRootGroup{}
}
