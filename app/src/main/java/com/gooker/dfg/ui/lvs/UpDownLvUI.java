/**
 * 
 */
package com.gooker.dfg.ui.lvs;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aoeng.degu.R;
import com.aoeng.degu.views.RefreshLV;

/**
 * May 16, 2014 5:25:53 PM
 * 
 */
public class UpDownLvUI extends Activity {
	private List<String> listViewData;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_lvs_up_down_more);

		final RefreshLV mRefreshListView = (RefreshLV) findViewById(R.id.refresh_listview);

		listViewData = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			listViewData.add("这是一条ListView的数据" + i);
		}

		final MyAdapter mAdapter = new MyAdapter();
		mRefreshListView.setAdapter(mAdapter);
		mRefreshListView.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				// 异步查询数据
				new AsyncTask<Void, Void, Void>() {

					@Override
					protected Void doInBackground(Void... params) {
						SystemClock.sleep(1000);
						listViewData.add(0, "这是下拉刷新出来BaseAnimation");
						return null;
					}

					protected void onPostExecute(Void result) {
						mAdapter.notifyDataSetChanged();

						// 隐藏头布局
						mRefreshListView.onRefreshFinish();
					}
				}.execute(new Void[] {});
			}

			@Override
			public void onLoadMoring() {
				new AsyncTask<Void, Void, Void>() {

					@Override
					protected Void doInBackground(Void... params) {
						SystemClock.sleep(1000);
						listViewData.add("加载更多刷新出来BaseAnimation1");
						listViewData.add("加载更多刷新出来BaseAnimation2");
						listViewData.add("加载更多刷新出来BaseAnimation3");
						listViewData.add("加载更多刷新出来BaseAnimation4");
						listViewData.add("加载更多刷新出来BaseAnimation5");
						return null;
					}

					@Override
					protected void onPostExecute(Void result) {
						super.onPostExecute(result);
						mAdapter.notifyDataSetChanged();
						mRefreshListView.onRefreshFinish();
					}

				}.execute(new Void[] {});
			}
		});
	}

	/**
	 * 填充适配器
	 * 
	 * @author dg
	 * 
	 */
	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return listViewData.size();
		}

		@Override
		public Object getItem(int arg0) {
			return listViewData.get(arg0);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView tv = null;
			if (convertView == null) {
				tv = new TextView(com.aoeng.degu.ui.lvs.UpDownLvUI.this);
			} else {
				tv = (TextView) convertView;
			}

			tv.setText(listViewData.get(position));
			tv.setTextSize(20);

			return tv;
		}

	}

	public interface OnRefreshListener {

		/**
		 * 下拉刷新执行的刷新任务, 使用时, 当刷新完毕之后, 需要手动的调用onRefreshFinish(), 去隐藏头布局
		 */
		public void onRefresh();

		/**
		 * 当加载更多时回调 当加载更多完毕之后, 需要手动的调用onRefreshFinish(), 去隐藏脚布局
		 */
		public void onLoadMoring();
	}
}
