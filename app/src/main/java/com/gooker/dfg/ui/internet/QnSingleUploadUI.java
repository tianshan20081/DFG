package com.gooker.dfg.ui.internet;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.UUID;

import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aoeng.degu.R;
import com.aoeng.degu.ui.BaseUI;
import com.aoeng.degu.ui.internet.*;
import com.aoeng.degu.ui.internet.QnBreakPointUploadUI;
import com.aoeng.degu.utils.ByteToInputStream;
import com.aoeng.degu.utils.common.ImageUtils;
import com.aoeng.degu.utils.common.LogUtils;
import com.aoeng.degu.utils.qiniu.QNApi;
import com.qiniu.auth.Authorizer;
import com.qiniu.io.IO;
import com.qiniu.rs.CallBack;
import com.qiniu.rs.CallRet;
import com.qiniu.rs.PutExtra;
import com.qiniu.rs.UploadCallRet;
import com.qiniu.utils.InputStreamAt;

public class QnSingleUploadUI extends BaseUI {
	public static final int PICK_PICTURE_RESUMABLE = 0;

	private Button btnUpload;
	private Button btnResumableUpload;
	private TextView hint;
	// @gist upload
	volatile boolean uploading = false;

	protected String bucketName = QNApi.BUCKET_ANDROIDPLAY;

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		if (v.equals(btnUpload)) {
			Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
			intent.setType("*/*");
			intent.addCategory(Intent.CATEGORY_OPENABLE);
			startActivityForResult(intent, PICK_PICTURE_RESUMABLE);
			return;
		}
		if (v.equals(btnResumableUpload)) {
			startActivity(new Intent(this, QnBreakPointUploadUI.class));
			return;
		}

	}

	@Override
	protected void loadViewLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.ui_qn_singleupload);
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		hint = (TextView) findViewById(R.id.textView1);
		btnUpload = (Button) findViewById(R.id.button1);
		btnUpload.setOnClickListener(this);
		btnResumableUpload = (Button) findViewById(R.id.button);
		btnResumableUpload.setOnClickListener(this);
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void processLogic() {
		// TODO Auto-generated method stub

	}

	/**
	 * 普通上传文件
	 * 
	 * @param uri
	 */
	private void doUploadUri(Uri uri) {
		if (uploading) {
			hint.setText("上传中，请稍后");
			return;
		}
		uploading = true;
		// String key = IO.UNDEFINED_KEY; // 自动生成key/
		String key = System.currentTimeMillis() + ""; // 自动生成key/
		PutExtra extra = new PutExtra();
		extra.params = new HashMap<String, String>();
		extra.params.put("x:a", "测试中文信息");
		hint.setText("上传中");
		// 返回 UploadTaskExecutor ，可执行cancel，见 MyResumableActivity
		Context context = this.getApplicationContext();
		IO.putFile(context, QNApi.getAuthorizer(), key, uri, extra, new CallBack() {
			@Override
			public void onProcess(long current, long total) {
				int percent = (int) (current * 100 / total);
				hint.setText("上传中: " + current + "/" + total + "  " + current / 1024 + "K/" + total / 1024 + "K; " + percent + "%");
			}

			@Override
			public void onSuccess(UploadCallRet ret) {
				uploading = false;
				String key = ret.getKey();
				String redirect = "http://" + bucketName + ".qiniudn.com/" + key;
				String redirect2 = "http://" + bucketName + ".u.qiniudn.com/" + key;
				LogUtils.i(redirect);
				LogUtils.i(redirect);
				hint.setText("上传成功! ret: " + ret.toString() + "  \r\n可到" + redirect + " 或  " + redirect2 + " 访问");
			}

			@Override
			public void onFailure(CallRet ret) {
				uploading = false;
				hint.setText("错误: " + ret.toString());
			}
		});
	}

	// @endgist

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != RESULT_OK)
			return;
		if (requestCode == PICK_PICTURE_RESUMABLE) {
			// doUploadUri(data.getData());
			doUploadFile(data.getData());
			// doUploadStreamAt(data.getData());
			return;
		}
	}

	private void doUploadStreamAt(Uri uri) {
		// TODO Auto-generated method stub

		if (uploading) {
			hint.setText("上传中，请稍后");
			return;
		}
		uploading = true;
		// String key = IO.UNDEFINED_KEY; // 自动生成key/
		String key = UUID.randomUUID().toString(); // 自动生成key/
		PutExtra extra = new PutExtra();
		extra.params = new HashMap<String, String>();
		extra.params.put("x:a", "测试中文信息");
		hint.setText("上传中");
		// 返回 UploadTaskExecutor ，可执行cancel，见 MyResumableActivity
		Context context = this.getApplicationContext();
		String path = ImageUtils.getUriString(uri, getContentResolver());
		LogUtils.d("path------------------" + path);
		InputStream stream = ImageUtils.compressForUpload2(path, 200 * 1024);
		InputStreamAt input = new InputStreamAt.ByteInput(ByteToInputStream.input2byte(stream));
		IO.put(QNApi.getAuthorizer(), key, input, extra, new CallBack() {
			@Override
			public void onProcess(long current, long total) {
				int percent = (int) (current * 100 / total);
				hint.setText("上传中: " + current + "/" + total + "  " + current / 1024 + "K/" + total / 1024 + "K; " + percent + "%");
			}

			@Override
			public void onSuccess(UploadCallRet ret) {

				LogUtils.i(ret.toString());
				uploading = false;
				String key = ret.getKey();
				String redirect = "http://" + bucketName + ".qiniudn.com/" + key;
				String redirect2 = "http://" + bucketName + ".u.qiniudn.com/" + key;
				LogUtils.i(redirect);
				LogUtils.i(redirect);
				hint.setText("上传成功! ret: " + ret.toString() + "  \r\n可到" + redirect + " 或  " + redirect2 + " 访问");
			}

			@Override
			public void onFailure(CallRet ret) {
				uploading = false;
				hint.setText("错误: " + ret.toString());
			}
		});

	}

	private void doUploadFile(Uri uri) {
		if (uploading) {
			hint.setText("上传中，请稍后");
			return;
		}
		uploading = true;
		String key = UUID.randomUUID().toString(); // 自动生成key
		PutExtra extra = new PutExtra();
		extra.params = new HashMap<String, String>();
		extra.params.put("x:a", "测试中文信息");
		hint.setText("上传中");
		// 返回 UploadTaskExecutor ，可执行cancel，见 MyResumableActivity
		Context context = this.getApplicationContext();
		String path = ImageUtils.getUriString(uri, getContentResolver());
		LogUtils.d("path------------------" + path);
		File file = new File(path);
		IO.putFile(QNApi.getAuthorizer(), key, file, extra, new CallBack() {
			@Override
			public void onProcess(long current, long total) {
				int percent = (int) (current * 100 / total);
				hint.setText("上传中: " + current + "/" + total + "  " + current / 1024 + "K/" + total / 1024 + "K; " + percent + "%");
			}

			@Override
			public void onSuccess(UploadCallRet ret) {
				uploading = false;
				String key = ret.getKey();
				String redirect = "http://" + bucketName + ".qiniudn.com/" + key;
				String redirect2 = "http://" + bucketName + ".u.qiniudn.com/" + key;
				LogUtils.i(redirect);
				LogUtils.i(redirect);
				hint.setText("上传成功! ret: " + ret.toString() + "  \r\n可到" + redirect + " 或  " + redirect2 + " 访问");
			}

			@Override
			public void onFailure(CallRet ret) {
				uploading = false;
				hint.setText("错误: " + ret.toString());
			}
		});

	}
}
