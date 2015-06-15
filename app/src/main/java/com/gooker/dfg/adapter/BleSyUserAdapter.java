package com.gooker.dfg.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aoeng.degu.R;
import com.aoeng.degu.utils.common.UIUtils;
import com.chronocloud.ryfibluetoothlibrary.entity.User;

import java.util.List;

public class BleSyUserAdapter extends BaseAdapter {

	private Context mContext;
	private List<User> mDatas;

	public BleSyUserAdapter(Context context, List<User> datas) {
		// TODO Auto-generated constructor stub
		this.mContext = context;
		this.mDatas = datas;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.mDatas.size();
	}

	@Override
	public User getItem(int position) {
		// TODO Auto-generated method stub
		return this.mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (null != convertView) {
			holder = (ViewHolder) convertView.getTag();
		} else {
			holder = new ViewHolder();
			convertView = UIUtils.inflate(R.layout.ui_ble_sy_users_item);
			holder.tvAccount = (TextView) convertView.findViewById(R.id.tvAccount);
			holder.tvAge = (TextView) convertView.findViewById(R.id.tvAge);
			holder.tvCmdId = (TextView) convertView.findViewById(R.id.tvCmdId);
			holder.tvHeight = (TextView) convertView.findViewById(R.id.tvHeight);
			holder.tvIndex = (TextView) convertView.findViewById(R.id.tvIndex);
			holder.tvNOrder = (TextView) convertView.findViewById(R.id.tvNumericalOder);
			holder.tvSex = (TextView) convertView.findViewById(R.id.tvSex);

			convertView.setTag(holder);
		}
		User user = getItem(position);
		if (null != user) {
			holder.tvAccount.setText("Account(总包数) :" + user.getAccount());
			holder.tvAge.setText("Sex :" + user.getSex());
			holder.tvCmdId.setText("CmdId : " + user.getCmdId());
			holder.tvHeight.setText("Height : " + user.getHeight() + " cm");
			holder.tvIndex.setText("Index(第几包) : " + user.getIndex());
			holder.tvNOrder.setText("NOrder(用户序号) : " + user.getNumericalOder());
			holder.tvSex.setText("Sex : " + ("0".equals(user.getSex()) ? "Woman" : "Man"));

		}

		return convertView;
	}

	private static class ViewHolder {

		public TextView tvIndex;
		public TextView tvAccount;
		public TextView tvCmdId;
		public TextView tvAge;
		public TextView tvHeight;
		public TextView tvNOrder;
		public TextView tvSex;

	}
}
