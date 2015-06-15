package com.gooker.dfg.ui.phonegap;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.aoeng.degu.R;
import com.aoeng.degu.ui.BaseUI;
import com.aoeng.degu.ui.phonegap.PhoneGapBarcodeUI;
import com.aoeng.degu.ui.phonegap.PhoneGapCameraUI;
import com.aoeng.degu.ui.phonegap.SimplePhoneGapUI;
import com.aoeng.degu.utils.common.UIUtils;

/**
 * 
 * @author Shaocheng Zhang Aug 15, 2014 3:40:49 PM 2014
 * 
 *         幫助文檔 https://github.com/apache/cordova-android 
 *         开发文档 http://docs.phonegap.com/en/edge/guide_platforms_index.md.html
 *         幫助博客 http://topmanopensource.iteye.com/blog/1536256
 *         			http://topmanopensource.iteye.com/category/223598
 */
public class PhoneGapHomeUI extends BaseUI {

	private Button btnSimple;
	private Button btnCamera;
	private Button btnBarcode;

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnSimple:
			UIUtils.startActivity(new Intent(UIUtils.getContext(), SimplePhoneGapUI.class));
			break;
		case R.id.btnCamera:
			UIUtils.startActivity(new Intent(UIUtils.getContext(), PhoneGapCameraUI.class));
			break;
		case R.id.btnBarcode:
			UIUtils.startActivity(new Intent(UIUtils.getContext(), PhoneGapBarcodeUI.class));
			break;
		default:
			break;
		}
	}

	@Override
	protected void loadViewLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.ui_phonegap_home);
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		btnSimple = (Button) findView(R.id.btnSimple);
		btnCamera = (Button) findView(R.id.btnCamera);
		btnBarcode = (Button) findView(R.id.btnBarcode);
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		btnSimple.setOnClickListener(this);
		btnCamera.setOnClickListener(this);
		btnBarcode.setOnClickListener(this);
	}

	@Override
	protected void processLogic() {
		// TODO Auto-generated method stub

	}

}
