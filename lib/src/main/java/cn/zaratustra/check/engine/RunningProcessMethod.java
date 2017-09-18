package cn.zaratustra.check.engine;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;

import java.util.List;

/**
 * Created by zaratustra on 2017/9/18.
 */

public class RunningProcessMethod extends BaseCheckMethod {


    public RunningProcessMethod(Context context) {
        super(context, Build.VERSION_CODES.GINGERBREAD, Integer.MAX_VALUE);
    }

    @Override
    public boolean isRunning(String processName) {
        if (processName == null || processName.isEmpty()) {
            return false;
        }

        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> list = am.getRunningAppProcesses();
        if (list != null) {
            for (ActivityManager.RunningAppProcessInfo info : list) {
                if (info != null && processName.equals(info.processName)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean isForeground(String processName) {
        if (processName == null || processName.isEmpty()) {
            return false;
        }

        String foregroundProcessName = getForegroundProcess();
        if (processName.equals(foregroundProcessName)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getForegroundProcess() {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> list = am.getRunningAppProcesses();
        if (list != null) {
            for (ActivityManager.RunningAppProcessInfo info : list) {
                if (info.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    return info.processName;
                }
            }
        }
        return "";
    }
}
