/**
 * 
 */
package com.gooker.dfg.ui.lvs;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aoeng.degu.R;
import com.aoeng.degu.views.ListViewCompat;
import com.aoeng.degu.views.SlideView;
import com.aoeng.degu.views.SlideView.OnSlideListener;

/**
 * May 16, 2014 4:39:55 PM
 * 
 */
public class MovingDelItemLvUI extends Activity implements OnItemClickListener, OnClickListener,
		OnSlideListener {
	private com.gooker.dfg.views.ListViewCompat mListView;
	private List<MessageItem> mMessageItems = new ArrayList<MessageItem>();
	private SlideAdapter adapter;
	private com.gooker.dfg.views.SlideView mLastSlideViewWithStatusOn;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_lvs_moving_del);

		initView();
	}

	/**
	 * 
	 */
	private void initView() {
		mListView = (com.gooker.dfg.views.ListViewCompat) findViewById(R.id.list);

		for (int i = 0; i < 20; i++) {
			MessageItem item = new MessageItem();
			if (i % 3 == 0) {
				item.iconRes = R.drawable.delete_default_qq_avatar;
				item.title = "腾讯新闻";
				item.msg = "青岛爆炸满月：大量鱼虾死亡";
				item.time = "晚上18:18";
			} else {
				item.iconRes = R.drawable.delete_wechat_icon;
				item.title = "微信团队";
				item.msg = "欢迎你使用微信";
				item.time = "12月18日";
			}
			mMessageItems.add(item);
		}

		adapter = new SlideAdapter();
		mListView.setAdapter(adapter);
		mListView.setOnItemClickListener(this);

	}

	private class SlideAdapter extends BaseAdapter {

		private LayoutInflater mInflater;

		SlideAdapter() {
			super();
			mInflater = getLayoutInflater();
		}

		@Override
		public int getCount() {
			return mMessageItems.size();
		}

		@Override
		public Object getItem(int position) {
			return mMessageItems.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			com.gooker.dfg.views.SlideView slideView = (com.gooker.dfg.views.SlideView) convertView;
			if (slideView == null) {
				View itemView = mInflater.inflate(R.layout.ui_lvs_moving_del_item, null);

				slideView = new com.gooker.dfg.views.SlideView(MovingDelItemLvUI.this);
				slideView.setContentView(itemView);

				holder = new ViewHolder(slideView);
				slideView.setOnSlideListener(MovingDelItemLvUI.this);
				slideView.setTag(holder);
			} else {
				holder = (ViewHolder) slideView.getTag();
			}
			MessageItem item = mMessageItems.get(position);
			item.slideView = slideView;
			item.slideView.shrink();

			holder.icon.setImageResource(item.iconRes);
			holder.title.setText(item.title);
			holder.msg.setText(item.msg);
			holder.time.setText(item.time);
			holder.deleteHolder.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					mMessageItems.remove(position);
					adapter.notifyDataSetChanged();
					Toast.makeText(MovingDelItemLvUI.this, "删除第" + position + "个条目", 0).show();
				}
			});

			return slideView;
		}

	}

	public class MessageItem {
		public int iconRes;
		public String title;
		public String msg;
		public String time;
		public com.gooker.dfg.views.SlideView slideView;
	}

	private static class ViewHolder {
		public ImageView icon;
		public TextView title;
		public TextView msg;
		public TextView time;
		public ViewGroup deleteHolder;

		ViewHolder(View view) {
			icon = (ImageView) view.findViewById(R.id.icon);
			title = (TextView) view.findViewById(R.id.title);
			msg = (TextView) view.findViewById(R.id.msg);
			time = (TextView) view.findViewById(R.id.time);
			deleteHolder = (ViewGroup) view.findViewById(R.id.holder);
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Toast.makeText(this, "onItemClick position=" + position, 0).show();

	}

	@Override
	public void onSlide(View view, int status) {
		if (mLastSlideViewWithStatusOn != null && mLastSlideViewWithStatusOn != view) {
			mLastSlideViewWithStatusOn.shrink();
		}

		if (status == SLIDE_STATUS_ON) {
			mLastSlideViewWithStatusOn = (com.gooker.dfg.views.SlideView) view;
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.holder:

			break;

		default:
			break;
		}
	}
}
