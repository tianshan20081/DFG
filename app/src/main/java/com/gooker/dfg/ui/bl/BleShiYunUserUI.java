package com.gooker.dfg.ui.bl;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.aoeng.degu.R;
import com.aoeng.degu.adapter.BleSyUserAdapter;
import com.aoeng.degu.application.DGApplication;
import com.aoeng.degu.domain.BleSyUser;
import com.aoeng.degu.ui.BaseUI;
import com.aoeng.degu.ui.bl.BleSyUserUI;
import com.aoeng.degu.utils.common.LogUtils;
import com.chronocloud.ryfibluetoothlibrary.BluetoothOpration;
import com.chronocloud.ryfibluetoothlibrary.entity.TestDataInfo;
import com.chronocloud.ryfibluetoothlibrary.entity.User;
import com.chronocloud.ryfibluetoothlibrary.listener.BluetoothOprationCallback;

public class BleShiYunUserUI extends BaseUI {

	private static final int BLE_SY_USER_REQUEST = 100;
	private BluetoothOpration mBluetoothOpration;
	private ListView lvBleSyUser;
	private List<User> mBleSyUserDatas = new ArrayList<User>();
	private BleSyUserAdapter mBleSyUserAdapter;

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void loadViewLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.ui_ble_sy_users);

		mBluetoothOpration = DGApplication._BluetoothOpration;
		mBluetoothOpration.addBluetoothOprationCallback(mBOcallback);
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		lvBleSyUser = (ListView) findView(R.id.lvBleSyUser);

		mBleSyUserAdapter = new BleSyUserAdapter(context, mBleSyUserDatas);
		lvBleSyUser.setAdapter(mBleSyUserAdapter);
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub

		lvBleSyUser.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				BleSyUser u = (BleSyUser) mBleSyUserDatas.get(position);
				Intent bleUserIntent = new Intent(com.aoeng.degu.ui.bl.BleShiYunUserUI.this, BleSyUserUI.class);
				bleUserIntent.putExtra("blesyuser", u);
				startActivityForResult(bleUserIntent, BLE_SY_USER_REQUEST);

			}
		});

	}

	@Override
	protected void processLogic() {
		// TODO Auto-generated method stub

	}

	protected void setUserData(User user) {
		// TODO Auto-generated method stub
		if (null != user) {
			if (!mBleSyUserDatas.contains(user)) {
				mBleSyUserDatas.add(user);
				mBleSyUserAdapter.notifyDataSetChanged();
			}
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mBluetoothOpration.removeBluetoothOprationCallback(mBOcallback);
	}

	@Override
	protected void onStart() {
		super.onStart();
		if (null != mBluetoothOpration) {
			mBleSyUserAdapter.notifyDataSetChanged();
			mBluetoothOpration.selectAllUser();
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (null != data) {
			if (requestCode == BLE_SY_USER_REQUEST) {

			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	BluetoothOprationCallback mBOcallback = new BluetoothOprationCallback() {

		@Override
		public void onWeight(int staut, double weight) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onUserIsExist(int staut) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onUpdateUser(int staut) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onTestDataInfo(TestDataInfo dataInfo) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onSelectUserScale(List<TestDataInfo> listDataInfo) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onSelectUserScale(int staut) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onSelectAllUser(Context context, Intent intent, List<User> listUser) {
			// TODO Auto-generated method stub

			if (null != listUser && listUser.size() > 0) {
				for (User user : listUser) {
					LogUtils.e("User into toString()" + user.toString());
					setUserData(user);
				}
			}

		}

		@Override
		public void onGetUserInfo(User user) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onDisconnected(Context context, Intent intent) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onDeleteUserScale(int staut) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onDeleteUser(int staut) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onCreateNewUser(int staut) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onConnectSuccess(Context context, Intent intent) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPureGuestMode(int staut) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onQuitPureGuestMode(int staut) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onReadMacAddress(String macAddress) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onResetScaleParam(int staut) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onZero(int staut) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onReadNumber(String number) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onWriteNumber(int staut) {
			// TODO Auto-generated method stub

		}
	};

}
