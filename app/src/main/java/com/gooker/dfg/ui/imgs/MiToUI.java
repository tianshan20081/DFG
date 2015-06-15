package com.gooker.dfg.ui.imgs;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.aoeng.degu.R;
import com.aoeng.degu.ui.BaseUI;
import com.aoeng.degu.utils.ImageHelper;

public class MiToUI extends BaseUI implements OnSeekBarChangeListener {
	private ImageView ivMito;
	private SeekBar sbar;
	private SeekBar sbarRo;
	private SeekBar sbarNum;
	private Bitmap mBitmap;
	private float mHue;
	private float mScal;
	private float mNum;
	private int midValue = 128;

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method
		// stub
	}

	@Override
	protected void loadViewLayout() {
		// TODO Auto-generated method
		// stub
		setContentView(R.layout.ui_imgs_mito_home);
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method
		// stub
		ivMito = (ImageView) findView(R.id.ivMito);
		mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_mito_01);
		ivMito.setImageBitmap(mBitmap);
		sbar = (SeekBar) findView(R.id.sbar);
		sbarRo = (SeekBar) findView(R.id.sbarRo);
		sbarNum = (SeekBar) findView(R.id.sbarNum);
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method
		// stub
		sbar.setMax(256);
		sbarNum.setMax(256);
		sbarNum.setMax(256);
		sbar.setOnSeekBarChangeListener(this);
		sbarNum.setOnSeekBarChangeListener(this);
		sbarRo.setOnSeekBarChangeListener(this);
		sbar.setProgress(midValue);
		sbarNum.setProgress(midValue);
		sbarRo.setProgress(midValue);
	}

	@Override
	protected void processLogic() {
		// TODO Auto-generated method
		// stub
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		// TODO Auto-generated method
		// stub
		switch (seekBar.getId()) {
		case R.id.sbar:
			mHue = (progress - midValue) * 1.0f / midValue * 180;
			break;
		case R.id.sbarNum:
			mNum = progress * 1.0f / midValue;
			break;
		case R.id.sbarRo:
			mScal = progress * 1.0f / midValue;
			break;
		}
		ivMito.setImageBitmap(ImageHelper.changeBitmap(mBitmap, mHue, mScal, mScal, mScal, mNum));
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method
		// stub
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method
		// stub
	}
}
