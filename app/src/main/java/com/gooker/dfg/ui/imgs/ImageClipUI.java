package com.gooker.dfg.ui.imgs;

import java.io.ByteArrayOutputStream;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.MemoryFile;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

import com.aoeng.degu.R;
import com.aoeng.degu.ui.BaseUI;
import com.aoeng.degu.ui.imgs.*;
import com.aoeng.degu.ui.imgs.PicIconUI;
import com.aoeng.degu.utils.CodeUtils;
import com.aoeng.degu.utils.common.LogUtils;
import com.aoeng.degu.utils.common.StringUtils;
import com.aoeng.degu.views.ClipImageLayout;

public class ImageClipUI extends com.gooker.dfg.ui.BaseUI {

	private Button btnSure;
	private com.gooker.dfg.views.ClipImageLayout cilPic;
	private String imgPath;

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnSure:

			Bitmap bitmap = cilPic.clip();

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
			byte[] datas = baos.toByteArray();

			Intent intent = new Intent(ImageClipUI.this, com.gooker.dfg.ui.imgs.PicIconUI.class);
			intent.putExtra("bitmap", datas);
			setResult(com.gooker.dfg.utils.CodeUtils.RESPONSE_CLIP_IMAGE, intent);
			finish();
			break;

		default:
			break;
		}
	}

	@Override
	protected void loadViewLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.ui_imgs_clip);
		imgPath = getIntent().getStringExtra("picPath");

	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		btnSure = (Button) findView(R.id.btnSure);
		cilPic = (com.gooker.dfg.views.ClipImageLayout) findView(R.id.cilPic);

		if (!com.gooker.dfg.utils.common.StringUtils.isEmpty(imgPath)) {
			Bitmap bitmap = BitmapFactory.decodeFile(imgPath);
			if (null != bitmap) {
				cilPic.setImageDrawable(bitmap);
			}

		}
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		btnSure.setOnClickListener(this);
	}

	@Override
	protected void processLogic() {
		// TODO Auto-generated method stub

	}

}
