/**
 *
 */
package com.gooker.dfg.ui.lvs;

import android.view.View;
import android.widget.Toast;

import com.gooker.dfg.R;
import com.gooker.dfg.adapter.swipe.SwipeSingleAdapter;
import com.gooker.dfg.ui.BaseUI;
import com.gooker.dfg.views.swipe.BaseSwipeListViewListener;
import com.gooker.dfg.views.swipe.SwipeListSingleView;
import com.gooker.dfg.views.swipe.SwipeListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Jun 30, 2014 4:15:02 PM ListView 中同时只出现 一个可以删除的选项
 */
public class LvSwipeSingleDelUI extends BaseUI {
    private SwipeListSingleView mSwipeListView;
    private SwipeSingleAdapter mAdapter;
    public static int deviceWidth;
    private List<String> testData;

    /*
     * (non-Javadoc)
     *
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     *
     * @see com.aoeng.degu.ui.BaseUI#loadViewLayout()
     */
    @Override
    protected void loadViewLayout() {
        // TODO Auto-generated method stub
        setContentView(R.layout.ui_lvs_swipe_single);
        mSwipeListView = (SwipeListSingleView) findViewById(R.id.lv_swipe_single);
        testData = getTestData();
        mAdapter = new SwipeSingleAdapter(this, R.layout.ui_lvs_swipe_single_item, testData, mSwipeListView);
        deviceWidth = getDeviceWidth();
        mSwipeListView.setAdapter(mAdapter);
        mSwipeListView.setSwipeListViewListener(new TestBaseSwipeListViewListener());
        reload();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.aoeng.degu.ui.BaseUI#findViewById()
     */
    @Override
    protected void findViewById() {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     *
     * @see com.aoeng.degu.ui.BaseUI#setListener()
     */
    @Override
    protected void setListener() {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     *
     * @see com.aoeng.degu.ui.BaseUI#processLogic()
     */
    @Override
    protected void processLogic() {
        // TODO Auto-generated method stub

    }

    private List<String> getTestData() {
        String[] obj = new String[]{"背对背拥抱", "第几个一百天", "江南", "那些你很冒险的梦", "醉赤壁", "西界", "爱与希望", "你要的不是我", "不潮不用花钱",
                "只对你有感觉", "简简单单"};
        List<String> list = new ArrayList<String>(Arrays.asList(obj));
        return list;
    }

    private int getDeviceWidth() {
        return getResources().getDisplayMetrics().widthPixels;
    }

    private void reload() {
        mSwipeListView.setSwipeMode(SwipeListView.SWIPE_MODE_LEFT);
        // mSwipeListView.setSwipeMode(SwipeListView.SWIPE_MODE_BOTH);
        mSwipeListView.setSwipeActionLeft(SwipeListView.SWIPE_ACTION_REVEAL);
        // mSwipeListView.setSwipeActionRight(settings.getSwipeActionRight());
        mSwipeListView.setOffsetLeft(deviceWidth * 1 / 3);
        // mSwipeListView.setOffsetRight(convertDpToPixel(settings.getSwipeOffsetRight()));
        mSwipeListView.setAnimationTime(0);
        mSwipeListView.setSwipeOpenOnLongPress(true);
    }

    class TestBaseSwipeListViewListener extends BaseSwipeListViewListener {

        @Override
        public void onClickFrontView(int position) {
            super.onClickFrontView(position);
            Toast.makeText(getApplicationContext(), testData.get(position) + "itemOnclick()", Toast.LENGTH_SHORT)
                    .show();
        }

        @Override
        public void onDismiss(int[] reverseSortedPositions) {
            for (int position : reverseSortedPositions) {
                testData.remove(position);
                Toast.makeText(getApplicationContext(), testData.get(position) + "onDismiss()", Toast.LENGTH_SHORT)
                        .show();
            }
            mAdapter.notifyDataSetChanged();
        }
    }
}
