package com.eastdawn.dao.oracle;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.eastdawn.dao.UserDao;
import com.eastdawn.common.CommonDao;
import com.eastdawn.po.User;

@SuppressWarnings({"serial", "unchecked"})
public class UserDaoImpl extends SqlMapClientDaoSupport implements UserDao {
	
	public List<User> queryUserByPage(Map queryMap) {
		return getSqlMapClientTemplate().queryForList("userMap.queryUserByPage", queryMap);
	}

	public Long getUserCountByPage(Map queryMap) {
		return (Long) getSqlMapClientTemplate().queryForObject("userMap.getUserCountByPage", queryMap);
	}
	
	public User getUserById(Long userId){
		return (User) getSqlMapClientTemplate().queryForObject("userMap.getUserById", userId);
	}
	
	public void add(User user) {
		 getSqlMapClientTemplate().insert("userMap.add", user);
	}
	
	public void deleteById(Long userId) {
		getSqlMapClientTemplate().update("userMap.deleteById", userId);

	}

	public void deleteUpdateById(User user) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("userMap.deleteUpdateById", user);
	}
	
	public void passwordUpdateById(User user) {
		getSqlMapClientTemplate().update("userMap.passwordUpdateById", user);
	}

}