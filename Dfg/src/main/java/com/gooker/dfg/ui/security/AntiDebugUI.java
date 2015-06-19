/**
 *
 */
package com.gooker.dfg.ui.security;

import android.content.pm.ApplicationInfo;
import android.view.View;

import com.gooker.dfg.R;
import com.gooker.dfg.ui.BaseUI;
import com.gooker.dfg.utils.common.Logger;
import com.gooker.dfg.utils.common.Toaster;


/**
 * May 22, 2014 1:48:42 PM
 */
public class AntiDebugUI extends BaseUI {

    /*
     * (non-Javadoc)
     *
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     *
     * @see com.aoeng.degu.ui.BaseUI#loadViewLayout()
     */
    @Override
    protected void loadViewLayout() {
        // TODO Auto-generated method stub
        setContentView(R.layout.ui_security_antidebug);

        // 设置应用程序 是否为 调试模式
        // android:debuggable="false"
    }

    /*
     * (non-Javadoc)
     *
     * @see com.aoeng.degu.ui.BaseUI#findViewById()
     */
    @Override
    protected void findViewById() {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     *
     * @see com.aoeng.degu.ui.BaseUI#setListener()
     */
    @Override
    protected void setListener() {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     *
     * @see com.aoeng.degu.ui.BaseUI#processLogic()
     */
    @Override
    protected void processLogic() {
        // TODO Auto-generated method stub
        if ((getApplicationInfo().flags &= ApplicationInfo.FLAG_DEBUGGABLE) != 0) {
            Logger.e("com.droider.antidebug", "程序被修改为可调试状态");
            Toaster.toast(context, "程序被修改为可调试状态");
            android.os.Process.killProcess(android.os.Process.myPid());
        }
        if (android.os.Debug.isDebuggerConnected()) { // 检测调试器
            Logger.e("com.droider.antidebug", "检测到测试器");
            Toaster.toast(context, "检测到测试器");
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }

}
