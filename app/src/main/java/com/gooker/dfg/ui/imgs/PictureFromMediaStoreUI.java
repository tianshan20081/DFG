package com.gooker.dfg.ui.imgs;

import java.util.Date;
import java.util.HashMap;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.aoeng.degu.R;
import com.aoeng.degu.domain.BaiDuLocation;
import com.aoeng.degu.domain.ImageInfo;
import com.aoeng.degu.parser.BaiDuLocationParser;
import com.aoeng.degu.services.DataCallback;
import com.aoeng.degu.ui.BaseUI;
import com.aoeng.degu.utils.CodeUtils;
import com.aoeng.degu.utils.DataUtils;
import com.aoeng.degu.utils.RequestVO;
import com.aoeng.degu.utils.net.URLUtils;

public class PictureFromMediaStoreUI extends BaseUI {

	private ImageView ivPic;
	private Button btnGetImg;
	private TextView tvImgInfo;
	private TextView tvPath;

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnGetImg:
			Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

			startActivityForResult(intent, CodeUtils.REQUEST_LOAD_IMAGE);
			break;

		default:
			break;
		}
	}

	@Override
	protected void loadViewLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.ui_imgs_pic_from_mediastore);
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		btnGetImg = (Button) findView(R.id.btnGetImg);
		ivPic = (ImageView) findView(R.id.ivPic);

		tvImgInfo = (TextView) findView(R.id.tvImgInfo);
		tvPath = (TextView) findView(R.id.tvPath);
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		btnGetImg.setOnClickListener(this);
	}

	@Override
	protected void processLogic() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub

		if (requestCode == CodeUtils.REQUEST_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA, MediaStore.Images.Media.LATITUDE, MediaStore.Images.Media.LONGITUDE, MediaStore.Images.Media.SIZE, MediaStore.Images.Media.TITLE,
					MediaStore.Images.Media.DESCRIPTION, MediaStore.Images.Media.MIME_TYPE, MediaStore.Images.Media.DATE_ADDED, MediaStore.Images.Media.DATE_MODIFIED,
					MediaStore.Images.Media.DATE_TAKEN, MediaStore.Images.Media.DISPLAY_NAME, MediaStore.Images.Media.HEIGHT, MediaStore.Images.Media.WIDTH };

			Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
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

				picInfo.setHeight(cursor.getInt(cursor.getColumnIndex(filePathColumn[11])));
				picInfo.setWidth(cursor.getInt(cursor.getColumnIndex(filePathColumn[12])));

			}

			cursor.close();
			if (null != picInfo) {
				ivPic.setImageBitmap(BitmapFactory.decodeFile(picInfo.getPicPath()));
				tvPath.setText(picInfo.getPicPath());
				log(picInfo.toString());
				loadImageInfo(picInfo);
			}

		}
	}

	private void loadImageInfo(ImageInfo picInfo) {
		// TODO Auto-generated method stub

		RequestVO reqVo = new RequestVO();
		reqVo.requestUrl = URLUtils.URL_BAIDU_LOCATE_API;
		reqVo.requestDataMap = getLocateMap(picInfo.getLatitude().toString().concat(",").concat(picInfo.getLongitude().toString()));
		reqVo.jsonParser = new BaiDuLocationParser();
		DataUtils.getDateFromServer(reqVo, new DataCallback<BaiDuLocation>() {

			@Override
			public void processData(BaiDuLocation obj, boolean paramBoolean) {
				// TODO Auto-generated method stub

				// TODO Auto-generated method stub
				if (null != obj) {
					log(obj.toString());
					tvImgInfo.setText(obj.getResult().getAddressComponent().toString());
				}

			}
		});
	}

	private HashMap<String, String> getLocateMap(String location) {
		// TODO Auto-generated method stub
		// http://api.map.baidu.com/geocoder/v2/?ak=n6ujmRzPDzruKkGgXZuF0YLY&location=39.89833450317383,116.491943359375&output=json&pois=0
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("ak", URLUtils.KEY_BAIDU_MAP);
		map.put("location", location);
		map.put("output", URLUtils.JSON);
		map.put("pois", "0");

		return map;
	}
}
