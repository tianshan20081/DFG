package com.gooker.dfg.ui.logins;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.aoeng.degu.R;
import com.aoeng.degu.ui.logins.LoginQQUI;

public class LoginQQSplashUI extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_logins_qq_splash);

		startMainActivity();
	}

	private void startMainActivity() {
		// TODO Auto-generated method stub
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent intent = new Intent(com.aoeng.degu.ui.logins.LoginQQSplashUI.this, LoginQQUI.class);
				startActivity(intent);
				com.aoeng.degu.ui.logins.LoginQQSplashUI.this.finish();
			}
		}, 3000);
	}
}
