package com.gooker.jdui.ui;

import android.os.Bundle;
import android.view.View;

import com.gooker.jdui.R;
import com.gooker.jdui.ui.base.BaseActivity;


/**
 * @author Tau.Chen 陈涛
 * 
 * @email tauchen1990@gmail.com,1076559197@qq.com
 * 
 * @date 2013年9月21日
 * 
 * @version V_1.0.0
 * 
 * @description
 * 
 */
public class CartActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		View view = new View(this);
		view.setBackgroundColor(R.color.red);
		setContentView(view);
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub

	}

}
