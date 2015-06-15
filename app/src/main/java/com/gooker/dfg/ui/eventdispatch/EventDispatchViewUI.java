package com.gooker.dfg.ui.eventdispatch;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;

import com.aoeng.degu.R;
import com.aoeng.degu.ui.BaseUI;
import com.aoeng.degu.utils.common.Logger;
import com.aoeng.degu.utils.common.UIUtils;
import com.aoeng.degu.views.EventDispatchView;

public class EventDispatchViewUI extends BaseUI {

	private EventDispatchView edView;
	private Button btnClick2;
	private Button btnClick1;

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void loadViewLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.ui_eventdispatch_view);
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		edView = (EventDispatchView) findView(R.id.edView);
		btnClick1 = (Button) findView(R.id.btnClick1);
		btnClick2 = (Button) findView(R.id.btnClick2);
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		edView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				Logger.i(TAG, "onTouch----" + event.getAction());
				UIUtils.getToastSafe("onTouch----" + event.getAction());
				return false;
			}
		});
		btnClick1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Logger.i(TAG, "btnClick1----onClick");
				UIUtils.getToastSafe("btnClick1----onClick");
			}
		});
		btnClick2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Logger.i(TAG, "btnClick2----onClick");
				UIUtils.getToastSafe("btnClick2----onClick");
			}
		});
	}

	@Override
	protected void processLogic() {
		// TODO Auto-generated method stub

	}

}
