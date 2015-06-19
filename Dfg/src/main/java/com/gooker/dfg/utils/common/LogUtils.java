package com.gooker.dfg.utils.common;

import java.util.List;

import android.text.TextUtils;
import android.util.Log;


/**
 * 日誌輸出控制類
 *
 * @author Shaocheng Zhang Aug 1, 2014 2:15:36 PM 2014
 */
public class LogUtils {
    /**
     * 日誌輸出級別 NONE
     */
    public static final int LEVEL_NONE = 0;
    /**
     * 日誌輸出級別 VERBOSE
     */
    public static final int LEVEL_VERBOSE = 1;
    /**
     * 日誌輸出級別 DEBUG
     */
    public static final int LEVEL_DEBUG = 2;
    /**
     * 日誌輸出級別 INFO
     */
    public static final int LEVEL_INFO = 3;
    /**
     * 日誌輸出級別 WARN
     */
    public static final int LEVEL_WARN = 4;
    /**
     * 日誌輸出級別 ERROR
     */
    public static final int LEVEL_ERROR = 5;

    public static String mTag = "DefinitiveGuide";
    /**
     * 允許日誌輸出級別 0 ：全部輸出，大於6 全部不輸出
     */
    private static int mDebugable = 6;
    /**
     * 用於計時的變量
     */
    private static long mTimestamp = 0;
    /**
     * 寫文件的 鎖對象
     */
    private static final Object mLogLock = new Object();

    /**
     * 以級別爲 v 的形式輸出 LOG
     *
     * @param msg
     */
    public static void v(String msg) {
        if (mDebugable >= LEVEL_VERBOSE) {
            Log.v(mTag, msg);
        }
    }

    /**
     * 以級別爲 d 的形式輸出 LOG
     *
     * @param msg
     */
    public static void d(String msg) {
        if (mDebugable >= LEVEL_DEBUG) {
            Log.d(mTag, msg);
        }
    }

    /**
     * 以級別爲 i 的形式輸出 LOG
     *
     * @param msg
     */
    public static void i(String msg) {
        if (mDebugable >= LEVEL_INFO) {
            Log.i(mTag, msg);
        }
    }

    /**
     * 以級別爲 w 的形式輸出 LOG
     *
     * @param msg
     */
    public static void w(String msg) {
        if (mDebugable >= LEVEL_WARN) {
            Log.w(mTag, msg);
        }
    }

    /**
     * 以級別爲 w 的形式輸出 LOG
     *
     * @param msg
     */
    public static void w(Throwable tr) {
        if (mDebugable >= LEVEL_WARN) {
            Log.w(mTag, tr);
        }
    }

    /**
     * 以級別爲 e 的形式輸出 LOG
     *
     * @param msg
     */
    public static void e(String msg) {
        if (mDebugable >= LEVEL_ERROR) {
            Log.e(mTag, msg);
        }
    }

    /**
     * 以級別爲 e 的形式輸出 LOG
     *
     * @param msg
     */
    public static void e(Throwable tr) {
        // TODO Auto-generated method stub
        if (mDebugable >= LEVEL_ERROR) {
            Log.e(mTag, "", tr);
        }
    }

    /**
     * 以級別爲 e 的形式輸出 LOG
     *
     * @param msg
     */
    public static void e(String msg, Throwable tr) {
        // TODO Auto-generated method stub
        if (mDebugable >= LEVEL_ERROR && null != msg) {
            Log.e(mTag, msg, tr);
        }
    }

    /**
     * @param log  日志信息
     * @param path 日志保存路径
     */
    public static void log2File(String log, String path) {
        log2File(log, path, true);
    }

    /**
     * @param log    日志信息
     * @param path   日志保存路径
     * @param append 是否以追加的方式保存
     */
    private static void log2File(String log, String path, boolean append) {
        // TODO Auto-generated method stub
        synchronized (mLogLock) {
            FileUtils.write2File(log + "\r\n", path, append);
        }
    }

    /**
     * 以级别为 e 的形式输出 msg 信息，附带时间戳，用于输出一个时间段起始点
     *
     * @param msg 需要输出的信息
     */
    public static void msgStartTime(String msg) {
        mTimestamp = System.currentTimeMillis();
        if (!TextUtils.isEmpty(msg)) {
            e("[Started:" + mTimestamp + "]" + msg);
        }
    }

    /**
     * 以级别为 e 的形式输出 msg 信息 ，附带时间戳，用于输出一个时间 结束点
     *
     * @param msg
     */
    public static void elapsed(String msg) {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - mTimestamp;
        mTimestamp = currentTime;
        e("[Elapsed:" + elapsedTime + "]" + msg);
    }

    public static <T> void printList(List<T> list) {
        if (list == null || list.size() < 1) {
            return;
        }
        int size = list.size();
        i("------------begin-----------");
        for (int i = 0; i < size; i++) {
            i(i + ":" + list.get(i).toString());
        }
        i("-------------end------------");
    }

}
