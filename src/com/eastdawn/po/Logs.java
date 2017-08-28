package com.eastdawn.po;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Logs implements Serializable {
	
	private Long logId;//日志ID
	private String userName;//用户登录名
	private String ip;//登录IP
	private Date time;//发生时间
	private String event;//事件
	private Integer times;//次数
	private Long userId;//用户ID
	
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
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
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
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
}