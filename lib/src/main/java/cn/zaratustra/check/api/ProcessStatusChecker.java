package cn.zaratustra.check.api;

import android.content.Context;

import java.util.ArrayList;

import cn.zaratustra.check.engine.BaseCheckMethod;
import cn.zaratustra.check.engine.CheckMethodFactory;

/**
 * Created by rosejames on 2017/9/17.
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

    public synchronized void init(Context context) {
        if (!isInit) {
            if (initCheckMethod != null) {
                for (int i = 0; i < initCheckMethod.length; i++) {

                }
            }
        }
    }
}
