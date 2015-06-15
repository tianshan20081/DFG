package com.gooker.dfg.ui.wv;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.aoeng.degu.R;
import com.aoeng.degu.ui.BaseUI;
import com.aoeng.degu.utils.common.FormatUtils;
import com.aoeng.degu.utils.common.UIUtils;

/**
 * 
 * @author aoeng Aug 13, 2014 12:43:12 AM
 * 
 *         http://blog.csdn.net/wangtingshuai/article/details/8631835
 */
public class JsCallJavaUI extends BaseUI {

	private WebView wvJsCallJava;
	private Button btnJavaCallJs;

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnJavaCallJs:
			// 无参数调用
			wvJsCallJava.loadUrl("javascript:javacalljs()");
			// 传递参数调用
			wvJsCallJava.loadUrl("javascript:javacalljswithargs(" + "'hello world'" + ")");
			break;

		default:
			break;
		}
	}

	@Override
	protected void loadViewLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.ui_wv_jscalljava);
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		wvJsCallJava = (WebView) findViewById(R.id.wvJsCallJava);
		btnJavaCallJs = (Button) findView(R.id.btnJavaCallJs);
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		btnJavaCallJs.setOnClickListener(this);
	}

	@Override
	protected void processLogic() {
		// TODO Auto-generated method stub
		wvJsCallJava.getSettings().setJavaScriptEnabled(true);
		wvJsCallJava.loadUrl("file:///android_asset/jscalljava.html");

		wvJsCallJava.addJavascriptInterface(this, "wst");
	}

	public void startFunction() {
		Toast.makeText(this, "js调用了java函数", Toast.LENGTH_SHORT).show();
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				// msgView.setText(msgView.getText() + "\njs调用了java函数");

			}
		});
	}

	public void callPhone(final String phone) {
		UIUtils.runInMainThread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub

				if (FormatUtils.isPhone(phone)) {
					Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
					UIUtils.startActivity(intent);
				} else {
					UIUtils.toastShow("Please input right phone number !");
					wvJsCallJava.loadUrl("javascript:clearPhoneInput()");
				}

			}
		});

	}

	public void startFunction(final String str) {
		Toast.makeText(this, "\njs调用了java函数传递参数：" + str, Toast.LENGTH_SHORT).show();
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				// msgView.setText(msgView.getText() + "\njs调用了java函数传递参数：" +
				// str);

			}
		});
	}
}
