package com.eastdawn.dao.oracle;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.eastdawn.dao.MetadataDao;
import com.eastdawn.po.Metadata;

@SuppressWarnings({"serial", "unchecked"})
public class MetadataDaoImpl extends SqlMapClientDaoSupport implements MetadataDao {

	public void add(Metadata metadata) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("metadataMap.add", metadata);
	}

	public List<Metadata> queryMetadataByPage(Map queryMap) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("metadataMap.queryMetadataByPage", queryMap);
	}
	
	public Long getMetadataCountByPage(Map queryMap) {
		return (Long) getSqlMapClientTemplate().queryForObject("metadataMap.getMetadataCountByPage", queryMap);
	}
	
	public void deleteById(Long dataId) {
		getSqlMapClientTemplate().update("metadataMap.deleteById", dataId);
	}
	public void updateById(Metadata metadata) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("metadataMap.updateById", metadata);
	}
}