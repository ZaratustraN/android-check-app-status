package cn.zaratustra.check.api;

import android.app.Application;

import java.util.ArrayList;

import cn.zaratustra.check.engine.BaseCheckMethod;
import cn.zaratustra.check.engine.CheckMethodFactory;

/**
 * Created by zaratustra on 2017/9/17.
 */

public class ProcessStatusChecker {

    private static ProcessStatusChecker mSingleton;

    private boolean isInit;

    private ArrayList<BaseCheckMethod> checkMethods;

    private CheckMethodFactory.CheckMethod[] initCheckMethod = {CheckMethodFactory.CheckMethod.RUNNING_TASK};

    private ProcessStatusChecker() {
        isInit = false;
        checkMethods = new ArrayList<>();
    }

    public static ProcessStatusChecker getInstance() {
        if (mSingleton == null) {
            synchronized (ProcessStatusChecker.class) {
                if (mSingleton == null) {
                    mSingleton = new ProcessStatusChecker();
                }
            }
        }
        return mSingleton;
    }

    public synchronized void init(Application context) {
        if (!isInit && initCheckMethod != null) {
            for (int i = 0; i < initCheckMethod.length; i++) {
                BaseCheckMethod baseCheckMethod = CheckMethodFactory
                        .buildCheckMethod(context, initCheckMethod[i]);
                if (baseCheckMethod != null) {
                    checkMethods.add(baseCheckMethod);
                }
            }
        }
    }

    public boolean isRunning(String processName) {
        for (int i = 0; i < checkMethods.size(); i++) {
            if (checkMethods.get(i).isRunning(processName)) {
                return true;
            }
        }
        return false;
    }

    public boolean isForeground(String processName) {
        for (int i = 0; i < checkMethods.size(); i++) {
            if (checkMethods.get(i).isForeground(processName)) {
                return true;
            }
        }
        return false;
    }

    public String getForegroundProcessName() {
        String foregroundProcessName = "";
        for (int i = 0; i < checkMethods.size(); i++) {
            String temp = checkMethods.get(i).getForegroundProcess();
            if (temp != null && !temp.isEmpty()) {
                foregroundProcessName = temp;
                break;
            }
        }
        return foregroundProcessName;
    }

}
