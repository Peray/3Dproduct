package com.eastdawn.po;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class ServiceGl implements Serializable {
	//服务基本信息
	private Long glId;//服务管理ID
	private String glNumber;//服务编号
	private String glName;//服务名称
	private String address;//服务地址
	private String content;//描述
	private Date fbTime;//服务发布时间
	private String fbPop;//发布人
	private String imgPath;//缩略图
	private Integer browse;//浏览量
	
	public Long getGlId() {
		return glId;
	}
	public void setGlId(Long glId) {
		this.glId = glId;
	}
	public String getGlNumber() {
		return glNumber;
	}
	public void setGlNumber(String glNumber) {
		this.glNumber = glNumber;
	}
	public String getGlName() {
		return glName;
	}
	public void setGlName(String glName) {
		this.glName = glName;
	}
	public Date getFbTime() {
		return fbTime;
	}
	public void setFbTime(Date fbTime) {
		this.fbTime = fbTime;
	}
	public String getFbPop() {
		return fbPop;
	}
	public void setFbPop(String fbPop) {
		this.fbPop = fbPop;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public Integer getBrowse() {
		return browse;
	}
	public void setBrowse(Integer browse) {
		this.browse = browse;
	}
}