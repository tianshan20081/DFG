/**
 * 
 */
package com.gooker.dfg.utils.enc;

import com.aoeng.degu.utils.enc.*;
import com.aoeng.degu.utils.enc.MD5Utils;
import com.gooker.dfg.utils.JniUtils;

/**
 * Jul 29, 2014 3:01:09 PM
 * 
 */
public class EncUtils {

	/**
	 * @param md5Src
	 * @return
	 */
	public static String getJavaMD5(String md5Src) {
		// TODO Auto-generated method stub

		return com.aoeng.degu.utils.enc.MD5Utils.getJavaMD5(md5Src);
	}

	/**
	 * @param md5CppSrc
	 * @return
	 */
	public static String getCppMD5(String md5CppSrc) {
		// TODO Auto-generated method stub
		return JniUtils.getCppMD5(md5CppSrc);
	}

	/**
	 * @param src
	 * @param key
	 * @return
	 */
	public static String getJavaMD5Salt(String src, String key) {
		// TODO Auto-generated method stub

		return MD5Utils.getJavaMD5Salt(src, key);
	}

	/**
	 * @param src
	 * @param key
	 * @return
	 */
	public static String getCppMD5Salt(String src, String key) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		System.out.println(getJavaMD5Salt("zhang", "test"));
	}

}
