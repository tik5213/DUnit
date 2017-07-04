package top.ftas.dunit.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import top.ftas.dunit.activity.DunitSimpleListActivity;
import top.ftas.dunit.core.DUnitManager;

public class MainActivity extends DunitSimpleListActivity {
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		DUnitManager.getInstance().showUnitModels(this);
//		DUnitManager.getInstance().showUnitGroupModels(this);
	}
}
