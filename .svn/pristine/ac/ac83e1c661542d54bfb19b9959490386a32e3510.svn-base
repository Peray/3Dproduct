package com.eastdawn.common;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.eastdawn.util.StaticName;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class AdminSessionInterceptor extends AbstractInterceptor implements Serializable {
	
	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		Logger logger = Logger.getLogger(AdminSessionInterceptor.class);
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		if (session.getAttribute(StaticName.session_admin) == null) {
			session.setAttribute(StaticName.session_msg, StaticName.session_msg_info);
			return StaticName.logon_admin;
		}
		logger.info(StaticName.session_admin + ": " + session.getAttribute(StaticName.session_admin));
		
		return actionInvocation.invoke();
	}

}
