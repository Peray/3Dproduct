package com.eastdawn.common;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.eastdawn.util.ConvertDate;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class DebugInterceptor extends AbstractInterceptor implements Serializable {
	
	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		Object action = actionInvocation.getAction();
		ActionContext  context = actionInvocation.getInvocationContext();
		Logger logger = Logger.getLogger(DebugInterceptor.class);
		HttpServletRequest request = ServletActionContext.getRequest();
		StringBuffer sb = new StringBuffer();
		
		// 获取请求的action名称  
		String actionName = context.getName();
		// 获取请求的方法名称
		String method = actionInvocation.getProxy().getMethod();
		// 获取action后附带参数
        Map<String, Object> parameters = context.getParameters();
        Set<String> keys = parameters.keySet();
        for (String key : keys) {
        	sb.append(key + "=" + request.getParameter(key) + ", ");
        }
        
		
		logger.info("进入Action: " + action.getClass().getName() + "!" + method);
		logger.info("参数：" + sb.toString());
		
		
		Date date1 = new Date();
		String result = actionInvocation.invoke();
		Date date2 = new Date();
		
		long consume = date2.getTime() - date1.getTime();
		if (consume > 1000 * 5) {
			logger.warn("用时：" + ConvertDate.consumeTime(consume));
		}
		
		logger.info("返回结果 : " + result);
		return result;
	}

}
