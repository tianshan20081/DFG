/**
 *
 */
package com.gooker.dfg.ui.lvs;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.gooker.dfg.R;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;


/**
 * Apr 15, 2014 5:49:43 PM
 */
public class PullToRefreshUI extends Activity {
    private LinkedList<String> mListItems;
    public static PullToRefreshListView weiboListView;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_lvs_rf_pull_to_refresh);
        weiboListView = (PullToRefreshListView) findViewById(R.id.weibolist);

        // Set a listener to be invoked when the list should be refreshed.
        weiboListView.setOnRefreshListener(new PullToRefreshListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Do work to refresh the list here.
                new GetDataTask(PullToRefreshUI.this, 0).execute();
            }

            @Override
            public void onLoadMore() {
                new GetDataTask(PullToRefreshUI.this, 1).execute();
            }
        });

        mListItems = new LinkedList<String>();
        mListItems.addAll(Arrays.asList(mStrings));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mListItems);

        weiboListView.setAdapter(adapter);
    }

    private class GetDataTask extends AsyncTask<Void, Void, String[]> {
        private Context context;
        private int index;

        public GetDataTask(Context context, int index) {
            this.context = context;
            this.index = index;
        }

        @Override
        protected String[] doInBackground(Void... params) {
            // Simulates a background job.
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                ;
            }
            return mStrings;
        }

        @Override
        protected void onPostExecute(String[] result) {
            if (index == 0) {
                // 将字符串“Added after refresh”添加到顶部
                mListItems.addFirst("Added after refresh...");

                SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日  HH:mm");
                String date = format.format(new Date());
                // Call onRefreshComplete when the list has been refreshed.
                weiboListView.onRefreshComplete(date);
            } else if (index == 1) {
                mListItems.addLast("Added after loadmore...");
                weiboListView.onLoadMoreComplete();
            }

            super.onPostExecute(result);
        }
    }

    public static String[] mStrings = {"一条微博", "两条微博", "三条微博", "四条微博", "五条微博", "六条微博", "七条微博",
            "八条微博", "九条微博", "十条微博", "十一条微博", "十二条微博", "九条微博", "十条微博", "十一条微博", "十二条微博"};

}
