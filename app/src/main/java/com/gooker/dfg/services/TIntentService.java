package com.gooker.dfg.services;

import com.aoeng.degu.utils.common.Logger;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;

public class TIntentService extends IntentService {
	private static final String TAG = com.aoeng.degu.services.TIntentService.class.getName();

	public TIntentService() {
		super("Test IntentService");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 10; i++) {
			Logger.i(TAG, Thread.currentThread().getName() + "-" + Thread.currentThread().getId());
			SystemClock.sleep(1000);
		}
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Logger.i(TAG, "onDestroy");
	}

}
