package com.gooker.dfg.utils;

import java.nio.ByteBuffer;

public class JniUtils {

	static {
		System.loadLibrary("defgui");
	}

	/**
	 * @return
	 */
	public native static String getMsgFromJni();

	/**
	 * @return
	 */
	public native static String getMsgFromJniCpp();

	/**
	 * @param i
	 * @return
	 */
	public native static int getJieCheng(int i);

	/**
	 * @param i
	 * @param j
	 * @return
	 */
	public native static int getSum(int i, int j);

	/**
	 * @param a
	 * @param b
	 */
	public native static void swap(int a, int b);

	/**
	 * @param bf
	 * @param rbf
	 */
	public native static void swapbf(ByteBuffer bf, ByteBuffer rbf);

	/**
	 * @param i
	 * 
	 */
	public native static void getCppLog(int i);

	/**
	 * @param md5CppSrc
	 * @return
	 */
	public native static String getCppMD5(String md5CppSrc);

}
