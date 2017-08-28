package com.eastdawn.dao.oracle;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.eastdawn.dao.MyServiceDao;
import com.eastdawn.po.MyService;

@SuppressWarnings({"serial", "unchecked"})
public class MyServiceDaoImpl extends SqlMapClientDaoSupport implements MyServiceDao {

	public void add(MyService myService) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("myServiceMap.add", myService);
	}

	public List<MyService> queryMSByPage(Map queryMap) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("myServiceMap.queryMSByPage", queryMap);
	}
	
	public Long getMSCountByPage(Map queryMap) {
		return (Long) getSqlMapClientTemplate().queryForObject("myServiceMap.getMSCountByPage", queryMap);
	}
	
	public void deleteById(Long serviceId) {
		getSqlMapClientTemplate().update("myServiceMap.deleteById", serviceId);
	}
	
	public List<MyService> querySearchKeyByPage(Map queryMap) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("myServiceMap.querySearchKeyByPage", queryMap);
	}
	
	public Long getSearchKeyCountByNum(Map queryMap) {
		return (Long) getSqlMapClientTemplate().queryForObject("myServiceMap.getSearchKeyCountByNum", queryMap);
	}
	
	public List<MyService> querySearchWeekByPage(Map queryMap) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("myServiceMap.querySearchWeekByPage", queryMap);
	}
	
	public List<MyService> querySearchMonthByPage(Map queryMap) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("myServiceMap.querySearchMonthByPage", queryMap);
	}
	
	public List<MyService> querySearchYearByPage(Map queryMap) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("myServiceMap.querySearchYearByPage", queryMap);
	}
	
	public List queryMyServiceByPage(Map queryMap) {
		// TODO Auto-generated method stub
		return (List)getSqlMapClientTemplate().queryForList("myServiceMap.queryMyServiceByPage", queryMap);
	}
}