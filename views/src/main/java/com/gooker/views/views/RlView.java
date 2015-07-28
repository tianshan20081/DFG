package com.gooker.views.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.gooker.views.utils.LogUtils;

/**
 * Created by sczhang on 15/7/24. 下午2:53
 * Package Name com.gooker.views.views
 * Project Name DFG
 */
public class RlView extends LinearLayout {
    public RlView(Context context) {
        this(context, null);
    }

    public RlView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RlView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }


    private void init() {

    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.e("RlView---dispatchTouchEvent--MotionEvent.ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.e("RlView--dispatchTouchEvent---MotionEvent.ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.e("RlView--dispatchTouchEvent---MotionEvent.ACTION_UP");
                break;

        }
//        LogUtils.e("RlView--dispatchTouchEvent---return true");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.e("RlView---onInterceptTouchEvent--MotionEvent.ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.e("RlView--onInterceptTouchEvent---MotionEvent.ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.e("RlView--onInterceptTouchEvent---MotionEvent.ACTION_UP");
                break;

        }
//        LogUtils.e("RlView--onInterceptTouchEvent---return true");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.e("RlView--onTouchEvent---MotionEvent.ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.e("RlView--onTouchEvent---MotionEvent.ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.e("RlView--onTouchEvent---MotionEvent.ACTION_UP");
                break;

        }
        LogUtils.e("RlView--onTouchEvent---return true");
        return true;
    }
}
