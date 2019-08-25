package darger2.test.dxy.cn.sample_test_library;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import top.ftas.dunit.annotation.DUnit;

/**
 * Created by tik on 2018/3/26.
 */

@DUnit(name = "SampleTestLibraryActivity - paramJson",paramJson = "{\"testName\":\"I am from other library！\",\"testAge\":18}")
public class SampleTestLibraryActivity extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_test_library);
        TextView textView = (TextView) findViewById(R.id.hello_txt);

        String str = "Hello，SampleTestLibraryActivity！";
        Intent intent = getIntent();
        if (intent != null) {
            str += "\nname:" + intent.getStringExtra("testName");
            str += "\nage:" + intent.getIntExtra("testAge", -1);
        }

        textView.setText(str);
    }
}
