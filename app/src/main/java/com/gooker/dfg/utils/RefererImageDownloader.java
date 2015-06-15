package com.gooker.dfg.utils;

import java.io.IOException;
import java.net.HttpURLConnection;

import android.content.Context;

import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

public class RefererImageDownloader extends BaseImageDownloader {

	public RefererImageDownloader(Context context) {
		super(context);
		// TODO Auto-generated
		// constructor stub
	}

	@Override
	protected HttpURLConnection createConnection(String url, Object extra) throws IOException {
		// TODO Auto-generated method
		// stub
		HttpURLConnection conn = super.createConnection(url, extra);
		if (null != conn) {
			conn.addRequestProperty("Referer", "Referer");
		}
		return conn;
	}

}
