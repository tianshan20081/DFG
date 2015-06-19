package com.gooker.dfg.services;


import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;

import com.gooker.dfg.utils.common.Logger;

public class TIntentService extends IntentService {
    private static final String TAG = TIntentService.class.getName();

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
