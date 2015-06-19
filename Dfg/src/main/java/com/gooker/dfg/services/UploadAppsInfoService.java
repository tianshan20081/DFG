package com.gooker.dfg.services;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import com.gooker.dfg.R;
import com.gooker.dfg.domain.AppInfo;
import com.gooker.dfg.utils.AppUtils;
import com.gooker.dfg.utils.common.FileUtils;
import com.gooker.dfg.utils.common.LogUtils;
import com.gooker.dfg.utils.common.StringUtils;
import com.gooker.dfg.utils.common.UIUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class UploadAppsInfoService extends Service {

    protected static final int APPS_SCAN_SUCCESS = 100;
    protected static final int APPS_SCAN_FAILURE = 101;
    protected static final int APPS_SCAN_EMPTY = 102;
    protected static final int APPS_SCAN_UPLOAD_FAILURE = 103;
    protected static final int APPS_SCAN_UPLOAD_SUCCESS = 104;
    private Handler handler = new Handler() {
        private String tempPath;

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case APPS_SCAN_SUCCESS:
                    UIUtils.getToastSafe(R.string.app_scan_success);
                    String infos = (String) msg.obj;
                    UIUtils.getToastSafe(R.string.app_scan_startupload);
                    tempPath = FileUtils.getTempleFilePath(infos);

                    startUploadInfos(tempPath);
                    break;
                case APPS_SCAN_FAILURE:
                    UIUtils.getToastSafe(R.string.app_scan_failure);
                    break;
                case APPS_SCAN_EMPTY:
                    UIUtils.getToastSafe(R.string.app_size_empty);
                    break;
                case APPS_SCAN_UPLOAD_FAILURE:
                    String error = (String) msg.obj;
                    UIUtils.getToastSafe(String.format(getString(R.string.app_upload_failure), error));
                    break;
                case APPS_SCAN_UPLOAD_SUCCESS:
                    String buket = (String) msg.obj;
                    UIUtils.getToastSafe(String.format(getString(R.string.app_upload_success), buket));
                    if (!StringUtils.isEmpty(tempPath)) {
                        if (FileUtils.deleteDir(tempPath)) {
                            UIUtils.getToastSafe(R.string.tempfile_del_success);
                            LogUtils.i(tempPath);
                            stopSelf();
                        }
                    }
                    break;
            }
        }

        ;

    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub

        new AsyncTask<Void, Void, List<AppInfo>>() {

            @Override
            protected List<AppInfo> doInBackground(Void... params) {
                // TODO Auto-generated method stub
                PackageManager pm = UploadAppsInfoService.this.getPackageManager(); // 获得PackageManager对象
                Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
                mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                // 通过查询，获得所有ResolveInfo对象.
                List<ResolveInfo> resolveInfos = pm.queryIntentActivities(mainIntent, 0);
                // 调用系统排序 ， 根据name排序
                // 该排序很重要，否则只能显示系统应用，而不能列出第三方应用程序
                Collections.sort(resolveInfos, new ResolveInfo.DisplayNameComparator(pm));
                List<AppInfo> mlistAppInfo = new ArrayList<AppInfo>();
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
                return mlistAppInfo;
            }

            @Override
            protected void onPostExecute(List<AppInfo> result) {
                // TODO Auto-generated method stub
                if (null != result) {
                    if (result.isEmpty()) {
                        handler.sendEmptyMessage(APPS_SCAN_EMPTY);
                    } else {
                        StringBuffer buffer = new StringBuffer();
                        buffer.append(AppUtils.getDeviceInfo()).append("\n");
                        for (AppInfo appInfo : result) {
                            buffer.append(appInfo.toString());
                            buffer.append("\n");
                        }
                        Message msg = handler.obtainMessage();
                        msg.what = APPS_SCAN_SUCCESS;
                        msg.obj = buffer.toString();
                        handler.sendMessage(msg);
                    }
                } else {
                    handler.sendEmptyMessage(APPS_SCAN_FAILURE);
                }

            }
        }.execute();

        return super.onStartCommand(intent, flags, startId);
    }

    protected void startUploadInfos(String tempPath) {
        // TODO Auto-generated method stub
        File tempFile = new File(tempPath);
//		IO.putFile(QNApi.getAuthorizer(QNApi.BUCKET_TSLOGS), tempFile.getName(), tempFile, new PutExtra(), new CallBack() {
//
//			@Override
//			public void onSuccess(UploadCallRet ret) {
//				// TODO Auto-generated method stub
//				Message msg = handler.obtainMessage();
//				msg.what = APPS_SCAN_UPLOAD_SUCCESS;
//				msg.obj = QNApi.BUCKET_TSLOGS;
//				handler.sendMessage(msg);
//				LogUtils.i(ret.toString());
//			}
//
//			@Override
//			public void onProcess(long current, long total) {
//				// TODO Auto-generated method stub
//				LogUtils.i("upload procress " + (current * 100 / total) + "%");
//			}
//
//			@Override
//			public void onFailure(CallRet ret) {
//				// TODO Auto-generated method stub
//				Message msg = handler.obtainMessage();
//				msg.what = APPS_SCAN_UPLOAD_FAILURE;
//				msg.obj = ret.toString();
//				handler.sendMessage(msg);
//				LogUtils.i(ret.toString());
//			}
//		});
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

}
