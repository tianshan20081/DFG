package com.gooker.dfg.views;

import com.aoeng.degu.R;
import com.aoeng.degu.utils.common.LogUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

public class MeshView extends View {
	private int WIDTH = 200;
	private int HEIGHT = 200;
	private int count = (WIDTH + 1) * (HEIGHT + 1) * 2;
	private float[] verts = new float[count];
	private float[] orig = new float[count];
	private Bitmap mBitmap;
	private float k = 1.0f;

	public MeshView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		initViews();
	}

	public MeshView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initViews();
	}

	public MeshView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initViews();
	}

	private void initViews() {
		// TODO Auto-generated method stub
		mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_mesh);
		int bmpWeight = mBitmap.getWidth();
		int bmpHeight = mBitmap.getHeight();
		int index = 0;
		for (int i = 0; i < HEIGHT + 1; i++) {
			float fy = bmpHeight * i / HEIGHT;
			for (int j = 0; j < WIDTH + 1; j++) {
				float fx = bmpWeight * j / WIDTH;
				orig[index * 2 + 0] = verts[index * 2 + 0] = fx;
				orig[index * 2 + 1] = verts[index * 2 + 1] = fy+100;
				index += 1;
			}
		}
		setBackgroundColor(Color.WHITE);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		for (int i = 0; i < HEIGHT + 1; i++) {
			for (int j = 0; j < WIDTH + 1; j++) {
				verts[(i * (WIDTH + 1) + j) * 2 + 0] += 0;
				float offsetY = (float) Math.sin((float) j / WIDTH * 2 * Math.PI + k * 2 * Math.PI) * 100;
				verts[(i * (WIDTH + 1) + j) * 2 + 1] = orig[(i * (WIDTH + 1) + j) * 2 + 1] + offsetY;
			}
		}
		k += 0.1;
		canvas.drawBitmapMesh(mBitmap, WIDTH, HEIGHT, verts, 0, null, 0, null);
		invalidate();
	}
}
