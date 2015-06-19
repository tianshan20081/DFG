package com.gooker.dfg.ui.mp;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.gooker.dfg.R;
import com.gooker.dfg.ui.BaseUI;
import com.gooker.dfg.utils.common.StringUtils;


public class MpRequestInfoUI extends BaseUI {
    private TextView tvInfo;
    private String info;

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
    }

    @Override
    protected void loadViewLayout() {
        // TODO Auto-generated method stub
        setContentView(R.layout.ui_mp_home);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        // TODO Auto-generated method stub
        String extras = getIntent().getStringExtra("extras");
        if (!StringUtils.isEmpty(extras)) {
            info = info + "\n" + extras;
            tvInfo.setText(info);
        }
    }

    @Override
    protected void findViewById() {
        // TODO Auto-generated method stub
        tvInfo = (TextView) findView(R.id.tvInfo);
        String extras = getIntent().getStringExtra("extras");
        if (!StringUtils.isEmpty(extras)) {
            tvInfo.setText(extras);
            info = extras;
        }
    }

    @Override
    protected void setListener() {
        // TODO Auto-generated method stub
    }

    @Override
    protected void processLogic() {
        // TODO Auto-generated method stub
    }
}
