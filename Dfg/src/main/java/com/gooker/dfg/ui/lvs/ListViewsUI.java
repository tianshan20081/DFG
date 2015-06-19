package com.gooker.dfg.ui.lvs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.gooker.dfg.R;

public class ListViewsUI extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ui_lvs_home);

        initViews();
    }

    private void initViews() {
        // TODO Auto-generated method stub
        this.findViewById(R.id.lvNormal).setOnClickListener(this);
        this.findViewById(R.id.lvPages).setOnClickListener(this);
        this.findViewById(R.id.btnLv01).setOnClickListener(this);
        this.findViewById(R.id.btnLv02).setOnClickListener(this);
        this.findViewById(R.id.btnExpandLv).setOnClickListener(this);
        this.findViewById(R.id.btnMovingDel).setOnClickListener(this);
        this.findViewById(R.id.btnUpDownLv).setOnClickListener(this);
        this.findViewById(R.id.btnLvMargin).setOnClickListener(this);
        this.findViewById(R.id.btnScrollDel).setOnClickListener(this);
        this.findViewById(R.id.btnSwipeDel).setOnClickListener(this);
        this.findViewById(R.id.btnSwipeSingleDel).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btnSwipeSingleDel:
                startActivity(new Intent(ListViewsUI.this, com.gooker.dfg.ui.lvs.LvSwipeSingleDelUI.class));
                break;
            case R.id.btnSwipeDel:
                startActivity(new Intent(ListViewsUI.this, com.gooker.dfg.ui.lvs.LvSwipeDelUI.class));
                break;
            case R.id.btnScrollDel:
                startActivity(new Intent(ListViewsUI.this, com.gooker.dfg.ui.lvs.LvScrollDelUI.class));
                break;
            case R.id.btnLvMargin:
                startActivity(new Intent(ListViewsUI.this, LvScrollMarginUI.class));
                break;
            case R.id.lvPages:
                startActivity(new Intent(ListViewsUI.this, com.gooker.dfg.ui.lvs.PagesListViewUI.class));
                break;
            case R.id.lvNormal:
                startActivity(new Intent(ListViewsUI.this, com.gooker.dfg.ui.lvs.NormalListViewUI.class));
                break;
            case R.id.btnLv01:
                startActivity(new Intent(ListViewsUI.this, com.gooker.dfg.ui.lvs.ReflashListUI.class));
                break;
            case R.id.btnLv02:
                startActivity(new Intent(ListViewsUI.this, com.gooker.dfg.ui.lvs.PullToRefreshUI.class));
                break;
            case R.id.btnExpandLv:
                startActivity(new Intent(ListViewsUI.this, ExpandLvUI.class));
                break;
            case R.id.btnMovingDel:
                startActivity(new Intent(ListViewsUI.this, com.gooker.dfg.ui.lvs.MovingDelItemLvUI.class));
                break;
            case R.id.btnUpDownLv:
                startActivity(new Intent(ListViewsUI.this, UpDownLvUI.class));
                break;
        }
    }
}
