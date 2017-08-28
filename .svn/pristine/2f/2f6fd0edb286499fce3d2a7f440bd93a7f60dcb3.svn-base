package com.eastdawn.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	
	public static Properties getProperties(String fileUrl) {
		InputStream inputStream = null;
		try {
			inputStream = PropertiesUtil.class.getResourceAsStream(fileUrl);
			Properties properties = new Properties();
			properties.load(inputStream);
			return properties;
		} catch (Exception e) {
			throw new RuntimeException("配置文件没有找到！ 文件名：" + fileUrl, e);
		} finally {
			if (inputStream != null) {try{inputStream.close();}catch(IOException e){}}
		}
	}
	
	public static void main(String[] args) {
		Properties properties = PropertiesUtil.getProperties("/config/config.properties");
		String str = properties.getProperty("my");
		System.out.println(str);
	}

}
