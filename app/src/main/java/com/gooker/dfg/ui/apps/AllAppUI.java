/**
 * 
 */
package com.gooker.dfg.ui.apps;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
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
 * May 19, 2014 9:58:41 AM
 * 
 */
@SuppressLint("NewApi")
public class AllAppUI extends Activity {
	private static final String TAG = AllAppUI.class.getName();
	private ListView lvAllApps;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_apps_manager_all);
		lvAllApps = (ListView) this.findViewById(R.id.lvAllApps);

		PackageManager pm = getPackageManager();
		List<ApplicationInfo> infos = pm.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
		// GET_UNINSTALLED_PACKAGES代表已删除，但还有安装目录的

		List<PackageInfo> packageInfos = pm.getInstalledPackages(0);
		if (null == packageInfos || packageInfos.size() == 0) {
			return;
		}

		StringBuffer sb = new StringBuffer();
		StringBuffer sbUserApp = new StringBuffer("User Apps ");
		sbUserApp.append("\n");
		for (PackageInfo p : packageInfos) {
			if ((p.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
				// 非系统应用
				String name = p.applicationInfo.name;
				int versionCode = p.versionCode;
				String versionName = p.versionName;
				String label = (String) p.applicationInfo.loadLabel(pm);
				Drawable icon = p.applicationInfo.loadIcon(pm);
				String packageName = p.packageName;

				sbUserApp.append("name:").append(name).append("\n");
				sbUserApp.append("versionCode:").append(versionCode).append("\n");
				sbUserApp.append("versionName:").append(versionName).append("\n");
				sbUserApp.append("label:").append(label).append("\n");
			} else {
				// 系统应用　　　　　　　　
			}
		}

		if (null != infos && infos.size() > 0) {
			List<com.gooker.dfg.domain.AppInfo> appInfos = new ArrayList<com.gooker.dfg.domain.AppInfo>();
			for (ApplicationInfo info : infos) {
				com.gooker.dfg.domain.AppInfo appInfo = new com.gooker.dfg.domain.AppInfo();
				appInfo.setAppIcon(info.loadIcon(pm));
				appInfo.setAppLabel((String) info.loadLabel(pm));
				appInfo.setPkgName(info.packageName);
				appInfo.setName(info.name);
				appInfos.add(appInfo);
				com.gooker.dfg.utils.common.Logger.i(TAG, appInfo.toString());
			}
			lvAllApps.setAdapter(new AppInfoAdapter(this, appInfos));
		} else {
			return;
		}

	}

	private class AppInfoAdapter extends BaseAdapter {
		private Context context;
		private List<com.gooker.dfg.domain.AppInfo> infos;

		public AppInfoAdapter(Context context, List<com.gooker.dfg.domain.AppInfo> infos) {
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
			com.gooker.dfg.domain.AppInfo info = infos.get(position);
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
