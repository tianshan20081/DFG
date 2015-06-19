package com.gooker.dfg.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

import com.gooker.dfg.R;


public class RefreshLV2 extends ListView implements OnScrollListener {

    public RefreshLV2(Context context) {
        super(context);
        // TODO Auto-generated constructor stub

        initHeader();
        initFooter();
        this.setOnScrollListener(this);

    }

    private void initFooter() {
        // TODO Auto-generated method stub
        View headerView = LayoutInflater.from(getContext()).inflate(
                R.layout.ui_lvs_rf_pull_to_refresh, null);

    }

    private void initHeader() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
        // TODO Auto-generated method stub

    }

}
