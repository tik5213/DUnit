package top.ftas.dunit.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import top.ftas.dunit.R;
import top.ftas.dunit.core.AbstractDisplayUnit;
import top.ftas.dunit.core.DUnitManager;
import top.ftas.dunit.core.ResultMessageHelper;
import top.ftas.dunit.group.DUnitGroupInterface;
import top.ftas.dunit.group.DUnitRootGroup;
import top.ftas.dunit.gson.BundleTypeAdapterFactory;
import top.ftas.dunit.model.DUnitBaseModel;
import top.ftas.dunit.model.DUnitGroupModel;
import top.ftas.dunit.model.DUnitModel;
import top.ftas.dunit.model.ModelType;
import top.ftas.dunit.thread.DUnitThreadManager;
import top.ftas.dunit.util.DUnitConstant;
import top.ftas.dunit.util.LogUtil;
import top.ftas.dunit.util.StatusBarUtils;
import top.ftas.dunit.util.TypefaceUtil;
import top.ftas.dunit.widget.DUnitSupportActionBar;

import static top.ftas.dunit.util.DUnitConstant.Sys.KEY_FRAGMENT_CLASS;
import static top.ftas.dunit.util.ThreadModel.CURRENT_THREAD;
import static top.ftas.dunit.util.ThreadModel.IO;
import static top.ftas.dunit.util.ThreadModel.MAIN;
import static top.ftas.dunit.util.ThreadModel.NEW_THREAD;

/**
 * Created by tik on 17/6/28.
 */

public class DUnitSimpleListActivity extends Activity implements DUnitSupportActionBar.OnSupportActionBarClickListener {
	public static final String KEY_GROUP = "KEY_GROUP";
	private LinearLayout mMainLinearLayout;
	private Activity mActivity;
	private Class<? extends DUnitGroupInterface> mCurrentGroup;
	private DUnitSupportActionBar mSupportActionBar;
	private Gson mGson = new GsonBuilder().registerTypeAdapterFactory(new BundleTypeAdapterFactory()).create();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		TypefaceUtil.getIconTypeFace(this);
		StatusBarUtils.setWindowStatusBarColor(this,R.color.dunitColorPrimaryDark);
		super.onCreate(savedInstanceState);
		ArrayList<DUnitBaseModel> unitModels;
		try {
			Class<? extends DUnitGroupInterface> group = getCurrentGroup();
			DUnitManager dUnitManager = DUnitManager.getInstance();
			unitModels = dUnitManager.getModelMap().get(group);
		}catch (Throwable e){
			LogUtil.log(this,e);
			return;
		}

		if (unitModels == null || unitModels.isEmpty()){
			setContentView(R.layout.activity_empty_simplelist_dunit);
			mSupportActionBar = new DUnitSupportActionBar(findViewById(R.id.action_bar_ll));
			mSupportActionBar.setOnSupportActionBarClickListener(this);
			showHomeButton();
			setActionBarTitle();
			return;
		}

