package com.eastdawn.po;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Hupo implements Serializable {
	
	private Long fId;//jsonId
	private String geometry;//坐标点
	private String lakeCode;//
	private String shapeLeng;
	private String name;
	private String shapeArea;
	private String point;
	
	public Long getFId() {
		return fId;
	}
	public void setFId(Long id) {
		fId = id;
	}
	public String getGeometry() {
		return geometry;
	}
	public void setGeometry(String geometry) {
		this.geometry = geometry;
	}
	public String getShapeLeng() {
		return shapeLeng;
	}
	public void setShapeLeng(String shapeLeng) {
		this.shapeLeng = shapeLeng;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShapeArea() {
		return shapeArea;
	}
	public void setShapeArea(String shapeArea) {
		this.shapeArea = shapeArea;
	}
	public String getLakeCode() {
		return lakeCode;
	}
	public void setLakeCode(String lakeCode) {
		this.lakeCode = lakeCode;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	
}