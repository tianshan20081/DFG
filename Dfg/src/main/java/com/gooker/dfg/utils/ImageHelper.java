package com.gooker.dfg.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

public class ImageHelper {

    public static Bitmap changeBitmap(Bitmap bm, float hue, float rScale, float gScale, float bScale, float sat) {
        // TODO Auto-generated method
        // stub

        Bitmap bp = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(bp);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setRotate(0, hue);
        colorMatrix.setRotate(1, hue);
        colorMatrix.setRotate(2, hue);

        ColorMatrix roMatrix = new ColorMatrix();
        roMatrix.setSaturation(sat);

        ColorMatrix matrix = new ColorMatrix();
        matrix.setScale(rScale, gScale, bScale, 1);

        ColorMatrix matrix2 = new ColorMatrix();
        matrix2.postConcat(matrix);
        matrix2.postConcat(roMatrix);
        matrix2.postConcat(colorMatrix);

        paint.setColorFilter(new ColorMatrixColorFilter(matrix2));
        canvas.drawBitmap(bm, 0, 0, paint);
        return bp;
    }
}
