/**
 * 
 */
package com.gooker.dfg.ui.jni;

import java.nio.ByteBuffer;

import android.view.View;

import com.aoeng.degu.R;
import com.aoeng.degu.ui.BaseUI;
import com.gooker.dfg.utils.JniUtils;

/**
 * @author [Aoeng Zhang] @datatime Jul 26, 2013:2:34:42 PM
 * @Email [zhangshch2008@gmail.com] Jul 26, 2013
 */
public class JNIHomeUI extends BaseUI {

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnHello:
			toast(JniUtils.getMsgFromJni());
			break;
		case R.id.btnHelloFromCpp:
			toast(JniUtils.getMsgFromJniCpp());
			break;
		case R.id.btnSum:
			toast(JniUtils.getSum(1, 2) + "");
			break;
		case R.id.btnJieCheng:
			toast(JniUtils.getJieCheng(5) + "");
			break;
		case R.id.btnChange:
			ByteBuffer bf = ByteBuffer.allocateDirect(10);
			ByteBuffer rbf = ByteBuffer.allocateDirect(10);
			;
			toast("交换之前:a=" + bf.getInt() + "   b=" + rbf.getInt());
			JniUtils.swapbf(bf, rbf);
			toast("交换之后 a=" + bf.getInt() + "   b=" + rbf.getInt());
			break;
		case R.id.btnCppLog:
			toast("jni Cpp log");
			JniUtils.getCppLog(1);
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
		setContentView(R.layout.ui_jni_home);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.degu.ui.BaseUI#findViewById()
	 */
	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.degu.ui.BaseUI#setListener()
	 */
	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		findView(R.id.btnHello).setOnClickListener(this);
		findView(R.id.btnHelloFromCpp).setOnClickListener(this);
		findView(R.id.btnSum).setOnClickListener(this);
		findView(R.id.btnJieCheng).setOnClickListener(this);
		findView(R.id.btnChange).setOnClickListener(this);
		findView(R.id.btnCppLog).setOnClickListener(this);
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
