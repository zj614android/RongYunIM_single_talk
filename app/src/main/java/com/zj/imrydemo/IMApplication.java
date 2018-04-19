package com.zj.imrydemo;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import io.rong.imkit.RongIM;

/**
 * Created by thinkpad on 2018/4/17.
 */

public class IMApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext())) ||
                "io.rong.push".equals(getCurProcessName(getApplicationContext()))) {
            RongIM.init(this);

        }


    }

    /**
     * 获得当前进程的名字
     *
     * @param context
     * @return 进程号
     */
    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

}
