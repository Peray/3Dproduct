package com.eastdawn.util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

@SuppressWarnings("serial")
public class StartUtil extends HttpServlet {

	@Override
	public void init() throws ServletException {
		System.out.println("进---入 startUtil------------------------");
		// TODO Auto-generated method stub
		super.init();
//		CategoryUtil.setContext(super.getServletContext());
//		CategoryUtil.foreach(CategoryUtil.getCategoryList());
	}
	
	

}
