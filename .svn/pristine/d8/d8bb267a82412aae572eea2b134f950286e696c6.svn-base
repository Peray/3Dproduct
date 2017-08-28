package com.eastdawn.bo.impl;

import com.eastdawn.dao.ServiceGlDao;
import com.eastdawn.bo.ServiceGlBO;
import com.eastdawn.common.CommonBO;
import com.eastdawn.po.ServiceGl;

@SuppressWarnings("serial")
public class ServiceGlBOImpl extends CommonBO implements ServiceGlBO {
	private ServiceGlDao glDao;
 
	public Long add(ServiceGl gl) {
		glDao.add(gl);
		return gl.getGlId();
	}
	public void updateById(ServiceGl gl) {
		glDao.updateById(gl);
	}
	public ServiceGlDao getGlDao() {
		return glDao;
	}
	public void setGlDao(ServiceGlDao glDao) {
		this.glDao = glDao;
	}

}
