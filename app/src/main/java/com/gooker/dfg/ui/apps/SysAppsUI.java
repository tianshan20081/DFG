/**
 * 
 */
package com.gooker.dfg.ui.apps;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.aoeng.degu.R;
import com.aoeng.degu.domain.AppInfo;
import com.aoeng.degu.utils.common.Logger;

/**
 * May 19, 2014 1:35:23 PM
 * 
 */
public class SysAppsUI extends Activity {
	private static final String TAG = com.aoeng.degu.ui.apps.SysAppsUI.class.getName();
	private ListView lvSysApps;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_apps_manager_sys);

		lvSysApps = (ListView) this.findViewById(R.id.lvSysApps);

		PackageManager pm = this.getPackageManager();
		Intent intent = new Intent(Intent.ACTION_MAIN, null);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		// 通过查询，获得所有ResolveInfo对象.
		List<ResolveInfo> resolveInfos = pm.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
		if (null == resolveInfos || resolveInfos.size() == 0) {
			return;
		}
		List<AppInfo> appInfos = new ArrayList<AppInfo>();
		for (ResolveInfo reInfo : resolveInfos) {
			AppInfo info = new AppInfo();
			info.setName(reInfo.activityInfo.name); // 获得该应用程序的启动Activity的name
			info.setPkgName(reInfo.activityInfo.packageName); // 获得应用程序的包名
			info.setAppLabel((String) reInfo.loadLabel(pm)); // 获得应用程序的Label
			info.setAppIcon(reInfo.loadIcon(pm)); // 获得应用程序图标
			appInfos.add(info);
			Logger.i(TAG, info.toString());
		}
		lvSysApps.setAdapter(new AppInfoAdapter(this, appInfos));
	}

	@SuppressLint("NewApi")
	private class AppInfoAdapter extends BaseAdapter {
		private Context context;
		private List<AppInfo> infos;

		public AppInfoAdapter(Context context, List<AppInfo> infos) {
			super();
			this.context = context;
			this.infos = infos;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.widget.Adapter#getCount()
		 */
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return infos.size();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.widget.Adapter#getItem(int)
		 */
		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return infos.get(position);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.widget.Adapter#getItemId(int)
		 */
		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.widget.Adapter#getView(int, android.view.View,
		 * android.view.ViewGroup)
		 */
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder holder;
			if (null != convertView) {
				holder = (ViewHolder) convertView.getTag();
			} else {
				LayoutInflater inflater = LayoutInflater.from(context);
				convertView = inflater.inflate(R.layout.ui_apps_manager_all_item, null);
				holder = new ViewHolder();
				holder.imIcon = (ImageView) convertView.findViewById(R.id.imIcon);
				holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
				holder.tvPackageName = (TextView) convertView.findViewById(R.id.tvPackageName);
				holder.tvLable = (TextView) convertView.findViewById(R.id.tvLable);
				convertView.setTag(holder);
			}
			AppInfo info = infos.get(position);
			holder.tvName.setText(info.getName());
			holder.tvLable.setText(info.getAppLabel());
			holder.tvPackageName.setText(info.getPkgName());
			holder.imIcon.setBackground(info.getAppIcon());
			return convertView;
		}

	}

	private static class ViewHolder {
		TextView tvName;
		TextView tvPackageName;
		TextView tvLable;
		ImageView imIcon;
	}
}
