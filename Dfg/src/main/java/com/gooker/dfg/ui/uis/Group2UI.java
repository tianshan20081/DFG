package com.gooker.dfg.ui.uis;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.gooker.dfg.R;
import com.gooker.dfg.utils.common.Toaster;


public class Group2UI extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ui_uis_group_2);
    }

    public void onClickGU2(View v) {
        Toaster.toast(this, Group2UI.class.getName().toUpperCase(), true);
    }

}
