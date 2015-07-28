package com.gooker.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.gooker.views.utils.LogUtils;

public class TouchActivity extends AppCompatActivity {

    private View rlHome;
    private View rlContent;
    private View btnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);

        LogUtils.e("onCreate");


        rlHome = findViewById(R.id.rlHome);
        rlContent = findViewById(R.id.rlContent);
        btnView = findViewById(R.id.btnView);


//        setViewListener();


    }

    private void setViewListener() {
        rlHome.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                LogUtils.e("rl home onTouch");
                return false;
            }
        });
        rlContent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                LogUtils.e("rlContent onTouch");
                return false;
            }
        });

        btnView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                LogUtils.e("btnView onTouch");
                return false;
            }
        });

        rlHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.e("rlHome onClick");
            }
        });

        rlContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.e("rlContent onClick");
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.e("btnView OnClick");
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.e("TouchActivity---dispatchTouchEvent--MotionEvent.ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.e("TouchActivity--dispatchTouchEvent---MotionEvent.ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.e("TouchActivity--dispatchTouchEvent---MotionEvent.ACTION_UP");
                break;

        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.e("TouchActivity---onTouchEvent--MotionEvent.ACTION_DOWN");

                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.e("TouchActivity---onTouchEvent--MotionEvent.ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.e("TouchActivity--onTouchEvent---MotionEvent.ACTION_UP");
                break;

        }
        return super.onTouchEvent(event);
    }






}
