package com.gooker.dfg.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.aoeng.degu.utils.common.Toaster;

public class BaseServices extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Toaster.toastCenter(this, "onCreate()", false);
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);

		Toaster.toastCenter(this, "onStart()", false);
	}

	@Override
	public void onRebind(Intent intent) {
		// TODO Auto-generated method stub
		super.onRebind(intent);
		Toaster.toastCenter(this, "onRebind()", false);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Toaster.toastCenter(this, "onDestroy()", false);
	}

	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		Toaster.toastCenter(this, "onUnbind()", false);
		return super.onUnbind(intent);

	}

}
