package com.gooker.dfg.ui.phonegap;

import android.os.Bundle;

import com.phonegap.DroidGap;

public class PhoneGapCameraUI extends DroidGap {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        super.loadUrl("file:///android_asset/www/pgcamera.html");
    }

}
