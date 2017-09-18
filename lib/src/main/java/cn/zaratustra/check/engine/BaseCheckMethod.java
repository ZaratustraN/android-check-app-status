package cn.zaratustra.check.engine;

import android.content.Context;

/**
 * Created by zaratustra on 2017/9/17.
 */

public abstract class BaseCheckMethod {

    protected Context context;

    private int supportMinAPI = 0;
    private int supportMaxAPI = Integer.MAX_VALUE;

    public BaseCheckMethod(Context context, int supportMinAPI, int supportMaxAPI) {
        this.context = context;
        this.supportMinAPI = supportMinAPI;
        this.supportMaxAPI = supportMaxAPI;
    }

    public abstract boolean isRunning(String processName);

    public abstract boolean isForeground(String processName);

    public abstract String getForegroundProcess();

    public int getSupportMinAPI() {
        return supportMinAPI;
    }

    public int getSupportMaxAPI() {
        return supportMaxAPI;
    }

    public void unitTest() {

    }

}
