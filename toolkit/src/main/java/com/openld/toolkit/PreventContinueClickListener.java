package com.openld.toolkit;

import android.view.View;

/**
 * author: lllddd
 * created on: 2021/8/1 13:55
 * description:
 */
public abstract class PreventContinueClickListener implements View.OnClickListener {
    private long mLastTime = 0L;

    // 最小点击间隔时间
    private static final long DURATION = 1000L;

    /**
     * 安全点击
     *
     * @param v 视图
     */
    public void onSafeClick(View v) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - mLastTime > DURATION) {
            onClick(v);
        } else {
            LogUtils.e("", "禁止连续点击！！！");
        }
        mLastTime = currentTime;
    }
}
