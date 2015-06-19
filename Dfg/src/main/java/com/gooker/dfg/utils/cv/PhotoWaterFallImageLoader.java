/**
 *
 */
package com.gooker.dfg.utils.cv;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;

/**
 * @author [ShaoCheng Zhang] Sep 8, 2013:12:41:55 PM
 * @Email [zhangshch2008@gmail.com]
 */
public class PhotoWaterFallImageLoader {

    private int lruMaxMemory;
    private static PhotoWaterFallImageLoader imageLoader;
    private LruCache<String, Bitmap> lruCache;

    private PhotoWaterFallImageLoader() {
        long systemMemorySize = Runtime.getRuntime().maxMemory();
        this.lruMaxMemory = (int) (systemMemorySize / 10);

        lruCache = new LruCache<String, Bitmap>(lruMaxMemory) {
            /*
             * (non-Javadoc)
             *
             * @see android.support.v4.util.LruCache#sizeOf(java.lang.Object,
             * java.lang.Object)
             */
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // TODO Auto-generated method stub
                return bitmap.getRowBytes() * bitmap.getHeight();
            }
        };
    }

    public static PhotoWaterFallImageLoader instancePhotoWaterFallImageLoader() {
        if (imageLoader == null) {
            return new PhotoWaterFallImageLoader();
        } else {
            return imageLoader;
        }
    }

    public void addBitmap2MemoryCache(String key, Bitmap bitmap) {
        if (lruCache.get(key) == null) {
            lruCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromCache(String key) {
        return lruCache.get(key);
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth) {
        int inSampleSize = 1;
        int width = options.outWidth;
        if (width > reqWidth) {
            int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = widthRatio;
        }
        return inSampleSize;
    }

    public static Bitmap decodeSampleBitmapFromResource(String pathName, int reqWidth) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathName, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(pathName, options);
    }

}
