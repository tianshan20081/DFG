/**
 * 
 */
package com.gooker.dfg.ui.games;

import android.app.Activity;
import android.os.Bundle;

import com.aoeng.degu.views.CompassView;

/**
 * Jun 5, 2014 4:32:29 PM
 * 
 */
public class CompassUI extends Activity {

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(new CompassView(this));
	}
}
