/**
 *
 */
package com.gooker.dfg.services;


import android.os.Handler;
import android.os.Message;

import com.gooker.dfg.ui.BaseUI;
import com.gooker.dfg.utils.Constant;
import com.gooker.dfg.utils.RequestVO;
import com.gooker.dfg.utils.common.Logger;
import com.gooker.dfg.utils.common.UIUtils;
import com.gooker.dfg.utils.net.NetUtil;

/**
 * May 21, 2014 5:19:24 PM
 */
public class BaseTask implements Runnable {
    private RequestVO reqVo;
    private Handler handler;

    public BaseTask(RequestVO reqVo, Handler handler) {
        this.reqVo = reqVo;
        this.handler = handler;
    }

    @Override
    public void run() {
        Object obj = null;
        Message msg = Message.obtain();
        try {
            if (NetUtil.hasNetwork(UIUtils.getContext())) {
                obj = NetUtil.post(reqVo);
                if (null != obj) {
                    Logger.i(BaseUI.TAG, obj.toString());
                    if (obj instanceof NetUtil.Status
                            ) {
                        // Intent intent = new Intent(BaseUI.this,
                        // LoginUI.class);
                        // intent.putExtra("notlogin", "notlogin");
                        // startActivityForResult(intent, NOT_LOGIN);
                    } else {
                        msg.what = Constant.SUCCESS;
                        msg.obj = obj;
                        handler.sendMessage(msg);
                        BaseUI.record.remove(this);
                    }
                } else {
                    msg.what = Constant.NET_ERROR;
                    msg.obj = obj;
                    handler.sendMessage(msg);
                    BaseUI.record.remove(this);
                }
            } else {
                msg.what = Constant.NET_FAILED;
                msg.obj = obj;
                handler.sendMessage(msg);
                BaseUI.record.remove(this);
            }
        } catch (Exception e) {
            BaseUI.record.remove(this);
            throw new RuntimeException(e);
        }
    }

}