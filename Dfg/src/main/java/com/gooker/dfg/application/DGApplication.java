/**
 *
 */
package com.gooker.dfg.application;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Debug;
import android.os.IBinder;
import android.os.Looper;

import com.chronocloud.ryfibluetoothlibrary.BluetoothOpration;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.gooker.dfg.utils.AppUtils;
import com.gooker.dfg.utils.ThreadPoolManager;
import com.gooker.dfg.utils.common.DensityUtils;
import com.gooker.dfg.utils.common.FileUtils;
import com.gooker.dfg.utils.common.LogUtils;
import com.gooker.dfg.utils.common.Logger;
import com.gooker.dfg.utils.common.StringUtils;
import com.gooker.dfg.utils.common.SystemUtils;
import com.gooker.dfg.utils.qiniu.QNApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * May 21, 2014 4:54:26 PM
 */
public class DGApplication extends Application {
    private static DGApplication mApplication;
    /**
     * 缓存路径
     */
    private static String cacheDir;
    private static Looper mMainThreadLooper;
    private static int mMainThreadId;
    private List<Activity> records = new ArrayList<Activity>();
    public static BluetoothOpration _BluetoothOpration;
    private static ThreadPoolManager mThreadPoolManager;
    private static Thread mMainThread;
    private static final String TAG = "HGApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        // 全局创建一次
        _BluetoothOpration = new BluetoothOpration(this);
        this.mApplication = this;
        this.mMainThreadLooper = getMainThreadLooper();
        this.mMainThreadId = android.os.Process.myPid();
        this.mMainThread = Thread.currentThread();
        mThreadPoolManager = ThreadPoolManager.getInstance();
        initJpush();
        // CrashHandler mCrashHandler =
        // CrashHandler.getInstance();
        // mCrashHandler.init();
        // Intent intent = new
        // Intent(UIUtils.getContext(),
        // LogFileUploadServices.class);
        // Intent intent = new
        // Intent(UIUtils.getContext(),
        // MulityLogFileUploadServices.class);
        // Intent intent = new
        // Intent(UIUtils.getContext(),
        // QiNiuFileUploadService.class);
        // getContext().startService(intent);
        printCommonInfo();
        Debug.startMethodTracing("filename");
        Debug.stopMethodTracing();

        Fresco.initialize(this);
    }

    private void initJpush() {
        JPushInterface.init(this);
        JPushInterface.setDebugMode(true);

        String rid = JPushInterface.getRegistrationID(this);
        if (!StringUtils.isEmpty(rid)) {
            LogUtils.e(TAG + "---reginationId : " + rid);
        }
        JPushInterface.setAlias(getContext(), "admin", new TagAliasCallback() {
            @Override
            public void gotResult(int arg0, String arg1, Set<String> arg2) {
                // TODO Auto-generated
                // method stub
                LogUtils.i(arg1);
            }
        });
    }

    private void printCommonInfo() {
        // TODO Auto-generated method
        // stub
        LogUtils.e("---AppKey----" + AppUtils.getAppKey(this.mApplication));
        LogUtils.i("FileUtils.getAppRootPath() --" + FileUtils.getAppRootPath());
        LogUtils.e("QNApi.getUpToken(QNApi.BUCKET_ANDROIDPLAY)" + QNApi.getUpToken(QNApi.BUCKET_ANDROIDPLAY));
        logCommonInfo();
    }

    private void logCommonInfo() {
        // TODO Auto-generated method stub
        LogUtils.i("SystemUtils.getWidthDpi()" + SystemUtils.getWidthDpi());
        LogUtils.i("SystemUtils.getHeightDpi()" + SystemUtils.getHeightDpi());
        LogUtils.i("SystemUtils.getScreenHeight()---px" + SystemUtils.getScreenHeight());
        LogUtils.i("SystemUtils.getScreenHeight()---dip" + DensityUtils.px2dip(SystemUtils.getScreenHeight()));
        LogUtils.i("SystemUtils.getScreenWidth()---px" + SystemUtils.getScreenWidth());
        LogUtils.i("SystemUtils.getScreenWidth()----dip" + DensityUtils.px2dip(SystemUtils.getScreenWidth()));
        LogUtils.i("SystemUtils.getScreenDensity()" + SystemUtils.getScreenDensity());
        LogUtils.i("SystemUtils.getOneAppMaxMemory()" + SystemUtils.getOneAppMaxMemory());
    }

    public static String getCacheDirPath() {
        return cacheDir;
    }

    private class ECServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Logger.d(TAG, "onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    }

    public void addActvity(Activity activity) {
        records.add(activity);
        Logger.d(TAG, "Current Acitvity Size :" + getCurrentActivitySize());
    }

    public void removeActvity(Activity activity) {
        records.remove(activity);
        Logger.d(TAG, "Current Acitvity Size :" + getCurrentActivitySize());
    }

    public void exit() {
        for (Activity activity : records) {
            activity.finish();
        }
    }

    public int getCurrentActivitySize() {
        return records.size();
    }

    public static Context getContext() {
        return mApplication;
    }

    public static Looper getMainThreadLooper() {
        // stub
        return mMainThreadLooper;
    }

    public static int getMainThreadId() {
        // stub
        return mMainThreadId;
    }

    public static Thread getMainThread() {
        return mMainThread;
    }

    public static ThreadPoolManager getThreadPoolManager() {
        // stub
        return mThreadPoolManager;
    }
}
