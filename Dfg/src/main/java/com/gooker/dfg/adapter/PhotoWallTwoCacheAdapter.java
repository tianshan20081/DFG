/**
 *
 */
package com.gooker.dfg.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.gooker.dfg.R;
import com.gooker.dfg.utils.cv.TwoCacheImageLoader;


/**
 * @author [ShaoCheng Zhang] Sep 5, 2013:2:50:54 PM
 * @Email [zhangshch2008@gmail.com]
 */
public class PhotoWallTwoCacheAdapter extends BaseAdapter {
    private Context context;
    private String[] urls;
    private TwoCacheImageLoader imageLoader;
    private boolean mbusy;

    public PhotoWallTwoCacheAdapter(Context context, String[] urls) {
        this.context = context;
        this.urls = urls;
        this.imageLoader = new TwoCacheImageLoader();
    }

    /*
     * (non-Javadoc)
     *
     * @see android.widget.Adapter#getCount()
     */
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return urls.length;
    }

    /*
     * (non-Javadoc)
     *
     * @see android.widget.Adapter#getItem(int)
     */
    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    /*
     * (non-Javadoc)
     *
     * @see android.widget.Adapter#getItemId(int)
     */
    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    /*
     * (non-Javadoc)
     *
     * @see android.widget.Adapter#getView(int, android.view.View,
     * android.view.ViewGroup)
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ImageView imageView = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.ui_cv_pthoto2cache_item, null);
            imageView = (ImageView) convertView.findViewById(R.id.imPotoTwoCache);
            convertView.setTag(imageView);

        } else {
            imageView = (ImageView) convertView.getTag();
        }
        String url = urls[position % urls.length];
        if (!mbusy) {
            imageLoader.loadImage(url, this, imageView);
        } else {
            Bitmap bitmap = imageLoader.getBitmapFromCache(url);
            if (null != bitmap) {
                imageView.setImageBitmap(bitmap);
            } else {
                imageView.setBackgroundResource(R.drawable.ic_launcher);
            }

        }
        return convertView;
    }

    public void setFlagBusy(boolean isBusy) {
        // TODO Auto-generated method stub
        this.mbusy = isBusy;
    }

}
