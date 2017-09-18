package cn.zaratustra.check.engine;

import android.content.Context;
import android.os.Build;

/**
 * Created by zaratustra on 2017/9/18.
 */

public class CheckMethodFactory {

    public enum CheckMethod {
        RUNNING_TASK, RUNNING_PROCESS
    }

    public static BaseCheckMethod buildCheckMethod(Context context, CheckMethod checkMethod) {
        BaseCheckMethod baseCheckMethod = null;
        switch (checkMethod) {
            case RUNNING_TASK:
                baseCheckMethod = new RunningTaskMethod(context);
                break;
            case RUNNING_PROCESS:
                baseCheckMethod = new RunningProcessMethod(context);
                break;
        }


        if (baseCheckMethod.getSupportMaxAPI() >= Build.VERSION.SDK_INT
                && Build.VERSION.SDK_INT >= baseCheckMethod.getSupportMinAPI()) {
            return baseCheckMethod;
        } else {
            return null;
        }
    }


}
