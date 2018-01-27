package com.talk.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

/**
 * ʱ���ʽ�� ������
 * 
 * @author gaoqiang01
 *
 */
public class DateHelper {
	/**
	 * ����ϵͳ����ʱ��
	 * 
	 * @param dateType
	 * @return
	 */
	public static String getNow(String dateType) {
		SimpleDateFormat df = new SimpleDateFormat(dateType);
		return df.format(new java.util.Date());
	}

	public static long GetDateStampYYYY_MM_DD(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = null;
		long re_time = 0;
		try {
			d = sdf.parse(date.trim() + " 00:00:00");
			long l = d.getTime();
			re_time = l;
		} catch (ParseException e) {
			return -1;
		}
		return re_time / 1000;
	}

	public static String StampConvertTimeBySecond_(long stamp) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(new Date(stamp * 1000));
		return date;
	}

	public static long GetDateStampYYYYMMDD(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		Date d = null;
		long re_time = 0;
		try {
			d = sdf.parse(date.trim() + " 00:00:00");
			long l = d.getTime();
			re_time = l;
		} catch (ParseException e) {
			return -1;
		}
		return re_time / 1000;
	}

	public static String StampConvertTimeBySecond(long stamp) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		String date = sdf.format(new Date(stamp * 1000));
		return date;
	}

	/**
	 * ָ������������n��
	 * 
	 * @param date
	 *            ָ������
	 * @param n
	 *            ����
	 * @return
	 */
	public static String AddDate(String date, int n) {
		long stamp = 0;
		if (date.indexOf('-') > 0) {
			stamp = GetDateStampYYYY_MM_DD(date);
			stamp = stamp + n * 24 * 3600;
			String newDate = StampConvertTimeBySecond_(stamp);
			return newDate.substring(0, 10);
		} else {
			stamp = GetDateStampYYYYMMDD(date);
			stamp = stamp + n * 24 * 3600;
			String newDate = StampConvertTimeBySecond(stamp);
			return newDate.substring(0, 8);
		}
	}

	public static int GetWeekDayNo0(String date) {
		SimpleDateFormat sdf = null;
		if (date.indexOf('-') > 0) {
			sdf = new SimpleDateFormat("yyyy-MM-dd");
		} else {
			sdf = new SimpleDateFormat("yyyyMMdd");
		}
		Date d = null;
		try {
			d = sdf.parse(date + " 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int weekday = d.getDay();
		if (weekday == 0) {
			weekday = 7;
		}
		return weekday;
	}

	/**
	 * ����ת����Timestamp
	 * 
	 * @param time
	 * @return
	 */
	public static long GetTimeStampYYYY_MM_DD(String time) {
		long re_time = -1;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		try {
			d = sdf.parse(time);
			re_time = d.getTime() / 1000;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return re_time;
	}

	/**
	 * �ַ���ת��������
	 * 
	 * @param str
	 * @return date
	 */
	public static Date StrToDate(String str, String dformat) {

		SimpleDateFormat format = new SimpleDateFormat(dformat);
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * �����������ڼ���������
	 * 
	 * @param early
	 * @param late
	 * @return
	 */
	public static final int daysBetween(Date early, Date late) {

		java.util.Calendar calst = java.util.Calendar.getInstance();
		java.util.Calendar caled = java.util.Calendar.getInstance();
		calst.setTime(early);
		caled.setTime(late);
		// ����ʱ��Ϊ0ʱ
		calst.set(java.util.Calendar.HOUR_OF_DAY, 0);
		calst.set(java.util.Calendar.MINUTE, 0);
		calst.set(java.util.Calendar.SECOND, 0);
		caled.set(java.util.Calendar.HOUR_OF_DAY, 0);
		caled.set(java.util.Calendar.MINUTE, 0);
		caled.set(java.util.Calendar.SECOND, 0);
		// �õ�����������������
		int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst.getTime().getTime() / 1000)) / 3600 / 24;

		return days + 1;
	}

	/**
	 * �������n��,���˼���
	 * 
	 * @param days
	 *            ��Խ������
	 * @param firstDayOfWeek
	 *            ��һ���������
	 * @param lastDayOfWeek
	 *            ���һ���������
	 * @param weeks
	 *            ��������
	 * @return
	 */
	public static int getweeks(int days, int firstDayOfWeek, int lastDayOfWeek, Set<String> weeks) {
		int w = 1;
		// days>7 ����ȡ�� 
		if (days > 7) {
			w = (int) Math.ceil((double) days / Constant.WEEK_LONG);
		}
		// �����ȵ���7 �������һ�첻������ ���ǿ�����
		else if (days == 7 && lastDayOfWeek != 5) {
			w = w + 1;
		}
		// ������С��7 �������ǵ�һ�쵫�ǰ������� �������
		else if (days < 7 && firstDayOfWeek != 6 && weeks.contains("6")) {
			w = w + 1;
		}
		// int w = (int) Math.ceil((double)days / Constant.WEEK_LONG);
		// if (lastDayOfWeek > 6&&days<=7) {
		// return w + 1;
		// }
		// if(isOverMonth){
		// return w+1;
		// }
		return w;
	}

	/**
	 * �ж����������Ƿ����
	 * 
	 * @param startDate
	 *            yyyy-MM-dd ��ʽ�Ŀ�ʼ����
	 * @param endDate
	 *            yyyy-MM-dd ��ʽ�Ľ�������
	 * @return
	 */
	public static boolean isOverWeek(String startDate, String endDate) {
		startDate = startDate.split(" ")[0];
		endDate = endDate.split(" ")[0];
		boolean flag = true;
		String[] s = startDate.split("-");
		String[] e = endDate.split("-");
		if ((s[0].equals(e[0])) && (s[1].equals(e[1]))) {
			flag = false;
		}
		return flag;
	}

	public static void main(String[] args) {

		// int s = daysBetween(StrToDate("2017-07-02", Constant.DATE_FORMAT_2),
		// StrToDate("2017-07-28", Constant.DATE_FORMAT_2));
		// int weeks = getweeks(s, 7);
		// System.out.println(weeks);

		//
		// boolean f = isOverWeek("2017-08-02", "2017-08-02");
		// System.out.println(f);

		int a = daysBetween(StrToDate("2017-08-01", Constant.DATE_FORMAT_2),
				StrToDate("2017-08-02", Constant.DATE_FORMAT_2)) + 1;
		System.out.println(a);

	}

}
