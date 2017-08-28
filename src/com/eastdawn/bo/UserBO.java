package com.eastdawn.bo;

import java.io.Serializable;

import com.eastdawn.po.MyService;
import com.eastdawn.po.User;

public interface UserBO extends Serializable  {
	public User getUserById(Long userId);
	public Long add(User user);
	public void passwordUpdateById(User user);
	public void updateLevelById(User user);
	public void updateLogTimeById(User user);
}