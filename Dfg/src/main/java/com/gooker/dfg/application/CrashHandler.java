package com.gooker.dfg.application;

import android.content.Intent;
import android.os.Looper;
import android.os.SystemClock;

import com.gooker.dfg.R;
import com.gooker.dfg.ui.BaseUI;
import com.gooker.dfg.ui.HomeUI;
import com.gooker.dfg.utils.AppUtils;
import com.gooker.dfg.utils.common.DateUtil;
import com.gooker.dfg.utils.common.FileUtils;
import com.gooker.dfg.utils.common.LogUtils;
import com.gooker.dfg.utils.common.Logger;
import com.gooker.dfg.utils.common.UIUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Date;


public class CrashHandler implements UncaughtExceptionHandler {
    private static final String TAG = CrashHandler.class.getName();
    private static CrashHandler mCrashHandler = new CrashHandler();
    private UncaughtExceptionHandler mDefaultUncaughtExceptionHandler;

    private CrashHandler() {
        // TODO Auto-generated constructor stub
    }

    public static CrashHandler getInstance() {
        return mCrashHandler;
    }

    public void init() {
        mDefaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        // TODO Auto-generated method stub
        if (!handleException(ex) && null != mDefaultUncaughtExceptionHandler) {
            mDefaultUncaughtExceptionHandler.uncaughtException(thread, ex);
        } else {
            SystemClock.sleep(3000);
            BaseUI.exit();
            // 在一个if-else判断中，如果我们程序是按照我们预想的执行，到最后我们需要停止程序，
            // 那么我们使用System.exit(0)，而System.exit(1)一般放在catch块中，当捕获到异常，
            // 需要停止程序，我们使用System.exit(1)。这个status=1是用来表示这个程序是非正常退出。
            System.exit(1);

            Intent intent = new Intent(UIUtils.getContext(), HomeUI.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            UIUtils.startActivity(intent);
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }

    private boolean handleException(Throwable ex) {
        // TODO Auto-generated method stub
        if (null == ex) {
            return false;
        }
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                Looper.prepare();
                UIUtils.getToastSafe(R.string.exception_exit);
                Looper.loop();
            }
        }).start();

        saveInfos2SDCard(ex);
        return true;
    }

    private void saveInfos2SDCard(Throwable ex) {
        // TODO Auto-generated method stub
        StringBuffer sb = new StringBuffer();
        sb.append(AppUtils.getDeviceInfo());
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (null != cause) {
            ex.printStackTrace(printWriter);
            cause = ex.getCause();
        }
        String exceptionInfo = DateUtil.yyyyMMddHHmmss.format(new Date()).concat("\n").concat(writer.toString());
        File logFile = FileUtils.getCrashFile();
        if (null == logFile) {
            LogUtils.e("make logFile error ");
            return;
        }
        if (logFile.exists()) {
            sb = new StringBuffer(exceptionInfo);
        } else {
            sb.append(exceptionInfo);
        }
        LogUtils.i("crashInfo------" + sb.toString());
        try {
            FileOutputStream fos = new FileOutputStream(logFile, true);
            LogUtils.e(sb.toString());
            fos.write(sb.toString().getBytes());
            fos.flush();
            fos.close();
            LogUtils.i("logFile.getAbsolutePath()" + logFile.getAbsolutePath());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Logger.e(TAG, "log file save to sd card error !");
        }
    }

}
