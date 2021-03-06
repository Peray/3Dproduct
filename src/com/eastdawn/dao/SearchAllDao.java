package com.eastdawn.dao;

import java.util.List;
import java.util.Map;

import com.eastdawn.po.Heliu;
import com.eastdawn.po.Heliut;
import com.eastdawn.po.Hupo;
import com.eastdawn.po.SearchAll;

@SuppressWarnings("unchecked")
public interface SearchAllDao {
	public List<SearchAll> queryAllByPage(Map queryMap);
	public Long getAllCountByPage(Map queryMap);

	public List<Hupo> queryHPAllByPage(Map queryMap);
	public List<Heliu> queryHLAllByPage(Map queryMap);
	public List<Heliut> queryHLTAllByPage(Map queryMap);
}