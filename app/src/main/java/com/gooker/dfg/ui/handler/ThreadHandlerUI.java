package com.gooker.dfg.ui.handler;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;

import com.aoeng.degu.R;
import com.aoeng.degu.ui.BaseUI;
import com.aoeng.degu.ui.handler.*;
import com.aoeng.degu.ui.handler.ReferenceHandlerUI;
import com.aoeng.degu.utils.common.Logger;

public class ThreadHandlerUI extends BaseUI {
	private static final int SEND_HELLO = 2;
	private Handler mHandler;

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnHandlerHeelo:
			toast("HELLO");
			mHandler.obtainMessage(SEND_HELLO, "hello").sendToTarget();
			Logger.e(TAG, "HELLO" + Thread.currentThread().getName());
			Logger.e(TAG, "HELLO" + Thread.currentThread().getId());
			break;
		case R.id.btnRefHandler:
			Intent intent = new Intent(com.aoeng.degu.ui.handler.ThreadHandlerUI.this, ReferenceHandlerUI.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

	@Override
	protected void loadViewLayout() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		setContentView(R.layout.ui_handler_thread);
		new CustomerThread().start();
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		findView(R.id.btnHandlerHeelo).setOnClickListener(this);
		findView(R.id.btnRefHandler).setOnClickListener(this);
	}

	@Override
	protected void processLogic() {
		// TODO Auto-generated method stub
	}

	public class CustomerThread extends Thread {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Looper.prepare();
			mHandler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					// TODO Auto-generated method stub
					switch (msg.what) {
					case SEND_HELLO:
						toast((String) msg.obj + Thread.currentThread().getName());
						Logger.e(TAG, (String) msg.obj + Thread.currentThread().getName());
						Logger.e(TAG, (String) msg.obj + Thread.currentThread().getId());
						break;
					default:
						break;
					}
					super.handleMessage(msg);
				}
			};
		}
	}
}
