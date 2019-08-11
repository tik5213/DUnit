package top.ftas.dunit.util;

import top.ftas.dunit.core.ResultMessageHelper;

/**
 * Created by tik on 2019-08-09.
 */
public class MessageUtil {
    static ThreadLocal<ResultMessageHelper> sHelperThreadLocal = new ThreadLocal<>();

    public static void setResultMessageHelper(ResultMessageHelper resultMessageHelper) {
        sHelperThreadLocal.set(resultMessageHelper);
    }

    public static ResultMessageHelper out() {
        return sHelperThreadLocal.get();
    }

}
