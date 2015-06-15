package com.gooker.dfg.ui.imgs;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.aoeng.degu.R;
import com.aoeng.degu.ui.BaseUI;
import com.aoeng.degu.ui.views.MoiveImageView;
import com.aoeng.degu.utils.CodeUtils;
import com.aoeng.degu.utils.common.StringUtils;

public class ImagePlayerUI extends com.gooker.dfg.ui.BaseUI {
	public static final int REQUEST_CODE_GALLERY = 0x1;
	public static final int REQUEST_CODE_TAKE_PICTURE = 0x2;
	public static final int REQUEST_CODE_CROP_IMAGE = 0x3;
	private LinearLayout lyBase;
	private com.gooker.dfg.ui.views.MoiveImageView moiveImageView;

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method
		// stub
		switch (v.getId()) {
		case R.id.btnLeft:
			moiveImageView.setPosLeft();
			break;
		case R.id.btnRight:
			moiveImageView.setPosRight();
			break;
		case R.id.btnTop:
			break;
		case R.id.btnBottom:
			break;
		default:
			break;
		}
	}

	@Override
	protected void loadViewLayout() {
		// TODO Auto-generated method
		// stub
		setContentView(R.layout.ui_imgs_play);
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method
		// stub
		lyBase = (LinearLayout) findViewById(R.id.lyBase);
		findView(R.id.btnLeft).setOnClickListener(this);
		findView(R.id.btnRight).setOnClickListener(this);
		findView(R.id.btnTop).setOnClickListener(this);
		findView(R.id.btnBottom).setOnClickListener(this);
		moiveImageView = new com.gooker.dfg.ui.views.MoiveImageView(this);
		lyBase.addView(moiveImageView);
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method
		// stub
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
		if (requestCode == com.gooker.dfg.utils.CodeUtils.REQUEST_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };
			Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
			String path = "";
			while (cursor.moveToNext()) {
				path = cursor.getString(cursor.getColumnIndex(filePathColumn[0]));
			}
			cursor.close();
			if (!com.gooker.dfg.utils.common.StringUtils.isEmpty(path)) {
			}
		}
	}
}
