package com.eastdawn.dwr;

import java.io.File;

import org.apache.log4j.Logger;

import com.eastdawn.util.StaticName;

public class DownloadDWR {
	private static Logger logger = Logger.getLogger(DownloadDWR.class);
	
	/**
	 * 验证文件是否存在
	 * @param filePath
	 * @return true : false
	 */
	public boolean isExist(String filePath) {
		try {
			File file = new File(StaticName.absoluteBasePath + filePath);
			
			if (!file.isFile()) {
				logger.warn(filePath + " is not exist!");
				return false;
			}
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
