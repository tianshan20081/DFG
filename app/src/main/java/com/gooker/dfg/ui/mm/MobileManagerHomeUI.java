/**
 * 
 */
package com.gooker.dfg.ui.mm;

import java.util.List;

import android.content.Context;
import android.os.Build;
import android.telephony.CellInfo;
import android.telephony.TelephonyManager;
import android.view.View;

import com.aoeng.degu.R;
import com.aoeng.degu.ui.BaseUI;
import com.aoeng.degu.utils.common.StringUtils;
import com.aoeng.degu.utils.common.UIUtils;

/**
 * @author sczhang 2014年12月17日 上午9:30:35
 */
public class MobileManagerHomeUI extends com.gooker.dfg.ui.BaseUI {

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnGetMobileNo:
			getMobileNo();
			break;

		default:
			break;
		}
	}

	/**
	 * 
	 */
	private void getMobileNo() {
		// TODO Auto-generated method stub
		TelephonyManager tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
		String mobileNo = tm.getLine1Number();
		if (!com.gooker.dfg.utils.common.StringUtils.isEmpty(mobileNo)) {
			com.gooker.dfg.utils.common.UIUtils.getToastSafe("mobile No is " + mobileNo);
		} else {
			com.gooker.dfg.utils.common.UIUtils.getToastSafe("load mobile No is null");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.degu.ui.BaseUI#loadViewLayout()
	 */
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
		findView(R.id.btnGetMobileNo).setOnClickListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.degu.ui.BaseUI#processLogic()
	 */
	@Override
	protected void processLogic() {
		// TODO Auto-generated method stub

	}

}
