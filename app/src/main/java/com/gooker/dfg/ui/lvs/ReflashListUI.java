/**
 * 
 */
package com.gooker.dfg.ui.lvs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aoeng.degu.R;
import com.aoeng.degu.ui.lvs.*;
import com.aoeng.degu.ui.lvs.NLPullRefreshView;
import com.aoeng.degu.ui.lvs.NLPullRefreshView.RefreshListener;

/**
 * Apr 15, 2014 5:19:04 PM
 * 
 */
public class ReflashListUI extends Activity implements RefreshListener {

	private Context mContext;
	private com.aoeng.degu.ui.lvs.NLPullRefreshView mPullRefreshView;
	private ListView mListView;

	Handler handler = new Handler() {
		public void handleMessage(Message message) {
			super.handleMessage(message);
			mPullRefreshView.finishRefresh();
			refreshCount++;
			((MyAdapter) (mListView.getAdapter())).notifyDataSetChanged();
			Toast.makeText(mContext, "数据刷新", Toast.LENGTH_SHORT).show();
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_lvs_rflv);
		init();

	}

	private void init() {
		mContext = this;
		mPullRefreshView = (com.aoeng.degu.ui.lvs.NLPullRefreshView) findViewById(R.id.refresh_root);
		mListView = (ListView) findViewById(R.id.lottery_list);
		mPullRefreshView.setRefreshListener(this);
		mListView.setAdapter(new MyAdapter());
	}

	static int refreshCount = 0;// 数据刷新标记

	/**
	 *
	 * @CreateDate: 2013-4-10 下午1:35:41
	 * @version 1.0.0
	 *
	 */
	class MyAdapter extends BaseAdapter {

		int size = 20;

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return size;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			String tag = "第" + refreshCount + "刷新:";
			if (arg1 == null) {
				arg1 = new TextView(mContext);
			}

			((TextView) arg1).setText(tag + arg0 + "---点击查看scrollView的下拉刷新");
			((TextView) arg1).setTextSize(20f);
			arg1.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent intent = new Intent(mContext, RefreshScrollUI.class);
					mContext.startActivity(intent);
				}
			});
			return arg1;
		}

	}

	/*-----------刷新接口方法实现-------	*/
	static int count = 0;

	@Override
	public void onRefresh(NLPullRefreshView view) {
		// 伪处理
		handler.sendEmptyMessageDelayed(1, 5000);

	}

}
