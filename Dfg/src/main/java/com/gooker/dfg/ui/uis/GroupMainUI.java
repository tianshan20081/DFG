package com.gooker.dfg.ui.uis;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.gooker.dfg.R;
import com.gooker.dfg.utils.common.Toaster;


public class GroupMainUI extends ActivityGroup {
    private LinearLayout lyHeader;
    private LinearLayout lyBody;
    private LinearLayout lyBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_uis_group_main);

        lyHeader = (LinearLayout) this.findViewById(R.id.lyHeader);
        lyBody = (LinearLayout) this.findViewById(R.id.lyBody);
        lyBottom = (LinearLayout) this.findViewById(R.id.lyBottom);

        // 获得 GroupUI1 的顶层资源
        View groupUI1 = getLocalActivityManager().startActivity("groupUI1", new Intent(GroupMainUI.this, Group1UI.class)).getDecorView();
        View groupUI2 = getLocalActivityManager().startActivity("groupUI2", new Intent(this, Group2UI.class)).getDecorView();
        View groupUI3 = getLocalActivityManager().startActivity("group3", new Intent(this, Group3UI.class)).getDecorView();

        lyHeader.addView(groupUI1);
        lyBody.addView(groupUI2);
        lyBottom.addView(groupUI3);

    }

    public void onClickBottom(View v) {

        Toaster.toast(this, GroupMainUI.class.getName().toUpperCase(), true);
    }

}
