/**
 * 
 */
package com.gooker.dfg.ui.apps;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.aoeng.degu.R;
import com.aoeng.degu.domain.AppInfo;
import com.aoeng.degu.utils.common.Logger;

/**
 * May 19, 2014 6:53:24 PM
 * 
 */
public class AppSizeUI extends Activity implements OnItemClickListener {
	private static final String TAG = com.aoeng.degu.ui.apps.AppSizeUI.class.getName();
	private ListView listview;
	private ArrayList<AppInfo> mlistAppInfo;
	private long cachesize;
	private long datasize;
	private long codesize;
	private long totalsize;
	private LayoutInflater infater;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_apps_manager_all_size);
		listview = (ListView) findViewById(R.id.lvAppSize);
		mlistAppInfo = new ArrayList<AppInfo>();
		queryAppInfo(); // 查询所有应用程序信息
		BrowseApplicationInfoAdapter browseAppAdapter = new BrowseApplicationInfoAdapter(this, mlistAppInfo);
		listview.setAdapter(browseAppAdapter);
		listview.setOnItemClickListener(this);
	}

	// 点击弹出对话框，显示该包得大小
	public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
		// 更新显示当前包得大小信息
		try {
			queryPacakgeSize(mlistAppInfo.get(position).getPkgName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		infater = (LayoutInflater) com.aoeng.degu.ui.apps.AppSizeUI.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View dialog = infater.inflate(R.layout.ui_app_manager_all_size_dialog, null);
		TextView tvcachesize = (TextView) dialog.findViewById(R.id.tvcachesize); // 缓存大小
		TextView tvdatasize = (TextView) dialog.findViewById(R.id.tvdatasize); // 数据大小
		TextView tvcodesize = (TextView) dialog.findViewById(R.id.tvcodesize); // 应用程序大小
		TextView tvtotalsize = (TextView) dialog.findViewById(R.id.tvtotalsize); // 总大小
		// 类型转换并赋值
		tvcachesize.setText(formateFileSize(cachesize));
		tvdatasize.setText(formateFileSize(datasize));
		tvcodesize.setText(formateFileSize(codesize));
		tvtotalsize.setText(formateFileSize(totalsize));
		// 显示自定义对话框
		AlertDialog.Builder builder = new AlertDialog.Builder(com.aoeng.degu.ui.apps.AppSizeUI.this);
		builder.setView(dialog);
		builder.setTitle(mlistAppInfo.get(position).getAppLabel() + "的大小信息为：");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel(); // 取消显示对话框
			}

		});
		builder.create().show();
	}

	public void queryPacakgeSize(String pkgName) throws Exception {
		if (pkgName != null) {
			// 使用放射机制得到PackageManager类的隐藏函数getPackageSizeInfo
			PackageManager pm = getPackageManager(); // 得到pm对象
			try {
				// 通过反射机制获得该隐藏函数
				// 这种是类静态函数，要用getDeclaredMethod而不是getMethod
				// 成员函数才能用getMethod
				Method getPackageSizeInfo = pm.getClass().getMethod("getPackageSizeInfo", String.class, IPackageStatsObserver.class);
				// getPackageSizeInfo.setAccessible(true);
				// 调用该函数，并且给其分配参数 ，待调用流程完成后会回调PkgSizeObserver类的函数
				getPackageSizeInfo.invoke(pm, pkgName, new PkgSizeObserver());
			} catch (Exception ex) {
				Logger.e(TAG, "NoSuchMethodException");
				ex.printStackTrace();
				throw ex; // 抛出异常
			}
		}
	}

	// aidl文件形成的Bindler机制服务类
	public class PkgSizeObserver extends IPackageStatsObserver.Stub {

		/***
		 * 回调函数，
		 * 
		 * @param pStatus
		 *            ,返回数据封装在PackageStats对象中
		 * @param succeeded
		 *            代表回调成功
		 */
		public void onGetStatsCompleted(PackageStats pStats, boolean succeeded) throws RemoteException {
			// TODO Auto-generated method stub
			cachesize = pStats.cacheSize; // 缓存大小
			datasize = pStats.dataSize; // 数据大小
			codesize = pStats.codeSize; // 应用程序大小
			totalsize = cachesize + datasize + codesize;
			Logger.i(TAG, "cachesize--->" + cachesize + " datasize---->" + datasize + " codeSize---->" + codesize);
		}
	}

	// 系统函数，字符串转换 long -String (kb)
	private String formateFileSize(long size) {
		return Formatter.formatFileSize(com.aoeng.degu.ui.apps.AppSizeUI.this, size);
	}

	// 获得所有启动Activity的信息，类似于Launch界面
	public void queryAppInfo() {
		PackageManager pm = this.getPackageManager(); // 获得PackageManager对象
		Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
		mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		// 通过查询，获得所有ResolveInfo对象.
		List<ResolveInfo> resolveInfos = pm.queryIntentActivities(mainIntent, 0);
		// 调用系统排序 ， 根据name排序
		// 该排序很重要，否则只能显示系统应用，而不能列出第三方应用程序
		Collections.sort(resolveInfos, new ResolveInfo.DisplayNameComparator(pm));
		if (mlistAppInfo != null) {
			mlistAppInfo.clear();
			for (ResolveInfo reInfo : resolveInfos) {
				String activityName = reInfo.activityInfo.name; // 获得该应用程序的启动Activity的name
				String pkgName = reInfo.activityInfo.packageName; // 获得应用程序的包名
				String appLabel = (String) reInfo.loadLabel(pm); // 获得应用程序的Label
				Drawable icon = reInfo.loadIcon(pm); // 获得应用程序图标
				// 为应用程序的启动Activity 准备Intent
				Intent launchIntent = new Intent();
				launchIntent.setComponent(new ComponentName(pkgName, activityName));
				// 创建一个AppInfo对象，并赋值
				AppInfo appInfo = new AppInfo();
				appInfo.setAppLabel(appLabel);
				appInfo.setPkgName(pkgName);
				appInfo.setAppIcon(icon);
				appInfo.setIntent(launchIntent);
				mlistAppInfo.add(appInfo); // 添加至列表中
			}
		}
	}

	// 自定义适配器类，提供给listView的自定义view
	public class BrowseApplicationInfoAdapter extends BaseAdapter {

		private List<AppInfo> mlistAppInfo = null;

		LayoutInflater infater = null;

		public BrowseApplicationInfoAdapter(Context context, List<AppInfo> apps) {
			infater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			mlistAppInfo = apps;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			System.out.println("size" + mlistAppInfo.size());
			return mlistAppInfo.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return mlistAppInfo.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertview, ViewGroup arg2) {
			System.out.println("getView at " + position);
			View view = null;
			ViewHolder holder = null;
			if (convertview == null || convertview.getTag() == null) {
				view = infater.inflate(R.layout.ui_app_manager_all_size_item, null);
				holder = new ViewHolder(view);
				view.setTag(holder);
			} else {
				view = convertview;
				holder = (ViewHolder) convertview.getTag();
			}
			AppInfo appInfo = (AppInfo) getItem(position);
			holder.appIcon.setImageDrawable(appInfo.getAppIcon());
			holder.tvAppLabel.setText(appInfo.getAppLabel());
			holder.tvPkgName.setText(appInfo.getPkgName());
			return view;
		}

		class ViewHolder {
			ImageView appIcon;
			TextView tvAppLabel;
			TextView tvPkgName;

			public ViewHolder(View view) {
				this.appIcon = (ImageView) view.findViewById(R.id.imgApp);
				this.tvAppLabel = (TextView) view.findViewById(R.id.tvAppLabel);
				this.tvPkgName = (TextView) view.findViewById(R.id.tvPkgName);
			}
		}
	}

}
