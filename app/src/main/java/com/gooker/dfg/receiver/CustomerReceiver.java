package com.gooker.dfg.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.aoeng.degu.utils.common.Toaster;

public class CustomerReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		if ("com.aoeng.degu.receiver.SEND_BROADCAST_RECEIVER".equals(intent.getAction())) {
			// 获得广播数据
			String info = intent.getStringExtra("data");
			Toaster.toast(context, "获取到广播信息" + info, false);
		}
	}

}
