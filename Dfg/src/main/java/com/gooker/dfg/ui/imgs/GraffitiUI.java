package com.gooker.dfg.ui.imgs;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

import com.gooker.dfg.R;
import com.gooker.dfg.ui.BaseUI;
import com.gooker.dfg.utils.common.FileUtils;
import com.gooker.dfg.utils.common.SystemUtils;
import com.gooker.dfg.utils.common.UIUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class GraffitiUI extends BaseUI {

    private ImageView ivGraffit;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    public int startY;
    public int startX;
    public int moveX;
    public int moveY;
    private Paint mPaint;

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method
        // stub
        switch (v.getId()) {
            case R.id.btnClear:
                if (null != mCanvas) {
                    mCanvas.drawColor(Color.WHITE);
                    ivGraffit.setImageBitmap(mBitmap);
                }
                break;
            case R.id.btnSave:

                // AT_MOST：我们可以指定一个上限，要保存的图片的大小不会超过它。
                // EXACTLY：我们指定了一个明确的大小，要求图片保存时满足这个条件。
                // UNSPECIFIED：图片多大，我们就保存多大。

                ivGraffit.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
                ivGraffit.layout(0, 0, ivGraffit.getMeasuredWidth(), ivGraffit.getMeasuredHeight());
                ivGraffit.buildDrawingCache();
                Bitmap drawingCache = ivGraffit.getDrawingCache();
                if (null == drawingCache) {
                    UIUtils.toastShow("null == drawingCache");
                    return;
                }
                // FileOutputStream fos =
                // new
                // FileOutputStream(file)
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                try {

                    drawingCache.compress(CompressFormat.JPEG, 100, baos);
                    baos.writeTo(new FileOutputStream(FileUtils.getImgFile()));
                } catch (Exception e) {
                    // TODO Auto-generated
                    // catch block
                    e.printStackTrace();
                } finally {

                    try {
                        baos.flush();
                        baos.close();
                    } catch (IOException e) {
                        // TODO
                        // Auto-generated
                        // catch block
                        e.printStackTrace();
                    }
                }
                break;

            default:
                break;
        }
    }

    @Override
    protected void loadViewLayout() {
        // TODO Auto-generated method
        // stub
        setContentView(R.layout.ui_imgs_graffit);
    }

    @Override
    protected void findViewById() {
        // TODO Auto-generated method
        // stub
        ivGraffit = (ImageView) findView(R.id.ivGraffit);

    }

    @Override
    protected void setListener() {
        // TODO Auto-generated method
        // stub
        findView(R.id.btnClear).setOnClickListener(this);
        findView(R.id.btnSave).setOnClickListener(this);

        ivGraffit.setOnTouchListener(new GraffitTouchListener());
    }

    @Override
    protected void processLogic() {
        // TODO Auto-generated method
        // stub
        mBitmap = Bitmap.createBitmap(SystemUtils.getScreenWidth(), SystemUtils.getScreenHeight(), Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mCanvas.drawColor(Color.WHITE);
        ivGraffit.setImageBitmap(mBitmap);
        mPaint = new Paint();
        mPaint.setColor(Color.GREEN);
        // 设置线宽
        mPaint.setStrokeWidth(15);
        mPaint.setAntiAlias(true);

    }

    class GraffitTouchListener implements OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            // TODO Auto-generated
            // method stub
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    startX = (int) event.getX();
                    startY = (int) event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    moveX = (int) event.getX();
                    moveY = (int) event.getY();

                    mCanvas.drawLine(startX, startY, moveX, moveY, mPaint);

                    ivGraffit.setImageBitmap(mBitmap);
                    startX = moveX;
                    startY = moveY;
                    break;
                case MotionEvent.ACTION_UP:

                    break;

                default:
                    break;
            }
            return true;
        }

    }

}
