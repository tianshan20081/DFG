/**
 * 
 */
package com.gooker.dfg.ui.security;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.aoeng.degu.R;
import com.aoeng.degu.ui.BaseUI;
import com.aoeng.degu.utils.common.Logger;

/**
 * May 22, 2014 1:57:19 PM
 * 
 */
public class CheckEmuUI extends BaseUI {

	private TextView tvInfo;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.degu.ui.BaseUI#loadViewLayout()
	 */
	@Override
	protected void loadViewLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.ui_security_checkemu);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.degu.ui.BaseUI#findViewById()
	 */
	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		tvInfo = (TextView) findView(R.id.tvInfo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.degu.ui.BaseUI#setListener()
	 */
	@Override
	protected void setListener() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aoeng.degu.ui.BaseUI#processLogic()
	 */
	@Override
	protected void processLogic() {
		// TODO Auto-generated method stub

		if (isRunningInEmualtor()) { // 检测模拟器
			tvInfo.setTextColor(Color.RED);
			tvInfo.setText("程序运行在模拟器中！");
		} else {
			tvInfo.setTextColor(Color.GREEN);
			tvInfo.setText("程序运行在真实设备中！");
		}
	}

	boolean isRunningInEmualtor() {
		boolean qemuKernel = false;
		Process process = null;
		DataOutputStream os = null;
		try {
			process = Runtime.getRuntime().exec("getprop ro.kernel.qemu");
			os = new DataOutputStream(process.getOutputStream());
			BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream(),
					"GBK"));
			os.writeBytes("exit\n");
			os.flush();
			process.waitFor();
			qemuKernel = (Integer.valueOf(in.readLine()) == 1);
			Logger.d("com.droider.checkqemu", "检测到模拟器:" + qemuKernel);
		} catch (Exception e) {
			qemuKernel = false;
			Logger.d("com.droider.checkqemu", "run failed" + e.getMessage());
		} finally {
			try {
				if (os != null) {
					os.close();
				}
				process.destroy();
			} catch (Exception e) {

			}
			Logger.d("com.droider.checkqemu", "run finally");
		}
		return qemuKernel;
	}

	public static String getProp(Context context, String property) {
		try {
			ClassLoader cl = context.getClassLoader();
			Class SystemProperties = cl.loadClass("android.os.SystemProperties");
			Method method = SystemProperties.getMethod("get", String.class);
			Object[] params = new Object[1];
			params[0] = new String(property);
			return (String) method.invoke(SystemProperties, params);
		} catch (Exception e) {
			return null;
		}
	}

}
