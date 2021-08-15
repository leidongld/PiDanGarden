package com.openld.toolkit;

import android.util.Log;

/**
 * author: lllddd
 * created on: 2021/8/1 13:37
 * description:日志工具类
 */
public class LogUtils {
    // 是否打开日志的开关
    private static boolean mLogOpen = false;

    // 日志前缀
    private static final String LOG_PREFIX = ">>>>>>  ";

    // 日志后缀
    private static final String LOG_SUFFIX = "  <<<<<<";

    private LogUtils() {

    }

    /**
     * 是否打开日志开关
     *
     * @param flag 日志开关
     */
    public static void openLog(boolean flag) {
        mLogOpen = flag;
    }

    /**
     * 打印debug日志
     *
     * @param tag    标签
     * @param detail 细节
     */
    public static void d(String tag, String detail) {
        if (mLogOpen) {
            Log.d(tag, LOG_PREFIX + detail + LOG_SUFFIX);
        }
    }

    /**
     * 打印error日志
     *
     * @param tag    标签
     * @param detail 细节
     */
    public static void e(String tag, String detail) {
        if (mLogOpen) {
            Log.e(tag, LOG_PREFIX + detail + LOG_SUFFIX);
        }
    }

    /**
     * 打印info日志
     *
     * @param tag    标签
     * @param detail 细节
     */
    public static void i(String tag, String detail) {
        if (mLogOpen) {
            Log.i(tag, LOG_PREFIX + detail + LOG_SUFFIX);
        }
    }

    /**
     * 打印warn日志
     *
     * @param tag    标签
     * @param detail 细节
     */
    public static void w(String tag, String detail) {
        if (mLogOpen) {
            Log.w(tag, LOG_PREFIX + detail + LOG_SUFFIX);
        }
    }
}
