package com.gooker.dfg.ui.shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.omg.SendingContext.RunTime;

import android.view.View;

import com.aoeng.degu.R;
import com.aoeng.degu.ui.BaseUI;
import com.aoeng.degu.utils.common.IOUtils;
import com.aoeng.degu.utils.common.LogUtils;
import com.aoeng.degu.utils.common.StringUtils;

public class ShellHomeUI extends com.gooker.dfg.ui.BaseUI {

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnWifiInfo:
			String shell = "cat /data/misc/wifi/wpa_supplicant.conf > /mnt/sdcard/df/wifi.log";
			String result = execute(shell);
			if (!com.gooker.dfg.utils.common.StringUtils.isEmpty(result)) {
				com.gooker.dfg.utils.common.LogUtils.e("----->" + result);
			}
			break;

		default:
			break;
		}
	}

	private String execute(String shell) {
		// TODO Auto-generated method stub
		Runtime runTime = Runtime.getRuntime();
		try {
			Process process = runTime.exec(shell);
			InputStream is = process.getInputStream();
			InputStream errIs = process.getErrorStream();
			String errMsg = com.gooker.dfg.utils.common.IOUtils.getStrFromInputStream(errIs);
			com.gooker.dfg.utils.common.LogUtils.e("error msg" + errMsg);
			String msg = com.gooker.dfg.utils.common.IOUtils.getStrFromInputStream(is);
			return msg;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void loadViewLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.ui_shell_home);

	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		findView(R.id.btnWifiInfo).setOnClickListener(this);
	}

	@Override
	protected void processLogic() {
		// TODO Auto-generated method stub

	}

}
