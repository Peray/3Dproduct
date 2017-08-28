package com.eastdawn.dao;

import java.util.List;
import java.util.Map;

import com.eastdawn.po.MyService;
@SuppressWarnings("unchecked")
public interface MyServiceDao {

	public List<MyService> queryMSByPage(Map queryMap);
	public void add(MyService myService);
	public Long getMSCountByPage(Map queryMap);
	public void deleteById(Long serviceId);
	
	public List<MyService> querySearchKeyByPage(Map queryMap);
	public Long getSearchKeyCountByNum(Map queryMap);
	
	public List<MyService> querySearchWeekByPage(Map queryMap);
	public List<MyService> querySearchMonthByPage(Map queryMap);
	public List<MyService> querySearchYearByPage(Map queryMap);
	//查询详细信息
	public  List<MyService> queryMyServiceByPage(Map queryMap);

}
