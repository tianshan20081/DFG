package com.gooker.dfg.ui.uis;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;

import com.gooker.dfg.utils.common.Toaster;


public class Action2UI extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        String data = getIntent().getStringExtra("data");
        if (!TextUtils.isEmpty(data)) {
            Toaster.toast(this, "参数值是" + data, true);
        }

    }

}
