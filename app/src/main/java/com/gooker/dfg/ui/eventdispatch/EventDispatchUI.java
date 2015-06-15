package com.gooker.dfg.ui.eventdispatch;

import android.content.Intent;
import android.view.View;

import com.aoeng.degu.R;
import com.aoeng.degu.ui.BaseUI;
import com.aoeng.degu.ui.eventdispatch.*;
import com.aoeng.degu.ui.eventdispatch.EventDispatchViewUI;
import com.aoeng.degu.utils.common.UIUtils;

/**
 * 事件分发
 * 
 * @author Shaocheng Zhang Aug 11, 2014 9:42:54 AM 2014
 */
public class EventDispatchUI extends BaseUI {

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnSimpleEventDispatch:
			UIUtils.startActivity(new Intent(UIUtils.getContext(),
					SimpleEventDispatchUI.class));

			break;
		case R.id.btnEventDispatchView:
			UIUtils.startActivity(new Intent(UIUtils.getContext(),
					EventDispatchViewUI.class));
			break;
		default:
			break;
		}
	}

	@Override
	protected void loadViewLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.ui_eventdispatch_home);

	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		findView(R.id.btnSimpleEventDispatch).setOnClickListener(this);
		findView(R.id.btnEventDispatchView).setOnClickListener(this);
	}

	@Override
	protected void processLogic() {
		// TODO Auto-generated method stub

	}

}
