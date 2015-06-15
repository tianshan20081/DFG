/**
 * 
 */
package com.gooker.dfg.ui.encryption;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.view.View;

import com.aoeng.degu.R;
import com.aoeng.degu.ui.BaseUI;
import com.aoeng.degu.utils.enc.EncUtils;
import com.aoeng.degu.utils.enc.EncryptionUtils;

/**
 * Jul 22, 2014 1:55:41 PM
 * 
 */
public class EncHomeUI extends BaseUI {

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String src = "test";
		String key = "testtest";
		String encStr;
		switch (v.getId()) {
		case R.id.btnDesEnc:
			try {
				toast("encode ------ >" + EncryptionUtils.getDesEnctry(URLEncoder.encode(src, "gbk"), "testtest"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.btnDesDec:
			toast("desc deconde ");
			try {
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						String des = EncryptionUtils.getDesEnctry("zhangsan", "testtest");
						System.out.println("deconde ----->" + EncryptionUtils.getDesDec(des, "texttext"));
					}
				}).start();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.btnJavaMD5:// java MD5
			String md5Str = EncUtils.getJavaMD5(src);
			toast("MD5 srcStr :" + src + "\n" + "MD5 MD5Str : " + md5Str);
			break;
		case R.id.btnCppMD5:// java MD5
			encStr = EncUtils.getCppMD5(src);
			toast("MD5 md5CppSrc :" + src + "\n" + "MD5 md5CppStr : " + encStr);
			break;
		case R.id.btnJavaMD5Salt:// java MD5
			encStr = EncUtils.getJavaMD5Salt(src,key);
			toast("MD5 md5CppSrc :" + src + "\n" + "MD5 md5CppStr : " + encStr);
			break;
		case R.id.btnCppMD5Salt:// java MD5
			encStr = EncUtils.getCppMD5Salt(src,key);
			toast("MD5 md5CppSrc :" + src + "\n" + "MD5 md5CppStr : " + encStr);
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
		setContentView(R.layout.ui_enc_home);
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
		findView(R.id.btnDesEnc).setOnClickListener(this);
		findView(R.id.btnDesDec).setOnClickListener(this);
		findView(R.id.btnJavaMD5).setOnClickListener(this);
		findView(R.id.btnCppMD5).setOnClickListener(this);
		findView(R.id.btnJavaMD5Salt).setOnClickListener(this);
		findView(R.id.btnCppMD5Salt).setOnClickListener(this);

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
