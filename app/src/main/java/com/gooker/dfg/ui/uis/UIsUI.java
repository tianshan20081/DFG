package com.gooker.dfg.ui.uis;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.aoeng.degu.R;

public class UIsUI extends Activity implements OnClickListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_uis);
		this.findViewById(R.id.btnUICall).setOnClickListener(this);
		this.findViewById(R.id.btnUICallDial).setOnClickListener(this);
		this.findViewById(R.id.btnUIOpenDial).setOnClickListener(this);
		this.findViewById(R.id.btnUISufferInternet).setOnClickListener(this);
		this.findViewById(R.id.btnUIEmailTo).setOnClickListener(this);
		this.findViewById(R.id.btnUISendEmail).setOnClickListener(this);
		this.findViewById(R.id.btnUIContects).setOnClickListener(this);
		this.findViewById(R.id.btnUISysSetting).setOnClickListener(this);
		this.findViewById(R.id.btnUIWifiSetting).setOnClickListener(this);
		this.findViewById(R.id.btnUIAudio).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		Intent intent = null;
		switch (v.getId()) {
		case R.id.btnUIAudio:
			intent = new Intent(Intent.ACTION_GET_CONTENT);
			intent.setType("audio/*");
			startActivity(Intent.createChooser(intent, "请选择音频处理程序"));
			break;
		case R.id.btnUIWifiSetting:
			intent = new Intent("android.settings.WIFI_SETTINGS");
			startActivity(intent);
			break;
		case R.id.btnUISysSetting:
			intent = new Intent("android.settings.SETTINGS");
			startActivity(intent);
			break;
		case R.id.btnUIContects:
			intent = new Intent("com.android.contacts.action.LIST_CONTACTS");
			startActivity(intent);
			break;
		case R.id.btnUISendEmail:
			intent = new Intent(Intent.ACTION_SEND);
			// 收件人
			intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "zhangshch2008@qq.com", "379456800@qq.com" });
			// 抄送
			intent.putExtra(Intent.EXTRA_CC, new String[] { "sczhang0131@163.com", "sczhang@163.com" });
			// 标题
			intent.putExtra(Intent.EXTRA_SUBJECT, "来自 Android 手机的邮件");
			// 邮件的内容
			intent.putExtra(Intent.EXTRA_TEXT, "我是邮件的内容");
			// 设置邮件内容格式 （text/plain） 纯文本
			intent.setType("text/plain");

			startActivity(Intent.createChooser(intent, "请选择邮件发送客户端"));
			break;
		case R.id.btnUIEmailTo:
			// 收件人
			intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:zhangshch2008@gamil.com"));
			// 抄送
			intent.putExtra(Intent.EXTRA_CC, new String[] { "379456800@qq.com" });
			// 密送
			intent.putExtra(Intent.EXTRA_BCC, new String[] { "zhangshch2008@gmail.com" });
			startActivity(intent);
			break;
		case R.id.btnUISufferInternet:
			intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.beijing.com.cn"));
			startActivity(intent);
			break;
		case R.id.btnUIOpenDial:
			intent = new Intent("com.android.phone.action.TOUCH_DIALER");
			startActivity(intent);
			break;
		case R.id.btnUICall:
			intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:13811691807"));
			startActivity(intent);
			break;
		case R.id.btnUICallDial:
			intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:13811691807"));
			startActivity(intent);
		default:
			break;
		}
	}

}
