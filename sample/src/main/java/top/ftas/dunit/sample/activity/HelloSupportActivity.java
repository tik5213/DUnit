package top.ftas.dunit.sample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import top.ftas.dunit.annotation.DUnit;
import top.ftas.dunit.sample.AllGroup;
import top.ftas.dunit.sample.R;

/**
 * Created by tik on 17/7/16.
 */

@DUnit(group = AllGroup.AutoGroup.class,paramJson = "{\"testName\":\"李四\",\"testAge\":22}")
public class HelloSupportActivity extends AppCompatActivity{
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hello);
		TextView textView = (TextView) findViewById(R.id.hello_txt);
		String str = "你好，AppCompatActivity！";
		Intent intent = getIntent();
		if (intent != null){
		    str += "\nname:" + intent.getStringExtra("testName");
			str += "\nage:" + intent.getIntExtra("testAge",-1);
		}
		textView.setText(str);
	}
}
