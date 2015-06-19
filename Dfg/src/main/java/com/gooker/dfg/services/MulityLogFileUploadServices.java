package com.gooker.dfg.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;

import com.gooker.dfg.utils.common.FileUtils;
import com.gooker.dfg.utils.common.Logger;
import com.gooker.dfg.utils.common.UIUtils;
import com.gooker.dfg.utils.net.URLUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;


public class MulityLogFileUploadServices extends Service {

    private static final String TAG = MulityLogFileUploadServices.class.getName();

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub

        return null;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        // String path = FileUtils.getAppCrashPath();
        // Logger.i(TAG, path);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub

        AsyncHttpClient client = new AsyncHttpClient();
        client.setTimeout(5000);
        RequestParams params = new RequestParams();
        params.put("logFile", getFileList());
//		params.put("name", "hello");
        String url = URLUtils.URL_HOST + URLUtils.urlLogsUpload;
        Logger.i(TAG, url);
        client.post(url, params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                // TODO Auto-generated method stub
                UIUtils.toastShow("upload Success");
                Logger.i(TAG, "onSuccess");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                // TODO Auto-generated method stub
                UIUtils.toastShow("upload Fail");
                Logger.i(TAG, "onFailure");
            }
        });

        return START_STICKY;
    }

    private ArrayList getFileList() {
        // TODO Auto-generated method stub
        ArrayList<File> files = new ArrayList<File>();

        String path = FileUtils.getAppCrashPath();
        if (!TextUtils.isEmpty(path)) {
            File fileCrash = new File(path);
            if (fileCrash.exists() && fileCrash.isDirectory() && fileCrash.list().length > 0) {
                String[] loges = fileCrash.list();
                if (null == loges || loges.length == 1) {
                    return null;
                }
                File logFile = null;
                for (String fileName : loges) {
                    logFile = new File(path, fileName);
                    if (logFile.exists() && logFile.isFile()) {
                        files.add(logFile);
                    }
                }
            }
        }

        return files;
    }

    private HashMap<String, String> getFileFieldMap() {
        HashMap<String, String> map = null;

        String path = FileUtils.getAppCrashPath();
        if (!TextUtils.isEmpty(path)) {
            File fileCrash = new File(path);
            if (fileCrash.exists() && fileCrash.isDirectory() && fileCrash.list().length > 0) {
                String[] loges = fileCrash.list();
                if (null == loges || loges.length == 1) {
                    return null;
                }
                map = new HashMap<String, String>();
                File logFile = null;
                for (String fileName : loges) {
                    logFile = new File(path, fileName);
                    if (logFile.exists() && logFile.isFile()) {
                        map.put(fileName, logFile.getAbsolutePath());
                        break;
                    }
                }
            }
        }
        return map;
    }

    private HashMap<String, String> getNormalFieldMap() {
        // TODO Auto-generated method stub
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("name", "name");
        // map.put("uploadDate", new Date().toString());

        return map;
    }
}
