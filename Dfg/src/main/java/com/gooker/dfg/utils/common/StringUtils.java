package com.gooker.dfg.utils.common;

import java.text.DecimalFormat;

import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;


public class StringUtils {
    /**
     * 判断字符串是否为空 为空的 标准 null ，“” ，“ ”，“null”，“NULL”
     *
     * @param path
     * @return
     */
    public static boolean isEmpty(String path) {
        // TODO Auto-generated method stub
        if (null != path && !"".equalsIgnoreCase(path.trim())
                && !"null".equalsIgnoreCase(path.trim())) {
            return false;
        }
        return true;
    }

    /**
     * 判斷字符串是否相等，其中有一個空字符串 或者 null 則返回 false
     *
     * @param args
     * @return
     */
    public static boolean isEquals(String... args) {
        String last = null;
        for (int i = 0; i < args.length; i++) {
            String str = args[i];
            if (isEmpty(str)) {
                return false;
            }
            if (last != null && !str.equalsIgnoreCase(last)) {
                return false;
            }
            last = str;
        }
        return true;
    }

    /**
     * 返回一个高亮的 spannable
     *
     * @param content 文本内容
     * @param color   高亮颜色
     * @param start   起始颜色
     * @param end     结束位置
     * @return 高亮 spannable
     */
    public static CharSequence getHighLightText(String content, int color,
                                                int start, int end) {
        if (TextUtils.isEmpty(content)) {
            return "";
        }
        start = start >= 0 ? start : 0;
        end = end <= content.length() ? end : content.length();
        SpannableString spannable = new SpannableString(content);
        CharacterStyle span = new ForegroundColorSpan(color);
        spannable.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannable;
    }

    /**
     * 获取链接样式的的字符串，即字符串下面有下划线
     *
     * @param resId 资源文件 （string） id
     * @return 返回链接样式的 字符串
     */
    public static Spanned getHtmlStyleString(int resId) {
        StringBuilder sb = new StringBuilder();
        sb.append("<a href=\"\"><u><b>").append(UIUtils.getString(resId));
        return Html.fromHtml(sb.toString());
    }

    /**
     * 格式化文件大小，不保留末尾的 0
     *
     * @param len
     * @return
     */
    public static String formatFileSize(long len) {
        return formatFileSize(len, false);
    }

    /**
     * 格式化文件大小，不保留末尾的 0 ，达到长度一致
     *
     * @param len
     * @param b
     * @return
     */
    private static String formatFileSize(long len, boolean keepZero) {
        // TODO Auto-generated method stub
        String size;
        DecimalFormat formatKeepTwoZero = new DecimalFormat("#.00");
        DecimalFormat formatKeepOneZero = new DecimalFormat("#.0");
        if (len < 1024) {
            size = String.valueOf(len + "B");
        } else if (len < 10 * 1024) {
            size = String.valueOf(len * 100 / 1024 / (float) 100) + "KB";
        } else if (len < 100 * 1024) {
            size = String.valueOf(len * 100 / 1024 / (float) 10) + "KB";
        } else if (len < 1024 * 1024) {
            size = String.valueOf(len / 1024) + "KB";
        } else if (len < 10 * 1024 * 1024) {
            // [10MB, 100MB)，保留一位小数
            if (keepZero) {
                size = String.valueOf(formatKeepOneZero.format(len * 10 / 1024
                        / 1024 / (float) 10))
                        + "MB";
            } else {
                size = String.valueOf(len * 10 / 1024 / 1024 / (float) 10)
                        + "MB";
            }
        } else if (len < 1024 * 1024 * 1024) {
            // [100MB, 1GB)，个位四舍五入
            size = String.valueOf(len / 1024 / 1024) + "MB";
        } else {
            // [1GB, ...)，保留两位小数
            size = String.valueOf(len * 100 / 1024 / 1024 / 1024 / (float) 100)
                    + "GB";
        }
        return size;
    }
}
