package com.eastdawn.bo.impl;

import com.eastdawn.dao.MetadataDao;
import com.eastdawn.bo.MetadataBO;
import com.eastdawn.common.CommonBO;
import com.eastdawn.po.Metadata;

@SuppressWarnings("serial")
public class MetadataBOImpl extends CommonBO implements MetadataBO {
	private MetadataDao metadataDao;
 
	public Long add(Metadata metadata) {
		metadataDao.add(metadata);
		return metadata.getDataId();
	}
	public void updateById(Metadata metadata) {
		metadataDao.updateById(metadata);
	}
	public MetadataDao getMetadataDao() {
		return metadataDao;
	}
	public void setMetadataDao(MetadataDao metadataDao) {
		this.metadataDao = metadataDao;
	}
}
