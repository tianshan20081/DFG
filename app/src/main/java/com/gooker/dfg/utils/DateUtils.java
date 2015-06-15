package com.gooker.dfg.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.text.TextUtils;

public class DateUtils {

	public static SimpleDateFormat HHmmss = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
	public static SimpleDateFormat HHmmssNoColon = new SimpleDateFormat("HHmmss", Locale.CHINA);
	public static SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);

	public static SimpleDateFormat MMddYYYYHHmmss = new SimpleDateFormat("MMddyyyyHHmmss", Locale.CHINA);
	public static SimpleDateFormat MMddHHmmss = new SimpleDateFormat("MMddHHmmss", Locale.CHINA);
	public static SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
	public static SimpleDateFormat yyyy_MM_dd = new SimpleDateFormat("yyyy.MM.dd", Locale.CHINA);

	public static SimpleDateFormat shortyyyyMMdd = new SimpleDateFormat("yyyyMMdd", Locale.CHINA);

	public static SimpleDateFormat yyyy_MM_dde = new SimpleDateFormat("yyyy-MM-dd E", Locale.CHINA);
	public static SimpleDateFormat yyyyMMddHHmm = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);

	public static SimpleDateFormat yyyy_MM_dd_HHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
	public static SimpleDateFormat HHmm = new SimpleDateFormat("HH:mm", Locale.CHINA);
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

}
