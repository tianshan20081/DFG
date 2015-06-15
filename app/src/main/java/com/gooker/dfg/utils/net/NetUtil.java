/**
 * 
 */
package com.gooker.dfg.utils.net;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.aoeng.degu.R;
import com.aoeng.degu.domain.LogFileUploadResult;
import com.aoeng.degu.services.DataCallback;
import com.aoeng.degu.utils.FileUploadVO;
import com.aoeng.degu.utils.RequestVO;
import com.aoeng.degu.utils.common.Logger;
import com.aoeng.degu.utils.common.UIUtils;
import com.aoeng.degu.utils.net.*;
import com.aoeng.degu.utils.net.HttpUtils;

/**
 * Mar 23, 2014 11:57:37 AM
 * 
 */
public class NetUtil {
	private static final String NOT_LOGIN = "notlogin";
	private static final String TAG = "NetUtil";
	private static Header[] headers = new BasicHeader[11];
	static {
		headers[0] = new BasicHeader("Appkey", "");
		headers[1] = new BasicHeader("Udid", "");
		headers[2] = new BasicHeader("Os", "");
		headers[3] = new BasicHeader("Osversion", "");
		headers[4] = new BasicHeader("Appversion", "");
		headers[5] = new BasicHeader("Sourceid", "");
		headers[6] = new BasicHeader("Ver", "");
		headers[7] = new BasicHeader("Userid", "");
		headers[8] = new BasicHeader("Usersession", "");
		headers[9] = new BasicHeader("Unique", "");
		headers[10] = new BasicHeader("Cookie", "");

	}

	/*
	 * 
	 */
	public static Object post(RequestVO vo) {
		HttpClient client = HttpUtils.getNewHttpClient();
		String url = vo.requestUrl;
		Logger.i(TAG, "Post " + url);
		HttpPost post = new HttpPost(url);
		// post.setHeaders(headers);
		Object obj = null;
		try {
			MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
			StringBuffer buffer = new StringBuffer("?");
			if (vo.requestDataMap != null) {
				HashMap<String, String> map = vo.requestDataMap;

				for (Entry<String, String> entry : map.entrySet()) {
					buffer.append(entry.getKey() + "=" + entry.getValue()).append("&");
					entityBuilder.addTextBody(entry.getKey(), entry.getValue(), ContentType.create("text/plain", Consts.UTF_8));
				}

			}
			if (vo instanceof FileUploadVO) {
				FileUploadVO fvo = (FileUploadVO) vo;
				if (fvo.fileFieldMap != null) {
					for (Entry<String, String> entry : fvo.fileFieldMap.entrySet()) {
						entityBuilder.addBinaryBody("upload", new File(entry.getValue()));
						buffer.append(entry.getKey()).append("&");
					}

				}
			}
			Logger.i(TAG, buffer.toString());
			post.setEntity(entityBuilder.build());
			HttpResponse response = client.execute(post);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				setCookie(response);
				String result = EntityUtils.toString(response.getEntity(), "UTF-8");
				Logger.i(TAG, result);
				try {
					// if (invilidateLogin(result)) {
					// return Status.Login;
					// }
					obj = vo.jsonParser.parseJSON(result);
				} catch (JSONException e) {
					Logger.e(TAG, e.getLocalizedMessage(), e);
				}
				return obj;
			}
		} catch (ClientProtocolException e) {
			Logger.e(TAG, e.getLocalizedMessage(), e);
		} catch (IOException e) {
			Logger.e(TAG, e.getLocalizedMessage(), e);
		}
		return null;
	}

	/**
	 * 添加Cookie
	 * 
	 * @param response
	 */
	private static void setCookie(HttpResponse response) {
		if (response.getHeaders("Set-Cookie").length > 0) {
			Logger.d(TAG, response.getHeaders("Set-Cookie")[0].getValue());
			headers[10] = new BasicHeader("Cookie", response.getHeaders("Set-Cookie")[0].getValue());
		}
	}

	/**
	 * 验证是否需要登录
	 * 
	 * @param result
	 * @return
	 * @throws JSONException
	 */
	private static boolean invilidateLogin(String result) throws JSONException {
		String formatResult = formatResult(result);
		JSONObject jsonObject = new JSONObject(formatResult);
		String responseCode = jsonObject.optString("response");
		if (NOT_LOGIN.equals(responseCode)) {
			return true;
		}
		return false;
	}

	/**
	 * @param result
	 * @return
	 */
	private static String formatResult(String result) {
		// TODO Auto-generated method stub
		result = result.replace("&lt;p&gt;", "");// <
		result = result.replace("&lt;br /&gt;", "");// >
		result = result.replace("&lt;/p&gt;", "");// >
		result = result.replace("alt=&quot;&quot; /&gt;&lt;", "");// >
		result = result.replace("&lt;", "");// <
		result = result.replace("&gt;", "");// <
		result = result.replace("&quot;", "");// <
		return result;
	}

	/**
	 * 
	 * @param vo
	 * @return
	 */
	public static Object get(RequestVO vo) {
		DefaultHttpClient client = new DefaultHttpClient();
		String url = UIUtils.getContext().getString(R.string.URL_HOST).concat(vo.requestUrl);
		Logger.i(TAG, "Get " + url);
		HttpGet get = new HttpGet(url);
		// get.setHeaders(headers);
		Object obj = null;
		try {

			HttpResponse response = client.execute(get);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				setCookie(response);
				String result = EntityUtils.toString(response.getEntity(), "UTF-8");
				Logger.d(TAG, result);
				try {
					if (invilidateLogin(result)) {
						return Status.Login;
					}
					obj = vo.jsonParser.parseJSON(result);
				} catch (JSONException e) {
					Logger.e(TAG, e.getLocalizedMessage(), e);
				}
				return obj;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获得网络连接是否可用
	 * 
	 * @param context
	 * @return
	 */
	public static boolean hasNetwork(Context context) {
		ConnectivityManager con = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo workinfo = con.getActiveNetworkInfo();
		if (workinfo == null || !workinfo.isAvailable()) {
			Toast.makeText(context, R.string.net_error, Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}

	public static enum Status {
		Login
	}

	public static void getDateFromServer(DataCallback<LogFileUploadResult> dataCallback) {
		// TODO Auto-generated method stub

	}
}
