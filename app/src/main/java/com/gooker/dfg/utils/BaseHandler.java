/**
 * 
 */
package com.gooker.dfg.utils;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;

import com.gooker.dfg.R;
import com.gooker.dfg.services.DataCallback;
import com.gooker.dfg.ui.BaseUI;
import com.gooker.dfg.utils.common.Logger;
import com.gooker.dfg.utils.common.UIUtils;

/**
 * May 21, 2014 5:11:23 PM
 * 
 */
public class BaseHandler extends Handler {
	private static final String TAG = BaseHandler.class.getName();
	private DataCallback callBack;
	private RequestVO reqVo;
	private ProgressDialog progressDialog;

	public BaseHandler(DataCallback callBack, RequestVO reqVo) {
		// TODO Auto-generated constructor stub
		this.callBack = callBack;
		this.reqVo = reqVo;
	}

	public void handleMessage(Message msg) {
		closeProgressDialog();
		if (msg.what == Constant.SUCCESS) {
			if (msg.obj == null) {
				CommonUtil.showInfoDialog(UIUtils.getContext(), UIUtils.getContext().getString(R.string.net_error));
			} else {
				callBack.processData(msg.obj, true);
			}
		} else if (msg.what == Constant.NET_FAILED) {
			CommonUtil.showInfoDialog(UIUtils.getContext(), UIUtils.getContext().getString(R.string.net_error));
		} else if (msg.what == Constant.NET_ERROR) {
			CommonUtil.showInfoDialog(UIUtils.getContext(), UIUtils.getContext().getString(R.string.net_error));
		}

		Logger.d(TAG, "recordSize:" + BaseUI.record.size());
	}

	/**
	 * 显示提示框
	 */
	protected void showProgressDialog() {
		if ((!((Activity) UIUtils.getContext()).isFinishing()) && (this.progressDialog == null)) {
			this.progressDialog = new ProgressDialog(UIUtils.getContext());
		}
		this.progressDialog.setTitle("标题");
		this.progressDialog.setMessage("数据加载中...");
		this.progressDialog.show();
	}

	/**
	 * 关闭提示框
	 */
	protected void closeProgressDialog() {
		if (this.progressDialog != null)
			this.progressDialog.dismiss();
	}
}
