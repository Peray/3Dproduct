package com.eastdawn.bo.impl;

import com.eastdawn.bo.AdminBO;
import com.eastdawn.common.CommonBO;
import com.eastdawn.dao.AdminDao;
import com.eastdawn.po.Admin;

@SuppressWarnings("serial")
public class AdminBOImpl extends CommonBO implements AdminBO {
	private AdminDao adminDao;

	public Admin getAdminById(Integer adminId) {
		return adminDao.getAdminById(adminId);
	}

	public AdminDao getAdminDao() {
		return adminDao;
	}

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

}
