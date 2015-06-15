/**
 * 
 */
package com.gooker.dfg.utils.enc;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.aoeng.degu.utils.enc.*;
import com.aoeng.degu.utils.enc.Base64;
import com.payease.andrjson.base64.BASE64Decoder;
import com.payease.andrjson.base64.BASE64Encoder;

/**
 * Jul 22, 2014 2:01:54 PM
 * 
 */
public class EncryptionUtils {
	private static byte[] iv = { 1, 2, 3, 4, 5, 6, 7, 8 };
	private static byte SECRETKEY[];
	private static BASE64Decoder base64de = new BASE64Decoder();
	private static BASE64Encoder base64en = new BASE64Encoder();

	/**
	 * @param src
	 */
	public static String getDesEnctry(String src, String key) {
		// TODO Auto-generated method stub

		SECRETKEY = key.getBytes();

		String ret = "";
		try {
			DESKeySpec dks = new DESKeySpec(SECRETKEY);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			javax.crypto.SecretKey securekey = keyFactory.generateSecret(dks);
			IvParameterSpec spec = new IvParameterSpec(SECRETKEY);
			Cipher encrypt_Cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			encrypt_Cipher.init(1, securekey, spec);

			byte dest[] = encrypt_Cipher.doFinal(src.getBytes());
			ret = base64en.encode(dest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;

	}

	/**
	 * @param des
	 * @return
	 * @throws Exception
	 * @throws NoSuchAlgorithmException
	 */
	public static String getDesDecode(String decryptString, String decryptKey) throws NoSuchAlgorithmException,
			Exception {
		byte[] byteMi = new Base64().decode(decryptString);
		IvParameterSpec zeroIv = new IvParameterSpec(iv);
		// IvParameterSpec zeroIv = new IvParameterSpec(new byte[8]);
		SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes(), "DES");
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
		byte decryptedData[] = cipher.doFinal(byteMi);

		return new String(decryptedData);
	}

	public static String getDesDec(String src, String desKey) {
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");

			SecretKeySpec key = new SecretKeySpec(desKey.getBytes(), "AES");

			// Here you need to accurately and correctly read your file into a byte
			// array. Either Google for a decent solution (there are many out there)
			// or use an existing implementation, such as Apache Commons commons-io.
			// Your existing effort is buggy and doesn't close its resources.

			cipher.init(Cipher.DECRYPT_MODE, key);

			// Just one call to doFinal
			byte[] plainText = cipher.doFinal(src.getBytes());

			// Note: don't do this. If you create a string from a byte array,
			// PLEASE pass a charset otherwise your result is platform dependent.
			return new String(plainText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

}
