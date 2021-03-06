package com.eastdawn.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.eastdawn.bo.MyServiceBO;
import com.eastdawn.dao.LogsDao;
import com.eastdawn.dao.MyServiceDao;
import com.eastdawn.dao.SearchAllDao;
import com.eastdawn.po.Logs;
import com.eastdawn.po.MyService;
import com.eastdawn.po.SearchAll;
import com.eastdawn.po.User;
import com.eastdawn.util.StaticName;

@SuppressWarnings("serial")
public class searchAllAction{
	
	private Long fId;//jsonId
	private String name;
	private String point;
	private String source;
	private String code;
	private String size;
	private String unit;
	
	private List allList;
	
	private Long statr;
	private Long totalNum;
	
	private SearchAllDao searchAllDao;

	//关键字河流查询
	public String execute() throws Exception {
		HttpServletResponse iResponse =ServletActionContext.getResponse();
		Map queryMap = new HashMap();
		try {
			if(this.source != null && !this.source.equals("")){
			    queryMap.put("source", source);
			}
			if(this.name != null && !this.name.equals("")){
			    queryMap.put("name", name);
			}
			totalNum = searchAllDao.getAllCountByPage(queryMap);
			System.out.println(totalNum);
			if(this.statr == null){
				queryMap.put("numStart", 0);
			}else{
				queryMap.put("numStart", (statr)*10);
			}
			List allList = searchAllDao.queryAllByPage(queryMap);

			JSONObject object = new JSONObject();
	        object.put("num", totalNum);
	        object.put("list", allList);
	        System.out.println(totalNum+object.toString());
	        iResponse.setCharacterEncoding("utf-8");
	        iResponse.getWriter().write(object.toString());
	        
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return null;
	}

	//ID河流查询
	public String detail() throws Exception {
		HttpServletResponse iResponse =ServletActionContext.getResponse();
		Map queryMap = new HashMap();
		System.out.println(source);
		try {
			if(this.source.equals("hupo")){
				queryMap.put("source", source);
				if(this.fId != null && !this.fId.equals("")){
				    queryMap.put("fId", fId);
				}
				allList = searchAllDao.queryHPAllByPage(queryMap);
			}else if(this.source.equals("heliu")){
				queryMap.put("source", source);
				if(this.fId != null && !this.fId.equals("")){
				    queryMap.put("fId", fId);
				}
				allList = searchAllDao.queryHLAllByPage(queryMap);
			}else{
				queryMap.put("source", source);
				if(this.fId != null && !this.fId.equals("")){
				    queryMap.put("fId", fId);
				}
				allList = searchAllDao.queryHLTAllByPage(queryMap);
			}
			JSONObject object = new JSONObject();
	        object.put("list", allList);
	        System.out.println(object.toString());
	        iResponse.setCharacterEncoding("utf-8");
	        iResponse.getWriter().write(object.toString());
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return null;	
	}

	public Long getFId() {
		return fId;
	}

	public void setFId(Long id) {
		fId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
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

	public SearchAllDao getSearchAllDao() {
		return searchAllDao;
	}

	public void setSearchAllDao(SearchAllDao searchAllDao) {
		this.searchAllDao = searchAllDao;
	}

	public List getAllList() {
		return allList;
	}

	public void setAllList(List allList) {
		this.allList = allList;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
}