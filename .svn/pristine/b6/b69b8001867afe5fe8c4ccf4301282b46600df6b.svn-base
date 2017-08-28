package com.eastdawn.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class NumberUtil {
	private static int index = 1;
	private static int today = 0;
	
	/**
	 * 在数字前面补0
	 * @param num
	 * @param digit 位数
	 * @return 例如（0001）
	 */
	public static String getZeroBefore(int num, int digit) {
		String str = "";
		int numLength = String.valueOf(num).length();
		if (numLength < digit) {
			for (int i = 0; i < digit - numLength; i++) {
				str += "0";
			}
		}
		return str += num;
	}
	
	/**
	 * 返回一天内下标数量
	 * @return
	 */
	public static synchronized int getIndex() {
		Calendar calendar = new GregorianCalendar();
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		System.out.println("day:" + day);
		if (day != today) {
			today = day;
			index = 1;
		}
		return index++;
	}
	
	public static void main(String[] args) {
//		System.out.println(NumberUtil.getZeroBefore(121, 4));
		for (int i = 0; i < 10; i++) {
			NumberUtil.Test t1 = new NumberUtil().new Test();
			t1.start();
		}
	}
	
	class Test extends Thread {

		public Test() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public void run() {
			System.out.println(NumberUtil.getIndex());
		}
		
	}

}
