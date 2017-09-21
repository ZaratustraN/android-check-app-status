package cn.zaratustra.check.implement;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;

import java.util.List;

/**
 * Created by zaratustra on 2017/9/17.
 */

public class RunningTaskMethod extends BaseCheckMethod {

    public RunningTaskMethod(Context context) {
        super(context, Build.VERSION_CODES.GINGERBREAD, Build.VERSION_CODES.KITKAT);
    }

    @Override
    public boolean isRunning(String processName) {
        if (processName == null || processName.isEmpty()) {
            return false;
        }
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfo = am.getRunningTasks(Integer.MAX_VALUE);
        if (null != runningTaskInfo && runningTaskInfo.size() > 0) {
            for (int i = 0; i < runningTaskInfo.size(); i++) {
                if (runningTaskInfo.get(i).baseActivity != null &&
                        processName.equals(runningTaskInfo.get(i).baseActivity.getPackageName())) {
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
        String foregroundProcess = getForegroundProcess();
        if (foregroundProcess != null && !foregroundProcess.isEmpty()
                && foregroundProcess.equals(processName)) {
            return true;
        }
        return false;
    }

    @Override
    public String getForegroundProcess() {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfo = am.getRunningTasks(1);
        if (null != runningTaskInfo && runningTaskInfo.size() > 0) {
            ComponentName cn = runningTaskInfo.get(0).topActivity;
            if (cn != null) {
                return cn.getPackageName();
            }
        }
        return "";
    }
}
