package com.eastdawn.util;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;

public class Video2flv {

	private static Logger logger = Logger.getLogger(Video2flv.class);

	public static void main(String[] args) {
		if (process("d:\\3.rmvb", "d:\\2.flv", "d:\\2.avi", "d:\\2.jpg")) {
			System.out.println("ok");
		}
	}

	private static boolean process(String originalPath, String targetPath, String tempPath, String imgPath) {
		int type = checkContentType(originalPath);
		boolean status = false;
		if (type == 0) {
			//status = processFLV(PATH);// 直接将文件转为flv文件 
			status = processImg(originalPath, imgPath);
			processFLV(originalPath, targetPath);
		} else if (type == 1) {
			String avifilepath = processAVI(originalPath, tempPath);
			if (avifilepath == null) {
				return false;// avi文件没有得到 
			}
//			status = processFLV(originalPath, targetPath);// 将avi转为flv 
		}
		return status;
	}

	private static int checkContentType(String originalPath) {
		String type = originalPath.substring(originalPath.lastIndexOf(".") + 1, originalPath.length())
				.toLowerCase();
		// ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等） 
		if (type.equals("avi")) {
			return 0;
		} else if (type.equals("mpg")) {
			return 0;
		} else if (type.equals("wmv")) {
			return 0;
		} else if (type.equals("3gp")) {
			return 0;
		} else if (type.equals("mov")) {
			return 0;
		} else if (type.equals("mp4")) {
			return 0;
		} else if (type.equals("asf")) {
			return 0;
		} else if (type.equals("asx")) {
			return 0;
		} else if (type.equals("flv")) {
			return 0;
		}
		// 对ffmpeg无法解析的文件格式(wmv9，rm，rmvb等), 
		// 可以先用别的工具（mencoder）转换为avi(ffmpeg能解析的)格式. 
		else if (type.equals("wmv9")) {
			return 1;
		} else if (type.equals("rm")) {
			return 1;
		} else if (type.equals("rmvb")) {
			return 1;
		}
		return 9;
	}

	private static boolean checkfile(String path) {
		File file = new File(path);
		if (!file.isFile()) {
			return false;
		}
		return true;
	}

	// 对ffmpeg无法解析的文件格式(wmv9，rm，rmvb等), 可以先用别的工具（mencoder）转换为avi(ffmpeg能解析的)格式. 
	private static String processAVI(String originalPath, String targetPath) {
		List<String> commend = new java.util.ArrayList<String>();
		String classPath = Class.class.getResource("/").getPath();

		commend.add(classPath + "ffmpeg\\mencoder");
		commend.add(originalPath);
		commend.add("-oac");
		commend.add("mp3lame");
		commend.add("lavc");
		commend.add("-lavcopts");
		commend.add("acodec=mp3:abitrate=64");
		commend.add("-ovc");
		commend.add("xvid");
		commend.add("-xvidencopts");
		commend.add("bitrate=600");
		commend.add("-of");
		commend.add("avi");
		commend.add("-o");
		commend.add(targetPath);
		
		System.out.println(commend);
		try {
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(commend);
			builder.start();
			return targetPath;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等） 
	private static boolean processFLV(String originalPath, String targetPath) {
		if (!checkfile(originalPath)) {
			logger.error(originalPath + " is not file");
			return false;
		}
		String classPath = Class.class.getResource("/").getPath();
		List<String> commend = new java.util.ArrayList<String>();
		commend.add(classPath + "ffmpeg\\ffmpeg.exe");
		commend.add("-i");
		commend.add(originalPath);
		commend.add("-ab");
		commend.add("64");
//    	commend.add(" -acodec "); 
//    	commend.add("codec"); 
		commend.add("-ac");
		commend.add("2");
		commend.add("-ar");
		commend.add("22050");
		commend.add("-b");
		commend.add("230");
//    	commend.add("-s"); 
//    	commend.add("350x240"); 
		commend.add("-r");
		commend.add("29.97");
		commend.add("-y");
		commend.add(targetPath);
		System.out.println(commend);
		try {
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(commend);
			builder.start();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 生成图片
	public static boolean processImg(String originalPath, String targetPath) {
		String classPath = Class.class.getResource("/").getPath();
		List<String> commend = new java.util.ArrayList<String>();
		commend.add(classPath + "ffmpeg\\ffmpeg.exe");
		commend.add("-i");
		commend.add(originalPath);
		commend.add("-y");
		commend.add("-f");
		commend.add("image2");
		commend.add("-ss");
		commend.add("38");
		commend.add("-t");
		commend.add("0.001");
		commend.add("-s");
		commend.add("320x240");
		commend.add(targetPath);
		try {
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(commend);
			builder.start();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
