package com.eastdawn.dao.oracle;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.eastdawn.dao.ServiceGlDao;
import com.eastdawn.po.ServiceGl;

@SuppressWarnings({"serial", "unchecked"})
public class ServiceGlDaoImpl extends SqlMapClientDaoSupport implements ServiceGlDao {

	public void add(ServiceGl gl) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("serviceGlMap.add", gl);
	}

	public List<ServiceGl> queryGLByPage(Map queryMap) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("serviceGlMap.queryGLByPage", queryMap);
	}
	
	public Long getGLCountByPage(Map queryMap) {
		return (Long) getSqlMapClientTemplate().queryForObject("serviceGlMap.getGLCountByPage", queryMap);
	}
	
	public void deleteById(Long glId) {
		getSqlMapClientTemplate().update("serviceGlMap.deleteById", glId);
	}
	
	public void updateById(ServiceGl gl) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("serviceGlMap.updateById", gl);
	}
	
	public Long getGLById(Map queryMap) {
		return (Long) getSqlMapClientTemplate().queryForObject("serviceGlMap.getGLById", queryMap);
	}
	
	public Long getGLCountByNum(Map queryMap) {
		return (Long) getSqlMapClientTemplate().queryForObject("serviceGlMap.getGLCountByNum", queryMap);
	}
	
	public List<ServiceGl> querySearchKeyByPage(Map queryMap) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("serviceGlMap.querySearchKeyByPage", queryMap);
	}
	
	public Long getSearchKeyCountByNum(Map queryMap) {
		return (Long) getSqlMapClientTemplate().queryForObject("serviceGlMap.getSearchKeyCountByNum", queryMap);
	}
	
	public List<ServiceGl> queryCategoryByPage(Map queryMap) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("serviceGlMap.queryCategoryByPage", queryMap);
	}
	
	public Long getCategoryCountByNum(Map queryMap) {
		return (Long) getSqlMapClientTemplate().queryForObject("serviceGlMap.getCategoryCountByNum", queryMap);
	}
	
	public void updateBrowseById(ServiceGl gl) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("serviceGlMap.updateBrowseById", gl);
	}
	
}