package com.eastdawn.dao;

import java.util.List;
import java.util.Map;

import com.eastdawn.po.User;

@SuppressWarnings("unchecked")
public interface UserDao {

	public List<User> queryUserByPage(Map queryMap);
	public Long getUserCountByPage(Map queryMap);
	
	public User getUserById(Long userId);
	
	public void add(User user);
	
	public void deleteById(Long userId);
	
	public void deleteUpdateById(User user);
	
	public void passwordUpdateById(User user);
	
	public void updateLevelById(User user);
	public void updateLogTimeById(User user);
	
	//判断在线用户不为管理员
	public List<User> queryOnlineUserByPage(Map queryMap);
}
