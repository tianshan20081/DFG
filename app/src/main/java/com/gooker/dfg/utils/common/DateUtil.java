package com.gooker.dfg.utils.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.text.TextUtils;

import com.aoeng.degu.utils.common.StringUtils;

public final class DateUtil {
	private static String[] weeks = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
	private static String[] days = { "周日", "周一", "周二", "周三", "周四", "周五", "周六", "执行一次" };
	public static SimpleDateFormat HHmmss = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
	/**
	 * HHmmss
	 */
	public static SimpleDateFormat HHmmssNoColon = new SimpleDateFormat("HHmmss", Locale.CHINA);
	/**
	 * yyyyMMddHHmmss
	 */
	public static SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);
	/**
	 * MMddyyyyHHmmss
	 */
	public static SimpleDateFormat MMddYYYYHHmmss = new SimpleDateFormat("MMddyyyyHHmmss", Locale.CHINA);
	/**
	 * MMddHHmmss
	 */
	public static SimpleDateFormat MMddHHmmss = new SimpleDateFormat("MMddHHmmss", Locale.CHINA);
	/**
	 * yyyy-MM-dd
	 */
	public static SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
	/**
	 * 2014.09.22
	 */
	public static SimpleDateFormat yyyy_MM_dd = new SimpleDateFormat("yyyy.MM.dd", Locale.CHINA);
	/**
	 * 2014.9.22
	 */
	public static SimpleDateFormat yyyy_M_d = new SimpleDateFormat("yyyy.M.d", Locale.CHINA);
	/**
	 * 2014年10月10日 15:20
	 */
	public static SimpleDateFormat yyyy_M_d_HH_mm = new SimpleDateFormat("yyyy年MM月dd日 HH:mm", Locale.CHINA);
	/**
	 * yyyy-M-d
	 */
	public static SimpleDateFormat YYYY_M_D_MIDDLE = new SimpleDateFormat("yyyy-M-d", Locale.CHINA);
	/**
	 * yyyyMMdd
	 */
	public static SimpleDateFormat shortyyyyMMdd = new SimpleDateFormat("yyyyMMdd", Locale.CHINA);
	/**
	 * yyyy-MM-dd E
	 */
	public static SimpleDateFormat yyyy_MM_dde = new SimpleDateFormat("yyyy-MM-dd E", Locale.CHINA);
	/**
	 * "yyyy-MM-dd HH:mm"
	 */
	public static SimpleDateFormat yyyyMMddHHmm = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static SimpleDateFormat yyyy_MM_dd_HHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
	/**
	 * HH:mm
	 */
	public static SimpleDateFormat HHmm = new SimpleDateFormat("HH:mm", Locale.CHINA);
	/**
	 * 20141025_21:25:36
	 */
	public static SimpleDateFormat yyyyMMdd_HHmmss = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA);

	public static String getCurrentDateTimeyyyyMMddHHmmss() {
		return yyyyMMddHHmmss.format(new Date());
	}

	/**
	 * 将这种类型yyyy-MM-dd HH:mm:ss的时间转化为long类型的
	 * 
	 * @param serverTimeString
	 * @return
	 */
	public static long getMillisecondsFromString(String serverTimeString) {
		if (null != serverTimeString && !"".equals(serverTimeString)) {
			return 0;
		}

		long millisecond;
		try {
			millisecond = yyyy_MM_dd_HHmmss.parse(serverTimeString).getTime();
			return millisecond;
		} catch (ParseException e) {
			return 0;
		}
	}

	/**
	 * 将毫秒数转化成yyyy-MM-dd HH:mm:ss类型的日期
	 * 
	 * @param milliseconds
	 * @return
	 */
	public static String getStringDateFromMilliseconds(long milliseconds) {
		if (milliseconds == 0) {
			return "";
		}

		String string;
		Date date = new Date(milliseconds);
		string = yyyy_MM_dd_HHmmss.format(date);
		return string;
	}

	public static String getTime(String timeStr, long time) {
		try {
			if (TextUtils.isEmpty(timeStr)) {
				return getCurrentDateTimeyyyyMMddHHmmss();
			} else {
				long timeSum = yyyyMMddHHmmss.parse(timeStr).getTime() + time;
				Date date = new Date(timeSum);
				return yyyyMMddHHmmss.format(date);
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return getCurrentDateTimeyyyyMMddHHmmss();
		}
	}

	public static String getyyyy_MM_ddTime(Date date) {
		return yyyyMMdd.format(date);
	}

	public static String getTimeyyyy_MM_dd_HH_mm_ss(String timeStr, long time) {
		try {
			if (TextUtils.isEmpty(timeStr)) {
				return yyyy_MM_dd_HHmmss.format(new Date());
			} else {
				long timeSum = yyyyMMddHHmmss.parse(timeStr).getTime() + time;
				Date date = new Date(timeSum);
				return yyyy_MM_dd_HHmmss.format(date);
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return yyyy_MM_dd_HHmmss.format(new Date());
		}
	}

	/**
	 * 根据毫秒数 获取天数
	 * 
	 * @param millisSeconds
	 * @return
	 */
	public static String getStringFromMillisSeconds(long millisSeconds) {
		String string = "";
		long days = millisSeconds / 1000 / 60 / 60 / 24;
		long hours = (millisSeconds - days * 24 * 60 * 60 * 1000) / 1000 / 60 / 60;
		long mins = (millisSeconds - days * 24 * 60 * 60 * 1000 - hours * 60 * 60 * 1000) / 1000 / 60;
		long seconds = (millisSeconds - days * 24 * 60 * 60 * 1000 - hours * 60 * 60 * 1000 - mins * 60 * 1000) / 1000;
		string = days + "天" + hours + "小时" + mins + "分钟" + seconds + "秒";
		return string;
	}

	public static String getTimeMMddYYYYHHmmss(String timeStr, long time) {
		try {
			if (TextUtils.isEmpty(timeStr)) {
				return MMddYYYYHHmmss.format(new Date());
			} else {
				long timeSum = yyyyMMddHHmmss.parse(timeStr).getTime() + time;
				Date date = new Date(timeSum);
				return MMddYYYYHHmmss.format(date);
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return MMddYYYYHHmmss.format(new Date());
		}
	}

	public static String getTimeMMddHHmmss(String timeStr, long time) {
		try {
			if (TextUtils.isEmpty(timeStr)) {
				return MMddHHmmss.format(new Date());
			} else {
				long timeSum = yyyyMMddHHmmss.parse(timeStr).getTime() + time;
				Date date = new Date(timeSum);
				return MMddHHmmss.format(date);
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return MMddHHmmss.format(new Date());
		}
	}

	public static long getTimeLong(String timeStr, long time) {
		try {
			if (TextUtils.isEmpty(timeStr)) {
				return new Date().getTime();
			} else {
				long timeSum = yyyyMMddHHmmss.parse(timeStr).getTime() + time;
				return timeSum;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date().getTime();
		}
	}

	public static String getDate(String timeStr, long time) {
		try {
			if (TextUtils.isEmpty(timeStr)) {
				Date date = new Date();
				return shortyyyyMMdd.format(date);
			} else {
				long timeSum = yyyyMMddHHmmss.parse(timeStr).getTime() + time;
				Date date = new Date(timeSum);
				return shortyyyyMMdd.format(date);
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return getDate(new Date());
		}
	}

	public static String getDateyyyy_MM_dd(String timeStr, long time) {
		try {
			if (TextUtils.isEmpty(timeStr)) {
				Date date = new Date();
				return yyyyMMdd.format(date);
			} else {
				long timeSum = yyyyMMddHHmmss.parse(timeStr).getTime() + time;
				Date date = new Date(timeSum);
				return yyyyMMdd.format(date);
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return getDate(new Date());
		}
	}

	public static String getOnlytime(String timeStr, long time) {
		try {
			if (TextUtils.isEmpty(timeStr)) {
				return HHmmssNoColon.format(new Date());
			} else {
				long timeSum = yyyyMMddHHmmss.parse(timeStr).getTime() + time;
				Date date = new Date(timeSum);
				return HHmmssNoColon.format(date);
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return HHmmssNoColon.format(new Date());
		}
	}

	public static String getOnlytime(Date date) {
		return HHmmssNoColon.format(date);
	}

	public static String getDate(Date date) {
		return yyyyMMdd.format(date);
	}

	public static String getTime() {
		return HHmmss.format(new Date());
	}

	public static Long getCurrentMilliseconds() {
		return (new Date().getTime());
	}

	public static String formatDate(String date) {
		try {
			Date d = yyyyMMdd.parse(date);
			return yyyyMMdd.format(d);
		} catch (ParseException e) {
			return null;
		}
	}

	public static String formatDate(SimpleDateFormat sdf, String date) {
		try {
			Date d = sdf.parse(date);
			String result = sdf.format(d);
			return result;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date addDate(Date dt, int num) {
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(dt);
		rightNow.add(Calendar.DATE, num);// 你要加减的日
		Date result = rightNow.getTime();
		return result;
	}

	/**
	 * 获取当前时间的字符串用来给图片命名
	 * 
	 * @return
	 */
	public static String getCurrentTimeForPicName() {
		Date date = new Date();
		String format = yyyyMMdd_HHmmss.format(date);
		return format;
	}

	public static String getWeek(Date dt) {
		// TODO Auto-generated method stub
		Calendar cl = Calendar.getInstance();
		cl.setTime(dt);
		int i = cl.get(Calendar.DAY_OF_WEEK) - 1;
		if (i < 0) {
			i = 0;
		}
		return weeks[i];
	}

	public static Date getOneWeekBefore() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.WEDNESDAY, -1);
		return calendar.getTime();
	}

	public static Date getOneMonthBefore() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		return calendar.getTime();
	}

	public static Date getThreeMonthBefore() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -3);
		return calendar.getTime();
	}

	public static Date getSixMonthBefore() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -6);
		return calendar.getTime();
	}

	public static Date getOneYearBefore() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, -1);
		return calendar.getTime();
	}

	public static Date getCurrentDate() {
		// TODO Auto-generated method stub
		return new Date();
	}

	/**
	 * 
	 * @param repeat
	 * @return
	 */
	public static String getDays(int repeat) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		if (repeat == 0) {
			sb.append(days[days.length - 1]);
			return sb.toString();
		}
		if (repeat == 127) {
			return "每天";
		}
		String dds = Integer.toBinaryString(repeat);
		if (StringUtils.isEmpty(dds)) {
			return sb.toString();
		}
		for (int j = dds.length() - 1; j >= 0; j--) {
			char ch = dds.charAt(j);
			if (ch != '0') {
				sb.append(",").append(days[dds.length() - 1 - j]);
			}
		}
		if (sb.toString().startsWith(",")) {
			return sb.toString().substring(1, sb.toString().length());
		}
		return sb.toString();
	}

	public static String getSelIndex(int repeat) {
		// TODO Auto-generated method stub
		String str = "";
		if (repeat == 0) {
			return "-1";
		}
		if (repeat == 127) {
			return "0123456";
		}
		String dds = Integer.toBinaryString(repeat);
		System.out.println("dds---" + dds);
		if (StringUtils.isEmpty(dds)) {
			return "-1";
		}
		for (int j = dds.length() - 1; j >= 0; j--) {
			String ch = dds.charAt(j) + "";
			System.out.println(ch + "ch");
			if ("1".equals(ch)) {
				str += ((dds.length() - 1 - j) + "");
			}
		}
		return str;
	}

}
