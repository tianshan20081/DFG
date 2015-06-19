/**
 *
 */
package com.gooker.dfg.ui.cv;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.gooker.dfg.R;
import com.gooker.dfg.ui.cv.views.BidirSlidingView;


/**
 * @author [Aoeng Zhang] @datatime Sep 2, 2013:4:30:43 PM
 * @Email [zhangshch2008@gmail.com] Sep 2, 2013
 */
public class BidirSlidingUI extends Activity {

    /**
     * 双向滑动菜单布局
     */
    private BidirSlidingView bidirSldingLayout;

    /**
     * 在内容布局上显示的ListView
     */
    private ListView contentList;

    /**
     * ListView的适配器
     */
    private ArrayAdapter<String> contentListAdapter;

    /**
     * 用于填充contentListAdapter的数据源。
     */
    private String[] contentItems = {"Content Item 1", "Content Item 2", "Content Item 3", "Content Item 4", "Content Item 5", "Content Item 6", "Content Item 7", "Content Item 8", "Content Item 9",
            "Content Item 10", "Content Item 11", "Content Item 12", "Content Item 13", "Content Item 14", "Content Item 15", "Content Item 16"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_cv_bidirslid);
        bidirSldingLayout = (BidirSlidingView) findViewById(R.id.bidir_sliding_layout);
        contentList = (ListView) findViewById(R.id.contentList);
        contentListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contentItems);
        contentList.setAdapter(contentListAdapter);
        bidirSldingLayout.setScrollEvent(contentList);
    }

}
