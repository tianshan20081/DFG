/**
 * 照片墙
 */
package com.gooker.dfg.ui.cv;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

import com.gooker.dfg.R;
import com.gooker.dfg.adapter.PhotoWallAdapter;
import com.gooker.dfg.utils.ImagesURL;


/**
 * @author [Aoeng Zhang] @datatime Sep 3, 2013:9:23:46 AM
 * @Email [zhangshch2008@gmail.com] Sep 3, 2013
 */
public class PhotoWallUI extends Activity {
    /**
     * 用于展示照片墙的GridView
     */
    private GridView mPhotoWall;

    /**
     * GridView的适配器
     */
    private PhotoWallAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_cv_photowall);
        mPhotoWall = (GridView) findViewById(R.id.photo_wall);
        adapter = new PhotoWallAdapter(this, 0, ImagesURL.imageThumbUrls, mPhotoWall);
        mPhotoWall.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 退出程序时结束所有的下载任务
        adapter.cancelAllTasks();
    }
}