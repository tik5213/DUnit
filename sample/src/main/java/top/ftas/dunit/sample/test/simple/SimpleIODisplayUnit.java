package top.ftas.dunit.sample.test.simple;

import android.widget.Toast;

import top.ftas.dunit.annotation.DUnit;
import top.ftas.dunit.core.AbstractDisplayUnit;
import top.ftas.dunit.util.LogUtil;
import top.ftas.dunit.util.ThreadModel;

import static android.R.attr.priority;

/**
 * Created by tik on 17/6/27.
 */

@DUnit(
		threadMode = ThreadModel.IO,
		priority = 100,
		name = "打印一个中文版的你好"
)
public class SimpleIODisplayUnit extends AbstractDisplayUnit{
	@Override
	public void callUnit() {
		Thread unitThread = Thread.currentThread();
		LogUtil.toast(mActivity,"你好，SimpleIODisplayUnit。DisplayUnit所在线程：" + unitThread);
	}
}
