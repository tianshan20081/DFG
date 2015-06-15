/**
 * 
 */
package com.gooker.dfg.ui.internet;

import android.content.Intent;
import android.view.View;

import com.aoeng.degu.R;
import com.aoeng.degu.ui.BaseUI;
import com.aoeng.degu.ui.internet.DomParserUI;
import com.aoeng.degu.ui.internet.QnBreakPointUploadUI;
import com.aoeng.degu.ui.internet.QnSingleUploadUI;
import com.aoeng.degu.ui.internet.download.MulityDownloadUI;
import com.aoeng.degu.ui.internet.download.NormalDownloadUI;
import com.aoeng.degu.utils.common.UIUtils;

/**
 * Jun 10, 2014 10:48:16 AM
 * 
 */
public class InternetHomeUI extends BaseUI {

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnMulityDownload:
			UIUtils.startActivity(MulityDownloadUI.class);
			break;
		case R.id.btnNormalDownload:
			UIUtils.startActivity(NormalDownloadUI.class);
			break;
		case R.id.btnDomParser:
			startActivity(new Intent(this, DomParserUI.class));
			break;
		case R.id.btnQnSingleUpload:
			startActivity(new Intent(this, QnSingleUploadUI.class));
			break;
		case R.id.btnQnBreakpointsUpload:
			startActivity(new Intent(this, QnBreakPointUploadUI.class));
			break;
		default:
			break;
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
		setContentView(R.layout.ui_internet_home);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.degu.ui.BaseUI#findViewById()
	 */
	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		this.findView(R.id.btnDomParser).setOnClickListener(this);
		this.findView(R.id.btnNormalDownload).setOnClickListener(this);
		this.findView(R.id.btnMulityDownload).setOnClickListener(this);
		this.findView(R.id.btnQnSingleUpload).setOnClickListener(this);
		this.findView(R.id.btnQnBreakpointsUpload).setOnClickListener(this);
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

	}

}
