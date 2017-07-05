package top.ftas.dunit.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import top.ftas.dunit.R;
import top.ftas.dunit.core.AbstractDisplayUnit;
import top.ftas.dunit.core.DUnitManager;
import top.ftas.dunit.group.DUnitGroupInterface;
import top.ftas.dunit.group.DUnitRootGroup;
import top.ftas.dunit.model.DUnitBaseModel;
import top.ftas.dunit.model.DUnitGroupModel;
import top.ftas.dunit.model.DUnitModel;
import top.ftas.dunit.thread.DUnitThreadManager;
import top.ftas.dunit.util.LogUtil;

/**
 * Created by tik on 17/6/28.
 */

public class DUnitSimpleListActivity extends AppCompatActivity{
	public static final String KEY_GROUP = "KEY_GROUP";
	private LinearLayout mMainLinearLayout;
	private Activity mActivity;
	private Class<? extends DUnitGroupInterface> mCurrentGroup;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ArrayList<DUnitBaseModel> unitModels = null;
		setContentView(R.layout.activity_simplelist_empty_dunit);
		try {
			Class<? extends DUnitGroupInterface> group = getCurrentGroup();
			DUnitManager dUnitManager = DUnitManager.getInstance();
			unitModels = dUnitManager.getModelMap().get(group);
		}catch (Throwable e){
			LogUtil.log(this,e);
			return;
		}

		showHomeButton();
		setActionBarTitle();
		if (unitModels == null || unitModels.isEmpty()){
			setContentView(R.layout.activity_simplelist_empty_dunit);
			return;
		}

		setContentView(R.layout.activity_simplelist_dunit);

		mActivity = this;
		initMainView();


		Collections.sort(unitModels, new Comparator<DUnitBaseModel>() {
			@Override
			public int compare(DUnitBaseModel unitModel1, DUnitBaseModel unitModel2) {
				return unitModel2.getPriority() - unitModel1.getPriority();
			}
		});

		for (DUnitBaseModel unitModel :
				unitModels) {
			try {
				addDisplayUnitButton(unitModel);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()){
			case android.R.id.home:
				finish();
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	protected void showHomeButton() {
		if (isRootPage()) return;
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

	}

	protected void setActionBarTitle(){
		if (isRootPage()) return;
		Class<? extends DUnitGroupInterface> currentGroup = getCurrentGroup();
		ArrayList<DUnitGroupModel> groupModels = DUnitManager.getInstance().getUnitGroupModels();
		for (DUnitGroupModel groupModel: groupModels) {
			if (groupModel.getOriginal() == currentGroup){
				getSupportActionBar().setTitle(groupModel.getName());
				return;
			}
		}
	}

	private Class<? extends DUnitGroupInterface> getCurrentGroup() {
		if (mCurrentGroup == null){
			Intent intent = getIntent();
			if (intent == null) {
				mCurrentGroup = getRootGroup();
			}else {
				try {
					Class<? extends DUnitGroupInterface> group = (Class<? extends DUnitGroupInterface>) intent.getSerializableExtra(KEY_GROUP);
					mCurrentGroup = group == null ? getRootGroup() : group;
				}catch (Exception e){
					mCurrentGroup = getRootGroup();
				}
			}
		}
		return mCurrentGroup;
	}

	protected Class<? extends DUnitGroupInterface> getRootGroup() {
		return DUnitRootGroup.class;
	}

	private void initMainView() {
		mMainLinearLayout = (LinearLayout) findViewById(R.id.main_ll);
	}

	/**
	 * 添加展示按钮
	 * @param unitModel
	 * @throws ClassNotFoundException
	 */
	private void addDisplayUnitButton(final DUnitBaseModel unitModel) throws ClassNotFoundException {
		Button button = (Button) getLayoutInflater().inflate(R.layout.btn_item_simplelist_dunit,mMainLinearLayout,false);
		button.setText(unitModel.getName());
		//禁止大写
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				switch (unitModel.getModelType()){
					case MODEL_TYPE_UNIT:
						doUnit(unitModel);
						break;
					case MODEL_TYPE_GROUP:
						startNextPage(unitModel);
						break;
				}
			}
		});
		mMainLinearLayout.addView(button);
	}

	private void startNextPage(DUnitBaseModel unitModel) {
		Class<? extends DUnitGroupInterface> group = unitModel.getOriginal();
		Intent intent = new Intent(this,getClass());
		intent.putExtra(KEY_GROUP,group);
		intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
		startActivity(intent);
	}

	private void doUnit(DUnitBaseModel unitModel) {
		try {
			Class<?> unitClass = Class.forName(unitModel.getOriginalClassName());
			AbstractDisplayUnit displayUnit = (AbstractDisplayUnit) unitClass.newInstance();
			displayUnit.setContext(DUnitSimpleListActivity.this.getApplicationContext());
			displayUnit.setActivity(DUnitSimpleListActivity.this);
			callDisplayUnit(displayUnit, (DUnitModel) unitModel);
		}catch (Exception e){
			log(e);
		}
	}

	private void callDisplayUnit(final AbstractDisplayUnit displayUnit, DUnitModel unitModel) {
		switch (unitModel.getThreadMode()){
			case CURRENT_THREAD:
				DUnitThreadManager.getInstance().postMain(displayUnit);
				displayUnit.callUnit();
				break;
			case MAIN:
				DUnitThreadManager.getInstance().postMain(displayUnit);
				break;
			case NEW_THREAD:
				DUnitThreadManager.getInstance().postNew(displayUnit);
				break;
			case IO:
				DUnitThreadManager.getInstance().postIO(displayUnit);
				break;
		}
	}

	protected boolean isRootPage(){
		return getRootGroup() == getCurrentGroup();
	}

	public void log(Exception e){
		LogUtil.log(this,e);
	}

}
