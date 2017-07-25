package top.ftas.dunit.widget;

import android.view.View;
import android.widget.TextView;

import top.ftas.dunit.R;

/**
 * Created by tik on 17/7/25.
 */

public class DUnitSupportActionBar implements View.OnClickListener {
	private View mRootView;
	private OnSupportActionBarClickListener mOnSupportActionBarClickListener;

	@Override
	public void onClick(View v) {
		if (mOnSupportActionBarClickListener == null) return;
		mOnSupportActionBarClickListener.onSupportActionBarClick(v);
	}

	public OnSupportActionBarClickListener getOnSupportActionBarClickListener() {
		return mOnSupportActionBarClickListener;
	}

	public void setOnSupportActionBarClickListener(OnSupportActionBarClickListener onSupportActionBarClickListener) {
		mOnSupportActionBarClickListener = onSupportActionBarClickListener;
	}

	public interface OnSupportActionBarClickListener{
		void onSupportActionBarClick(View view);
	}

	public DUnitSupportActionBar(View rootView) {
		mRootView = rootView;
	}

	public void setTitle(String title){
		TextView textView = (TextView) mRootView.findViewById(R.id.action_bar_title);
		if (textView == null) return;
		textView.setVisibility(View.VISIBLE);
		textView.setText(title);
	}

	public void setDisplayHomeAsUpEnabled(boolean enable) {
		View view = mRootView.findViewById(R.id.action_bar_home);
		if (enable){
			view.setVisibility(View.VISIBLE);
			view.setOnClickListener(this);
		}else {
			view.setVisibility(View.GONE);
		}
	}
}
