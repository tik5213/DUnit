package top.ftas.dunit.sample.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import top.ftas.dunit.sample.R;

/**
 * Created by tik on 17/7/16.
 */

public class HelloActivity extends BaseActivity{
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hello);
		TextView textView = (TextView) findViewById(R.id.hello_txt);
		textView.setText("你好，Activity！");
	}
}
