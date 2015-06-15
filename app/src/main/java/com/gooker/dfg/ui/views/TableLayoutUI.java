/**
 * 
 */
package com.gooker.dfg.ui.views;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.aoeng.degu.R;
import com.aoeng.degu.ui.BaseUI;

/**
 * Jul 16, 2014 11:22:05 AM
 * 
 */
public class TableLayoutUI extends BaseUI {

	private TableLayout layout;
	private int k;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnTlAdd:

			TableRow row = new TableRow(this);
			TextView textView1 = new TextView(this);
			textView1.setText("社区" + ++k);
			textView1.setTextSize(11);
			textView1.setBackgroundResource(R.drawable.table_view);
			textView1.setGravity(Gravity.CENTER);
			row.addView(textView1);

			TextView textView2 = new TextView(this);
			textView2.setText(k+"");
			textView2.setTextSize(11);
			textView2.setBackgroundResource(R.drawable.table_view);
			textView2.setGravity(Gravity.CENTER);
			row.addView(textView2);

			TextView textView3 = new TextView(this);
			textView3.setText("6");
			textView3.setTextSize(11);
			textView3.setBackgroundResource(R.drawable.table_view);
			textView3.setGravity(Gravity.CENTER);
			row.addView(textView3);

			layout.addView(row, new TableLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT));
			break;

		default:
			break;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.degu.ui.BaseUI#loadViewLayout()
	 */
	@Override
	protected void loadViewLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.ui_views_tablelayout);

		k = 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.degu.ui.BaseUI#findViewById()
	 */
	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		layout = (TableLayout) findViewById(R.id.work_censustable_id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.degu.ui.BaseUI#setListener()
	 */
	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		findView(R.id.btnTlAdd).setOnClickListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.degu.ui.BaseUI#processLogic()
	 */
	@Override
	protected void processLogic() {
		// TODO Auto-generated method stub

	}

}
