package com.gooker.dfg.services;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;

import com.aoeng.degu.domain.LogFileUploadResult;
import com.aoeng.degu.parser.LogFileUploadParser;
import com.aoeng.degu.services.DataCallback;
import com.aoeng.degu.utils.DataUtils;
import com.aoeng.degu.utils.FileUploadVO;
import com.aoeng.degu.utils.common.FileUtils;
import com.aoeng.degu.utils.common.Logger;
import com.aoeng.degu.utils.net.URLUtils;

public class LogFileUploadServices extends Service {

	private static final String TAG = com.aoeng.degu.services.LogFileUploadServices.class.getName();

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		String path = FileUtils.getAppCrashPath();
		Logger.i(TAG, path);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub

		FileUploadVO vo = new FileUploadVO();
		vo.requestUrl = URLUtils.URL_HOST + URLUtils.urlLogsUpload;
		vo.jsonParser = new LogFileUploadParser();
		vo.requestDataMap = getNormalFieldMap();
		vo.fileFieldMap = getFileFieldMap();

		DataUtils.getDateFromServer(vo, new DataCallback<LogFileUploadResult>() {

			@Override
			public void processData(LogFileUploadResult result, boolean paramBoolean) {
				// TODO Auto-generated method stub
				if (null != result) {
					Logger.i(TAG, result.toString());
					List<String> uploadedFile = result.getFileNames();
				}
			}
		});

		return START_STICKY;
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
