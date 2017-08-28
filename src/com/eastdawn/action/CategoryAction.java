package com.eastdawn.action;

import java.io.IOException;
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

public class CategoryAction extends PageAction{
	
	private Long categoryId;//类别ID
	private String categoryName;//类别名称
	private String path;//类别路径
	private Long parentId;//类别父ID
	
	private List categoryList;
	private CategoryDao categoryDao;
	private CategoryBO categoryBO;
	
	private Category category;
	
	private Long statr;
	private Long totalNum;
	
	//类别添加方法
	public String add() throws Exception {
		HttpServletResponse iResponse =ServletActionContext.getResponse();
		Category category = new Category();
		try {
			category.setCategoryName(this.categoryName);
			category.setPath(this.path);
			category.setParentId(this.categoryId);
			categoryBO.add(category);
			categoryId = null;
			iResponse.getWriter().write("1");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	    return null;
	}
	
	//类别查询方法
	public String execute() throws Exception {
		HttpServletResponse iResponse =ServletActionContext.getResponse();
		Map queryMap = new HashMap();
		//根据父ID查询类别
		if(this.categoryId == null || this.categoryId.equals("")){
		    queryMap.put("parentIsNull", true);
		} else {
			queryMap.put("parentId", categoryId);
		}
		
		totalNum = categoryDao.getCategoryCountByPage(queryMap);
//		if(statr == null){
//			queryMap.put("numStart", 0);
//		}else{
//			queryMap.put("numStart", (statr-1)*10);
//		}
		categoryList = categoryDao.queryCategoryByPage(queryMap); 
		JSONObject object = new JSONObject();  
        object.put("num", totalNum); 
        object.put("list", categoryList); 
        System.out.println(totalNum+object.toString());
        iResponse.setCharacterEncoding("utf-8");
        iResponse.getWriter().write(object.toString());
		return null;	
	}
	
	
	//类别查询方法
	public String executeAll() throws Exception {
		HttpServletResponse iResponse =ServletActionContext.getResponse();
		Map queryMap = new HashMap();
		//根据父ID查询类别
		if(this.categoryId == null || this.categoryId.equals("")){
		    queryMap.put("parentIsNull", true);
		} else {
			queryMap.put("parentId", categoryId);
		}
		
		totalNum = categoryDao.getCategoryCountByPage(queryMap);
//		if(statr == null){
//			queryMap.put("numStart", 0);
//		}else{
//			queryMap.put("numStart", (statr-1)*10);
//		}
		categoryList = categoryDao.queryCategoryByPage(queryMap); 
		JSONObject object = new JSONObject();  
        object.put("num", totalNum); 
        object.put("list", categoryList); 
        System.out.println(totalNum+object.toString());
        iResponse.setCharacterEncoding("utf-8");
        iResponse.getWriter().write(object.toString());
		return null;	
	}
	
	//修改方法
	public String edit() throws ParseException{
		HttpServletResponse iResponse =ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();	
        
        try {
        	Map queryMap = new HashMap();
            queryMap.put("categoryId", categoryId);
            categoryList = categoryDao.queryCategoryByPage(queryMap); 
    		JSONObject object = new JSONObject();  
            object.put("list", categoryList); 
            System.out.println(totalNum+object.toString());
            iResponse.setCharacterEncoding("utf-8");
        	iResponse.getWriter().write(object.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	//更新
	public String update() throws ParseException, IOException{
		HttpServletResponse iResponse =ServletActionContext.getResponse();
		Category category = new Category();	
		try {
			category.setCategoryId(getCategoryId());
			category.setCategoryName(getCategoryName());
			category.setPath(getPath());
			categoryBO.updateById(category);
			iResponse.getWriter().write("1");
		} catch (Exception e) {
			// TODO: handle exception
			iResponse.getWriter().write("2");
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	//删除
	public String delete() throws Exception{
		System.out.println("Start to execute delete Action!");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse iResponse =ServletActionContext.getResponse();
		try {
			categoryId = Long.parseLong(request.getParameter("categoryId"));
			Map queryMap = new HashMap();
			//根据父ID查询类别
			if(this.categoryId != null || !this.categoryId.equals("")){
				queryMap.put("parentId", categoryId);
			} 
			List categoryList = categoryDao.queryCategoryByPage(queryMap);
			if(categoryList.size() != 0){
		        iResponse.getWriter().write("2");
			}else{
				categoryDao.deleteById(categoryId);
		        iResponse.getWriter().write("1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
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

	public CategoryBO getCategoryBO() {
		return categoryBO;
	}

	public void setCategoryBO(CategoryBO categoryBO) {
		this.categoryBO = categoryBO;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Long getStatr() {
		return statr;
	}

	public void setStatr(Long statr) {
		this.statr = statr;
	}

	public Long getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Long totalNum) {
		this.totalNum = totalNum;
	}
}