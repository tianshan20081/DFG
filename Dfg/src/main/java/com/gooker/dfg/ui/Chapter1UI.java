package com.gooker.dfg.ui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

import com.gooker.dfg.R;
import com.gooker.dfg.views.CircleCanvasView;

import java.util.Random;

import cn.jpush.android.api.JPushInterface;


public class Chapter1UI extends Activity {
    private com.gooker.dfg.views.CircleCanvasView mCircleCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        //装载布局
        ViewGroup viewGroup = (ViewGroup) getLayoutInflater().inflate(R.layout.ui_chap1, null);
        mCircleCanvas = new com.gooker.dfg.views.CircleCanvasView(this);
        viewGroup.addView(mCircleCanvas, new LayoutParams(LayoutParams.FILL_PARENT, 700));
        setContentView(viewGroup);
        //setContentView(R.layout.ui_chap1);
    }

    // 开始随机绘制圆形
    public void onClick_DrawRandomCircle(View v) {
        Random random = new Random();
        float randomX = (float) (random.nextInt(450));
        float randomY = (float) (100 + random.nextInt(700));
        float randomRadius = (float) (20 + random.nextInt(40));
        int randomColor = 0;
        // 产生 0-100 的随机数，若产生的随机数大于 50 ，则画笔颜色为蓝色
        if (random.nextInt(100) > 50) {
            randomColor = Color.BLUE;
        } else {
            if (random.nextInt(100) > 50) randomColor = Color.RED;
            else
                randomColor = Color.GREEN;
        }
        CircleCanvasView.CircleInfo circleInfo = new CircleCanvasView.CircleInfo();
        circleInfo.setX(randomX);
        circleInfo.setY(randomY);
        circleInfo.setRadius(randomRadius);
        circleInfo.setColor(randomColor);
        mCircleCanvas.mCircleInfos.add(circleInfo);
        mCircleCanvas.invalidate();
    }

    public void onClick_Clear(View v) {
        mCircleCanvas.mCircleInfos.clear();
        mCircleCanvas.invalidate();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        JPushInterface.onPause(this);
    }
}
