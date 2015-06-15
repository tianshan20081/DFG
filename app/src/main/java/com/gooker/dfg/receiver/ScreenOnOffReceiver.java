package com.gooker.dfg.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.aoeng.degu.receiver.InCallReceiver;
import com.aoeng.degu.utils.common.Toaster;

public class ScreenOnOffReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		if (Intent.ACTION_SCREEN_ON.equals(intent.getAction())) {
			// 屏幕亮
			InCallReceiver.showToast(context, "屏幕亮");
			Toaster.log(context.getPackageName(), "屏幕亮");
		} else if (Intent.ACTION_SCREEN_OFF.equals(intent.getAction())) {
			InCallReceiver.showToast(context, "屏幕暗");
			Toaster.log(context.getPackageName(), "屏幕暗");
		}
	}

}
