package com.eastdawn.action;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;


import com.eastdawn.common.PageAction;
import com.eastdawn.dao.CategoryDao;
import com.eastdawn.bo.CategoryBO;
import com.eastdawn.po.Category;

public class FirstAction extends PageAction{
	private List categoryList;
	private CategoryDao categoryDao;
	
	//类别查询方法
	public String execute() throws Exception {
		HttpServletResponse iResponse =ServletActionContext.getResponse();
		Map queryMap = new HashMap();
		//根据父ID查询类别
		try {
			queryMap.put("parentIsNull", true);
			categoryList = categoryDao.queryCategoryByPage(queryMap); 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return SUCCESS;	
	}
	public List getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List categoryList) {
		this.categoryList = categoryList;
	}

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	
}