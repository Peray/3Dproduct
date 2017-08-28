package com.eastdawn.bo;

import java.io.Serializable;

import com.eastdawn.po.Admin;

public interface AdminBO extends Serializable {
	
	public Admin getAdminById(Integer adminId);

}
