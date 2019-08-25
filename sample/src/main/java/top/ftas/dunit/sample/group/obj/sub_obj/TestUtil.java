package top.ftas.dunit.sample.group.obj.sub_obj;

import top.ftas.dunit.annotation.DUnit;
import top.ftas.dunit.core.AbstractDisplayUnit;

/**
 * Created by tik on 2019-08-26.
 */
@DUnit(group = SubObjectGroup.class)
public class TestUtil extends AbstractDisplayUnit {
    @Override
    public void callUnit() {
        mMessageHelper.appendLine("我是 SubObjectGroup 的测试 Unit");
    }
}
