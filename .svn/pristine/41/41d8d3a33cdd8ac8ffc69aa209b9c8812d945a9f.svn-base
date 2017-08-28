package com.eastdawn.common;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.eastdawn.util.StaticName;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class UserSessionInterceptor extends AbstractInterceptor implements Serializable {
	
	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		if (session.getAttribute(StaticName.session_user) == null) {
			session.setAttribute(StaticName.session_msg, StaticName.session_msg_info);
			return StaticName.logon_user;
		}
		
		return actionInvocation.invoke();
	}

}
