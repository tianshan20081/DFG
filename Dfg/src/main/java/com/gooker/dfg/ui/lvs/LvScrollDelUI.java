/**
 *
 */
package com.gooker.dfg.ui.lvs;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.gooker.dfg.R;
import com.gooker.dfg.ui.BaseUI;
import com.gooker.dfg.views.lv.SlideCutListView;

import java.util.ArrayList;
import java.util.List;


/**
 * Jun 30, 2014 3:14:28 PM
 */
public class LvScrollDelUI extends BaseUI implements SlideCutListView.RemoveListener {
    private SlideCutListView slideCutListView;
    private ArrayAdapter<String> adapter;
    private List<String> dataSourceList = new ArrayList<String>();

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
        setContentView(R.layout.ui_lvs_scroll_del);
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

        slideCutListView = (SlideCutListView) findViewById(R.id.slideCutListView);
        slideCutListView.setRemoveListener(this);

        for (int i = 0; i < 20; i++) {
            dataSourceList.add("滑动删除" + i);
        }

        adapter = new ArrayAdapter<String>(this, R.layout.ui_lvs_scroll_item, R.id.list_item, dataSourceList);
        slideCutListView.setAdapter(adapter);

        slideCutListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(LvScrollDelUI.this, dataSourceList.get(position) + "onclick()", Toast.LENGTH_SHORT)
                        .show();
            }
        });

    }

    // 滑动删除之后的回调方法
    @Override
    public void removeItem(SlideCutListView.RemoveDirection direction, int position) {
        adapter.remove(adapter.getItem(position));

        switch (direction) {
            case RIGHT:
                Toast.makeText(this, "向右删除  " + position, Toast.LENGTH_SHORT).show();
                break;
            case LEFT:
                Toast.makeText(this, "向左删除  " + position, Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }

    }

}
