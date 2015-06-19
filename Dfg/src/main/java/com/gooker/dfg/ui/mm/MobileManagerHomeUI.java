/**
 *
 */
package com.gooker.dfg.ui.mm;

import android.app.AlarmManager;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.view.View;

import com.gooker.dfg.R;
import com.gooker.dfg.utils.common.StringUtils;
import com.gooker.dfg.utils.common.UIUtils;

import java.util.TimeZone;


/**
 * @author sczhang 2014年12月17日 上午9:30:35
 */
public class MobileManagerHomeUI extends com.gooker.dfg.ui.BaseUI {


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGetMobileNo:
                getMobileNo();
                break;
            case R.id.btnSetTimeZone:
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.setTimeZone("Asia/Taipei");
                break;
            case R.id.btnGetTimeZone:
                TimeZone tz = TimeZone.getDefault();
                String s = "TimeZone   " + tz.getDisplayName(false, TimeZone.SHORT) + " Timezon id :: " + tz.getID();
                UIUtils.toastShow(s);
                break;
            case R.id.btnResetTimeZone:
                AlarmManager alarmManager2 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager2.setTimeZone("Asia/Shanghai");
                break;
            default:
                break;
        }
    }


    private void getMobileNo() {
        TelephonyManager tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        String mobileNo = tm.getLine1Number();
        if (!StringUtils.isEmpty(mobileNo)) {
            UIUtils.getToastSafe("mobile No is " + mobileNo);
        } else {
            UIUtils.getToastSafe("load mobile No is null");
        }
    }


    @Override
    protected void loadViewLayout() {
        // TODO Auto-generated method stub
        setContentView(R.layout.ui_mobile_manager_home);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.aoeng.degu.ui.BaseUI#findViewById()
     */
    @Override
    protected void findViewById() {

    }

    /*
     * (non-Javadoc)
     *
     * @see com.aoeng.degu.ui.BaseUI#setListener()
     */
    @Override
    protected void setListener() {
        findView(R.id.btnGetMobileNo).setOnClickListener(this);
        findView(R.id.btnSetTimeZone).setOnClickListener(this);
        findView(R.id.btnGetTimeZone).setOnClickListener(this);
        findView(R.id.btnResetTimeZone).setOnClickListener(this);
    }


    @Override
    protected void processLogic() {

    }

}
