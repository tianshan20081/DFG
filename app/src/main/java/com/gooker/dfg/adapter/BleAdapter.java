package com.gooker.dfg.adapter;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aoeng.degu.R;
import com.aoeng.degu.utils.common.StringUtils;
import com.aoeng.degu.utils.common.UIUtils;

import java.util.ArrayList;
import java.util.List;

public class BleAdapter extends BaseAdapter {

	private Context mContext;
	private List<BluetoothDevice> mDatas ;
	private List<Integer> mRssi = new ArrayList<Integer>();
	private List<byte[]> mRecord = new ArrayList<byte[]>();
	
	public BleAdapter(Context context, List<BluetoothDevice> datas) {
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
	public BluetoothDevice getItem(int position) {
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
		ViewHolder holder = null ;
		if (null != convertView) {
			holder = (ViewHolder) convertView.getTag();
		}else {
			convertView = UIUtils.inflate(R.layout.ui_ble_home_item);
			holder = new ViewHolder();
			holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
			holder.tvMac = (TextView) convertView.findViewById(R.id.tvMac);
			
			convertView.setTag(holder);
		}
		BluetoothDevice device = getItem(position);
		if (null != device) {
			if (!StringUtils.isEmpty(device.getName())) {
				holder.tvName.setText(device.getName());	
			}else {
				holder.tvName.setText("unKnow Name");
			}
			if (!StringUtils.isEmpty(device.getAddress())) {
				holder.tvMac.setText(device.getAddress());
			}else {
				holder.tvMac.setText("unKnow Address");
			}
			
		}
		return convertView;
	}
	
	private static class ViewHolder{
		public TextView tvName ;
		public TextView tvMac ;
//		public TextView tvRssi ;
	}

	public void add(BluetoothDevice device, int rssi, byte[] scanRecord) {
		// TODO Auto-generated method stub
		if (null == this.mDatas) {
			this.mDatas = new ArrayList<BluetoothDevice>();
		}
		if (!this.mDatas.contains(device)) {
			this.mDatas.add(device);
			this.mRssi.add(rssi);
			this.mRecord.add(scanRecord);
			notifyDataSetChanged();
		}
	}

	public BluetoothDevice getDevice(int position) {
		// TODO Auto-generated method stub
		
		return this.mDatas.get(position);
	}

}
