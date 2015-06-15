package com.gooker.dfg.ui.internet.download;

import java.io.File;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aoeng.degu.R;
import com.aoeng.degu.services.MultiThreadDownloadTask;
import com.aoeng.degu.ui.BaseUI;
import com.aoeng.degu.utils.common.FileUtils;

public class MulityDownloadUI extends BaseUI {
	private MultiThreadDownloadTask task;
	private boolean isStart;
	private ImageButton startIB;
	String url = "http://dl.m.jd.com/download/android/360buy.apk";
	private static TextView percentTV;
	private static TextView progressTV;
	private static ProgressBar downloadPB;

	private static Handler handler = new Handler() {
		private long contentLength;

		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MultiThreadDownloadTask.MESSAGE_WHAT_CONTENT_LENGTH:
				contentLength = (Long) msg.obj;
				downloadPB.setMax((int) contentLength);
				break;
			case MultiThreadDownloadTask.MESSAGE_WHAT_COMPLETED_LENGTH:
				long completedLength = (Long) msg.obj;
				downloadPB.setProgress((int) completedLength);
				percentTV.setText(completedLength * 100 / contentLength + "%");
				progressTV.setText(completedLength + " / " + contentLength);
				break;
			}
		}
	};

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.startIB:
			if (!isStart) {
				isStart = true;
				task.start();
				startIB.setImageResource(android.R.drawable.ic_media_pause);
			} else {
				isStart = false;
				task.stop();
				startIB.setImageResource(android.R.drawable.ic_media_play);
			}
			break;

		default:
			break;
		}
	}

	@Override
	protected void loadViewLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.ui_internet_download_mulity);

	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub

		File file = new File(FileUtils.getDownloadDir(), "360buy.apk");
		task = new MultiThreadDownloadTask(url, file, 3, this, handler);

		downloadPB = (ProgressBar) findViewById(R.id.downloadPB);
		startIB = (ImageButton) findViewById(R.id.startIB);
		percentTV = (TextView) findViewById(R.id.percentTV);
		progressTV = (TextView) findViewById(R.id.progressTV);
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		startIB.setOnClickListener(this);
	}

	@Override
	protected void processLogic() {
		// TODO Auto-generated method stub

	}

}
