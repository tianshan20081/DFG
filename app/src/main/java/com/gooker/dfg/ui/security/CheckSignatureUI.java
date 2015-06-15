/**
 * 
 */
package com.gooker.dfg.ui.security;


import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.aoeng.degu.R;
import com.aoeng.degu.ui.BaseUI;

/**
 * May 22, 2014 2:18:03 PM
 * 
 */
public class CheckSignatureUI extends com.gooker.dfg.ui.BaseUI {

	private TextView tvInfo;

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
		setContentView(R.layout.ui_security_signature);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.degu.ui.BaseUI#findViewById()
	 */
	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		tvInfo = (TextView) findView(R.id.tvInfo);
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
		int sig = getSignature("com.droider.checksignature");
		if (sig != 2071749217) {
			tvInfo.setTextColor(Color.RED);
			tvInfo.setText("检测到程序签名不一致，该程序被重新打包过！");
		} else {
			tvInfo.setTextColor(Color.GREEN);
			tvInfo.setText("该程序没有被重新打包过！");
		}
	}

	public int getSignature(String packageName) {
		PackageManager pm = this.getPackageManager();
		PackageInfo pi = null;
		int sig = 0;
		try {
			pi = pm.getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
			Signature[] s = pi.signatures;
			sig = s[0].hashCode();
		} catch (Exception e1) {
			sig = 0;
			e1.printStackTrace();
		}
		return sig;
	}
}
