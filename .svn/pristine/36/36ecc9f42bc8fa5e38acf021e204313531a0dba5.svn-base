package com.eastdawn.dao;

import java.util.List;
import java.util.Map;

import com.eastdawn.po.Logs;
import com.eastdawn.po.User;

@SuppressWarnings("unchecked")
public interface LogsDao {

	public List<Logs> queryLogByPage(Map queryMap);
	public void add(Logs logs);
	public Long getLogCountByPage(Map queryMap);
	public void deleteById(Long logId);
	
	public List<Logs> querySearchDaysByPage(Map queryMap);
	public List<Logs> querySearchWeekByPage(Map queryMap);
	public List<Logs> querySearchUpWeekByPage(Map queryMap);
	public List<Logs> querySearchMonthByPage(Map queryMap);
	public List<Logs> querySearchUpMonthByPage(Map queryMap);
	public List<Logs> querySearchTimeByPage(Map queryMap);
}
