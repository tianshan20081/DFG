package com.gooker.dfg.receiver;

import android.app.AlertDialog;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.view.Gravity;
import android.widget.Toast;

import com.gooker.dfg.utils.common.Toaster;

import java.lang.reflect.Method;


public class InCallReceiver extends BroadcastReceiver {

	private static Object obj;

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);
		switch (tm.getCallState()) {
		case TelephonyManager.CALL_STATE_RINGING:// 响铃
			String telNo = intent.getStringExtra("incoming_number");
			showToast(context, "tel:" + telNo);
			Toaster.toast(context, telNo, true);
			Toaster.log(context.getPackageName(), telNo);
			new AlertDialog.Builder(context).setMessage(telNo).create().show();
			break;
		case TelephonyManager.CALL_STATE_IDLE:// 挂断
			closeToast();
			break;
		case TelephonyManager.CALL_STATE_OFFHOOK:// 接听电话
			Toaster.log(context.getPackageName(), "电话接听中");
			break;

		}
	}

	private void closeToast() {
		// TODO Auto-generated method stub
		if (null != obj) {
			try {
				Method method = obj.getClass().getDeclaredMethod("hide", null);
				method.invoke(obj, null);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}

	public static void showToast(Context context, String string) {
		// TODO Auto-generated method stub
		Toast toast = Toast.makeText(context, string, Toast.LENGTH_LONG);
		toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
		toast.show();
		// try {
		// Field field = toast.getClass().getDeclaredField("mTN");
		// field.setAccessible(true);
		// obj = field.get(toast);
		// Method method = obj.getClass().getDeclaredMethod("show", null);
		// method.invoke(obj, null);
		// } catch (Exception e) {
		//
		// }

	}

}
