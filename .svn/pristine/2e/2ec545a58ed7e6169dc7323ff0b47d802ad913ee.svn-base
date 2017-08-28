package com.eastdawn.dao.oracle;

import java.util.List;
import java.util.Map;

import com.eastdawn.common.CommonDao;
import com.eastdawn.dao.AdminDao;
import com.eastdawn.po.Admin;

@SuppressWarnings("serial")
public class AdminDaoImpl extends CommonDao implements AdminDao {
	
	public Admin getAdminById(Integer adminId){
		return (Admin) getSqlMapClientTemplate().queryForObject("adminMap.getAdminById", adminId);
	}
	public List queryAdminByPage(Map queryMap) {
		// TODO Auto-generated method stub
		return (List)getSqlMapClientTemplate().queryForList("adminMap.queryAdminByPage", queryMap);
	}
	
}
