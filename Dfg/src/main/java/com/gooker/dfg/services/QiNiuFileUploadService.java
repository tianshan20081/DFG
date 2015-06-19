package com.gooker.dfg.services;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import com.gooker.dfg.R;
import com.gooker.dfg.utils.common.FileUtils;
import com.gooker.dfg.utils.common.LogUtils;
import com.gooker.dfg.utils.common.UIUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class QiNiuFileUploadService extends Service {

    protected static final int CRASH_FILE_SCAN_FINISH = 1;
    protected static final int FILE_UPLOAD_SUCCESS = 2;
    private List<String> mFils;
//	private PutExtra extra = new PutExtra();

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CRASH_FILE_SCAN_FINISH:
                    String string = mFils.get(0);
                    File file = new File(string);
                    if (file.exists() && file.isFile()) {
                        UIUtils.getToastSafe("begin upload file ");
                        uploader(file);
                    }

                    break;
                case FILE_UPLOAD_SUCCESS:
                    mFils = FileUtils.getFileLists(FileUtils.getAppCrashPath());
                    if (null != mFils && mFils.size() > 0) {
                        String pat = mFils.get(0);
                        File patFile = new File(pat);
                        if (patFile.exists() && patFile.isFile()) {
                            uploader(patFile);
                        }
                    } else {
                        UIUtils.getToastSafe(R.string.app_upload_success);
                        stopSelf();
                    }
                    break;
                default:
                    break;
            }
        }

        ;

    };
    private List<String> mList = new ArrayList<String>();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        LogUtils.i("QiNiuFileUploadService ------ onStartCommand");
        new AsyncTask<Void, Void, List<String>>() {

            @Override
            protected List<String> doInBackground(Void... params) {
                // TODO Auto-generated method stub
                return FileUtils.getFileLists(FileUtils.getAppCrashPath());
            }

            @Override
            protected void onPostExecute(List<String> result) {
                // TODO Auto-generated method stub
                super.onPostExecute(result);
                if (null != result && result.size() > 0) {
                    mFils = result;
                    handler.sendEmptyMessage(CRASH_FILE_SCAN_FINISH);
                } else {
                    LogUtils.i(" result.size ==0");
                    UIUtils.getToastSafe("result.size ==0");
                }
            }
        }.execute();

        return super.onStartCommand(intent, flags, startId);
    }

    protected void getUploads(String path) {
        // TODO Auto-generated method stub
        File file = new File(path);
        String[] fls = file.list();
        for (String string : fls) {
            File ff = new File(file, string);
            if (ff.isFile() && ff.isFile()) {
                mList.add(ff.getAbsolutePath());
            } else {
                getUploads(ff.getAbsolutePath());
            }
        }
    }

    private void uploader(final File file) {
        // TODO Auto-generated method stub
        LogUtils.i(file.getAbsolutePath());
//		IO.putFile(QNApi.getAuthorizer(QNApi.BUCKET_TSLOGS), file.getName(), file, extra, new CallBack() {
//
//			@Override
//			public void onSuccess(UploadCallRet ret) {
//				// TODO Auto-generated method stub
//				Message message = handler.obtainMessage();
//				message.what = FILE_UPLOAD_SUCCESS;
//				handler.sendMessage(message);
//				file.delete();
//				LogUtils.i("IO.putFile  upload " + ret.getResponse());
//			}
//
//			@Override
//			public void onProcess(long current, long total) {
//				// TODO Auto-generated method stub
//				LogUtils.i("IO.putFile  upload " + (current * 1.0 / total));
//			}
//
//			@Override
//			public void onFailure(CallRet ret) {
//				// TODO Auto-generated method stub
//				LogUtils.i("IO.putFile  upload " + ret.getResponse());
//			}
//		});
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

}
