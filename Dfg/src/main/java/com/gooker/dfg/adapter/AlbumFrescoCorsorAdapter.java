package com.gooker.dfg.adapter;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CursorAdapter;

import com.facebook.drawee.view.SimpleDraweeView;
import com.gooker.dfg.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sczhang on 15/6/22.
 */
public class AlbumFrescoCorsorAdapter extends CursorAdapter {
    private int mViewId ;
    private List<String> mPaths ;

    public AlbumFrescoCorsorAdapter(Context context,int resourceId, Cursor c) {
        super(context, c);
        this.mViewId = resourceId;
        mPaths = new ArrayList<>();
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
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(mViewId,null);
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
            CheckBox ckbox = (CheckBox) view.findViewById(R.id.list_item_cb);
            String imgPath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.ivFrescoSimpleView);
            simpleDraweeView.setImageURI(Uri.parse("file://" + imgPath));

            ckbox.setOnClickListener(new View.OnClickListener(){

                /**
                 * Called when a view has been clicked.
                 *
                 * @param v The view that was clicked.
                 */
                @Override
                public void onClick(View v) {
//                    if ()
                }
            });
        }
    }
}
