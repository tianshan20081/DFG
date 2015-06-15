package com.gooker.dfg.ui.services;

import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;

import com.aoeng.degu.R;
import com.aoeng.degu.services.BaseServices;
import com.aoeng.degu.services.BindServices;
import com.aoeng.degu.services.PersonService;
import com.aoeng.degu.services.TIntentService;
import com.aoeng.degu.utils.common.Logger;
import com.aoeng.degu.utils.common.Toaster;
import com.aoeng.degu.utils.common.UIUtils;

public class ServiceUI extends Activity implements OnClickListener {
	protected static final String TAG = com.aoeng.degu.ui.services.ServiceUI.class.getName();
	private Intent intent;
	private BindServices bindServices;
	private ServiceConnection serviceConnection;

	private IPerson person;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_service_home);

		this.findViewById(R.id.btnStartService).setOnClickListener(this);
		this.findViewById(R.id.btnStopService).setOnClickListener(this);
		this.findViewById(R.id.btnBindService).setOnClickListener(this);
		this.findViewById(R.id.btnUnBindService).setOnClickListener(this);
		this.findViewById(R.id.btnServices).setOnClickListener(this);
		this.findViewById(R.id.btnClearServices).setOnClickListener(this);
		this.findViewById(R.id.btnIntentService).setOnClickListener(this);
		this.findViewById(R.id.btnBindsService).setOnClickListener(this);
		this.findViewById(R.id.btnUnBindsService).setOnClickListener(this);
		this.findViewById(R.id.btnCallBindService).setOnClickListener(this);

		serviceConnection = new ServiceConnection() {

			@Override
			public void onServiceDisconnected(ComponentName name) {
				// TODO Auto-generated method stub
				bindServices = null;
				Toaster.toastCenter(com.aoeng.degu.ui.services.ServiceUI.this, "Services Faild", false);
			}

			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				// TODO Auto-generated method stub
				bindServices = ((BindServices.MyBinder) service).getServices();
				Toaster.toastCenter(com.aoeng.degu.ui.services.ServiceUI.this, "Services Connection !", false);
			}
		};
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnCallBindService:
			try {
				String per = person.getPerson();
				UIUtils.getToastSafe(per);
				Logger.i(TAG, per);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.btnUnBindsService:
			UIUtils.getContext().unbindService(conn);
			break;
		case R.id.btnBindsService:
			Intent bindIntent = new Intent(UIUtils.getContext(), PersonService.class);
			UIUtils.getContext().bindService(bindIntent, conn, Service.BIND_AUTO_CREATE);
			UIUtils.getToastSafe("bindIntent");
			break;
		case R.id.btnIntentService:
			Intent serviceIntent = new Intent(UIUtils.getContext(), TIntentService.class);
			UIUtils.getContext().startService(serviceIntent);
			break;
		case R.id.btnServices:
			ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
			List<RunningServiceInfo> serviceInfos = activityManager.getRunningServices(100);
			String str = "";
			for (RunningServiceInfo runningServiceInfo : serviceInfos) {
				str += runningServiceInfo.service.getClassName() + "\n";
			}
			Toaster.log("Running Services:", str);
			Toaster.toast(this, str, true);
			Logger.i(TAG, str);
			break;
		case R.id.btnClearServices:

			break;
		case R.id.btnBindService:
			intent = new Intent(this, BindServices.class);
			bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
			break;
		case R.id.btnUnBindService:
			unbindService(serviceConnection);
			break;

		case R.id.btnStartService:
			intent = new Intent(this, BaseServices.class);
			startService(intent);
			break;
		case R.id.btnStopService:
			stopService(intent);
			break;

		default:
			break;
		}
	}

	private ServiceConnection conn = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			UIUtils.getToastSafe("ServiceConnection ---------onServiceDisconnected");
			Logger.i(TAG, "ServiceConnection ---------onServiceDisconnected");
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			UIUtils.getToastSafe("ServiceConnection ---------onServiceConnected");
			Logger.i(TAG, "ServiceConnection ---------onServiceConnected");
			person = IPerson.Stub.asInterface(service);
			try {
				person.setName("Aoeng");
				person.setSex("Girl");
				person.setAge(20);

			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	};

}
