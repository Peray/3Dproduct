package com.eastdawn.bo.impl;

import com.eastdawn.dao.CategoryDao;
import com.eastdawn.bo.CategoryBO;
import com.eastdawn.common.CommonBO;
import com.eastdawn.po.Category;

@SuppressWarnings("serial")
public class CategoryBOImpl extends CommonBO implements CategoryBO {
	private CategoryDao categoryDao;
 
	public Long add(Category category) {
		categoryDao.add(category);
		return category.getCategoryId();
	}
	public void updateById(Category category) {
		categoryDao.updateById(category);
	}
	
	public CategoryDao getCategoryDao() {
		return categoryDao;
	}
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
}
