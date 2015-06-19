package com.gooker.dfg.ui.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.View;

import com.gooker.dfg.R;


public class MoiveImageView extends View {

    Paint mPaint = null;
    Bitmap bitMap = null;
    Bitmap bitMapDisplay = null;
    int m_posX = 120;
    int m_posY = 50;
    int m_bitMapWidth = 0;
    int m_bitMapHeight = 0;
    Matrix mMatrix = null;
    float mAngle = 0.0f;
    float mScale = 1f;// 1为原图的大小

    public MoiveImageView(Context context) {
        super(context);
        mPaint = new Paint();
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        bitMap = BitmapFactory.decodeResource(this.getResources(), R.drawable.pic_moive);
        bitMapDisplay = bitMap;
        mMatrix = new Matrix();
        // 获取图片宽高
        m_bitMapWidth = bitMap.getWidth();
        m_bitMapHeight = bitMap.getHeight();
    }

    // 向左移动
    public void setPosLeft() {
        m_posX -= 10;
    }

    // 向右移动
    public void setPosRight() {
        m_posX += 10;
    }

    // 向左旋转
    public void setRotationLeft() {
        mAngle--;
        setAngle();
    }

    public void startPlay() {

    }

    // 向右旋转
    public void setRotationRight() {
        mAngle++;
        setAngle();
    }

    // 缩小图片
    public void setNarrow() {
        if (mScale > 0.5) {
            mScale -= 0.1;
            setScale();
        }
    }

    // 放大图片
    public void setEnlarge() {
        if (mScale < 2) {
            mScale += 0.1;
            setScale();
        }
    }

    // 设置缩放比例
    public void setAngle() {
        mMatrix.reset();
        mMatrix.setRotate(mAngle);
        bitMapDisplay = Bitmap.createBitmap(bitMap, 0, 0, m_bitMapWidth, m_bitMapHeight, mMatrix, true);
    }

    // 设置旋转比例
    public void setScale() {
        mMatrix.reset();
        // float sx X轴缩放
        // float sy Y轴缩放
        mMatrix.postScale(mScale, mScale);
        bitMapDisplay = Bitmap.createBitmap(bitMap, 0, 0, m_bitMapWidth, m_bitMapHeight, mMatrix, true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitMapDisplay, m_posX, m_posY, mPaint);
        invalidate();
    }

}
