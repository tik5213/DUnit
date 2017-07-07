package top.ftas.dunit.sample.other;

import top.ftas.dunit.annotation.DUnit;
import top.ftas.dunit.core.AbstractDisplayUnit;
import top.ftas.dunit.sample.AllGroup;
import top.ftas.dunit.util.LogUtil;

/**
 * Created by tik on 17/6/28.
 */

@DUnit(group = AllGroup.OtherDisplayGroup.class)
public class TestInnerClassDisplayUnit extends AbstractDisplayUnit {

	public void toastCurrentThread(){
		final Thread currentThread = Thread.currentThread();
		LogUtil.toast(mActivity,getClass().getSimpleName() + " --> " + "当前线程是：" + currentThread);
	}

	@Override
	public void callUnit() {
		toastCurrentThread();
	}

	public static class TestInnerClass1 extends TestInnerClassDisplayUnit {}
	public static class TestInnerClass2 extends TestInnerClassDisplayUnit {}

	public static class TestInnerClass3 extends TestInnerClassDisplayUnit {
		public static class TestInnerClass3A extends TestInnerClassDisplayUnit {}
		public static class TestInnerClass3B extends TestInnerClassDisplayUnit {}
	}

}
