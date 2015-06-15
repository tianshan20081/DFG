package com.gooker.dfg.ui.uis;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.aoeng.degu.R;
import com.aoeng.degu.utils.common.Toaster;

public class Group3UI extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_uis_group_3);
	}

	public void onClickGU3(View v) {
		Toaster.toast(this, com.aoeng.degu.ui.uis.Group3UI.class.getName().toUpperCase(), true);
	}
}
