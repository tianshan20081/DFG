package com.gooker.dfg.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

//用于数据生成
public class ListViewAdapter extends BaseAdapter {
	public int count = 10 ;
	private Context context ;
	public ListViewAdapter(Context context){
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return count;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		TextView view;
		if (convertView == null) {
			view = new TextView(this.context);
		}else {
			view = (TextView) convertView;
		}
		view.setText("ListItem "+position);
		view.setTextSize(20f);
		view.setGravity(Gravity.CENTER);
		view.setHeight(80);
		
		return view;
	}

	
}
