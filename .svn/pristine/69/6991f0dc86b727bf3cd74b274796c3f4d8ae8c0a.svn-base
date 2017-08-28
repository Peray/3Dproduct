package com.eastdawn.dao.oracle;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.eastdawn.dao.SearchAllDao;
import com.eastdawn.po.Heliu;
import com.eastdawn.po.Heliut;
import com.eastdawn.po.Hupo;
import com.eastdawn.po.SearchAll;

@SuppressWarnings({"serial", "unchecked"})
public class SearchAllDaoImpl extends SqlMapClientDaoSupport implements SearchAllDao {
	public List<SearchAll> queryAllByPage(Map queryMap) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("searchAllMap.queryAllByPage", queryMap);
	}
	public Long getAllCountByPage(Map queryMap) {
		return (Long) getSqlMapClientTemplate().queryForObject("searchAllMap.getAllCountByPage", queryMap);
	}
	
	public List<Hupo> queryHPAllByPage(Map queryMap) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("searchAllMap.queryHPAllByPage", queryMap);
	}
	public List<Heliu> queryHLAllByPage(Map queryMap) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("searchAllMap.queryHLAllByPage", queryMap);
	}
	public List<Heliut> queryHLTAllByPage(Map queryMap) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("searchAllMap.queryHLTAllByPage", queryMap);
	}
}