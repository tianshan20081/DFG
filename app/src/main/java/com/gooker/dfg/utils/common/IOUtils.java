package com.gooker.dfg.utils.common;

import com.aoeng.degu.utils.common.*;
import com.aoeng.degu.utils.common.LogUtils;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * 
 * @author Shaocheng Zhang Aug 1, 2014 2:11:35 PM 2014
 */
public class IOUtils {

	public static boolean close(Closeable io) {
		// TODO Auto-generated method stub
		if (null != io) {
			try {
				io.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				LogUtils.e(e);
			}
		}
		return true;
	}

	public static String getStrFromInputStream(InputStream is) {
		if (null == is) {
			return null;
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuffer sb = new StringBuffer();
		String line = null;
		try {
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			is.close();
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sb.toString();
	}

}
