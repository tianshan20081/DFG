package com.gooker.dfg.utils;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.storage.StorageManager;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.text.TextUtils;
import android.util.Log;

import com.aoeng.degu.domain.ImageGroup;
import com.aoeng.degu.domain.ImageInfo;
import com.aoeng.degu.utils.common.FileUtils;
import com.aoeng.degu.utils.common.LogUtils;
import com.aoeng.degu.utils.common.UIUtils;

public class PhotoUtils {
	static String[] filePathColumn = { Media.DATA, Media.LATITUDE, Media.LONGITUDE, Media.SIZE, Media.TITLE,
			Media.DESCRIPTION, Media.MIME_TYPE, Media.DATE_ADDED, Media.DATE_MODIFIED, Media.DATE_TAKEN,
			Media.DISPLAY_NAME };

	public static TreeMap<String, ImageGroup> getAllPhotos() {
		// TODO Auto-generated method stub
		TreeMap<String, ImageGroup> treeMap = new TreeMap<String, ImageGroup>(new Comparator<String>() {

			@Override
			public int compare(String lhs, String rhs) {
				// TODO Auto-generated method stub
				return (int) (Long.parseLong(rhs) - Long.parseLong(lhs));
			}
		});

		HashMap<String, String> paramMap = new HashMap<String, String>();
		String[] arrayOfString = { "_id", "_data", "_size", "_display_name", "mime_type", "title", "date_added", "date_modified", "description", "isprivate", "latitude", "longitude", "datetaken",
				"orientation", "mini_thumb_magic", "bucket_id", "bucket_display_name" };
		ContentResolver localContentResolver = UIUtils.getContext().getContentResolver();
		for (Uri localUri = Media.INTERNAL_CONTENT_URI;; localUri = Media.EXTERNAL_CONTENT_URI) {
			Cursor localCursor = localContentResolver.query(localUri, arrayOfString, "_size > 32768", null, "datetaken DESC");
			if (localCursor.moveToFirst()) {
				int i = localCursor.getColumnIndexOrThrow("bucket_id");
				int j = localCursor.getColumnIndexOrThrow("bucket_display_name");
				do {
					String str1 = localCursor.getString(i);
					String str2 = localCursor.getString(j);
					if ((!TextUtils.isEmpty(str2)) && (!paramMap.containsKey(str1)))
						paramMap.put(str1, str2);
					Log.d("preference", str1 + " " + str2);
				} while (localCursor.moveToNext());
			}
		}

	}

	private static void a(Context paramContext, Map paramMap, boolean paramBoolean) {
		String[] arrayOfString = { "_id", "_data", "_size", "_display_name", "mime_type", "title", "date_added", "date_modified", "description", "isprivate", "latitude", "longitude", "datetaken",
				"orientation", "mini_thumb_magic", "bucket_id", "bucket_display_name" };
		ContentResolver localContentResolver = UIUtils.getContext().getContentResolver();
		if (paramBoolean)
			;
		for (Uri localUri = Media.INTERNAL_CONTENT_URI;; localUri = Media.EXTERNAL_CONTENT_URI) {
			Cursor localCursor = localContentResolver.query(localUri, arrayOfString, "_size > 32768", null, "datetaken DESC");
			if (localCursor.moveToFirst()) {
				int i = localCursor.getColumnIndexOrThrow("bucket_id");
				int j = localCursor.getColumnIndexOrThrow("bucket_display_name");
				do {
					String str1 = localCursor.getString(i);
					String str2 = localCursor.getString(j);
					if ((!TextUtils.isEmpty(str2)) && (!paramMap.containsKey(str1)))
						paramMap.put(str1, str2);
					Log.d("preference", str1 + " " + str2);
				} while (localCursor.moveToNext());
			}
			return;
		}
	}

