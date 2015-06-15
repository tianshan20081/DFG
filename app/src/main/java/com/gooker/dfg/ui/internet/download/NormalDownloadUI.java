package com.gooker.dfg.ui.internet.download;

import java.io.File;

import org.apache.http.Header;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.aoeng.degu.R;
import com.aoeng.degu.ui.BaseUI;
import com.aoeng.degu.utils.common.FileUtils;
import com.aoeng.degu.utils.net.asyncthhpclient.AsyncHttpClient;
import com.aoeng.degu.utils.net.asyncthhpclient.AsyncHttpResponseHandler;
import com.aoeng.degu.utils.net.asyncthhpclient.FileAsyncHttpResponseHandler;

public class NormalDownloadUI extends BaseUI {

	private TextView btnStart;
	private TextView btnStop;
	// String url = "http://dl.m.jd.com/download/android/360buy.apk";
	String url = "http://nokee.qiniudn.com/6406556c-f608-451e-997d-912b9875215dIMG_20141030_123003.jpg?imageView2/2/h/768/q/85";
	private AsyncHttpClient client;

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnStart:

			normalDownload();
			getContentLength();
			break;
		case R.id.btnStop:
			break;

		}
	}

	private void getContentLength() {
		// TODO Auto-generated method stub

		AsyncHttpClient client = new AsyncHttpClient();
		client.setTimeout(5000);
		client.head(url, new AsyncHttpResponseHandler() {
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				for (Header header : headers)
					if ("Content-Length".equals(header.getName()))
						System.out.println(header.getValue());
			}

			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
			}
		});
	}

	private void normalDownload() {
		// TODO Auto-generated method stub
		File file = new File(FileUtils.getDownloadDir(), "360buy.apk");
		AsyncHttpClient client = new AsyncHttpClient();
		client.setTimeout(5000);

		client.get(url, new FileAsyncHttpResponseHandler(file) {
			public void onSuccess(int statusCode, Header[] headers, File file) {
				Toast.makeText(getApplicationContext(), "下载完成", Toast.LENGTH_SHORT).show();
			}

			public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
				throwable.printStackTrace();
			}
		});
	}

	@Override
	protected void loadViewLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.ui_internet_download_normal);
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		btnStart = (TextView) findView(R.id.btnStart);
		btnStop = (TextView) findView(R.id.btnStop);
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		btnStart.setOnClickListener(this);
		btnStop.setOnClickListener(this);
	}

	@Override
	protected void processLogic() {
		// TODO Auto-generated method stub
	}

}
