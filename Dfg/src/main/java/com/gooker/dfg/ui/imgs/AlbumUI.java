package com.gooker.dfg.ui.imgs;

import android.database.Cursor;
import android.provider.MediaStore;
import android.view.View;
import android.widget.GridView;

import com.gooker.dfg.R;
import com.gooker.dfg.adapter.AlbumCorsorAdapter;
import com.gooker.dfg.ui.BaseUI;
import com.gooker.dfg.utils.common.FileUtils;
import com.gooker.dfg.utils.common.LogUtils;
import com.gooker.dfg.utils.common.UIUtils;

public class AlbumUI extends BaseUI {


    private GridView lvAlbum;
    private AlbumCorsorAdapter mCursorAdapter;

    /**
     *
     */
    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.ui_album);
    }

    /**
     *
     */
    @Override
    protected void findViewById() {
        lvAlbum = (GridView)findViewById(R.id.lvAlbum);
    }

    /**
     *
     */
    @Override
    protected void setListener() {

    }

    /**
     *
     */
    @Override
    protected void processLogic() {
        String[] filePathColumn = {MediaStore.Images.Media._ID, MediaStore.Images.Media.DATA, MediaStore.Images.Media.LATITUDE, MediaStore.Images.Media.LONGITUDE,
                MediaStore.Images.Media.SIZE, MediaStore.Images.Media.TITLE, MediaStore.Images.Media.DESCRIPTION, MediaStore.Images.Media.MIME_TYPE,
                MediaStore.Images.Media.DATE_ADDED, MediaStore.Images.Media.DATE_MODIFIED, MediaStore.Images.Media.DATE_TAKEN,
                MediaStore.Images.Media.DISPLAY_NAME };
        String url = FileUtils.getPhonePhotoFolder().getAbsolutePath();
        String param = MediaStore.Images.Media.DATA + " LIKE '%" + url + "%' ";
        Cursor cursor = UIUtils.getContext().getContentResolver()
                .query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, filePathColumn, param, null, MediaStore.Images.Media.DATE_TAKEN + " DESC ");
        if (null == cursor) {
            LogUtils.e("null == cursor ");
            return ;
        }
        mCursorAdapter = new AlbumCorsorAdapter(AlbumUI.this,R.layout.ui_album_item,cursor);
        lvAlbum.setAdapter(mCursorAdapter);
    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

    }
}
