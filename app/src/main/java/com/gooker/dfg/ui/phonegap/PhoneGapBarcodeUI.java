package com.gooker.dfg.ui.phonegap;

import android.os.Bundle;

import com.phonegap.DroidGap;

public class PhoneGapBarcodeUI extends DroidGap {
	@Override
	public void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		
		super.loadUrl("file:///android_asset/www/gpbarcode.html");
	}

}
