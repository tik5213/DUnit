package top.ftas.dunit.util;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by tik on 17/7/25.
 */

public class TypefaceUtil {
	private static Typeface iconTypeFace;

	public static Typeface getIconTypeFace(Context context) {
		if (iconTypeFace == null){
			iconTypeFace = Typeface.createFromAsset(context.getAssets(),"fonts/iconfont.ttf");
		}
		return iconTypeFace;
	}
	public static Typeface getIconTypeFace() {
		if (iconTypeFace == null){
			throw new RuntimeException("使用了还未初始化的字体");
		}
		return iconTypeFace;
	}
}
