package com.gooker.dfg.ui.eventdispatch;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;

import com.gooker.dfg.R;
import com.gooker.dfg.ui.BaseUI;
import com.gooker.dfg.utils.common.Logger;
import com.gooker.dfg.utils.common.UIUtils;


/**
 * SimpleEventDisptch
 *
 * @author Shaocheng Zhang Aug 11, 2014 11:11:12 AM 2014
 *         <p/>
 *         thanks bolg :
 *         http://blog.csdn.net/guolin_blog/article/details/9097463
 */
public class SimpleEventDispatchUI extends BaseUI implements OnTouchListener {

    private Button btnEventDispatch;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btnEventDispatch:
                Logger.i(TAG, "onTouch------>" + event.getAction());
                UIUtils.getToastSafe("onTouch------>" + event.getAction());
                break;

        }
        // 当返回值为 false 的时候 将不会执行 onclick() 方法 此处 该次点击 将会被消费掉
        return true;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btnEventDispatch:
                Logger.i(TAG, "OnClick()");
                UIUtils.getToastSafe("OnClick()");
                break;

        }

    }

    @Override
    protected void loadViewLayout() {
        // TODO Auto-generated method stub
        setContentView(R.layout.ui_eventdispatch_simple);
    }

    @Override
    protected void findViewById() {
        // TODO Auto-generated method stub
        btnEventDispatch = (Button) findView(R.id.btnEventDispatch);
    }

    @Override
    protected void setListener() {
        // TODO Auto-generated method stub

        btnEventDispatch.setOnClickListener(this);
        btnEventDispatch.setOnTouchListener(this);
    }

    @Override
    protected void processLogic() {
        // TODO Auto-generated method stub

    }

}
