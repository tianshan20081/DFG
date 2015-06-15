/**
 * 
 */
package com.gooker.dfg.domain;

import android.content.Intent;
import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * May 19, 2014 10:10:08 AM
 * 
 */
public class AppInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6048549878338414744L;

	private String name;

	private String appLabel; // 应用程序标签
	private Drawable appIcon; // 应用程序图像
	private Intent intent; // 启动应用程序的Intent
							// ，一般是Action为Main和Category为Lancher的Activity
	private String pkgName; // 应用程序所对应的包名

	private long cachesize; // 缓存大小
	private long datasize; // 数据大小
	private long codesieze; // 应用程序大小

	public long getCachesize() {
		return cachesize;
	}

	public void setCachesize(long cachesize) {
		this.cachesize = cachesize;
	}

	public long getDatasize() {
		return datasize;
	}

	public void setDatasize(long datasize) {
		this.datasize = datasize;
	}

	public long getCodesieze() {
		return codesieze;
	}

	public void setCodesieze(long codesieze) {
		this.codesieze = codesieze;
	}

	public String getAppLabel() {
		return appLabel;
	}

	public void setAppLabel(String appName) {
		this.appLabel = appName;
	}

	public Drawable getAppIcon() {
		return appIcon;
	}

	public void setAppIcon(Drawable appIcon) {
		this.appIcon = appIcon;
	}

	public Intent getIntent() {
		return intent;
	}

	public void setIntent(Intent intent) {
		this.intent = intent;
	}

	public String getPkgName() {
		return pkgName;
	}

	public void setPkgName(String pkgName) {
		this.pkgName = pkgName;
	}

	@Override
	public String toString() {
		return "AppInfo [name=" + name + ", appLabel=" + appLabel + ", appIcon=" + appIcon + ", intent=" + intent + ", pkgName=" + pkgName + ", cachesize=" + cachesize + ", datasize=" + datasize
				+ ", codesieze=" + codesieze + "]\n";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
