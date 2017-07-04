package top.ftas.dunit.util;

import top.ftas.dunit.model.DUnitBaseModel;


/**
 * Created by tik on 17/7/3.
 */

public class TestWildcardType<T extends DUnitBaseModel>{
	public void test(T t){
		t.getGroup();
	}
}
