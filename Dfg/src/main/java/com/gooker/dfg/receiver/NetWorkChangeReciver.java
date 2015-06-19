package com.gooker.dfg.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.gooker.dfg.utils.common.StringUtils;
import com.gooker.dfg.utils.common.UIUtils;

public class NetWorkChangeReciver extends BroadcastReceiver {
    private static final String TAG = "NetWorkChangeReciver";

    public NetWorkChangeReciver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (null != intent) {
            String action = intent.getAction();
            if (!StringUtils.isEmpty(action)) {
                if (action.equalsIgnoreCase("android.net.conn.CONNECTIVITY_CHANGE")) {
                    ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                    Log.i(TAG, "onReceive(), context:" + context);
                    ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo[] info = manager.getAllNetworkInfo();

                    NetworkInfo wifiInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                    NetworkInfo activeInfo = manager.getActiveNetworkInfo();
                    if (activeInfo != null && wifiInfo.isConnected()) {
                        Log.e(TAG, "NetworkChangeReceiver  wifiInfo.isConnected()");
                        UIUtils.toastShow("NetworkChangeReceiver  wifiInfo.isConnected()");


                    }
                }
            }
        }

    }
}
