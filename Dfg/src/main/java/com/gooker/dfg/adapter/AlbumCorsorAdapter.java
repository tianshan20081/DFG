package com.gooker.dfg.adapter;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;

import com.gooker.dfg.R;
import com.gooker.dfg.utils.ImageLoaderUtils;
import com.gooker.dfg.utils.common.LogUtils;

/**
 * Created by sczhang on 15/6/21.
 */
public class AlbumCorsorAdapter extends CursorAdapter {
    private Context mContext ;
    private int mViewId ;
    private String TAG = "[AlbumCorsorAdapter]";

    public AlbumCorsorAdapter(Context context,int resourceId, Cursor c) {
        super(context, c);
        this.mViewId = resourceId;
    }


    /**
     * Makes a new view to hold the data pointed to by cursor.
     *
     * @param context Interface to application's global information
     * @param cursor  The cursor from which to get the data. The cursor is already
     *                moved to the correct position.
     * @param parent  The parent to which the new view is attached to
     * @return the newly created view.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
//        View inflate = LayoutInflater.from(context).inflate(mViewId, null);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(mViewId,null);
//        view.findViewById(R.id.list_item_iv);
        LogUtils.i(TAG+"newView");
        return view;
    }

    /**
     * Bind an existing view to the data pointed to by cursor
     *
     * @param view    Existing view, returned earlier by newView
     * @param context Interface to application's global information
     * @param cursor  The cursor from which to get the data. The cursor is already
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        if (null != view){
            ImageView iv = (ImageView) view.findViewById(R.id.list_item_iv);
            String imgPath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            LogUtils.e(TAG+"img path"+imgPath);
//            Bitmap bitmap = BitmapFactory.decodeFile(imgPath);
//            iv.setImageBitmap(bitmap);
            ImageLoaderUtils.displayLocalImage(imgPath, iv);
//            Image
//            iv.
        }
    }
}
