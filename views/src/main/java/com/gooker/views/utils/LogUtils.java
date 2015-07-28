package com.gooker.views.utils;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by sczhang on 15/7/24. 下午2:47
 * Package Name com.gooker.views.utils
 * Project Name DFG
 */
public class LogUtils {
    public static String customTagPrefix = "";

    public static void e(String content) {
        StackTraceElement caller = Thread.currentThread().getStackTrace()[4];
        String tag = generateTag(caller);
        Log.e("tag", "\n" + content);
    }

    private static String generateTag(StackTraceElement caller) {
        String tag = "%s.%s(L:%d)";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        tag = String.format(tag, new Object[]{callerClazzName, caller.getMethodName(), Integer.valueOf(caller.getLineNumber())});
        tag = TextUtils.isEmpty(customTagPrefix) ? tag : customTagPrefix + ":" + tag;
        return tag;
    }
}
