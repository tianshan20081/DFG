package com.gooker.views.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

import com.gooker.views.utils.LogUtils;

/**
 * Created by sczhang on 15/7/24. 下午2:58
 * Package Name com.gooker.views.views
 * Project Name DFG
 */
public class Btn extends Button {

    public Btn(Context context) {
        this(context, null);
    }

    public Btn(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Btn(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.e("Btn--dispatchTouchEvent---MotionEvent.ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.e("Btn--dispatchTouchEvent---MotionEvent.ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.e("Btn--dispatchTouchEvent---MotionEvent.ACTION_UP");
                break;

        }
//        LogUtils.e("Btn--dispatchTouchEvent---return true");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.e("Btn--onTouchEvent---MotionEvent.ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.e("Btn--onTouchEvent---MotionEvent.ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.e("Btn--onTouchEvent---MotionEvent.ACTION_UP");
                break;

        }
//        LogUtils.e("Btn--onTouchEvent---return true");
        return super.onTouchEvent(event);
    }
}
