package com.sdk.keepbackground.utils;

import android.util.Log;

/**
 * Log管理类
 */
public class LogUtil {
    private static final String TAG = "====log===>";
    public final static int LOG_LEVEL_DEBUG = 2;
    public final static int LOG_LEVEL_TEST = 1;
    public final static int LOG_LEVEL_RELEASE = 0;
    private static int level = LOG_LEVEL_DEBUG;

    /**
     * 默认level 为 LOG_LEVEL_RELEASE
     * 开发为 LOG_LEVEL_DEBUG，测试为 LOG_LEVEL_TEST，生产为：LOG_LEVEL_RELEASE
     * LOG_LEVEL_DEBUG的时候输出为log相同级别，比如log.e ,log.d
     * LOG_LEVEL_TEST的时候输出为均为log.i，区别显示
     * LOG_LEVEL_RELEASE,不打印日志
     */
    public static void setLevel(int level) {
        LogUtil.level = level;
        if (LogUtil.level > 0) {
            LogUtil.writes(TAG + "日志级别为：" + (LogUtil.level == 1 ? "测试" : "开发"));
        } else {
            LogUtil.writes(TAG + "日志级别为：正式");
        }
    }

    /**
     * 获得APP的日志级别
     *
     * @return
     */
    public static int getLevel() {
        return level;
    }

    public static void writes(String msg) {
        if (level <= LOG_LEVEL_RELEASE) {

        } else if (level == LOG_LEVEL_TEST) {
            write(msg);
        } else if (level == LOG_LEVEL_DEBUG) {
            write(msg);
        }
    }

    public static void write(String str) {
        if (null != str) {
            str = str.trim();
            int index = 0;
            int maxLength = 9999;
            String sub;
            while (index < str.length()) {
                // java的字符不允许指定超过总的长度end
                if (str.length() <= index + maxLength) {
                    //  str＝str.substring(int beginIndex);
                    // 截取掉str从首字母起长度为beginIndex的字符串，将剩余字符串赋值给str；
                    sub = str.substring(index);
                } else {
                    //str＝str.substring(int beginIndex，int endIndex);
                    // 截取str中从beginIndex开始至endIndex结束时的字符串，并将其赋值给str;
                    sub = str.substring(index, index + maxLength);
                }
                index += maxLength;
                if (level == LOG_LEVEL_RELEASE) {
                    return;
                }
                if (level == LOG_LEVEL_DEBUG) {
                    Log.e(TAG, sub.trim());
                    return;
                }
                if (level == LOG_LEVEL_TEST) {
                    Log.d(TAG, sub.trim());
                    return;
                }


            }
        } else {
            Log.e(TAG, "数据为null");
        }
    }
}



