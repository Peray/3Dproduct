//package com.eastdawn.tag;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.jsp.JspException;
//import javax.servlet.jsp.JspWriter;
//import javax.servlet.jsp.tagext.BodyContent;
//
//import org.apache.log4j.Logger;
//import org.springframework.context.ApplicationContext;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//
//import com.eastdawn.common.BaseSupportTag;
//import com.eastdawn.common.PageUtil;
//import com.eastdawn.dao.FilesDao;
//import com.eastdawn.po.Files;
//
//@SuppressWarnings("serial")
//public class CategoryFileTag extends BaseSupportTag {
//	private Long categoryId;
//	
//	@SuppressWarnings("unchecked")
//	public void writeTagBodyContent(JspWriter out, BodyContent bodyContent)
//			throws IOException {
//		Logger logger = Logger.getLogger(CategoryFileTag.class);
//		logger.info("进入Tag: " + CategoryFileTag.class.getName());
//		HttpServletRequest request = (HttpServletRequest) super.pageContext.getRequest();
//		String queryStr = request.getQueryString();
//		StringBuffer bodyStr = new StringBuffer(bodyContent.getString());
//		bodyStr = parseExclude(bodyStr);
//		bodyStr = replaceTagWithRealInfo(bodyStr, "<-ProductShow.QURL->",
//				queryStr);
//		bodyStr = replaceTagWithRealInfo(bodyStr, "<!--URLROOT-->", request
//				.getContextPath());
//		bodyStr = replaceTagWithRealInfo(bodyStr, "<-Request_PageURL->",request.getServletPath().substring(1));
//		out.println(bodyStr.substring(0, bodyStr.indexOf("<!--FileList_Row_Start-->")));
//		String circulationTrContent = bodyStr.substring(bodyStr.indexOf("<!--FileList_Row_Start-->")
//				+ "<!--FileList_Row_Start-->".length(), bodyStr.indexOf("<!--FileList_Row_End-->"));
//		StringBuffer columnBody = new StringBuffer( circulationTrContent);
//		
//		
//		//业务逻辑代码从这里开始
//		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());
//		FilesDao filesDao = (FilesDao) ctx.getBean("filesDao");
//		Map queryMap = new HashMap();
//		List<Files> filesList = null;
//		
//		try {
//			queryMap.put("status", "1");
//			queryMap.put("categoryMap", categoryId);
//			queryMap.put(PageUtil.NUM_END, 4);
//			queryMap.put("updateTimeDesc", true);
//			filesList = filesDao.queryFileByPage(queryMap);
//		} catch (RuntimeException e) {
//			e.printStackTrace();
//		}
//		
//		if (filesList == null ||  filesList.size() <= 0) {
//			columnBody = replaceTagMiddle(columnBody, "<!--Null_Start-->", "<!--Null_End-->");
//			columnBody = replaceTagWithRealInfo(columnBody, "<!--null-->", "当前栏目空！");
//			out.println(columnBody.toString());
//		} else {
//			for (Files file : filesList) {
//				columnBody = new StringBuffer(circulationTrContent);
//				
//				columnBody = replaceTagWithRealInfo(columnBody, "<!--FileName-->", file.getFileName());
//				columnBody = replaceTagHyperlink(columnBody,
//						"<!--File_Id_Start-->", "<!--File_Id_End-->",
//						"fileId=" + file.getFileId() + "\"");
//				
//				out.println(columnBody.toString());
//			}
//			
//		}
//		
//		out.println(bodyStr.substring(bodyStr .indexOf("<!--FileList_Row_End-->")
//				+ "<!--FileList_Row_End-->".length()));
//		bodyContent.clearBody();
//		logger.info("结束Tag: " + CategoryFileTag.class.getName());
//	}
//	
//	public void otherDoStartTagOperations() {
//		HttpServletResponse response = (HttpServletResponse) pageContext
//				.getResponse();
//		response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
//		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
//		response.setDateHeader("Expires", -1); // Prevents caching at the proxy
//
//	}
//
//	@Override
//	public int doStartTag() throws JspException {
//		this.otherDoStartTagOperations();
//		return EVAL_BODY_BUFFERED;
//	}
//
//	@Override
//	public int doEndTag() throws JspException {
//		return EVAL_PAGE;
//	}
//
//	@Override
//	public int doAfterBody() throws JspException {
//		try {
//			//
//			// This code is generated for tags whose bodyContent is "JSP"
//			//
//			BodyContent bodyContent = super.getBodyContent();
//			JspWriter out = bodyContent.getEnclosingWriter();
//			this.writeTagBodyContent(out, bodyContent);
//		} catch (Exception ex) {
//			handleBodyContentException(ex);
//		}
//		return SKIP_BODY;
//	}
//	
//	public void handleBodyContentException(Exception ex) throws JspException {
//		// Since the doAfterBody method is guarded, place exception handing code
//		// here.
//		ex.printStackTrace();
//		throw new JspException("error in RegisterTag: " + ex);
//	}
//
//	public void setCategoryId(Long categoryId) {
//		this.categoryId = categoryId;
//	}
//
//	public Long getCategoryId() {
//		return categoryId;
//	}
//
//}
