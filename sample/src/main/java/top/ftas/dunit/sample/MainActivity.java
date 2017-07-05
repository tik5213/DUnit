package top.ftas.dunit.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;

import top.ftas.dunit.activity.DUnitSimpleListActivity;

public class MainActivity extends DUnitSimpleListActivity {
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		DUnitManager.getInstance().showUnitModels(this);
//		DUnitManager.getInstance().showUnitGroupModels(this);
	}
}
