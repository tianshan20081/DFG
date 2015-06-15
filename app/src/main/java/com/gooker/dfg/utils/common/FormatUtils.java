package com.gooker.dfg.utils.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatUtils {
	public static void main(String[] args) {
		String phone = "12345";
		if (isPhone(phone)) {
			System.out.println("phoe");
		} else {
			System.out.println("1");
		}
	}

	public static boolean isPhone(String phone) {
		// TODO Auto-generated method stub
		String expt = "^(13|18|15)\\d{9}$";
		Pattern pattern = Pattern.compile(expt);
//		Pattern pattern = Pattern.compile("^((13[0-9])|(15[^4,D])|(18[0,5-9]))\\d{8}$");
		Matcher matcher = pattern.matcher(phone);
		return matcher.matches();
		// return phone.matches("^(13|18)");
	}

}
