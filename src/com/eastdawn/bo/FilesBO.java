package com.eastdawn.bo;

import java.io.Serializable;

import com.eastdawn.po.Files;


public interface FilesBO extends Serializable{

	public Long add(Files files);
	public void updateById(Files files);

}
