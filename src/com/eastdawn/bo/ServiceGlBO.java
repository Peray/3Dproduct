package com.eastdawn.bo;

import java.io.Serializable;

import com.eastdawn.po.ServiceGl;

public interface ServiceGlBO extends Serializable  {
	public Long add(ServiceGl gl);
	public void updateById(ServiceGl gl);
}