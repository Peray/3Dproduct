package com.eastdawn.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.eastdawn.bo.MyServiceBO;
import com.eastdawn.dao.LogsDao;
import com.eastdawn.dao.MyServiceDao;
import com.eastdawn.po.Logs;
import com.eastdawn.po.MyService;
import com.eastdawn.po.User;
import com.eastdawn.util.StaticName;

@SuppressWarnings("serial")
public class MyServiceAction{
	
	private Long serviceId;//服务ID
	private String serviceNumber;//编号
	private String serviceName;//服务名称
	private String address;//服务地址
	private Date time;//订阅时间
	private Long userId;//用户ID
	private Long parentId;//服务ID
	
	private List serviceList;
	private String info;
	private Long statr;
	private Long totalNum;
	
	private MyService myService;
	private MyServiceDao myServiceDao;
	private MyServiceBO myServiceBO;
	private LogsDao logsDao;
	
	//我的资源添加
	public String add() throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		HttpServletResponse response =ServletActionContext.getResponse();
		MyService myService = new MyService();
		User user =(User)session.getAttribute(StaticName.session_user);	
		try {
			myService.setServiceNumber(this.serviceNumber);
			myService.setServiceName(this.serviceName);
			myService.setAddress(this.address);
			myService.setTime(new Date());
			myService.setUserId(user.getUserId());
			myService.setParentId(this.parentId);
			myServiceBO.add(myService);
			this.logAdd("定制服务-新增"+this.parentId);
			response.getWriter().write("1");
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("2");
			// TODO: handle exception
		}
		return null;
	}
	
	//服务查询
	public String execute() throws Exception {
		HttpServletResponse iResponse =ServletActionContext.getResponse();
		Map queryMap = new HashMap();
		try {
			if(this.serviceId != null && !this.serviceId.equals("")){
			    queryMap.put("serviceId", serviceId);
			}
			if(this.serviceName != null && !this.serviceName.equals("")){
			    queryMap.put("serviceName", serviceName);
			}
			if(this.userId != null && !this.userId.equals("")){
			    queryMap.put("userId", userId);
			}
			if(this.parentId != null && !this.parentId.equals("")){
			    queryMap.put("parentId", parentId);
			}
			totalNum = myServiceDao.getMSCountByPage(queryMap);
			System.out.println(totalNum);
			if(statr == null){
				queryMap.put("numStart", 0);
			}else{
				queryMap.put("numStart", (statr-1)*10);
			}
			serviceList = myServiceDao.queryMSByPage(queryMap);
			
			JSONObject object = new JSONObject();
	        object.put("num", totalNum); 
	        object.put("list", serviceList); 
	        System.out.println(totalNum+object.toString());
	        iResponse.setCharacterEncoding("utf-8");
	        iResponse.getWriter().write(object.toString());
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	//服务查询
	public String loadService() throws Exception {
		HttpServletResponse iResponse =ServletActionContext.getResponse();
		Map queryMap = new HashMap();
		try {
			if(this.userId != null && !this.userId.equals("")){
			    queryMap.put("userId", userId);
			}
			serviceList = myServiceDao.queryMyServiceByPage(queryMap);
			JSONObject object = new JSONObject();
	        object.put("list", serviceList); 
	        System.out.println(totalNum+object.toString());
	        iResponse.setCharacterEncoding("utf-8");
	        iResponse.getWriter().write(object.toString());
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return null;	
	}

	public void delete() throws Exception{
		System.out.println("Start to execute delete Action!");
		HttpServletResponse iResponse =ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		Map queryMap = new HashMap();
		
		try {
			serviceId = Long.parseLong(request.getParameter("serviceId"));
			myServiceDao.deleteById(serviceId);
			this.logAdd("定制服务-删除ID"+serviceId);
			iResponse.getWriter().write("1");
		} catch (Exception e) {
			e.printStackTrace();
			iResponse.getWriter().write("2");
		}
	}
	
	public String logAdd(String event) throws Exception {
		try {
			HttpSession session = ServletActionContext.getRequest().getSession();
			User user =(User)session.getAttribute(StaticName.session_user);
			Logs logs = new Logs();
			logs.setUserName(user.getLogName());
			logs.setUserId(user.getUserId());
			logs.setIp(ServletActionContext.getRequest().getRemoteAddr());
			logs.setEvent(event);
			logs.setTime(new Date());
			logs.setTimes(1);
			this.logsDao.add(logs);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//关键字查询
	public String searchKey() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		List<String> paramList = new ArrayList();
		Map queryMap = new HashMap();
		try {
			if (info != null && !info.trim().equals("")) {
				this.infoAssay(paramList);
				queryMap.put("paramList", paramList);
			}
			if(this.userId != null && !this.userId.equals("")){
			    queryMap.put("userId", userId);
			}
			totalNum = myServiceDao.getSearchKeyCountByNum(queryMap);
			System.out.println(totalNum);
			if(statr == null){
				queryMap.put("numStart", 0);
			}else{
				queryMap.put("numStart", (statr-1)*10);
			}
			List searchList = myServiceDao.querySearchKeyByPage(queryMap);
			JSONObject object = new JSONObject();  
	        object.put("num", totalNum); 
	        object.put("list", searchList);
	        System.out.println(object.toString());
	        response.setCharacterEncoding("utf-8");
	        response.getWriter().write(object.toString());
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	//搜索栏信息分析
	private void  infoAssay(List paramList) {
		String[] keywords = this.info.trim().split(" ");
		for (int i = 0; i < keywords.length; i++) {
			if (!keywords[i].equals("")) {
				paramList.add(keywords[i].toLowerCase());
			}
		}
	}
	
	//本周定制服务次数
	public String searchWeek() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		Map queryMap = new HashMap();
		try {
			List searchList = myServiceDao.querySearchWeekByPage(queryMap);
			JSONObject object = new JSONObject();  
	        object.put("list", searchList);
	        System.out.println(object.toString());
	        response.setCharacterEncoding("utf-8");
	        response.getWriter().write(object.toString());
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//本月定制服务次数
	public String searchMonth() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		Map queryMap = new HashMap();
		try {
			List searchList = myServiceDao.querySearchMonthByPage(queryMap);
			JSONObject object = new JSONObject();  
	        object.put("list", searchList);
	        System.out.println(object.toString());
	        response.setCharacterEncoding("utf-8");
	        response.getWriter().write(object.toString());
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	//本年定制服务次数
	public String searchYear() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		Map queryMap = new HashMap();
		try {
			List searchList = myServiceDao.querySearchYearByPage(queryMap);
			JSONObject object = new JSONObject();  
	        object.put("list", searchList);
	        System.out.println(object.toString());
	        response.setCharacterEncoding("utf-8");
	        response.getWriter().write(object.toString());
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceNumber() {
		return serviceNumber;
	}

	public void setServiceNumber(String serviceNumber) {
		this.serviceNumber = serviceNumber;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public List getServiceList() {
		return serviceList;
	}

	public void setServiceList(List serviceList) {
		this.serviceList = serviceList;
	}

	public Long getStatr() {
		return statr;
	}

	public void setStatr(Long statr) {
		this.statr = statr;
	}

	public Long getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Long totalNum) {
		this.totalNum = totalNum;
	}

	public MyService getMyService() {
		return myService;
	}

	public void setMyService(MyService myService) {
		this.myService = myService;
	}

	public MyServiceDao getMyServiceDao() {
		return myServiceDao;
	}

	public void setMyServiceDao(MyServiceDao myServiceDao) {
		this.myServiceDao = myServiceDao;
	}

	public MyServiceBO getMyServiceBO() {
		return myServiceBO;
	}

	public void setMyServiceBO(MyServiceBO myServiceBO) {
		this.myServiceBO = myServiceBO;
	}

	public LogsDao getLogsDao() {
		return logsDao;
	}

	public void setLogsDao(LogsDao logsDao) {
		this.logsDao = logsDao;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}