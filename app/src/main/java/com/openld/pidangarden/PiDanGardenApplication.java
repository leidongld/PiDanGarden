package com.openld.pidangarden;

import android.app.Application;

import com.openld.toolkit.LogUtils;
import com.openld.toolkit.ScreenUtils;

/**
 * author: lllddd
 * created on: 2021/8/1 13:48
 * description:
 */
public class PiDanGardenApplication extends Application {
    private static final String TAG = "PiDanGardenApplication";

    @Override
    public void onCreate() {
        super.onCreate();

        // 打开日志开关
        LogUtils.openLog(true);

        ScreenUtils.init(getApplicationContext());
    }
}
