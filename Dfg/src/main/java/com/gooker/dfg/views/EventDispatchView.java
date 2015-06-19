package com.gooker.dfg.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * @author Shaocheng Zhang Aug 11, 2014 1:21:39 PM 2014
 *         <p/>
 *         thanks Android事件分发机制完全解析，带你从源码的角度彻底理解(下)
 *         http://blog.csdn.net/guolin_blog/article/details/9153747
 */
public class EventDispatchView extends LinearLayout {

    public EventDispatchView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public EventDispatchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        // 如果 返回值为 false 表示 EventDispatchView 没有消费掉本次的点击事件，点击事件将会继续往下 执行
        // 如果 返回值为 false 表示 EventDispatchView 消费掉本次的点击事件，点击事件将不会继续往下 执行，其它子 View
        // 的点击事件 将不会继续执行
        return true;
    }
}
