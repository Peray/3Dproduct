package com.eastdawn.dao.oracle;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.eastdawn.dao.LogsDao;
import com.eastdawn.po.Logs;
import com.eastdawn.po.User;

@SuppressWarnings({"serial", "unchecked"})
public class LogsDaoImpl extends SqlMapClientDaoSupport implements LogsDao {

	public void add(Logs logs) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("logsMap.add", logs);
	}

	public List<Logs> queryLogByPage(Map queryMap) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("logsMap.queryLogByPage", queryMap);
	}
	
	public Long getLogCountByPage(Map queryMap) {
		return (Long) getSqlMapClientTemplate().queryForObject("logsMap.getLogCountByPage", queryMap);
	}
	
	public void deleteById(Long logId) {
		getSqlMapClientTemplate().update("logsMap.deleteById", logId);
	}
	
	public List<Logs> querySearchDaysByPage(Map queryMap) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("logsMap.querySearchDaysByPage", queryMap);
	}
	public List<Logs> querySearchWeekByPage(Map queryMap) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("logsMap.querySearchWeekByPage", queryMap);
	}
	public List<Logs> querySearchUpWeekByPage(Map queryMap) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("logsMap.querySearchUpWeekByPage", queryMap);
	}
	public List<Logs> querySearchMonthByPage(Map queryMap) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("logsMap.querySearchMonthByPage", queryMap);
	}
	public List<Logs> querySearchUpMonthByPage(Map queryMap) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("logsMap.querySearchUpMonthByPage", queryMap);
	}
	public List<Logs> querySearchTimeByPage(Map queryMap) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("logsMap.querySearchTimeByPage", queryMap);
	}
}