package com.gooker.dfg.ui.lvs;

import android.view.View;
import android.widget.ListView;

import com.aoeng.degu.R;
import com.aoeng.degu.ui.BaseUI;

public class CommonListViewUI extends com.gooker.dfg.ui.BaseUI {
	private ListView lvCommon;

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	}

	@Override
	protected void loadViewLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.ui_lv_common);
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		lvCommon = (ListView) findViewById(R.id.lvCommon);
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void processLogic() {
		// TODO Auto-generated method stub
//		lvCommon.setAdapter(new );
	}
}
