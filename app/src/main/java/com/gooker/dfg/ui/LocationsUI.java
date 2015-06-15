/**
 * 
 */
package com.gooker.dfg.ui;

import android.app.Activity;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import cn.jpush.android.api.JPushInterface;

import com.aoeng.degu.R;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

/**
 * May 6, 2014 3:42:34 PM
 * 
 */
public class LocationsUI extends Activity implements OnClickListener {
	private TextView tvLocationInfo;
	private LocationClient mLocationClient;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.app.Activity#onCreate
	 * (android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_locations);
		this.findViewById(R.id.btnSysLocation).setOnClickListener(this);
		this.findViewById(R.id.btnBaiduLocation).setOnClickListener(this);
		tvLocationInfo = (TextView) this.findViewById(R.id.tvLocationInfo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.view.View.OnClickListener
	 * #onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnSysLocation:
			LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
			Location location = getLocationProvider(locationManager);
			if (location != null) {
				StringBuffer buffer = new StringBuffer();
				buffer.append("Latitude:").append(Double.valueOf(location.getLatitude()));
				buffer.append("\n");
				buffer.append("Longitude:").append(Double.valueOf(location.getLongitude()));
				tvLocationInfo.setText(buffer.toString());
			} else {
				tvLocationInfo.setText("获取经纬度失败");
			}
			break;
		case R.id.btnBaiduLocation:
			loadLocation();
			mLocationClient.start();
			break;
		default:
			break;
		}
	}

	/**
	 * 
	 */
	private void loadLocation() {
		// TODO Auto-generated method stub
		mLocationClient = new LocationClient(this);
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true); // 打开gps
		option.setCoorType("bd09ll"); // 设置坐标类型为bd09ll
		option.setPriority(LocationClientOption.NetWorkFirst); // 设置网络优先
		option.setProdName("locSDKDemo2"); // 设置产品线名称
		option.setScanSpan(5000); // 定时定位，每隔5秒钟定位一次。
		mLocationClient.setLocOption(option);
		mLocationClient.registerLocationListener(new BDLocationListener() {
			@Override
			public void onReceiveLocation(BDLocation location) {
				if (location == null) return;
				StringBuffer sb = new StringBuffer(256);
				sb.append("time : ");
				sb.append(location.getTime());
				sb.append("\nerror code : ");
				sb.append(location.getLocType());
				sb.append("\nlatitude : ");
				sb.append(location.getLatitude());
				sb.append("\nlontitude : ");
				sb.append(location.getLongitude());
				sb.append("\nradius : ");
				sb.append(location.getRadius());
				if (location.getLocType() == BDLocation.TypeGpsLocation) {
					sb.append("\nspeed : ");
					sb.append(location.getSpeed());
					sb.append("\nsatellite : ");
					sb.append(location.getSatelliteNumber());
				} else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
					sb.append("\naddr : ");
					sb.append(location.getAddrStr());
					sb.append("\nProvince  :").append(location.getProvince() + location.getPoi());
					sb.append("\nCity  :").append(location.getCity() + location.getCityCode());
					sb.append("\nDistrict  :").append(location.getDistrict());
					sb.append("\nStreet  :").append(location.getStreet());
				}
				sb.append("\nsdk version : ");
				sb.append(mLocationClient.getVersion());
				tvLocationInfo.setText(sb.toString());
				mLocationClient.stop();
			}

			public void onReceivePoi(BDLocation location) {
				// return ;
			}
		});
	}

	/**
	 * @param locationManager
	 * @return
	 */
	private Location getLocationProvider(LocationManager locationManager) {
		// TODO Auto-generated method stub
		Location location = null;
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setBearingRequired(false);
		criteria.setPowerRequirement(Criteria.POWER_LOW);
		String reLocationProvider = locationManager.getBestProvider(criteria, true);
		location = locationManager.getLastKnownLocation(reLocationProvider);
		return location;
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		JPushInterface.onResume(this);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		JPushInterface.onPause(this);
	}
}
