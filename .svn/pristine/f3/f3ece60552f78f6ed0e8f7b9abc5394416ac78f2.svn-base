package com.eastdawn.bo.impl;

import com.eastdawn.bo.FilesBO;
import com.eastdawn.common.CommonBO;
import com.eastdawn.dao.FilesDao;
import com.eastdawn.po.Files;

@SuppressWarnings("serial")
public class FilesBOImpl extends CommonBO implements FilesBO {
	private FilesDao fileDao;

	public Long add(Files files) {
		fileDao.add(files);
		
		return files.getFileId();
	}
	public void updateById(Files files) {
		fileDao.updateById(files);
	}
	public FilesDao getFileDao() {
		return fileDao;
	}

	public void setFileDao(FilesDao fileDao) {
		this.fileDao = fileDao;
	}

}