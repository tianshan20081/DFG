package com.gooker.dfg.ui.imgs;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;

import com.gooker.dfg.R;
import com.gooker.dfg.ui.BaseUI;
import com.gooker.dfg.utils.IntentUtils;
import com.gooker.dfg.utils.common.FileUtils;
import com.gooker.dfg.utils.common.ImageUtils;
import com.gooker.dfg.utils.common.LogUtils;
import com.gooker.dfg.utils.common.StringUtils;
import com.gooker.dfg.utils.common.UIUtils;

import java.io.File;


public class TakeIconUI extends BaseUI {

    private File mPhotoFile;
    private File mCropFile;
    private ImageView ivUserIcon;
    private String imgPath;

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btnTakePhoto:
                if (!FileUtils.isSDCardAvailable()) {
                    UIUtils.toastShow("NO Sdcard !");
                    return;
                }
                Intent takePhoto = IntentUtils.getTakePickIntent(mPhotoFile);
                startActivityForResult(takePhoto, IntentUtils.CAMERA_WITH_DATA);
                LogUtils.i("takePhoto");
                break;

            default:
                break;
        }
    }

    @Override
    protected void loadViewLayout() {
        // TODO Auto-generated method stub
        setContentView(R.layout.ui_imgs_icon_take);
        mPhotoFile = new File(FileUtils.getCacheDir(), FileUtils.getPhotoFileName());
        mCropFile = new File(FileUtils.getCacheDir(), "crop_" + FileUtils.getPhotoFileName());
    }

    @Override
    protected void findViewById() {
        // TODO Auto-generated method stub
        findView(R.id.btnTakePhoto).setOnClickListener(this);
        ivUserIcon = (ImageView) findView(R.id.img);
    }

    @Override
    protected void setListener() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void processLogic() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub

        if (requestCode == IntentUtils.PHOTO_CROP_DATA) {
            imgPath = mPhotoFile.getAbsolutePath();
            if (!StringUtils.isEmpty(imgPath)) {
                ivUserIcon.setImageBitmap(ImageUtils.parseBitmapToLittle(mPhotoFile.getAbsolutePath()));
            }
        } else if (requestCode == IntentUtils.CAMERA_WITH_DATA) {
            Bitmap bitmap = BitmapFactory.decodeFile(mPhotoFile.getAbsolutePath());
            if (null != bitmap) {
                int width = 100;
                int height = 100;
                bitmap.recycle();
                Intent intent = IntentUtils.cropImageUri(mPhotoFile, mCropFile, width, height);
                startActivityForResult(intent, IntentUtils.CAMERA_CROP_DATA);

            } else {
                log("不存在的目标文件");
                UIUtils.getToastSafe("不存在的目标文件");
            }

        } else if (requestCode == IntentUtils.CAMERA_CROP_DATA) {
            imgPath = mCropFile.getAbsolutePath();
            Bitmap bitmap = BitmapFactory.decodeFile(mCropFile.getAbsolutePath());
            if (null != bitmap) {
                bitmap.recycle();
                ivUserIcon.setImageBitmap(ImageUtils.parseBitmapToLittle(imgPath));
                if (mPhotoFile.exists()) {
                    mPhotoFile.delete();
                }
            } else {
                log("不存在的目标文件");
                UIUtils.getToastSafe("不存在的目标文件");
            }
        }

    }
}
