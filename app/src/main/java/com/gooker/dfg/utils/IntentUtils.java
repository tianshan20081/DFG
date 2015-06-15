package com.gooker.dfg.utils;

import java.io.File;
import java.net.URI;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

public class IntentUtils {

	/* 用来标识请求照相功能的activity */
	/**
	 * 打开相机拍照
	 */
	public static final int CAMERA_WITH_DATA = 3023;
	/* 用来标识请求gallery的activity */
	public static final int PHOTO_PICKED_WITH_DATA = 3021;
	/* 图片切割 */
	/**
	 * 从图库选择图片 并裁剪
	 */
	public static final int PHOTO_CROP_DATA = 3025;
	/**
	 * 裁剪图片的意图
	 */
	public static final int CAMERA_CROP_DATA = 3027;

	/**
	 * 从相册中选择头像，设置
	 * 
	 * @param f
	 *            头像文件
	 * @param width
	 *            输出宽度
	 * @param height
	 *            输出高度
	 * @return Intnet
	 */
	public static Intent getPhotoPickCropIntent(File f, int width, int height) {
		// Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
		Intent intent = new Intent("android.intent.action.PICK");
		intent.setType("image/*");
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", width);
		intent.putExtra("outputY", height);
		intent.putExtra("scale", true);
		intent.putExtra("return-data", false);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
		intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
		return intent;
	}

	/**
	 * 打开相机进行拍照
	 * 
	 * @param f
	 *            图片文件
	 * @return 返回头像文件Intent
	 */
	public static Intent getTakePickIntent(File f) {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
		return intent;
	}

	/**
	 * 实现对图片裁剪的意图
	 * 
	 * @param srcFile
	 *            源文件（图片）
	 * @param dstFile
	 *            目标文件 裁剪后的图片
	 * @param outputX
	 *            输出文件的宽度
	 * @param outputY
	 *            输出文件的高度
	 * @return 返回 打开 图片裁剪器 的Intent
	 */
	public static Intent cropImageUri(File srcFile, File dstFile, int outputX, int outputY) {

		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(Uri.fromFile(srcFile), "image/*");
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", outputX);
		intent.putExtra("outputY", outputY);
		intent.putExtra("scale", true);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(dstFile));
		intent.putExtra("return-data", false);
		return intent;
	}

}
