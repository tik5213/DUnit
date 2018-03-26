package top.ftas.dunit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import top.ftas.dunit.R;

import static top.ftas.dunit.util.DUnitConstant.Sys.KEY_FRAGMENT_CLASS;

/**
 * Created by tik on 17/7/12.
 */

public class SingleSupportFragmentActivity<T extends Fragment> extends AppCompatActivity{
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
		return R.layout.activity_fragment_dunit;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getLayoutResId());
		FragmentManager fragmentManager = getSupportFragmentManager();
		Fragment fragment = fragmentManager.findFragmentById(R.id.fragmentContainer);
		if (fragment == null){
			fragment = createFragment();
			if (getIntent() != null && getIntent().getExtras() != null){
				fragment.setArguments(getIntent().getExtras());
			}
			FragmentTransaction transaction = fragmentManager.beginTransaction();
			transaction.add(R.id.fragmentContainer, fragment);
			transaction.commit();
		}
		onFragmentHasCreated((T) fragment,savedInstanceState);
	}

}
