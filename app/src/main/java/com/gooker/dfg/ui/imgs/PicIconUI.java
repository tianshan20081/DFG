package com.gooker.dfg.ui.imgs;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.aoeng.degu.R;
import com.aoeng.degu.ui.BaseUI;
import com.aoeng.degu.ui.imgs.ImageClipUI;
import com.aoeng.degu.utils.CodeUtils;
import com.aoeng.degu.utils.common.LogUtils;
import com.aoeng.degu.utils.common.StringUtils;

/**
 * https://github.com/biokys/cropimage.
 * git
 * 
 * @author sczhang
 * 
 */
public class PicIconUI extends BaseUI {

	private Button btnChosePic;
	private ImageView ivPicIcon;

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method
		// stub
		switch (v.getId()) {
		case R.id.btnChosePic:
			Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

			startActivityForResult(intent, CodeUtils.REQUEST_LOAD_IMAGE);
			break;

		default:
			break;
		}

	}

	@Override
	protected void loadViewLayout() {
		// TODO Auto-generated method
		// stub
		setContentView(R.layout.ui_imgs_pic_icon);
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method
		// stub
		btnChosePic = (Button) findView(R.id.btnChosePic);
		ivPicIcon = (ImageView) findView(R.id.ivPicIcon);

	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method
		// stub
		btnChosePic.setOnClickListener(this);
	}

	@Override
	protected void processLogic() {
		// TODO Auto-generated method
		// stub

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method
		// stub

		if (requestCode == CodeUtils.REQUEST_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };

			Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
			String path = "";
			while (cursor.moveToNext()) {
				path = cursor.getString(cursor.getColumnIndex(filePathColumn[0]));
			}
			cursor.close();
			if (!StringUtils.isEmpty(path)) {
				// 打开 图片 剪裁器 进行剪裁
				Intent intent = new Intent(com.aoeng.degu.ui.imgs.PicIconUI.this, ImageClipUI.class);
				intent.putExtra("picPath", path);
				startActivityForResult(intent, CodeUtils.REQUEST_CLIP_IMAGE);
			}
		} else if (requestCode == CodeUtils.REQUEST_CLIP_IMAGE && resultCode == CodeUtils.RESPONSE_CLIP_IMAGE && data != null) {
			byte[] b = data.getByteArrayExtra("bitmap");
			LogUtils.e("clipImage   response-------------------------------");
			Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
			if (bitmap != null) {
				ivPicIcon.setImageBitmap(bitmap);
			}
		}
	}

}
