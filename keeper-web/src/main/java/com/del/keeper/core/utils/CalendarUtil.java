package com.del.keeper.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author liyi
 */
public class CalendarUtil {

	private static final String DEFAULT_FORMAT = "yyyy-MM-dd";

	/***
	 *默认格式的现在日期
	 *
	 * @return
	 */
	public static Date now() {
		return new Date();
	}

	/***
	 * 返回指定格式的现在日期
	 *
	 * @return
	 */
	public static Date now(String format) {
		return formatDate(format, now());
	}

	/***
	 * yyyy-MM-dd 格式的现在日期
	 *
	 * @return
	 */
	public static Date today() {
		return formatDate(DEFAULT_FORMAT, now());
	}

	/***
	 * yyyy-MM-dd 格式的日期
	 *
	 * @return
	 */
	public static Date getTomorrow() {
		Calendar c = convertToCalendar(today());
		c.add(Calendar.DAY_OF_MONTH, 1);
		return c.getTime();
	}

	/***
	 * 按照指定的格式解析日期字符串
	 *
	 * @return
	 */
	public static Date parseDate(String dateString, String format) {
		SimpleDateFormat formater = new SimpleDateFormat();
		formater.applyPattern(format);
		formater.setLenient(false);
		try {
			return formater.parse(dateString);
		} catch (ParseException e) {
			return null;
		}
	}

	/***
	 * 按照指定的格式格式化日期
	 *
	 * @return
	 */
	public static Date formatDate(String format, Date date) {
		SimpleDateFormat formater = new SimpleDateFormat();
		formater.applyPattern(format);
		formater.setLenient(false);
		try {
			return formater.parse(formater.format(date));
		} catch (ParseException e) {
			return now();
		}
	}

	/***
	 * 返回yyyy-MM-dd 格式的日期字符串
	 *
	 * @return
	 */
	public static String format(Date date) {
		return format(DEFAULT_FORMAT, date);
	}

	/***
	 * 返回指定格式的日期字符串
	 *
	 * @return
	 */
	public static String format(String format, Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat formater = new SimpleDateFormat();
		formater.applyPattern(format);
		return formater.format(date);
	}

	/***
	 * 返回今天是星期几 例如：星期一
	 *
	 * @return
	 */
	public static String getWeekDay() {
		return format("EEEE", today());
	}

	/***
	 * 返回指定的日期是星期几 例如：星期一
	 *
	 * @return
	 */
	public static String getWeekDay(Date date) {
		return format("EEEE", date);
	}

	/***
	 * 返回现在日期的年份
	 *
	 * @return
	 */
	public static int getNowYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	/***
	 * 返回现在日期的月份
	 *
	 * @return
	 */
	public static int getNowMonth() {
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}

	/***
	 * 返回现在日期的天
	 *
	 * @return
	 */
	public static int getNowDay() {
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}

	public static int getLastYear() {
		return Calendar.getInstance().get(Calendar.YEAR) - 1;
	}

	public static int getLastMonth() {
		Calendar temp = Calendar.getInstance();
		temp.add(Calendar.MONTH, -1);
		return temp.get(Calendar.MONTH) + 1;
	}

	public static int getLastMonthYearValue() {
		Calendar temp = Calendar.getInstance();
		temp.add(Calendar.MONTH, -1);
		return temp.get(Calendar.YEAR);
	}

	public static Date getMonthStart(int year, int month) {
		return parseDate(String.valueOf(year) + month, "yyyyM");
	}

	public static Date getNextMonthStart(int year, int month) {
		Calendar temp = getMonthEndCalendar(year, month);
		temp.add(Calendar.DAY_OF_MONTH, 1);
		return temp.getTime();
	}

	public static Date getLastMonthEnd(int year, int month) {
		Calendar temp = getMonthEndCalendar(year, month);
		temp.add(Calendar.MONTH, -1);
		return temp.getTime();
	}

	public static Date getLastMonthStart(int year, int month) {
		Calendar result = Calendar.getInstance();
		result.setTime(getMonthStart(year, month));
		result.add(Calendar.MONTH, -1);
		return result.getTime();
	}

	public static Date getMonthEnd(int year, int month) {
		return getMonthEndCalendar(year, month).getTime();
	}

	private static Calendar getMonthEndCalendar(int year, int month) {
		Calendar result = Calendar.getInstance();
		result.setTime(getMonthStart(year, month));
		result.set(Calendar.DAY_OF_MONTH, result
						.getActualMaximum(Calendar.DAY_OF_MONTH));
		return result;
	}

	/***
	 * 返回指定日期的年份
	 *
	 * @return
	 */
	public static int getYear(Date date) {
		Calendar c = convertToCalendar(date);
		return c.get(Calendar.YEAR);
	}

	/***
	 * 返回指定日期的月份
	 *
	 * @return
	 */
	public static int getMonth(Date date) {
		Calendar c = convertToCalendar(date);
		return c == null ? 1 : c.get(Calendar.MONTH) + 1;
	}

	/***
	 * 返回指定日期的天
	 *
	 * @return
	 */
	public static int getDay(Date date) {
		Calendar c = convertToCalendar(date);
		return c == null ? 1 : c.get(Calendar.DAY_OF_MONTH);
	}

	/***
	 * 返回指定日期的小时
	 *
	 * @return
	 */
	public static int getHour(Date date) {
		Calendar c = convertToCalendar(date);
		return c == null ? 1 : c.get(Calendar.HOUR_OF_DAY);
	}

	/***
	 * 返回指定日期的分钟
	 *
	 * @return
	 */
	public static int getMinute(Date date) {
		Calendar c = convertToCalendar(date);
		return c == null ? 1 : c.get(Calendar.MINUTE);
	}
	
	/***
   * 返回指定日期的秒
   *
   * @return
   */
  public static int getSecond(Date date) {
    Calendar c = convertToCalendar(date);
    return c == null ? 1 : c.get(Calendar.SECOND);
  }

	/***
	 * 返回指定日期是哪一个星期
	 *
	 * @return
	 */
	public static int getWeek(Date date) {
		Calendar c = convertToCalendar(date);
		return c == null ? 1 : c.get(Calendar.WEEK_OF_YEAR);
	}
	
	/***
   * 返回指定日期是星期几
   *
   * @return
   */
  public static int getDayOfWeek(Date date) {
    Calendar c = convertToCalendar(date);
    return c == null ? 1 : c.get(Calendar.DAY_OF_WEEK);
  }

	public static Calendar convertToCalendar(Date date) {
		if (date == null) {
			return null;
		}
		Calendar result = Calendar.getInstance();
		result.setTime(date);
		return result;
	}

}
