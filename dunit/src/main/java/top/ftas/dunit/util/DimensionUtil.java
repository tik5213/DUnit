package top.ftas.dunit.util;

import android.content.Context;

/**
 * Created by tik on 17/6/28.
 * 获取dimens.xml中的数据
 */

public class DimensionUtil {
	public static int getDim(Context context,int id){
		return context.getResources().getDimensionPixelOffset(id);
	}
}
