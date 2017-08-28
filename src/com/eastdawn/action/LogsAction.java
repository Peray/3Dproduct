package com.eastdawn.action;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.eastdawn.po.ServiceGl;
import com.eastdawn.po.User;
import com.eastdawn.util.ConvertDate;
import com.eastdawn.util.StaticName;

@SuppressWarnings("serial")
public class LogsAction{
	
	private Long logId;//日志ID
	private String userName;//用户登录名
	private String ip;//登录IP
	private String event;//事件
	private Integer times;//次数
	private Date startTime;
	private Date endTime;
	
	private List logsList;
	
	private Long statr;
	private Long totalNum;
	
	private Logs logs;
	private LogsDao logsDao;
	
	//日志查询
	public String execute() throws Exception {
		HttpServletResponse iResponse =ServletActionContext.getResponse();
		
		Map queryMap = new HashMap();
		try {
			System.out.println(this.startTime+"---"+this.endTime);
			if(this.startTime != null && !this.startTime.equals("")){
			    queryMap.put("startTime", startTime);
			}
			if(this.endTime != null && !this.endTime.equals("")){
				String str = ConvertDate.convert(this.endTime) + " 23:59:59";
				queryMap.put("endTime", str);
			}
			totalNum = logsDao.getLogCountByPage(queryMap);
			System.out.println(totalNum);
			if(statr == null){
				queryMap.put("numStart", 0);
			}else{
				queryMap.put("numStart", (statr-1)*10);
			}
			logsList = logsDao.queryLogByPage(queryMap);
			
			JSONObject object = new JSONObject();
	        object.put("num", totalNum); 
	        object.put("list", logsList); 
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
			logId = Long.parseLong(request.getParameter("logId"));
			logsDao.deleteById(logId);
			iResponse.getWriter().write("1");
		} catch (Exception e) {
			e.printStackTrace();
			iResponse.getWriter().write("2");
		}
	}

	public Long getLogId() {
		return logId;
	}


	public void setLogId(Long logId) {
		this.logId = logId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getIp() {
		return ip;
	}


	public void setIp(String ip) {
		this.ip = ip;
	}


	public String getEvent() {
		return event;
	}


	public void setEvent(String event) {
		this.event = event;
	}


	public Integer getTimes() {
		return times;
	}


	public void setTimes(Integer times) {
		this.times = times;
	}


	public Date getStartTime() {
		return startTime;
	}


	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}


	public Date getEndTime() {
		return endTime;
	}


	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


	public List getLogsList() {
		return logsList;
	}


	public void setLogsList(List logsList) {
		this.logsList = logsList;
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


	public Logs getLogs() {
		return logs;
	}


	public void setLogs(Logs logs) {
		this.logs = logs;
	}


	public LogsDao getLogsDao() {
		return logsDao;
	}


	public void setLogsDao(LogsDao logsDao) {
		this.logsDao = logsDao;
	}
	
}