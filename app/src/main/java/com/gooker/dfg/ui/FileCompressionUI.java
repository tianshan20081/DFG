package com.gooker.dfg.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import cn.jpush.android.api.JPushInterface;

import com.aoeng.degu.R;
import com.aoeng.degu.utils.common.Toaster;

public class FileCompressionUI extends Activity implements OnClickListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_file_comm);
		this.findViewById(R.id.btnCom).setOnClickListener(this);
		this.findViewById(R.id.btnComMore).setOnClickListener(this);
		this.findViewById(R.id.btnDeCom).setOnClickListener(this);
		this.findViewById(R.id.btnDeComMore).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnComMore:
			try {
				// 压缩多个文件
				String[] fileNames = { "main.xml", "string.xml" };
				FileOutputStream foss = new FileOutputStream(android.os.Environment.getExternalStorageDirectory() + "/file.zip");
				ZipOutputStream zos = new ZipOutputStream(foss);
				int i = 1;
				while (i <= fileNames.length) {
					ZipEntry zipEntry = new ZipEntry(fileNames[i - 1]);
					zos.putNextEntry(zipEntry);
					InputStream ins = getResources().getAssets().open(fileNames[i - 1]);
					byte[] buffer = new byte[1024];
					int count = 0;
					while ((count = ins.read(buffer)) >= 0) {
						zos.write(buffer, 0, count);
					}
					zos.flush();
					zos.closeEntry();
					ins.close();
					i++;
				}
				zos.finish();
				zos.close();
				Toaster.toast(this, "Compress AS zip File Success !", false);
			} catch (Exception e) {
				log(e.getMessage());
				Toaster.toast(this, e.getMessage(), false);
			}
			break;
		case R.id.btnDeComMore:
			try {
				String fileNames = android.os.Environment.getExternalStorageDirectory() + "/file.zip";
				if (!new File(fileNames).exists()) {
					Toaster.toast(this, "The Zip File Cann't find !", false);
					return;
				}
				FileInputStream fis = new FileInputStream(fileNames);
				ZipInputStream zis = new ZipInputStream(fis);
				ZipEntry zipEntry = null;
				while ((zipEntry = zis.getNextEntry()) != null) {
					FileOutputStream fos = new FileOutputStream(android.os.Environment.getExternalStorageDirectory() + File.separator
							+ zipEntry.getName());
					byte[] buffer = new byte[1024];
					int count = 0;
					while ((count = zis.read(buffer)) >= 0) {
						fos.write(buffer, 0, count);
					}
					zis.closeEntry();
					fos.close();
					Toaster.toast(this, "DeCompress Zip File Success !", false);
				}
				zis.close();
			} catch (Exception e) {
				log(e.getMessage());
				Toaster.toast(this, e.getMessage(), false);
			}
			break;
		case R.id.btnCom:
			try {
				FileOutputStream fos = new FileOutputStream(android.os.Environment.getExternalStorageDirectory() + File.separator + "file.jar");
				JarOutputStream jos = new JarOutputStream(fos);
				JarEntry jarEntry = new JarEntry("string.xml");
				jos.putNextEntry(jarEntry);
				InputStream in = getResources().getAssets().open("string.xml");
				int count = 0;
				byte[] buffer = new byte[1024];
				while ((count = in.read(buffer)) >= 0) {
					jos.write(buffer, 0, count);
				}
				in.close();
				jos.closeEntry();
				jos.close();
				Toaster.toast(this, "Compress string.xml as Jar File Success !", false);
			} catch (Exception e) {
				log(e.getMessage());
				Toaster.toast(this, e.getMessage(), false);
			}
			break;
		case R.id.btnDeCom:
			try {
				// 加压文件
				String filePath = android.os.Environment.getExternalStorageDirectory() + File.separator + "file.jar";
				if (!new File(filePath).exists()) {
					Toaster.toast(this, "The Compress File is not Exists !", false);
					return;
				}
				FileInputStream fis = new FileInputStream(filePath);
				JarInputStream jis = new JarInputStream(fis);
				JarEntry jarEntry = jis.getNextJarEntry();
				FileOutputStream fos = new FileOutputStream(android.os.Environment.getExternalStorageDirectory() + File.separator
						+ jarEntry.getName());
				byte[] buffer = new byte[1024];
				int count = 0;
				while ((count = jis.read(buffer)) >= 0) {
					fos.write(buffer, 0, count);
				}
				jis.closeEntry();
				jis.close();
				fos.close();
				Toaster.toast(this, " Jar File DeCompress Success", false);
			} catch (Exception e) {
				log(e.getMessage());
				Toaster.toast(this, e.getMessage(), false);
			}
			break;
		}
	}

	private void log(String message) {
		// TODO Auto-generated method stub
		Toaster.log(com.aoeng.degu.ui.FileCompressionUI.class.getName().toUpperCase(), message);
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
