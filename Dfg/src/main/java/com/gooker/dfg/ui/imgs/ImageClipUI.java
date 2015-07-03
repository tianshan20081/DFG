package com.gooker.dfg.ui.imgs;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.Button;

import com.gooker.dfg.R;
import com.gooker.dfg.ui.BaseUI;
import com.gooker.dfg.utils.CodeUtils;
import com.gooker.dfg.views.ClipImageLayout;

import java.io.ByteArrayOutputStream;


public class ImageClipUI extends BaseUI {

    private Button btnSure;
    private com.gooker.dfg.views.ClipImageLayout cilPic;
    private String imgPath;

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btnSure:

                Bitmap bitmap = cilPic.clip();

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] datas = baos.toByteArray();

                Intent intent = new Intent(ImageClipUI.this, PicIconUI.class);
                intent.putExtra("bitmap", datas);
                setResult(CodeUtils.RESPONSE_CLIP_IMAGE, intent);
                finish();
                break;

            default:
                break;
        }
    }

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.ui_imgs_clip);
        imgPath = getIntent().getStringExtra("picPath");

    }

    @Override
    protected void findViewById() {
        btnSure = (Button) findView(R.id.btnSure);
        cilPic = (ClipImageLayout) findView(R.id.cilPic);

        if (!com.gooker.dfg.utils.common.StringUtils.isEmpty(imgPath)) {
            Bitmap bitmap = BitmapFactory.decodeFile(imgPath);
            if (null != bitmap) {
                cilPic.setImageDrawable(bitmap);
            }

        }
    }

    @Override
    protected void setListener() {
        btnSure.setOnClickListener(this);
    }

    @Override
    protected void processLogic() {
        // TODO Auto-generated method stub

    }

}
