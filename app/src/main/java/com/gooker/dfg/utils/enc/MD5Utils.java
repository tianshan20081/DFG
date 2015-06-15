/**
 * 
 */
package com.gooker.dfg.utils.enc;

import com.aoeng.degu.utils.enc.*;
import com.aoeng.degu.utils.enc.Md5;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Jul 29, 2014 3:02:11 PM
 * 
 */
public class MD5Utils {

	/**
	 * @param md5Src
	 * @return
	 */
	public static String getJavaMD5(String md5Src) {
		// TODO Auto-generated method stub
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(md5Src.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			// result = buf.toString().substring(8, 24);
			// System.out.println("mdt 16bit: " + buf.toString().substring(8, 24));
			// System.out.println("md5 32bit: " + buf.toString());
			return buf.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}

	/**
	 * @param src
	 *            获取摘要字符串
	 * @param key
	 *            加盐
	 * @return 返回 对 字符串 src 进行加盐 后获取到的 摘要值 MD5
	 */
	public static String getJavaMD5Salt(String src, String key) {
		// TODO Auto-generated method stub
		com.aoeng.degu.utils.enc.Md5 md5 = new com.aoeng.degu.utils.enc.Md5("");
		try {
			byte b[] = null;
			md5.hmac_Md5(src, key);
			b = md5.getDigest();
			return Md5.stringify(b);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	private static String getJaveMD5Salt2(String src, String key) {
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
