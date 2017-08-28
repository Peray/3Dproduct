package com.eastdawn.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadFile extends HttpServlet {
	/**
	 * Constructor of the object.
	 */
	public UploadFile() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}
	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); //两个都用
		response.setCharacterEncoding("UTF-8");
		System.out.println("进入upload方法");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String dateString = formatter.format(new Date());
		  String path = request.getSession().getServletContext().getRealPath("\\share");  
		          DiskFileItemFactory factory = new DiskFileItemFactory();  
		          ServletFileUpload upload = new ServletFileUpload(factory);  
		          upload.setHeaderEncoding("utf-8");
		   String fileName= "";
		   String newFile="";
		          try {
		              List<FileItem> list = (List<FileItem>) upload.parseRequest(request);  
		              System.out.println(list.size());
		              for (FileItem fileItem : list) {
		                  fileName = fileItem.getName();
		                  System.out.println(fileName);//获取文件全名
		                  String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
		                  System.out.println(prefix);
		                  //------------<<文档类名称命名规范>>
		                	  newFile= dateString+"_"+new Random().nextInt(999999) + "." +prefix;//截取文件后缀
		                  //newFile= Math.random() + "." +prefix;//截取文件后缀
		                  System.out.println(newFile);//生成新名字
		                  File file = new File(path + "/"+newFile );  
		                  System.out.println(file);
		                  fileItem.write(file);  
		                  fileItem.delete();  
		              }
		        } catch (Exception e) {  
		              e.printStackTrace();  
		        }
		        PrintWriter out = response.getWriter();
				out.println("/" + newFile);
				System.out.println(newFile+"-----newFile");
				out.flush();
				out.close();
	}
	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
}
