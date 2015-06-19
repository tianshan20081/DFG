package com.gooker.dfg.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gooker.dfg.R;
import com.gooker.dfg.utils.common.UIUtils;


public class ImagePullToRefreshLv extends ListView implements OnScrollListener {
    private LayoutInflater mInflate;
    private TextView mRefreshTv;
    private View mRefreshHeaderView;
    private LayoutInflater mInflater;
    private TextView mRefreshViewText;
    private ImageView mRefreshViewImage;
    private ProgressBar mRefreshViewProgress;
    private TextView mRefreshViewLastUpdated;
    private RelativeLayout mLoadMoreFooterView;
    private TextView mLoadMoreText;
    private ProgressBar mLoadMoreProgress;
    private int mRefreshOriginalTopPadding;
    private int mRefreshHeaderViewHeight;

    public ImagePullToRefreshLv(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        init(context);
    }

    public ImagePullToRefreshLv(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        init(context);
    }

    public ImagePullToRefreshLv(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
        init(context);
    }

    private void init(Context context) {
        // TODO Auto-generated method stub
        mInflater = (LayoutInflater) UIUtils.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mRefreshHeaderView = (View) mInflate.inflate(R.layout.ui_imgs_pic_wall_lv_header, null);
        mRefreshViewText = (TextView) mRefreshHeaderView.findViewById(R.id.pull_to_refresh_text);
        mRefreshViewImage = (ImageView) mRefreshHeaderView.findViewById(R.id.pull_to_refresh_image);
        mRefreshViewProgress = (ProgressBar) mRefreshHeaderView.findViewById(R.id.pull_to_refresh_progress);
        mRefreshViewLastUpdated = (TextView) mRefreshHeaderView.findViewById(R.id.pull_to_refresh_updated_at);
        mLoadMoreFooterView = (RelativeLayout) mInflater.inflate(R.layout.ui_lvs_rf_loadmore_footer, this, false);
        mLoadMoreText = (TextView) mLoadMoreFooterView.findViewById(R.id.loadmore_text);
        mLoadMoreProgress = (ProgressBar) mLoadMoreFooterView.findViewById(R.id.loadmore_progress);

        mRefreshViewImage.setMinimumHeight(50); // 设置图片最小高度
        mRefreshHeaderView.setOnClickListener(new OnClickImageRefreshListener());
        mRefreshOriginalTopPadding = mRefreshHeaderView.getPaddingTop();
        mLoadMoreFooterView.setOnClickListener(new OnClickImageLoadMoreListener());


        addHeaderView(mRefreshHeaderView);
        addFooterView(mLoadMoreFooterView);

        super.setOnScrollListener(this);

        measureView(mRefreshHeaderView);
        mRefreshHeaderViewHeight = mRefreshHeaderView.getMeasuredHeight();

    }

    private void measureView(View mRefreshHeaderView2) {
        // TODO Auto-generated method stub

    }

    private class OnClickImageRefreshListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub

        }

    }

    private class OnClickImageLoadMoreListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub

        }

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        // TODO Auto-generated method stub

    }
}
