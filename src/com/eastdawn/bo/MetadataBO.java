package com.eastdawn.bo;

import java.io.Serializable;

import com.eastdawn.po.Metadata;

public interface MetadataBO extends Serializable  {
	public Long add(Metadata metadata);
	public void updateById(Metadata metadata);
}