package top.ftas.dunit.sample.group.unit;

import android.widget.Toast;

import top.ftas.dunit.annotation.DUnit;
import top.ftas.dunit.core.AbstractDisplayUnit;
import top.ftas.dunit.sample.group.GroupDemoGroup;
import top.ftas.dunit.thread.DUnitThreadManager;
import top.ftas.dunit.util.LogUtil;

/**
 * Created by tik on 17/7/4.
 */

@DUnit(group = GroupDemoGroup.RetrofitGroup.class,
		name = "Retrofit的Get方法"
)
public class RetrofitGetMethodUnit extends AbstractDisplayUnit {
	@Override
	public void callUnit() {
		DUnitThreadManager.getInstance().postMain(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(mActivity,"成功！",Toast.LENGTH_LONG).show();
			}
		});
	}
}
