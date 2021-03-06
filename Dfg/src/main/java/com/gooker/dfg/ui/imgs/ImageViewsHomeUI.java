/**
 *
 */
package com.gooker.dfg.ui.imgs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.gooker.dfg.R;
import com.gooker.dfg.utils.common.UIUtils;


/**
 * May 16, 2014 6:02:25 PM
 */
public class ImageViewsHomeUI extends Activity implements OnClickListener {

    /*
     * (non-Javadoc)
     *
     * @see
     * android.app.Activity#onCreate
     * (android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method
        // stub
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ui_imgs_home);

        this.findViewById(R.id.btnCircleImg).setOnClickListener(this);
        this.findViewById(R.id.btnShapeImg).setOnClickListener(this);
        this.findViewById(R.id.btnGetPicFromMediaStore).setOnClickListener(this);
        this.findViewById(R.id.btnGetPicsFromMediaStore).setOnClickListener(this);
        this.findViewById(R.id.btnPicWall).setOnClickListener(this);
        this.findViewById(R.id.btnTakeIcon).setOnClickListener(this);
        this.findViewById(R.id.btnPicIcon).setOnClickListener(this);
        this.findViewById(R.id.btnImgCrop).setOnClickListener(this);
        this.findViewById(R.id.btnImageAutoGroup).setOnClickListener(this);
        this.findViewById(R.id.btnImagePlay).setOnClickListener(this);
        this.findViewById(R.id.btnGraffiti).setOnClickListener(this);
        this.findViewById(R.id.btnMitoPic).setOnClickListener(this);
        this.findViewById(R.id.btnMesh).setOnClickListener(this);
        this.findViewById(R.id.btnAlbum).setOnClickListener(this);
        this.findViewById(R.id.btnAlbumFresco).setOnClickListener(this);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * android.view.View.OnClickListener
     * #onClick(android.view.View)
     */
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method
        // stub
        switch (v.getId()) {
            case R.id.btnMitoPic:
                UIUtils.startActivity(MiToUI.class);
                break;
            case R.id.btnMesh:
                UIUtils.startActivity(MeshUI.class);
                break;
            case R.id.btnGraffiti:
                UIUtils.startActivity(GraffitiUI.class);
                break;
            case R.id.btnTakeIcon:
                UIUtils.startActivity(TakeIconUI.class);
                break;
            case R.id.btnPicWall:
                UIUtils.startActivity(PictureWallUI.class);
                break;
            case R.id.btnGetPicFromMediaStore:
                UIUtils.startActivity(PictureFromMediaStoreUI.class);
                break;
            case R.id.btnGetPicsFromMediaStore:
                UIUtils.startActivity(PicturesFromMediaStoreUI.class);
                break;
            case R.id.btnCircleImg:
                startActivity(new Intent(this, CircleImgUI.class));
                break;
            case R.id.btnShapeImg:
                startActivity(new Intent(this, CustomShapeImgUI.class));
                break;
            case R.id.btnPicIcon:
                startActivity(new Intent(this, PicIconUI.class));
                break;
            case R.id.btnImgCrop:
                startActivity(new Intent(this, PicCropUI.class));
                break;
            case R.id.btnImageAutoGroup:
                startActivity(new Intent(this, ImageAutoGroupUI.class));
                break;
            case R.id.btnImagePlay:
                startActivity(new Intent(this, ImagePlayerUI.class));
                break;
            case R.id.btnAlbum:
                startActivity(new Intent(this, AlbumUI.class
                ));
                break; case R.id.btnAlbumFresco:
                startActivity(new Intent(this, AlbumFrescoUI.class
                ));
                break;
            default:
                break;
        }
    }
}
