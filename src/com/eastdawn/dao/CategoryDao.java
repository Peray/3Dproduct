package com.eastdawn.dao;

import java.util.List;
import java.util.Map;

import  com.eastdawn.po.Category;

public interface CategoryDao {
	
	public Category getCategoryById(Long categoryId);
	
	public  List<Category> queryCategoryByPage(Map queryMap);
	
    public Long getCategoryCountByPage(Map queryMap);
    
	public void add(Category category);
	
	public void deleteById(Long categoryId);
	
	public void updateById(Category category);


}
