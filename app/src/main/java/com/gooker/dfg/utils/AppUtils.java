package com.gooker.dfg.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Bundle;

import com.aoeng.degu.utils.common.LogUtils;
import com.aoeng.degu.utils.common.Logger;
import com.aoeng.degu.utils.common.UIUtils;

public class AppUtils {

	/**
	 * 在AndroidManifest.xml中，<meta-data>元素可以作为子元素，被包含在<activity>、<application>
	 * 、<service>和<receiver>元素中，但 不同的父元素，在应用时读取的方法也不同。
	 * 
	 * 1 ：在Activity的应用。 xml代码段： <activity...> <meta-data android:name="myMsg"
	 * android:value="hello my activity"></meta-data> </activity>
	 * 
	 * java代码段： ActivityInfo info=this.getPackageManager()
	 * .getActivityInfo(getComponentName(), PackageManager.GET_META_DATA);
	 * String msg=info.metaData.getString("myMsg");
	 * System.out.println("myMsg:"+msg);
	 * 
	 * 2：在application的应用。 xml代码段： <application...> <meta-data
	 * android:value="hello my application" android:name="myMsg"></meta-data>
	 * </application>
	 * 
	 * java代码段： ApplicationInfo appInfo = this.getPackageManager()
	 * .getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
	 * String msg=appInfo.metaData.getString("myMsg");
	 * System.out.println("myMsg:"+msg);
	 * 
	 * 3：在service的应用。 xml代码段： <service android:name="MetaDataService">
	 * <meta-data android:value="hello my service"
	 * android:name="myMsg"></meta-data> </service>
	 * 
	 * java代码段： ComponentName cn=new ComponentName(this, MetaDataService.class);
	 * ServiceInfo info=this.getPackageManager() .getServiceInfo(cn,
	 * PackageManager.GET_META_DATA); String
	 * msg=info.metaData.getString("myMsg"); System.out.println("myMsg:"+msg);
	 * 
	 * 4: 在receiver的应用。 xml代码段: <receiver android:name="MetaDataReceiver">
	 * <meta-data android:value="hello my receiver"
	 * android:name="myMsg"></meta-data> <intent-filter> <action
	 * android:name="android.intent.action.PHONE_STATE"></action>
	 * </intent-filter> </receiver> java代码段： ComponentName cn=new
	 * ComponentName(context, MetaDataReceiver.class); ActivityInfo
	 * info=context.getPackageManager() .getReceiverInfo(cn,
	 * PackageManager.GET_META_DATA); String
	 * msg=info.metaData.getString("myMsg"); System.out.println("myMsg:"+msg);
	 */

	private static final String KEY_APP_KEY = "USER_PRIVATE_KEY";

	// 取得AppKey
	public static String getAppKey(Context context) {
		Bundle metaData = null;
		String appKey = null;
		try {
			ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
			if (null != ai)
				metaData = ai.metaData;
			if (null != metaData) {
				appKey = metaData.getString(KEY_APP_KEY);
			}
		} catch (NameNotFoundException e) {

		}
		return appKey;
	}

	public static String getAppName() {
		// TODO Auto-generated method stub
		ApplicationInfo info = UIUtils.getContext().getApplicationContext().getApplicationInfo();
		String pache = info.packageName;
		String name = info.name;
		CharSequence label = info.loadLabel(UIUtils.getContext().getPackageManager());
		LogUtils.i(pache + " 	" + name);
		return name;
	}

	public static String getDeviceName() {

		return Build.DEVICE;
	}

	public static String getDeviceInfo() {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		for (Entry<String, String> entry : getDeviceMap().entrySet()) {
			sb.append(entry.getKey()).append("=").append(entry.getValue()).append("\n");
		}
		return sb.toString();
	}

	public static Map<String, String> getDeviceMap() {

		// TODO Auto-generated method stub
		PackageManager pm = UIUtils.getContext().getPackageManager();
		Map<String, String> map = new HashMap<String, String>();
		try {
			PackageInfo pi = pm.getPackageInfo(UIUtils.getContext().getPackageName(), PackageManager.GET_ACTIVITIES);
			if (null != pi) {
				String versionName = pi.versionName == null ? "null" : pi.versionName;
				String versionCode = pi.versionCode + "";
				map.put("versionName", versionName);
				map.put("versionCode", versionCode);
			}
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			LogUtils.e("get application info error ");
		}
		try {
			Field[] fields = Build.class.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				map.put(field.getName(), field.get(null).toString());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LogUtils.e("Reflect Build error");
		}
		return map;
	}
}
