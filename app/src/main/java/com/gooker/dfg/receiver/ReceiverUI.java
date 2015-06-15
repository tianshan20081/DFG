package com.gooker.dfg.receiver;

import java.util.List;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.aoeng.degu.R;
import com.aoeng.degu.receiver.*;
import com.aoeng.degu.receiver.CustomerReceiver;
import com.aoeng.degu.utils.common.Toaster;

public class ReceiverUI extends Activity implements OnClickListener {

	private ShortMessageReceiver messageReceiver = null;
	private ScreenOnOffReceiver screenOnOffReceiver = null;
	private TextView tvBatteryInfo;
	private com.aoeng.degu.receiver.CustomerReceiver customerReceiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_recevice_sms);
		this.findViewById(R.id.btnSmsRegisterReceiver).setOnClickListener(this);
		this.findViewById(R.id.btnSmsUnRegisterReceiver).setOnClickListener(this);
		this.findViewById(R.id.btnScreenRegisterReceiver).setOnClickListener(this);
		this.findViewById(R.id.btnScreenUnRegisterReceiver).setOnClickListener(this);
		this.findViewById(R.id.btnRegBroad).setOnClickListener(this);
		this.findViewById(R.id.btnSendBroadcast).setOnClickListener(this);
		this.findViewById(R.id.btnAllBoadcast).setOnClickListener(this);
		this.findViewById(R.id.btnClearBroadcast).setOnClickListener(this);

		tvBatteryInfo = (TextView) this.findViewById(R.id.tvBatteryInfo);

		messageReceiver = new ShortMessageReceiver();
		screenOnOffReceiver = new ScreenOnOffReceiver();
		customerReceiver = new CustomerReceiver();

		BatteryReceiver batteryReceiver = new BatteryReceiver();
		registerReceiver(batteryReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = null;
		IntentFilter filter = null;
		switch (v.getId()) {
		case R.id.btnAllBoadcast:
			// 获取到所有的已经注册的广播接收器
			PackageManager packageManager = getPackageManager();
			intent = new Intent("com.aoeng.degu.receiver.SEND_BROADCAST_RECEIVER");
			// 查询指定广播
			List<ResolveInfo> resolveInfos = packageManager.queryBroadcastReceivers(intent, PackageManager.GET_INTENT_FILTERS);
			Toaster.toast(this, "查询到" + resolveInfos.size() + "个广播接收器", false);
			String s = "";
			for (ResolveInfo resolveInfo : resolveInfos) {
				s += String.valueOf(resolveInfo.toString()) + "\n\n";
			}
			Toaster.toast(this, "广播接收器为：" + s, false);
			break;
		case R.id.btnClearBroadcast:
			// 清空所有的广播接收器
			break;
		case R.id.btnSendBroadcast:
			// 发送广播
			intent = new Intent("com.aoeng.degu.receiver.SEND_BROADCAST_RECEIVER");
			intent.addCategory("com.aoeng.degu.receiver.myCategory");
			intent.putExtra("data", "com.aoeng.degu.receiver");
			sendBroadcast(intent);
			Toaster.toastCenter(this, "broadCast Send success", true);
			break;
		case R.id.btnRegBroad:
			// 注册广播接收者
			filter = new IntentFilter("com.aoeng.degu.receiver.SEND_BROADCAST_RECEIVER");
			filter.addCategory("com.aoeng.degu.receiver.myCategory");
			registerReceiver(customerReceiver, filter);
			Toaster.toast(this, "广播接收器注册成功", false);
			break;
		case R.id.btnScreenRegisterReceiver:
			filter = new IntentFilter();

			filter.addAction(Intent.ACTION_SCREEN_ON);
			filter.addAction(Intent.ACTION_SCREEN_OFF);
			registerReceiver(screenOnOffReceiver, filter);
			break;
		case R.id.btnScreenUnRegisterReceiver:
			if (screenOnOffReceiver != null) {
				unregisterReceiver(screenOnOffReceiver);
			}
			break;
		case R.id.btnSmsRegisterReceiver:
			filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
			filter.setPriority(Integer.MAX_VALUE);
			registerReceiver(messageReceiver, filter);
			Toaster.toast(this, "短信监听广播注册成功", true);
			break;
		case R.id.btnSmsUnRegisterReceiver:
			if (null != messageReceiver) {
				unregisterReceiver(messageReceiver);
				Toaster.toast(this, "短信监听广播取消注册成功", true);
			}
			break;
		}
	}

	private class BatteryReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			if (intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
				// 当前电量值
				int level = intent.getIntExtra("level", 0);
				// 电量的总刻度值
				int scal = intent.getIntExtra("scal", 100);
				tvBatteryInfo.setText("当前电量为:" + level * 1.00 / scal * 100 + "%");
			}
		}

	}
}
