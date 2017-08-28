package com.eastdawn.util;

public class JVMUtil {
	
	public static void memory() {
		System.out.println("JVM MAX MEMORY: " + Runtime.getRuntime().maxMemory()/1024/1024+"M");
		System.out.println("JVM IS USING MEMORY:" + Runtime.getRuntime().totalMemory()/1024/1024+"M");
		System.out.println("JVM IS FREE MEMORY:" + Runtime.getRuntime().freeMemory()/1024/1024+"M");
	}
	
	public static void main(String[] args) {
		JVMUtil.memory();
	}

}
