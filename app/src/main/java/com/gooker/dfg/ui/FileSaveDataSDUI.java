package com.gooker.dfg.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import cn.jpush.android.api.JPushInterface;

import com.aoeng.degu.R;
import com.aoeng.degu.utils.common.Toaster;

public class FileSaveDataSDUI extends Activity implements OnClickListener {
	private ImageView imPic;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_data_sd);
		this.findViewById(R.id.btnCopyFileToSD).setOnClickListener(this);
		this.findViewById(R.id.btnShowImg).setOnClickListener(this);
		imPic = (ImageView) this.findViewById(R.id.imPic);
		if (android.os.Environment.isExternalStorageRemovable()) {
			Toaster.toast(this, "SD 卡是真实的", true);
		} else {
			Toaster.toast(this, "SD 卡是虚拟的", true);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnShowImg:
			String fileName = android.os.Environment.getExternalStorageDirectory() + "/image.jpg";
			if (!new File(fileName).exists()) {
				Toaster.toast(this, "文件不存在!", true);
				return;
			}
			try {
				FileInputStream fin = new FileInputStream(fileName);
				// imPic.setBackgroundDrawable(Drawable.createFromStream(fin,
				// "image"));
				Bitmap bitmap = BitmapFactory.decodeStream(fin);
				imPic.setImageBitmap(bitmap);
				fin.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case R.id.btnCopyFileToSD:
			try {
				FileOutputStream fos = new FileOutputStream(android.os.Environment.getExternalStorageDirectory() + "/image.jpg");
				InputStream is = getResources().getAssets().open("mengmeng.jpg");
				byte[] buffer = new byte[1024];
				int count = 0;
				while ((count = is.read(buffer)) != -1) {
					fos.write(buffer, 0, count);
				}
				is.close();
				fos.close();
				Toaster.toast(this, "文件拷贝完毕", true);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		JPushInterface.onResume(this);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		JPushInterface.onPause(this);
	}
}
