package com.gooker.dfg.utils.qiniu;

import org.json.JSONObject;

import com.aoeng.degu.utils.qiniu.*;
import com.aoeng.degu.utils.qiniu.Base64;
import com.aoeng.degu.utils.qiniu.Config;
import com.aoeng.degu.utils.qiniu.PutPolicy;
import com.qiniu.auth.Authorizer;

public class QNApi {

	private static final int WEIGHT = 0;
	private static Mac mac;
	public static final String BUCKET_TSHAN = "tshan";
	public static final String BUCKET_ANDROIDPLAY = "androidplay";
	public static final String BUCKET_TSLOGS = "tslogs";

	static {
		com.aoeng.degu.utils.qiniu.Config.ACCESS_KEY = "yXKy9jtDxW-WbSYdgeFiJmGFbLTeMqhgTOEUA7uc";
		com.aoeng.degu.utils.qiniu.Config.SECRET_KEY = "GWKfr2UAorAIGPcv_9dcDhUJoB51TkCDP6xOD0kv";
		mac = new Mac(com.aoeng.degu.utils.qiniu.Config.ACCESS_KEY, Config.SECRET_KEY);
	}

	public static String getUpToken(String bucketName) {
		// 请确保该bucket已经存在
		String uptoken;
		try {
			PutPolicy putPolicy = new PutPolicy(bucketName);
			uptoken = putPolicy.token(mac);
			// System.out.println(uptoken);
			return uptoken;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public static String getBucketByUpToken(String uptoken) {
		try {
			String str = uptoken.split(":")[2];
			String jsonStr = new String(Base64.decode(str, Base64.URL_SAFE | Base64.NO_WRAP), "utf-8");
			JSONObject json = new JSONObject(jsonStr);
			String scope = json.optString("scope");
			String bucketName = scope.split(":")[0];
			return bucketName;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Log.d("Scope", bucketName);
		return null;
	}

	/**
	 * 获得七牛服务器上面 图片的最小高度为 min 的请求参数
	 * 
	 * @param minValue
	 *            最小边的值
	 * @param type
	 *            类型 所限定的类型 高度 or 宽度
	 * @return ?imageView2/2/%s/%d/q/85
	 */
	public static String getMinHeight(int minValue, ImageInfo type) {
		// TODO Auto-generated method stub
		String format = "?imageView2/2/%s/%d/q/85";
		return String.format(format, type.getValue(), minValue);
	}

	enum ImageInfo {
		WEIGHT("w"), HEIGHT("h");

		private String value;

		ImageInfo(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}

	}

	public static Mac getMac() {
		// TODO Auto-generated method stub
		return mac;
	}

	public static Authorizer getAuthorizer() {
		// TODO Auto-generated method stub
		Authorizer auth = new Authorizer();
		auth.setUploadToken(com.aoeng.degu.utils.qiniu.QNApi.getUpToken(BUCKET_TSLOGS));
		return auth;
	}

	/**
	 * 获得七牛 的 Authorizer
	 * 
	 * @param bucket
	 *            bucket 空间名称
	 * @return
	 */
	public static Authorizer getAuthorizer(String bucket) {
		// TODO Auto-generated method stub
		Authorizer auth = new Authorizer();
		auth.setUploadToken(com.aoeng.degu.utils.qiniu.QNApi.getUpToken(bucket));
		return auth;
	}
}
