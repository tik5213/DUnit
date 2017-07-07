package top.ftas.dunit.sample.group.unit;

import android.widget.Toast;

import top.ftas.dunit.annotation.DUnit;
import top.ftas.dunit.util.ThreadModel;

/**
 * Created by tik on 17/7/4.
 */

@DUnit(name = "Get方法",threadMode = ThreadModel.MAIN)
public class OkHttpGetMethodUnit extends OkHttpBase {
	@Override
	public void callUnit() {
		Toast.makeText(mActivity,"Get方法调用成功",Toast.LENGTH_LONG).show();
	}
}
