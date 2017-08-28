package com.eastdawn.dao;

import java.util.List;
import java.util.Map;

import com.eastdawn.po.Files;

public interface FilesDao {
	
	public List<Files> queryFileByPage(Map queryMap);
	
	public void add(Files files);
	
	public void deleteById(Long fileId);
	
	public void updateById(Files files);
	
}
