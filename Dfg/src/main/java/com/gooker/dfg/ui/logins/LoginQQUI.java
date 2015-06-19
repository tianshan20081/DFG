package com.gooker.dfg.ui.logins;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.gooker.dfg.R;


public class LoginQQUI extends Activity implements OnClickListener {
    private Button login_Button;
    private View moreHideBottomView, input2;
    private ImageView more_imageView;
    private boolean mShowBottom = false;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_logins_qq_login);
        initView();
    }

    private void initView() {
        login_Button = (Button) findViewById(R.id.buton1);
        login_Button.setOnClickListener(this);

        moreHideBottomView = findViewById(R.id.morehidebottom);
        more_imageView = (ImageView) findViewById(R.id.more_image);

        input2 = findViewById(R.id.input2);
        input2.setOnClickListener(this);
    }

    public void showBottom(boolean bShow) {
        if (bShow) {
            moreHideBottomView.setVisibility(View.GONE);
            more_imageView.setImageResource(R.drawable.qq_login_more_up);
            mShowBottom = true;
        } else {
            moreHideBottomView.setVisibility(View.VISIBLE);
            more_imageView.setImageResource(R.drawable.qq_login_more);
            mShowBottom = false;
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.input2:
                showBottom(!mShowBottom);
                break;
            case R.id.buton1:
                // showRequestDialog();
                intent = new Intent(LoginQQUI.this, com.gooker.dfg.ui.logins.MainQQActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private Dialog mDialog = null;

    private void showRequestDialog() {
        if (mDialog != null) {
            mDialog.dismiss();
            mDialog = null;
        }
        mDialog = com.gooker.dfg.utils.DialogFactory.creatRequestDialog(this, "正在验证账号...");
        mDialog.show();
    }


}
