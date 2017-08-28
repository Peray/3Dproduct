package com.eastdawn.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertDate {
	
	public static String getRiqiHourMinute(Date date) throws ParseException{
		String strdate = "";
		if(date != null){
			SimpleDateFormat simple = new SimpleDateFormat("yyyyMMddHHmm");
			strdate = simple.format(date);
		}
		return strdate;
	}
	public static String convertDate(String date){
		String returnstr = "";
		String[] array = new String[3];
		array = date.split("-");
		returnstr = array[0] + "-" + (array[1].length()==2?array[1]:("0"+array[1])) 
			+ "-" + (array[2].length()==2?array[2]:("0"+array[2]));
		return returnstr;
	}
	
	public static String convertDate(String date, String hour, String minute){
		String returnstr = "";
		if(date!=null && date.length()>0){
			String[] array = new String[3];
			array = date.split("-");
			returnstr = array[0] + "-" + (array[1].length()==2?array[1]:("0"+array[1])) 
				+ "-" + (array[2].length()==2?array[2]:("0"+array[2]));
			returnstr += " " + hour + ":" + minute + ":00";
		} else {
			returnstr += "2008-08-08 00:00:00";
		}
		return returnstr;
	}
	
	public static Date convertToDate(Date date, String hour, String minute){
		String returnstr = "";
		if(date!=null){
			returnstr = convert(date);
			if(hour!=null){
				returnstr += " " + hour;
				if(minute!=null && minute.length()>0){
					returnstr += ":" + minute + ":00";
				} else {
					returnstr += " 00:00:00";
				}
			} else {
				returnstr += " 00:00:00";
			}
			System.out.println("returnstr======" + returnstr);
			return Timestamp.valueOf(returnstr);
		} else {
			return null;
		}
	}
	
	public static String convertStringtoTime(String date){
		String returnstr = "";
		if(date!=null && !date.equals("")){
			String[] array = new String[3];
			array = date.split("-");
			returnstr = array[0] + "-" + (array[1].length()==2?array[1]:("0"+array[1])) 
				+ "-" + (array[2].length()==2?array[2]:("0"+array[2])) + " 00:00:00";
		}
		return returnstr;
	}
	
	public static String getDate(Date date) throws ParseException{
		String strdate = "";
		if(date != null){
			SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			strdate = simple.format(date);
			System.out.println(strdate);
			
			DateFormat dateFormat = DateFormat.getDateInstance();
			strdate = dateFormat.format(date);
		}
		return strdate;
	}
	
	public static String getDateHourMinute(Date date) throws ParseException{
		String strdate = "";
		if(date != null){
			SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			strdate = simple.format(date);
			strdate = strdate.substring(0, 16);
			System.out.println(strdate);
		}
		return strdate;
	}
	
	public static String getDateHourMinuteSecond(Date date) throws ParseException{
		String strdate = "";
		if(date != null){
			SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			strdate = simple.format(date);
		}
		return strdate;
	}
	
	public static String getHour(Date date) throws ParseException{
		String strhour = "";
		if(date!=null){
			SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			strhour = simple.format(date);
			strhour = strhour.split(" ")[1].substring(0, 2);
			System.out.println(strhour);
		}
		return strhour;
	}
	
	public static String getMinute(Date date) throws ParseException{
		String strminute = "";
		if(date!=null){
			SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			strminute = simple.format(date);
			strminute = strminute.split(" ")[1].substring(3, 5);
			System.out.println(strminute);
		}
		return strminute;
	}
	
	public static String convert(Date date) {
		if (date != null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			return format.format(date);
		} else {
			return "";
		}
	}
	
	public static String getHourAndMinute(Date date) {
		if (date != null) {
			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			return format.format(date);
		} else {
			return "";
		}
	}
	
	public static Date parse(String date) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			return format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getDate(Date date, String format) throws ParseException{
		String strdate = "";
		if(date != null){
			SimpleDateFormat simple = new SimpleDateFormat(format);
			strdate = simple.format(date);
		}
		return strdate;
	}
	
	public static String getHourAndMinute(String hour, String minute, boolean isBegin){
		String date = null;
		
		if (hour == null || hour.equals("")) {
			if (isBegin) {
				hour = "00";
			} else {
				hour = "23";
			}
		}
		
		if (minute == null || minute.equals("")) {
			if (isBegin) {
				minute = "00";
			} else {
				minute = "59";
			}
		}
		
		date = hour + ":" + minute;
 		return date;
	}
	
	/**
	 * 获得Date
	 * @param year 年
	 * @param month	月
	 * @param day	日
	 * @param hour	小时
	 * @param minute	分钟
	 * @param second	秒
	 * @return Date
	 */
	public static Date parse(Integer year, Integer month, Integer day, Integer hour, Integer minute, Integer second) {
		try {
			StringBuffer str1 = new StringBuffer();//目标字符串
			StringBuffer str2 = new StringBuffer();//格式字符串
			if (year != null) {
				str1.append(year + "_");
				str2.append("yyyy_");
			}
			if (month != null) {
				str1.append(month + "_");
				str2.append("MM_");
			}
			if (day != null) {
				str1.append(day + "_");
				str2.append("dd_");
			}
			if (hour != null) {
				str1.append(hour + "_");
				str2.append("HH_");
			}
			if (minute != null) {
				str1.append(minute + "_");
				str2.append("mm_");
			}
			if (second != null) {
				str1.append(second + "_");
				str2.append("ss_");
			}
			System.out.println("目标字符串： " + str1.toString());
			System.out.println("格式字符串： " + str2.toString());
			SimpleDateFormat format = new SimpleDateFormat(str2.toString());
			return format.parse(str1.toString());
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Date parse(String date, String formatStr) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(formatStr);
			return format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String consumeTime(long date) {
		String str = "";
		
		long num = 0L;
		
		num = date % 1000;
		str = str + num + "毫秒";
		date = date / 1000;
		if (date > 0) {
			num = date % 60;
			str = num + "秒" + str;
			date = date / 60;
			if (date > 0) {
				num = date % 60;
				str = num + "分" + str;
				date = date / 60;
				if (date > 0) {
					num = date % 24;
					str = num + "时" + str;
					date = date / 24;
					if (date > 0) {
						str = date + "天" + str;
					}
				}
			}
		}
		return str;
	}
	
	public static void main(String[] args) {
		
		System.out.println(ConvertDate.consumeTime(1510013422L));
		
//		String date1 = ConvertDate.getHourAndMinute(null, null, true);
//		String date2 = ConvertDate.getHourAndMinute(null, null, false);
//		String date3 = ConvertDate.getHourAndMinute(null, "36", true);
//		String date4 = ConvertDate.getHourAndMinute(null, "1", false);
//		String date5 = ConvertDate.getHourAndMinute("15", "15", true);
//		
//		System.out.println(date1);
//		System.out.println(date2);
//		System.out.println(date3);
//		System.out.println(date4);
//		System.out.println(date5);
//		
//		String date = "2010-09-08";
//		System.out.println(ConvertDate.parse(date));
//		
//		System.out.println(ConvertDate.parse(2010,10,20,10,null,null));
	}
	
}
