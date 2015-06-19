package com.gooker.dfg.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class OutCallReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        String phoneNo = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
        InCallReceiver.showToast(context, phoneNo);
    }

}
