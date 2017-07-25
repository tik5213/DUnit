package top.ftas.dunit.sample_simple;

import android.app.Activity;
import android.os.Bundle;

import top.ftas.dunit.annotation.DUnit;

/**
 * Created by tik on 17/7/25.
 */

@DUnit
public class MainActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
}
