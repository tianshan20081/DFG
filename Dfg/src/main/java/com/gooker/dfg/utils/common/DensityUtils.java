package com.gooker.dfg.utils.common;

import android.content.Context;
import android.content.res.Resources;


public class DensityUtils {

    /**
     * 根据手机的分辨率�?dp 的单�?转成�?px(像素)
     */
    public static int dip2px(float dpValue) {
        final float scale = UIUtils.getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率�?px(像素) 的单�?转成�?dp
     */
    public static int px2dip(float pxValue) {
        final float scale = UIUtils.getContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     * @date 2013年7月23日
     */
    public static int getWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕高度
     *
     * @param context
     * @return
     * @date 2013年7月23日
     */
    public static int getHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static float dp2px(Resources resources, float dp) {
        final float scale = resources.getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }

    public static float sp2px(Resources resources, float sp) {
        final float scale = resources.getDisplayMetrics().scaledDensity;
        return sp * scale;
    }
}