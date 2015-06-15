package com.gooker.dfg.ui.phonegap;


import android.os.Bundle;

import com.phonegap.DroidGap;

/**
 * 
 * @author Shaocheng Zhang Aug 15, 2014 3:24:18 PM 2014
 * 
 *         https://github.com/apache/cordova-android 步驟 1.下載 PhoneGap http://phonegap.com/install/ 2.解壓壓縮包， 進入phonegap-2.9.1/lib/android/framework 執行 android update project -p . -t android-18 ant jar 3.得到 cordova-2.9.1.jar 拷貝至 android 工程 libs 下面 並添加至環境變量 4.考唄 cordova.js 至 assets/www/ 下面 將 config.xml 考唄至 res/xml/下面 5.在 assets/www 下面 新建 pgindex.html 6.SimplePhoneGapUI extends DroidGap onCreate(...){ super.loadUrl("file://android_asset/www/gpindex.html"); } 7.在 AndroidManifest.xml 中增加 <activity name="SimplePhoneGapUI" ...> 8.在 AndroidManifest.xml 中增加 <activity name="org.apache.cordova.DroidGap" ...> <intent-filter></intent-filter> </activity>
 * 
 */
/*
 * 入門教程 http://www.adobe.com/cn/devnet/html5/articles/getting-started-with-phonegap-in-eclipse-for-android.html
 */
public class SimplePhoneGapUI extends DroidGap {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		super.loadUrl("file:///android_asset/www/pgindex.html");
	}

}
