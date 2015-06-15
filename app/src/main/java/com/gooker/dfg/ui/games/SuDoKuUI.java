/**
 * 
 */
package com.gooker.dfg.ui.games;

import android.app.Activity;
import android.os.Bundle;

import com.aoeng.degu.views.SuDoKuView;

/**
 * Jun 3, 2014 11:48:48 AM
 * 
 */
public class SuDoKuUI extends Activity {

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(new SuDoKuView(this));
	}
}
