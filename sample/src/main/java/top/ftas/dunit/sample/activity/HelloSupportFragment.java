package top.ftas.dunit.sample.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import top.ftas.dunit.annotation.DUnit;
import top.ftas.dunit.sample.AllGroup;
import top.ftas.dunit.sample.R;

/**
 * Created by tik on 17/7/16.
 */

@DUnit(group = AllGroup.AutoGroup.class,paramJson = "{\"testName\":\"王五\",\"testAge\":22}")
public class HelloSupportFragment extends Fragment{
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_hello,container,false);
		TextView textView = (TextView) rootView.findViewById(R.id.hello_txt);

		String str = "你好，SupportFragment！";
		Bundle bundle = getArguments();
		if (bundle != null){
			str += "\nname:" + bundle.getString("testName");
			str += "\nage:" + bundle.getInt("testAge",-1);
		}

		textView.setText(str);

		return rootView;
	}
}
