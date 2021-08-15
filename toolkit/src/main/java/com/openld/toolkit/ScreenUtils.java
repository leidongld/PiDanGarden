package com.openld.toolkit;

import android.content.Context;

import androidx.annotation.NonNull;

/**
 * author: lllddd
 * created on: 2021/8/15 11:00
 * description:屏幕尺寸工具类
 */
public class ScreenUtils {
    private static float sDensity;

    private ScreenUtils() {
    }

    /**
     * dp转px
     *
     * @return px尺寸
     */
    public static float dp2px(float dp) {
        return dp * sDensity + 0.5F;
    }

    /**
     * px转dp
     *
     * @return dp尺寸
     */
    public static float px2dp(@NonNull Context context, float px) {
        return px / sDensity + 0.5F;
    }

    public static void init(@NonNull Context context) {
        sDensity = context.getResources().getDisplayMetrics().density;
    }
}
