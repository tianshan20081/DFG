/**
 * 
 */
package com.gooker.dfg.ui.cd;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.aoeng.degu.R;
import com.aoeng.degu.utils.common.Toaster;

/**
 * @author [Aoeng Zhang] @datatime Jul 26, 2013:2:38:46 PM
 * @Email [zhangshch2008@gmail.com] Jul 26, 2013
 */
public class CoordinatesUI extends Activity implements OnClickListener {

	private static final String TAG = com.aoeng.degu.ui.cd.CoordinatesUI.class.getName().toUpperCase();

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_cd_home);
		this.findViewById(R.id.btnCoord).setOnClickListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnCoord:
			LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
			Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			String string = "";
			if (null == location) {
				location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
				string += "network";
			}
			string += "Latitude is " + location.getLatitude() + "\n" + "the Longitude is " + location.getLongitude();
			Toaster.toastCenter(this, string, true);
			Toaster.log(TAG, string);
			break;

		default:
			break;
		}
	}

}
