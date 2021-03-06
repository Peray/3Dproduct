package com.eastdawn.action;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;


import com.eastdawn.common.PageAction;
import com.eastdawn.dao.LogsDao;
import com.eastdawn.po.Logs;
import com.eastdawn.po.User;
import com.eastdawn.util.StaticName;
public class SessionAction extends PageAction{
	private Integer yn;
	private LogsDao logsDao;
	
	//类别查询方法
	public String execute() throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		HttpServletRequest request = ServletActionContext.getRequest();
	    ServletContext application = session.getServletContext();
		HttpServletResponse response = ServletActionContext.getResponse();
		//根据父ID查询类别
		try {
			System.out.println(StaticName.session_user+"----");
			User user =(User)session.getAttribute(StaticName.session_user);
			List onlineUserList = (List) application.getAttribute("onlineUserList");
			if(user != null && !user.equals("")){
				yn = 1;
				int count = Collections.frequency(onlineUserList,user.getLogName());
		        System.out.println(count);
		        if(count == 0){
		        	session.setAttribute(StaticName.session_msg, StaticName.logout_msg);
		    		session.setAttribute(StaticName.session_user, null);
		        }
			}else{
				yn = 0;
			}
			
			System.out.println(user);
			JSONObject object = new JSONObject();
			object.put("YN", yn); 
			object.put("user", user); 
	        System.out.println(object.toString());
	        response.setCharacterEncoding("utf-8");
	        response.getWriter().write(object.toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;	
	}

	public Integer getYn() {
		return yn;
	}

	public void setYn(Integer yn) {
		this.yn = yn;
	}

	public LogsDao getLogsDao() {
		return logsDao;
	}

	public void setLogsDao(LogsDao logsDao) {
		this.logsDao = logsDao;
	}

}