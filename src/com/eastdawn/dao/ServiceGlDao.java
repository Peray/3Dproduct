package com.eastdawn.dao;

import java.util.List;
import java.util.Map;

import com.eastdawn.po.ServiceGl;

@SuppressWarnings("unchecked")
public interface ServiceGlDao {

	public List<ServiceGl> queryGLByPage(Map queryMap);
	public void add(ServiceGl gl);
	public Long getGLCountByPage(Map queryMap);
	public void deleteById(Long glId);
	public void updateById(ServiceGl gl);
	public Long getGLById(Map queryMap);
	public Long getGLCountByNum(Map queryMap);
	public List<ServiceGl> querySearchKeyByPage(Map queryMap);
	public Long getSearchKeyCountByNum(Map queryMap);
	
	public List<ServiceGl> queryCategoryByPage(Map queryMap);
	public Long getCategoryCountByNum(Map queryMap);
	//浏览量
	public void updateBrowseById(ServiceGl gl);
}
