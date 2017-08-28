package com.eastdawn.bo.impl;

import com.eastdawn.dao.UserDao;
import com.eastdawn.bo.UserBO;
import com.eastdawn.common.CommonBO;
import com.eastdawn.po.User;

@SuppressWarnings("serial")
public class UserBOImpl extends CommonBO implements UserBO {
	private UserDao userDao;
 
	//根据主键查找用户信息
	public User getUserById(Long userId) {
		return userDao.getUserById(userId);
	}
	
	public Long add(User user) {
		userDao.add(user);
		return user.getUserId();
	}
	public void passwordUpdateById(User user) {
		userDao.passwordUpdateById(user);
		
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
