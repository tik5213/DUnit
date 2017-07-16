package top.ftas.dunit.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import top.ftas.dunit.R;

import static top.ftas.dunit.util.DUnitConstant.Sys.KEY_FRAGMENT_CLASS;

/**
 * Created by tik on 17/7/12.
 */

public class SingleFragmentActivity<T extends Fragment> extends Activity{
	protected T createFragment(){
		try {
			Intent intent = getIntent();
			Class<?> fragmentClass = (Class<?>) intent.getSerializableExtra(KEY_FRAGMENT_CLASS);
			Fragment fragment = (Fragment) fragmentClass.newInstance();
			return (T) fragment;
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}
	protected void onFragmentHasCreated(T fragment,Bundle savedInstanceState){}

	protected int getLayoutResId(){
		return R.layout.activity_fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getLayoutResId());
		FragmentManager fragmentManager = getFragmentManager();
		Fragment fragment = fragmentManager.findFragmentById(R.id.fragmentContainer);
		if (fragment == null){
			fragment = createFragment();
			FragmentTransaction transaction = fragmentManager.beginTransaction();
			transaction.add(R.id.fragmentContainer, fragment);
			transaction.commit();
		}
		onFragmentHasCreated((T) fragment,savedInstanceState);
	}

}