	/**
	 * @return
	 */
	public static List<ImageGroup> getAutoGroupImgs() {
		// TODO Auto-generated method stub
		long startScan = System.currentTimeMillis();
		LogUtils.i("start scan " + startScan);

		// 获取sdcard的路径：外置和内置
		Uri uri = Media.EXTERNAL_CONTENT_URI;
		List<ImageGroup> groups = new ArrayList<ImageGroup>();
		try {

			List<String> imgPaths = getImgsPaths();
			if (null == imgPaths) {
				return null;
			}
			List<ImageInfo> images = new ArrayList<ImageInfo>();
			for (String imgPath : imgPaths) {
				if (isExists(imgPath)) {
					images.addAll(getAutoGroup(imgPath, uri));
				}
			}
			long endScan = System.currentTimeMillis();
			LogUtils.i("endScan " + endScan + "scan time " + (endScan - startScan) + "scasn size " + images.size());
			if (images != null && images.size() > 0) {
				groups = getAutoGroups(images);
			}
			long endAuto = System.currentTimeMillis();
			LogUtils.i("endScan " + endScan + "scan time " + (endAuto - endScan) + "  groupsoize" + groups.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LogUtils.e(e);
		}
		return groups;

	}

	/**
	 * @param images
	 * @return
	 */
	private static List<ImageGroup> getAutoGroups(List<ImageInfo> images) {
		if (null == images || images.size() == 0) {
			return null;
		}
		List<ImageGroup> groups = new ArrayList<ImageGroup>();

		ImageGroup iu = new ImageGroup();
		iu.add(images.get(0));
		groups.add(iu);
		for (int i = 1; i < images.size(); i++) {
			ImageGroup ig = groups.get(groups.size() - 1);
			if (ig.isBelong(images.get(i))) {
				ig.add(images.get(i));
			} else {
				// 不属于 该分组，进行保存，新建下一个分组
				ig = new ImageGroup();
				ig.add(images.get(i));
				groups.add(ig);
				ig = null;
			}
		}
		return groups;
	}

	/**
	 * @param newUrl
	 * @param uri
	 * @return
	 */
	private static Collection<? extends ImageInfo> getAutoGroup(String url, Uri uri) {

		// 构建查询条件，且只查询jpeg和png的图片
		StringBuilder selection = new StringBuilder();
		selection.append(Media.DATA + " LIKE '%" + url + "%' and (");
		selection.append(Media.MIME_TYPE).append("=?");
		selection.append(" or ");
		selection.append(Media.MIME_TYPE).append("=?) and ");
		selection.append(Media.SIZE).append(">?");
		String[] values = new String[] { "image/jpeg", "image/png", "32768" };
		Cursor cursor = UIUtils.getContext().getContentResolver().query(uri, filePathColumn, selection.toString(), values, Media.DATE_TAKEN + " DESC ");
		if (null == cursor) {
			LogUtils.e("null == cursor ");
			return null;
		}
		List<ImageInfo> groups = new ArrayList<ImageInfo>();
		ImageInfo picInfo = null;
		while (cursor.moveToNext()) {
			picInfo = new ImageInfo();
			picInfo.setPicPath(cursor.getString(cursor.getColumnIndex(filePathColumn[0])));
			picInfo.setLatitude(cursor.getDouble(cursor.getColumnIndex(filePathColumn[1])));
			picInfo.setLongitude(cursor.getDouble(cursor.getColumnIndex(filePathColumn[2])));
			picInfo.setSize(cursor.getLong(cursor.getColumnIndex(filePathColumn[3])));
			picInfo.setTitle(cursor.getString(cursor.getColumnIndex(filePathColumn[4])));
			picInfo.setDescription(cursor.getString(cursor.getColumnIndex(filePathColumn[5])));
			picInfo.setMimeType(cursor.getString(cursor.getColumnIndex(filePathColumn[6])));
			picInfo.setAddDate(new Date(cursor.getLong(cursor.getColumnIndex(filePathColumn[7]))));
			picInfo.setModifyDate(new Date(cursor.getLong(cursor.getColumnIndex(filePathColumn[8]))));
			picInfo.setTakenDate(new Date(cursor.getLong(cursor.getColumnIndex(filePathColumn[9]))));
			picInfo.setDisplayName(cursor.getString(cursor.getColumnIndex(filePathColumn[10])));

			if (picInfo.getPicPath().contains("截图") || picInfo.getPicPath().contains("截屏") || picInfo.getPicPath().toLowerCase().contains("screenshot".toLowerCase())) {
				continue;
			}
			groups.add(picInfo);
		}
		cursor.close();
		return groups;
	}

	/**
	 * @param newUrl
	 * @return
	 */
	private static boolean isExists(String newUrl) {
		// TODO Auto-generated method stub
		File file = new File(newUrl);
		if (file.exists() && file.isDirectory()) {
			return true;
		}
		return false;
	}

	/**
	 * @return
	 */
	public static List<ImageInfo> getScanedImgs() {
		// TODO Auto-generated method stub
		// 获取sdcard的路径：外置和内置
		Uri uri = Media.EXTERNAL_CONTENT_URI;
		String url = FileUtils.getPhonePhotoFolder().getAbsolutePath();
		List<ImageInfo> groups = new ArrayList<ImageInfo>();
		List<String> imgs = getImgsPaths();
		for (String imgPath : imgs) {
			if (isExists(imgPath)) {
				groups.addAll(getAutoGroup(imgPath, uri));
			}
		}
		return groups;

	}

	/**
	 * @param paths
	 * @return
	 */
	private static List<String> getImgsPaths() {
		// TODO Auto-generated method stub

		try {
			StorageManager sm = (StorageManager) UIUtils.getContext().getSystemService(Context.STORAGE_SERVICE);
			String[] paths = (String[]) sm.getClass().getMethod("getVolumePaths", null).invoke(sm, null);
			List<String> list = new ArrayList<String>();
			if (null == paths || paths.length == 0) {
				return null;
			}
			for (String sysUrl : paths) {
				list.add(sysUrl.concat("/photo"));
				list.add(sysUrl.concat("/DCIM"));
				list.add(sysUrl.concat("/相机"));
				list.add(sysUrl.concat("/相册"));
				list.add(sysUrl.concat("/照片"));
				list.add(sysUrl.concat("/相片"));
				list.add(sysUrl.concat("/Camera"));
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LogUtils.e(e);
		}
		return null;

	}
}
