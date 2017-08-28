package com.eastdawn.util;

import java.util.Properties;

public class StaticName {
	public static String session_admin = "AdminSession";
	public static String session_user = "UserSession";
	public static String session_msg = "sessionMsg";
	public static String session_msg_info = "会话过期，请重新登录！";//"Session is lost, please logon again!";
	
	public static String logon_admin = "adminLogon";
	public static String logon_user = "userLogon";
	public static String logon_error_msg = "用户名或者密码错误，请重新登录！";
	public static String validate_error_msg = "验证码错误，请重新登录！";
	
	public static String logout_msg = "退出成功！";
	
	public static String onlineBasePath = "";
	public static String onlineAbsolutePath = "";
	public static String absoluteBasePath = "";

	static {
		Properties properties = PropertiesUtil.getProperties("/config/config.properties");
		onlineBasePath = properties.getProperty("onlineBasePath");
		onlineAbsolutePath = properties.getProperty("onlineAbsolutePath");
		absoluteBasePath = properties.getProperty("absoluteBasePath");
	}
}
