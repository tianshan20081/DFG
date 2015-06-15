package com.gooker.dfg.services;

import com.aoeng.degu.ui.services.PersonImpl;
import com.aoeng.degu.utils.common.Logger;
import com.aoeng.degu.utils.common.UIUtils;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class PersonService extends Service {
	private static final String TAG = com.aoeng.degu.services.PersonService.class.getName();
	private PersonImpl personImpl;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		UIUtils.getToastSafe("PersonService-------onCreate");
		Logger.i(TAG, "PersonService-------onCreate");
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		UIUtils.getToastSafe("PersonService-------onBind");
		Logger.i(TAG, "PersonService-------onBind");
		personImpl = new PersonImpl();
		return personImpl;
	}

	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		UIUtils.getToastSafe("PersonService-------onUnbind");
		Logger.i(TAG, "PersonService-------onUnbind");
		return super.onUnbind(intent);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		UIUtils.getToastSafe("PersonService-------onDestroy");
		Logger.i(TAG, "PersonService-------onDestroy");
	}

	@Override
	@Deprecated
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		UIUtils.getToastSafe("PersonService-------onStart");
		Logger.i(TAG, "PersonService-------onStart");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		UIUtils.getToastSafe("PersonService-------onStartCommand");
		Logger.i(TAG, "PersonService-------onStartCommand");
		return super.onStartCommand(intent, flags, startId);
	}

}
