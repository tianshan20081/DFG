package com.gooker.dfg.ui.chartengine;


import android.view.View;
import android.widget.Button;

import com.gooker.dfg.R;
import com.gooker.dfg.ui.BaseUI;
import com.gooker.dfg.utils.common.UIUtils;

public class ChartEngineHomeUI extends BaseUI {

	private Button btnSimleChartEngine;

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnSimleChartEngine:
			UIUtils.startActivity(SimpleChartEngineUI.class);
			break;

		default:
			break;
		}
	}

	@Override
	protected void loadViewLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.ui_chartengine_home);
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		btnSimleChartEngine = (Button) findView(R.id.btnSimleChartEngine);
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		btnSimleChartEngine.setOnClickListener(this);
	}

	@Override
	protected void processLogic() {
		// TODO Auto-generated method stub

	}

}
