package top.ftas.dunit.sample.activity;

import android.app.Activity;

import top.ftas.dunit.annotation.DUnit;
import top.ftas.dunit.annotation.DUnitHidden;
import top.ftas.dunit.sample.AllGroup;
import top.ftas.dunit.util.DUnitConstant;

/**
 * Created by tik on 17/7/16.
 */

@DUnitHidden
@DUnit(unitType = DUnitConstant.UnitType.AUTO,group = AllGroup.AutoGroup.class)
public class BaseActivity extends Activity{

}
