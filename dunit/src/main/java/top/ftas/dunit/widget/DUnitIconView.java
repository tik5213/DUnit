package top.ftas.dunit.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import top.ftas.dunit.util.TypefaceUtil;

/**
 * Created by tik on 17/7/25.
 */

public class DUnitIconView extends TextView{
	public DUnitIconView(Context context) {
		super(context);
		init();
	}

	private void init() {
		//设置字体图标
		this.setTypeface(TypefaceUtil.getIconTypeFace());
	}

	public DUnitIconView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public DUnitIconView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

}
