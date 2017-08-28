package com.eastdawn.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import com.eastdawn.util.StaticName;

public class BindSession implements HttpSessionBindingListener {
	 
	 private String username;
	 
	 public  BindSession(String username){
	   this.username=username;
	 }
	 public void valueBound(HttpSessionBindingEvent event) {
	   HttpSession session = event.getSession();
	  //String name=(String)session.getAttribute("name");
	      ServletContext application = session.getServletContext();

	      // 把用户名放入在线列表
	      List onlineUserList = (List) application.getAttribute("onlineUserList");
	      // 第一次使用前，需要初始化
	      if (onlineUserList == null) {
	          onlineUserList = new ArrayList();
	          application.setAttribute("onlineUserList", onlineUserList);
	      }
	      int count = Collections.frequency(onlineUserList,username);
	      System.out.println(count);
	      if(count > 0){
	    	  System.out.println("用户已存在");
	      }else{
	    	  onlineUserList.add(this.username);
	    	  session.setAttribute("onlineUserList", onlineUserList);
	      }
	      System.out.println("valueBound: .........."+onlineUserList.size());
	 }

	 public void valueUnbound(HttpSessionBindingEvent event) {
	     HttpSession session = event.getSession();
	  // String name=(String)session.getAttribute("name");
	     ServletContext application = session.getServletContext();
         try {
        	// 从在线列表中删除用户名
        	 List onlineUserList = (List) application.getAttribute("onlineUserList");
        	 String userName = (String) session.getAttribute("userkey");
        	 onlineUserList.remove(userName);
	         System.out.println(this.username + "退出。");
		 } catch (Exception e) {
			// TODO: handle exception
			 e.printStackTrace();
		 }
	 }
}