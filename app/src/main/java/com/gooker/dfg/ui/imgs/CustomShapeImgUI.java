/**
 * 
 */
package com.gooker.dfg.ui.imgs;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.aoeng.degu.R;
import com.aoeng.degu.views.CustomShapeImageView;
import com.aoeng.degu.views.CustomShapeSquareImageView;

/**
 * May 16, 2014 6:19:42 PM
 * 
 */
public class CustomShapeImgUI extends Activity {

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_imgs_shap);

		GridView gridView = (GridView) findViewById(R.id.gridview);
		gridView.setAdapter(new SvgImagesAdapter(this));
	}

	private class SvgImagesAdapter extends BaseAdapter {
		private List<Integer> mSvgRawResourceIds = new ArrayList<Integer>();

		private Context mContext;

		public SvgImagesAdapter(Context context) {
			mContext = context;

			mSvgRawResourceIds.add(R.raw.shape_star);
			mSvgRawResourceIds.add(R.raw.shape_heart);
			mSvgRawResourceIds.add(R.raw.shape_flower);
			mSvgRawResourceIds.add(R.raw.shape_star_2);
			mSvgRawResourceIds.add(R.raw.shape_star_3);
			mSvgRawResourceIds.add(R.raw.shape_circle_2);
			mSvgRawResourceIds.add(R.raw.shape_5);
		}

		@Override
		public int getCount() {
			return mSvgRawResourceIds.size();
		}

		@Override
		public Integer getItem(int i) {
			return mSvgRawResourceIds.get(i);
		}

		@Override
		public long getItemId(int i) {
			return mSvgRawResourceIds.get(i);
		}

		@Override
		public View getView(int i, View view, ViewGroup viewGroup) {
			return new CustomShapeSquareImageView(mContext, R.drawable.sample_1,
					CustomShapeImageView.Shape.SVG, getItem(i));// It is just a sample ;)
		}

	}

}
