package com.eastdawn.action;

import java.net.URLDecoder;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.eastdawn.common.PageAction;
import com.eastdawn.util.StaticName;

@SuppressWarnings("serial")
public class UserLoginAction extends PageAction {
	
	private String phone;
	private String yzm;
	
	private List loginList;
	
	public String logon() throws Exception {
		HttpServletResponse iResponse =ServletActionContext.getResponse();
	    HttpSession session = ServletActionContext.getRequest().getSession();
		HttpServletRequest request = ServletActionContext.getRequest();
	    
		Cookie[] cookies = request.getCookies();//这样便可以获取一个cookie数组
		System.out.println("Cookie长度:" + cookies.length);           //读取本机共存在多少COOKIE  
	    if (cookies != null) {
		     for (int i = 0; i < cookies.length; i++) {  
			      if (cookies[i].getName().equals("user")) {  
			       System.out.println("For 内部Cookie"+URLDecoder.decode(cookies[i].getValue(),"UTF-8")); 
			       String str[] = URLDecoder.decode(cookies[i].getValue(),"UTF-8").split("-"); 
			       String dh = str[0];
			       String dx = str[1];
			       
			       if(!phone.equals(dh)){
			    	   iResponse.getWriter().write("1");
			    	   return null;
			       }else if(!yzm.equals(dx)){
			    	   iResponse.getWriter().write("2");
			    	   return null;
			       }else{
			    	   iResponse.getWriter().write("3");
		   			   session.setAttribute("U_LoginName",phone);
		   			   session.setAttribute("password",40);
				   }
			       Cookie cookie = new Cookie("user", null);
			       cookie.setMaxAge(0);
			      }  
		     }
	    } else {  
	    	System.out.println("没有Cookie");  
	    }  
		return null;
	}
	
	public String logout() {
		
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute(StaticName.session_admin, null );
		return "logout";
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getYzm() {
		return yzm;
	}

	public void setYzm(String yzm) {
		this.yzm = yzm;
	}

	public List getLoginList() {
		return loginList;
	}

	public void setLoginList(List loginList) {
		this.loginList = loginList;
	}
}
