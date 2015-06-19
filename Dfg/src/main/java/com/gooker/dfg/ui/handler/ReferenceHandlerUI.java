package com.gooker.dfg.ui.handler;


import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import com.gooker.dfg.R;
import com.gooker.dfg.ui.BaseUI;
import com.gooker.dfg.utils.common.LogUtils;

import java.lang.ref.WeakReference;

public class ReferenceHandlerUI extends BaseUI {
    public static final int SEND_HELLO = 100;
    private Button btnSendInfo;
    private final MyHandler mHandler = new MyHandler(this);

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btnSendInfo:
                mHandler.obtainMessage(SEND_HELLO, "reference handler").sendToTarget();
                LogUtils.e(ReferenceHandlerUI.class.getName() + "send message");
                break;
            default:
                break;
        }
    }

    @Override
    protected void loadViewLayout() {
        // TODO Auto-generated method stub
        setContentView(R.layout.ui_handler_reference_handler);
    }

    @Override
    protected void findViewById() {
        // TODO Auto-generated method stub
        btnSendInfo = (Button) findView(R.id.btnSendInfo);
    }

    @Override
    protected void setListener() {
        // TODO Auto-generated method stub
        btnSendInfo.setOnClickListener(this);
    }

    @Override
    protected void processLogic() {
        // TODO Auto-generated method stub
    }

    private static class MyHandler extends Handler {
        private WeakReference<Activity> mActivity;

        public MyHandler(Activity activity) {
            // TODO Auto-generated constructor stub
            mActivity = new WeakReference<Activity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            Activity activity = mActivity.get();
            if (null != activity) {
                switch (msg.what) {
                    case SEND_HELLO:
                        String info = (String) msg.obj;
                        LogUtils.e(ReferenceHandlerUI.class.getName() + "------" + info);
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
