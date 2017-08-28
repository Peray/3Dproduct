package com.eastdawn.common;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

@SuppressWarnings("serial")
public class ImgServlet extends HttpServlet {
	
	private String getSessionName() {
		return "imageCode";
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Logger logger = Logger.getLogger(ImgServlet.class);
		response.setHeader("Cache-Control", "no-store");
		response.setContentType("image/jpeg");
		
		int number = AuthImg.create_number();
		request.getSession().setAttribute(getSessionName(), new Integer(number));
		ImageIO.write(AuthImg.create_Image(number), "JPG", response.getOutputStream());
		response.flushBuffer();
		logger.info("imageCode = " + request.getSession().getAttribute(this.getSessionName()));
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
