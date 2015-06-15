package com.gooker.dfg.ui.lvs;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aoeng.degu.adapter.ListViewAdapter;
import com.aoeng.degu.utils.common.Toaster;

public class PagesListViewUI extends ListActivity implements OnScrollListener {

	private static final String TAG = com.aoeng.degu.ui.lvs.PagesListViewUI.class.getName().toUpperCase();
	private ListViewAdapter adapter = new ListViewAdapter(com.aoeng.degu.ui.lvs.PagesListViewUI.this);
	private ListView listView;
	private int lastItem = 0;
	private LinearLayout loadingLayout;
	// 设置布局显示属性
	private LayoutParams mLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	// 设置布局显示目标最大化属性
	private LayoutParams ffLayoutParams = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
	private ProgressBar progressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		log("--------------->onCreate(Bundle savedInstanceState)");
		// 现行布局
		LinearLayout layout = new LinearLayout(this);
		// 设置布局水平方向
		layout.setOrientation(LinearLayout.HORIZONTAL);
		// 进度条
		progressBar = new ProgressBar(this);
		// 进度条显示位置
		progressBar.setPadding(0, 0, 15, 0);
		// 把进度条加入到 layout 中
		layout.addView(progressBar, mLayoutParams);
		// 文本内容
		TextView textView = new TextView(this);
		textView.setText("加载中...");
		textView.setGravity(Gravity.CENTER_VERTICAL);
		// 把文本加入到 layout 中
		layout.addView(textView, ffLayoutParams);
		// 设置layout 的重力方向，即对其方式是
		layout.setGravity(Gravity.CENTER);
		// 设置listView 的页脚 layout
		loadingLayout = new LinearLayout(this);
		loadingLayout.addView(layout, mLayoutParams);
		loadingLayout.setGravity(Gravity.CENTER);

		// 得到一个ListView 用来显示条目数目
		listView = getListView();
		// 添加道页脚显示
		listView.addFooterView(loadingLayout);
		// 给Listew 添加适配器
		setListAdapter(adapter);
		// 给listiew 注册滚动监听
		listView.setOnScrollListener(this);

	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub

		log("------onScroll >>FirstItem" + firstVisibleItem + " , visiable: " + visibleItemCount + ", total" + totalItemCount);

		lastItem = firstVisibleItem + visibleItemCount - 1;
		log("------onScroll >>lastItem :" + lastItem);
		// 显示50条listItem 级0-49 因为 onScroll 是在滑动执行过之后才触发，所以用 adapter.count
		// <=41做条件
		if (adapter.count <= 41) {
			if (firstVisibleItem + visibleItemCount == totalItemCount) {
				adapter.count += 10;
				adapter.notifyDataSetChanged();
				listView.setSelection(lastItem);
				int currenPage = adapter.count / 10;
				toast("第" + currenPage + "页");
			}

		} else {
			listView.removeFooterView(loadingLayout);
		}

	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		if (lastItem == adapter.count && scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
			log("------->onScrollStateChanged>> state " + scrollState + "lastItem" + lastItem);
			// 显示50 条ListItem 即 0-49 ,因为onScrollStateChange
			// 是在“拖动滑动”执行过之后才触发，所以用adapter.count <= 41 作为条件
			if (adapter.count <= 41) {
				adapter.count += 10;
				adapter.notifyDataSetChanged();
			}
		}
	}

	private void log(String msg) {
		// TODO Auto-generated method stub
		Toaster.log(TAG, msg);
	}

	private void toast(String msg) {
		// TODO Auto-generated method stub
		Toaster.toastCenter(com.aoeng.degu.ui.lvs.PagesListViewUI.this, msg, true);
	}
}
