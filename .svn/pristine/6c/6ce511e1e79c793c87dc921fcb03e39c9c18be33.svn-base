package com.eastdawn.common;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.eastdawn.util.StaticName;

public class AdminSessionFilter implements Filter {
	
	public void destroy() {
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain filterChain) throws IOException, ServletException {
		Logger logger = Logger.getLogger(AdminSessionFilter.class);
		HttpServletRequest  request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		HttpSession session = request.getSession();
		
		//不拦截的页面
		String[] except = {"/login.jsp","/admin/index.jsp", "/admin/left.jsp", "/admin/right.jsp", "/admin/login.jsp"};
		Set<String> exceptSet = this.getExceptSet(except);
		
		String currentURL = request.getRequestURI(); // 取得根目录所对应的绝对路径:
		String targetURL = currentURL.substring(currentURL.lastIndexOf("/"), currentURL.length()); // 截取到当前文件名用于比较
		
		logger.info("currentURL：" + currentURL + "   targetURL: " + targetURL);
		
		if (currentURL.contains("/admin")) {
			if (!exceptSet.contains(targetURL)) {
				// 判断当前页是否是重定向以后的登录页面页面，如果是就不做session的判断，防止出现死循环
				if (session.getAttribute(StaticName.session_admin) == null) {
			
					session.setAttribute(StaticName.session_msg, StaticName.session_msg_info);
					// 如果session为空表示用户没有登录就重定向到/index.jsp页面
					response.sendRedirect(request.getContextPath() + "/admin/index.jsp");
					logger.info("session失效，当前url：" + currentURL + "   重定向到url: /admin/index.jsp");
					
					return;
				}
			}
		}
		filterChain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {
	}
	
	private Set<String> getExceptSet(String[] except) {
		Set<String> exceptSet = new HashSet<String>();
		for (String str : except) {
			exceptSet.add(str);
		}
		return exceptSet;
	}
}