		setContentView(R.layout.activity_simplelist_dunit);
		mSupportActionBar = new DUnitSupportActionBar(findViewById(R.id.action_bar_ll));
		mSupportActionBar.setOnSupportActionBarClickListener(this);
		showHomeButton();
		showHomeButton();
		setActionBarTitle();

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
				if (unitModel.isHidden()) continue;
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
		if (isRootPage()){
			String pkgName = getPackageName();
			try {
				PackageInfo pkgInfo = getPackageManager().getPackageInfo(pkgName,0);
				CharSequence appLabel = getPackageManager().getApplicationLabel(pkgInfo.applicationInfo);
				getSupportActionBar().setTitle(appLabel.toString());
			} catch (PackageManager.NameNotFoundException e) {
				e.printStackTrace();
			}
			return;
		}
		Class<? extends DUnitGroupInterface> currentGroup = getCurrentGroup();
		ArrayList<DUnitGroupModel> groupModels = DUnitManager.getInstance().getUnitGroupModels();
		for (DUnitGroupModel groupModel: groupModels) {
			if (groupModel.getOriginal() == currentGroup){
				getSupportActionBar().setTitle(groupModel.getName());
				return;
			}
		}
	}

	private DUnitSupportActionBar getSupportActionBar(){
		return mSupportActionBar;
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
						doUnit((DUnitModel) unitModel,v);
						break;
					case MODEL_TYPE_GROUP:
						startNextPage(unitModel);
						break;
				}
			}
		});
		mMainLinearLayout.addView(button);
		if (unitModel.getModelType() == ModelType.MODEL_TYPE_UNIT){
			View messageView = getLayoutInflater().inflate(R.layout.message_simplelist_dunit,mMainLinearLayout,false);
			mMainLinearLayout.addView(messageView);
			button.setTag(messageView);
		}
	}

	private void startNextPage(DUnitBaseModel unitModel) {
		Class<? extends DUnitGroupInterface> group = unitModel.getOriginal();
		Intent intent = new Intent(this,getClass());
		intent.putExtra(KEY_GROUP,group);
		intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
		startActivity(intent);
	}

	@Override
	public void onSupportActionBarClick(View view) {
		int id = view.getId();
		if (id == R.id.action_bar_home){
			finish();
		}
	}

	private static class SimpleResultMessageHelper implements ResultMessageHelper{
		private WeakReference<TextView> mTextViewWeakReference;
		private WeakReference<View> mViewWeakReference;
		public SimpleResultMessageHelper(View view){
			mViewWeakReference = new WeakReference<>(view);
			TextView textView = (TextView) view.findViewById(R.id.simplelist_dunit_message_txt);
			mTextViewWeakReference = new WeakReference<>(textView);
		}

		@Override
		public void print(final String resultMessage) {
			DUnitThreadManager.getInstance().postMain(new Runnable() {
				@Override
				public void run() {
					View v = mViewWeakReference.get();
					v.setVisibility(View.VISIBLE);
					TextView textView = mTextViewWeakReference.get();
					textView.setText(resultMessage);
				}
			});
		}

		@Override
		public void printLine(String resultMessage) {
			print(resultMessage + "\n");
		}

		@Override
		public void append(final String resultMessage) {
			DUnitThreadManager.getInstance().postMain(new Runnable() {
				@Override
				public void run() {
					View v = mViewWeakReference.get();
					v.setVisibility(View.VISIBLE);
					TextView textView = mTextViewWeakReference.get();
					String msg = textView.getText() + resultMessage;
					textView.setText(msg);
				}
			});
		}

		@Override
		public void appendLine(String resultMessage) {
			append(resultMessage + "\n");
		}

		@Override
		public void clean() {
			print("");
		}

		@Override
		public void hiddenAndClean() {
			DUnitThreadManager.getInstance().postMain(new Runnable() {
				@Override
				public void run() {
					mViewWeakReference.get().setVisibility(View.GONE);
					mTextViewWeakReference.get().setText("");
				}
			});
		}

		@Override
		public void hidden() {
			DUnitThreadManager.getInstance().postMain(new Runnable() {
				@Override
				public void run() {
					mViewWeakReference.get().setVisibility(View.GONE);
				}
			});
		}

		@Override
		public void show() {
			DUnitThreadManager.getInstance().postMain(new Runnable() {
				@Override
				public void run() {
					mViewWeakReference.get().setVisibility(View.VISIBLE);
				}
			});
		}
	}

	private void doUnit(DUnitModel unitModel, View v) {
		try {
			Class<?> unitClass = Class.forName(unitModel.getOriginalClassName());
			if (unitModel.getUnitType().equals(DUnitConstant.UnitType.AUTO)){
				if (Activity.class.isAssignableFrom(unitClass)){
					doActivityUnit(unitClass,unitModel,v);
					return;
				}
				if (android.app.Fragment.class.isAssignableFrom(unitClass)){
					doFragmentUnit(unitClass,unitModel,v);
					return;
				}

				if (android.support.v4.app.Fragment.class.isAssignableFrom(unitClass)){
					doSupportFragmentUnit(unitClass,unitModel,v);
					return;
				}
			}
			AbstractDisplayUnit displayUnit = (AbstractDisplayUnit) unitClass.newInstance();
			displayUnit.setContext(DUnitSimpleListActivity.this.getApplicationContext());
			displayUnit.setActivity(DUnitSimpleListActivity.this);
			ResultMessageHelper messageHelper = new SimpleResultMessageHelper((View) v.getTag());
			ResultMessageHelper newMessageHelper = displayUnit.getMessageHelperWrapper(messageHelper);
			if (newMessageHelper != null) messageHelper = newMessageHelper;
			displayUnit.setMessageHelper(messageHelper);
			callDisplayUnit(displayUnit, unitModel);
		}catch (Exception e){
			log(e);
		}
	}

	private void doSupportFragmentUnit(Class<?> unitClass, DUnitBaseModel unitModel, View v) {
		Intent intent = new Intent(this,SingleSupportFragmentActivity.class);
		intent.putExtra(KEY_FRAGMENT_CLASS,unitClass);
		intent.putExtras(getBundleFromDUnitModel(unitModel));
		startActivity(intent);
	}

	private void doFragmentUnit(Class<?> unitClass, DUnitBaseModel unitModel, View v) {
		Intent intent = new Intent(this,SingleFragmentActivity.class);
		intent.putExtra(KEY_FRAGMENT_CLASS,unitClass);
		intent.putExtras(getBundleFromDUnitModel(unitModel));
		startActivity(intent);
	}

	private void doActivityUnit(Class<?> unitClass, DUnitBaseModel unitModel, View v) {
		Intent intent = new Intent(this,unitClass);
		intent.putExtras(getBundleFromDUnitModel(unitModel));
		startActivity(intent);
	}

	private Bundle getBundleFromDUnitModel(DUnitBaseModel dUnitBaseModel){
		if (dUnitBaseModel instanceof DUnitModel){
			DUnitModel dUnitModel = (DUnitModel) dUnitBaseModel;
			Bundle bundle = mGson.fromJson(dUnitModel.getParamJson(),Bundle.class);
			return bundle;
		}else {
			return new Bundle();
		}
	}

	private void callDisplayUnit(final AbstractDisplayUnit displayUnit, DUnitModel unitModel) {
		switch (unitModel.getThreadMode()){
			case CURRENT_THREAD:
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
