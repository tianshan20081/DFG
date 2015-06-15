package com.gooker.dfg.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.widget.ImageView;

import com.aoeng.degu.R;
import com.aoeng.degu.constant.Constants;
import com.aoeng.degu.utils.RefererImageDownloader;
import com.aoeng.degu.utils.common.FileUtils;
import com.aoeng.degu.utils.common.LogUtils;
import com.aoeng.degu.utils.common.StringUtils;
import com.aoeng.degu.utils.common.UIUtils;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

public class ImageLoaderUtils {

	private static ImageLoader mImageLoader = null;
	private static DisplayImageOptions imageOptions = new DisplayImageOptions.Builder().showImageOnFail(R.drawable.pic_thumb)
			.showImageForEmptyUri(R.drawable.pic_thumb).showStubImage(R.drawable.pic_thumb).bitmapConfig(Config.RGB_565)
			.imageScaleType(ImageScaleType.IN_SAMPLE_INT).cacheInMemory(true).cacheOnDisk(true).considerExifParams(true).build();
	private static File cacheDir = new File(FileUtils.getCacheDir());
	private static ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(UIUtils.getContext())
			.memoryCacheExtraOptions(320, 480)
			// default = device screen
			// dimensions
			.diskCacheExtraOptions(320, 480, null)
			// .taskExecutor(...)
			// .taskExecutorForCachedImages(...)
			.threadPoolSize(5)
			// default
			.threadPriority(Thread.NORM_PRIORITY - 2)
			// default
			.tasksProcessingOrder(QueueProcessingType.FIFO)
			// default
			.denyCacheImageMultipleSizesInMemory().memoryCache(new LruMemoryCache(2 * 1024 * 1024)).memoryCacheSize(2 * 1024 * 1024)
			.memoryCacheSizePercentage(30) // default
			.diskCache(new UnlimitedDiscCache(cacheDir)) // default
			.diskCacheSize(200 * 1024 * 1024).diskCacheFileCount(100).diskCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
			.imageDownloader(new RefererImageDownloader(UIUtils.getContext())) // default
			.imageDecoder(new BaseImageDecoder(true)) // default
			// .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
			// // default
			.defaultDisplayImageOptions(imageOptions)
			// .writeDebugLogs()
			.build();

	public static synchronized ImageLoader getImageLoader() {
		if (null == mImageLoader) {
			mImageLoader = ImageLoader.getInstance();
			mImageLoader.init(configuration);
		}

		BaseImageDownloader downloader = new BaseImageDownloader(UIUtils.getContext());

		return mImageLoader;
	}

	/**
	 * 图片显示工具
	 * 
	 * @param path
	 *            uri Image URI (i.e.
	 *            "http://site.com/image.png"
	 *            ,
	 *            "file:///mnt/sdcard/image.png"
	 *            (显示 本地文件 可以不加
	 *            file://)
	 *            )
	 * @param ivFront
	 */
	public static void displayLocalImage(String path, ImageView imageView) {
		// TODO Auto-generated method
		// stub
		if (!StringUtils.isEmpty(path) && !path.startsWith("file://")) {
			File file = new File(path);
			if (file.exists() && file.isFile()) {
				path = "file://" + path;
			}
		}
		getImageLoader().displayImage(path, imageView);

	}

	public static void displayLocalImage(String path, ImageView ivIcon, DisplayImageOptions options) {
		// TODO Auto-generated method
		// stub
		if (!StringUtils.isEmpty(path) && !path.startsWith("file://")) {
			File file = new File(path);
			if (file.exists() && file.isFile()) {
				path = "file://" + path;
			}
		}
		getImageLoader().displayImage(path, ivIcon, options);
	}

	public static ByteArrayInputStream compressForUpload(String path) {
		if (StringUtils.isEmpty(path)) {
			return null;

		}
		File file = new File(path);
		if (!file.exists() || !file.isFile()) {
			return null;
		}
		ImageSize targetImageSize = getTargetImageSize(path);
		path = "file://" + path;
		Bitmap imageSync = getImageLoader().loadImageSync(path, targetImageSize);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		imageSync.compress(CompressFormat.JPEG, 95, baos);
		return new ByteArrayInputStream(baos.toByteArray());
	}

	public void compressSaveImgToSdCard(String path) {
		if (StringUtils.isEmpty(path)) {
			return;

		}
		File file = new File(path);
		if (!file.exists() || !file.isFile()) {
			return;
		}
		ImageSize targetImageSize = getTargetImageSize(path);
		path = "file://" + path;
		Bitmap imageSync = getImageLoader().loadImageSync(path, targetImageSize);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		imageSync.compress(CompressFormat.JPEG, 95, baos);
		try {
			FileOutputStream fos = new FileOutputStream(new File(FileUtils.getImgFile()));
			fos.write(baos.toByteArray());
			fos.flush();
			fos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch
			// block
			e.printStackTrace();
		}

	}

	private static ImageSize getTargetImageSize(String path) {
		// TODO Auto-generated method
		// stub
		Options opts = new Options();
		BitmapFactory.decodeFile(path, opts);
		int w = opts.outWidth;
		int h = opts.outHeight;
		if (w <= Constants.IMAGE_SIDE_MIN_VALUE || h <= Constants.IMAGE_SIDE_MIN_VALUE) {
			return new ImageSize(w, h);
		}
		int width;
		int height;
		float scal = 1.0f;
		if (w > h) {
			scal = (float) (h * 1.0 / Constants.IMAGE_SIDE_MIN_VALUE);
			width = (int) (w / scal + 0.5f);
			height = Constants.IMAGE_SIDE_MIN_VALUE;
		} else {
			scal = (float) (w * 1.0 / Constants.IMAGE_SIDE_MIN_VALUE);
			height = (int) (h / scal + 0.5);
			width = Constants.IMAGE_SIDE_MIN_VALUE;
		}
		LogUtils.e("------------image src size w:" + w + "\t h:" + h + "\t image target size w:" + width + "\t h:" + height);
		return new ImageSize(width, height);
	}

	/**
	 * 根据Uri获取文件的路径
	 * 
	 * @Title: getUriString
	 * @param uri
	 * @return String
	 */
	public static String getUriString(Uri uri, ContentResolver cr) {
		String imgPath = null;
		if (uri != null) {
			String uriString = uri.toString();
			// 小米手机的适配问题，小米手机的uri以file开头，其他的手机都以content开头
			// 以content开头的uri表明图片插入数据库中了，而以file开头表示没有插入数据库
			// 所以就不能通过query来查询，否则获取的cursor会为null。
			if (uriString.startsWith("file")) {
				// uri的格式为file:///mnt....,将前七个过滤掉获取路径
				imgPath = uriString.substring(7, uriString.length());
				return imgPath;
			}
			Cursor cursor = cr.query(uri, null, null, null, null);
			cursor.moveToFirst();
			imgPath = cursor.getString(1); // 图片文件路径

		}
		return imgPath;
	}

}
