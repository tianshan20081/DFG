package com.gooker.dfg.ui.nt;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gooker.dfg.R;
import com.gooker.dfg.utils.common.Toaster;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;


public class NetWorkUI extends Activity implements OnClickListener {
	private static final String TAG = NetWorkUI.class.getName().toUpperCase();
	private BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			Toaster.toastCenter(NetWorkUI.this, "可用端口：" + msg.what, false);
		};
	};
	private Button btnScanServerPort;
	private EditText etClientInfo;
	private EditText etPhoneIP;
	private EditText etPhonePort;
	private Button btnStartPhoneShare;
	private Button btnStopPhoneShare;

	protected ServerSocket serverSocket;
	private EditText etPhone;
	private TextView tvPhoneInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_nt_home);
		btnScanServerPort = (Button) this.findViewById(R.id.btnScanServerPort);
		this.findViewById(R.id.btnScanServerPort).setOnClickListener(this);
		etClientInfo = (EditText) this.findViewById(R.id.etClientInfo);
		this.findViewById(R.id.btnSendMsgFromClient).setOnClickListener(this);
		this.findViewById(R.id.btnWifiIP).setOnClickListener(this);

		etPhoneIP = (EditText) this.findViewById(R.id.etPhoneIP);
		etPhonePort = (EditText) this.findViewById(R.id.etPhonePort);
		btnStartPhoneShare = (Button) this.findViewById(R.id.btnStartPhoneShare);
		btnStopPhoneShare = (Button) this.findViewById(R.id.btnStopPhoneShare);
		btnStartPhoneShare.setEnabled(false);
		btnStopPhoneShare.setEnabled(false);
		this.findViewById(R.id.btnStartPhoneShare).setOnClickListener(this);
		this.findViewById(R.id.btnStopPhoneShare).setOnClickListener(this);
		this.findViewById(R.id.btnGetPhoneAddress).setOnClickListener(this);
		this.findViewById(R.id.btnOpenBlueTooth).setOnClickListener(this);
		this.findViewById(R.id.btnOpenBlueTooth2).setOnClickListener(this);
		this.findViewById(R.id.btnCloseBlueTooth).setOnClickListener(this);

		etPhone = (EditText) this.findViewById(R.id.etPhone);
		tvPhoneInfo = (TextView) this.findViewById(R.id.tvPhoneInfo);

		this.findViewById(R.id.btnWebService).setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnWebService:// 通过 WebService 获取得到 手机号码对数第

			String phone = etPhone.getText().toString().trim();
			if (null == phone || "".equals(phone)) {
				toast("请输入正确的手记号吗");
				return;
			}
			// getPhoneInfo(phone);
			break;
		case R.id.btnOpenBlueTooth:
			if (!bluetoothAdapter.isEnabled()) {
				Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
				startActivityForResult(intent, 1);
				this.findViewById(R.id.btnOpenBlueTooth).setEnabled(false);
				this.findViewById(R.id.btnOpenBlueTooth2).setEnabled(false);
			} else {

			}

			break;
		case R.id.btnOpenBlueTooth2:
			if (!bluetoothAdapter.isEnabled()) {
				bluetoothAdapter.enable();
				this.findViewById(R.id.btnOpenBlueTooth).setEnabled(false);
				this.findViewById(R.id.btnOpenBlueTooth2).setEnabled(false);
			} else {

			}

			break;
		case R.id.btnCloseBlueTooth:
			if (bluetoothAdapter.isEnabled()) {
				bluetoothAdapter.disable();
				this.findViewById(R.id.btnOpenBlueTooth).setEnabled(true);
				this.findViewById(R.id.btnOpenBlueTooth2).setEnabled(true);
			} else {

			}

			break;
		case R.id.btnStopPhoneShare:
			try {
				serverSocket.close();
			} catch (IOException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			btnStartPhoneShare.setEnabled(true);
			break;
		case R.id.btnStartPhoneShare:
			final String port = etPhonePort.getText().toString().trim();
			final String addressIP = etPhoneIP.getText().toString().trim();
			if (TextUtils.isEmpty(port)) {
				Toaster.toastCenter(this, "请输入端口号!", false);
				return;
			}
			new Thread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						serverSocket = new ServerSocket();
						serverSocket.bind(new InetSocketAddress(addressIP, Integer.valueOf(port)));
						while (true) {
							Socket socket = serverSocket.accept();
							TelephonyManager telephonyManager = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);
							socket.getOutputStream().write(("设备编号为：" + String.valueOf(telephonyManager.getDeviceId().hashCode()).hashCode()).getBytes("UTF-8"));
							socket.close();
						}

					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					Toaster.toast(com.aoeng.degu.ui.nt.NetWorkUI.this, "分享成功!", false);
				}
			}).start();
			btnStopPhoneShare.setEnabled(true);
			btnStartPhoneShare.setEnabled(false);
			break;
		case R.id.btnGetPhoneAddress:
			try {
				List<String> ipList = new ArrayList<String>();
				Enumeration<NetworkInterface> enumeration = NetworkInterface.getNetworkInterfaces();
				while (enumeration.hasMoreElements()) {
					NetworkInterface networkInterface = (NetworkInterface) enumeration.nextElement();
					Enumeration<InetAddress> ips = networkInterface.getInetAddresses();
					while (ips.hasMoreElements()) {
						ipList.add(ips.nextElement().getHostAddress());
					}
				}
				String ipStr = "";
				for (String string : ipList) {
					if (!string.contains("127.0.0.1")) {
						etPhoneIP.setText(string);
						Toaster.log(TAG, string);
					}
				}
				if (TextUtils.isEmpty(etPhoneIP.getText().toString().trim())) {
					Toaster.toastCenter(this, "请链接无线路由器，再获得链接地址！", true);
					return;
				}
				etPhoneIP.setText("192.168.7.70");
				btnStartPhoneShare.setEnabled(true);
			} catch (SocketException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case R.id.btnWifiIP:
			try {
				List<String> ipList = new ArrayList<String>();
				Enumeration<NetworkInterface> enumeration = NetworkInterface.getNetworkInterfaces();
				while (enumeration.hasMoreElements()) {
					NetworkInterface networkInterface = (NetworkInterface) enumeration.nextElement();
					Enumeration<InetAddress> ips = networkInterface.getInetAddresses();
					while (ips.hasMoreElements()) {
						ipList.add(ips.nextElement().getHostAddress());
					}
				}
				String ipStr = "";
				for (String string : ipList) {
					ipStr += string + "\n";
				}
				Toaster.toastCenter(this, ipStr, false);
				Toaster.log(TAG, ipStr);
			} catch (SocketException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case R.id.btnSendMsgFromClient:
			String msg = etClientInfo.getText().toString().trim();
			if (TextUtils.isEmpty(msg)) {
				Toaster.toast(this, "Please input the Send message !", false);
				return;
			}
			try {
				Socket socket = new Socket("192.168.7.247", 10000);
				OutputStream os = socket.getOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(os);
				BufferedWriter bufferedWriter = new BufferedWriter(osw);
				bufferedWriter.write(msg);
				bufferedWriter.flush();

				InputStream in = socket.getInputStream();
				InputStreamReader ins = new InputStreamReader(in);
				BufferedReader bufferedReader = new BufferedReader(ins);
				String serverMsg = "";
				String msgStr = "";
				while ((msgStr = bufferedReader.readLine()) != null) {
					serverMsg += msgStr;
					Toaster.toast(this, msgStr, false);
				}
				Toaster.toast(this, serverMsg, false);
			} catch (Exception e) {
				// TODO: handle exception
			}

			break;
		case R.id.btnScanServerPort:
			new Thread(new ScanPorts(1, 65535)).start();
			Toaster.toastCenter(this, "开始扫描", false);
			btnScanServerPort.setEnabled(false);
			break;

		default:
			break;
		}
	}

	/*
	 * private void getPhoneInfo(String phone) { // TODO Auto-generated method
	 * stub new AsyncTask<Void, Void, Void>() { private ProgressDialog dialog;
	 * 
	 * protected void onPreExecute() { dialog =
	 * ViewUtils.getPro(NetWorkUI.this,"數據加載中..."); dialog.show(); };
	 * 
	 * @Override protected Void doInBackground(Void... params) { // TODO
	 * Auto-generated method stub String nameSpace = "http://phone.centen.com";
	 * String methodName = "LoginCertification"; String endPoint =
	 * "http://192.168.0.94:83/phoneService/services/PhoneWebService";
	 * 
	 * String soapAction = "http://phone.centen.com/LoginCertification";
	 * 
	 * // 指定WebService的命名空间和调用的方法名 SoapObject rpc = new SoapObject(nameSpace,
	 * methodName);
	 * 
	 * 
	 * // 设置需调用WebService接口需要传入的两个参数mobileCode、userId rpc.addProperty("userId",
	 * "系统管理员"); rpc.addProperty("psw", "123");
	 * 
	 * 
	 * // 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本 SoapSerializationEnvelope
	 * envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
	 * 
	 * 
	 * envelope.bodyOut = rpc; // 设置是否调用的是dotNet开发的WebService envelope.dotNet =
	 * false; // 等价于envelope.bodyOut = rpc; envelope.setOutputSoapObject(rpc);
	 * 
	 * 
	 * HttpTransportSE transport = new HttpTransportSE(endPoint); try { //
	 * 调用WebService transport.call(soapAction, envelope); } catch (Exception e)
	 * { e.printStackTrace(); } // 获取返回的数据 SoapObject object = (SoapObject)
	 * envelope.bodyIn; // 获取返回的结果 String result =
	 * object.getProperty(0).toString();
	 * 
	 * System.err.println(">>>>>>>>>>>>>"+result); return null; } protected void
	 * onPostExecute(Void result) { ViewUtils.dismiss(dialog);
	 * tvPhoneInfo.setText(text); }; }.execute(null); }
	 */
	private void toast(String msg) {
		// TODO Auto-generated method stub
		Toaster.toastCenter(com.aoeng.degu.ui.nt.NetWorkUI.this, msg, true);
	}

	class ScanPorts extends Thread {
		private int minPort, maxPort;
		private String str = "可用端口有：";

		public ScanPorts(int minPort, int maxPort) {
			this.maxPort = maxPort;
			this.minPort = minPort;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub

			for (int i = minPort; i < maxPort; i++) {
				try {
					Socket socket = new Socket();
					SocketAddress socketAddress = new InetSocketAddress("210.73.90.26", i);
					socket.connect(socketAddress, 50);
					Toaster.toastCenter(com.aoeng.degu.ui.nt.NetWorkUI.this, "可用端口" + i, false);
					handler.sendEmptyMessage(i);
					socket.close();
					str += i + ";";
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			handler.post(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					Toaster.toastCenter(com.aoeng.degu.ui.nt.NetWorkUI.this, "扫描完成" + str, false);
					Toaster.log(com.aoeng.degu.ui.nt.NetWorkUI.class.getName().toUpperCase(), str);
					btnScanServerPort.setEnabled(true);
				}
			});
			super.run();
		}
	}
}
