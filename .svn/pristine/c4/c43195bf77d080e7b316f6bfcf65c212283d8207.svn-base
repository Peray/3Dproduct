package com.eastdawn.dao.oracle;

import java.util.List;
import java.util.Map;

import com.eastdawn.po.Files;
import com.eastdawn.po.Metadata;
import com.eastdawn.common.CommonDao;
import com.eastdawn.dao.FilesDao;
import com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBaseIterators.ParentIterator;

public class FilesDaoImpl extends CommonDao implements FilesDao{
	
	public List<Files> queryFileByPage(Map queryMap) {
		return getSqlMapClientTemplate().queryForList("filesMap.queryFileByPage", queryMap);
	}
	public void add(Files files) {
		 getSqlMapClientTemplate().insert("filesMap.add", files);
	}
	public void deleteById(Long fileId) {
		getSqlMapClientTemplate().update("filesMap.deleteById", fileId);
	}
	
	public void updateById(Files files) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("filesMap.updateById", files);
	}
}
