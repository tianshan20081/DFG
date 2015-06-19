package com.gooker.dfg.ui.logins;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.gooker.dfg.R;


public class LoginUIs extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ui_logins);

        initView();
        setViewEvent();
    }

    private void setViewEvent() {
        // TODO Auto-generated method stub

    }

    private void initView() {
        // TODO Auto-generated method stub
        this.findViewById(R.id.btnLoginQQ).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btnLoginQQ:
                intent = new Intent(LoginUIs.this, LoginQQSplashUI.class);
                break;

        }
        startActivity(intent);
    }
}
