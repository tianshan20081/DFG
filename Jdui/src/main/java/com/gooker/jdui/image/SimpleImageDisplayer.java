package com.gooker.jdui.image;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;

/**
 * @author Tau.Chen 陈涛
 * @version V_1.0.0
 * @email tauchen1990@gmail.com,1076559197@qq.com
 * @date 2013年9月12日
 * @description
 */
public class SimpleImageDisplayer implements BitmapDisplayer {

    private int targetWidth;

    public SimpleImageDisplayer(int targetWidth) {
        this.targetWidth = targetWidth;
    }





    /**
     * Displays bitmap in {@link ImageAware}.
     * <b>NOTE:</b> This method is called on UI thread so it's strongly recommended not to do any heavy work in it.
     *
     * @param bitmap     Source bitmap
     * @param imageAware {@linkplain ImageAware Image aware view} to
     *                   display Bitmap
     * @param loadedFrom Source of loaded image
     */
    @Override
    public void display(Bitmap bitmap, ImageAware imageAware, LoadedFrom loadedFrom) {
        if (bitmap != null) {
            bitmap = ImageUtils.resizeImageByWidth(bitmap, targetWidth);
        }
        imageAware.setImageBitmap(bitmap);
    }
}
