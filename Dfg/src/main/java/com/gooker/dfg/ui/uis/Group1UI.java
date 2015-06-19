package com.gooker.dfg.ui.uis;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.gooker.dfg.R;
import com.gooker.dfg.utils.common.Toaster;


public class Group1UI extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ui_uis_group_1);
    }

    public void onClickGU1(View v) {
        Toaster.toast(this, Group1UI.class.getName().toUpperCase(), true);

    }
}
