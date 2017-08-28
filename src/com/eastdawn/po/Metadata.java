package com.eastdawn.po;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Metadata implements Serializable {
	//字典信息
	private Long dataId;//服务ID
	private String fwContent;//方位描述
	private String zdx;//地理信息坐标(最大X)
	private String zdy;//最大Y
	private String zxx;//最小X
	private String zxy;//最小Y
	private String fbl;//影像分辨率
	private String fwlx;//服务类型
	private String yxSource;//来源
	private String yxcjdw;//原始影像采集单位
	private String yxcldw;//影像处理单位
	private String yxsdr;//影像审定者
	private String yxsddw;//影像审定单位
	private String yxsdsj;//影像审定时间
	private String sjmj;//数据密集
	private String cqgs;//知识产权归属
    private String shPop;//审核人
    private String shTime;//审核时间
    private Long parentId;//所属记录ID
    private String sx;//时相
    private String wxlx;//卫星类型
    private Date statTime;
    private Date endTime;//
    
	public Long getDataId() {
		return dataId;
	}
	public void setDataId(Long dataId) {
		this.dataId = dataId;
	}
	public String getFwContent() {
		return fwContent;
	}
	public void setFwContent(String fwContent) {
		this.fwContent = fwContent;
	}
	public String getZdx() {
		return zdx;
	}
	public void setZdx(String zdx) {
		this.zdx = zdx;
	}
	public String getZdy() {
		return zdy;
	}
	public void setZdy(String zdy) {
		this.zdy = zdy;
	}
	public String getZxx() {
		return zxx;
	}
	public void setZxx(String zxx) {
		this.zxx = zxx;
	}
	public String getZxy() {
		return zxy;
	}
	public void setZxy(String zxy) {
		this.zxy = zxy;
	}
	public String getFbl() {
		return fbl;
	}
	public void setFbl(String fbl) {
		this.fbl = fbl;
	}
	public String getYxSource() {
		return yxSource;
	}
	public void setYxSource(String yxSource) {
		this.yxSource = yxSource;
	}
	public String getYxcjdw() {
		return yxcjdw;
	}
	public void setYxcjdw(String yxcjdw) {
		this.yxcjdw = yxcjdw;
	}
	public String getYxcldw() {
		return yxcldw;
	}
	public void setYxcldw(String yxcldw) {
		this.yxcldw = yxcldw;
	}
	public String getYxsdr() {
		return yxsdr;
	}
	public void setYxsdr(String yxsdr) {
		this.yxsdr = yxsdr;
	}
	public String getYxsddw() {
		return yxsddw;
	}
	public void setYxsddw(String yxsddw) {
		this.yxsddw = yxsddw;
	}
	public String getYxsdsj() {
		return yxsdsj;
	}
	public void setYxsdsj(String yxsdsj) {
		this.yxsdsj = yxsdsj;
	}
	public String getSjmj() {
		return sjmj;
	}
	public void setSjmj(String sjmj) {
		this.sjmj = sjmj;
	}
	public String getCqgs() {
		return cqgs;
	}
	public void setCqgs(String cqgs) {
		this.cqgs = cqgs;
	}
	public String getShPop() {
		return shPop;
	}
	public void setShPop(String shPop) {
		this.shPop = shPop;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getFwlx() {
		return fwlx;
	}
	public void setFwlx(String fwlx) {
		this.fwlx = fwlx;
	}
	public String getWxlx() {
		return wxlx;
	}
	public void setWxlx(String wxlx) {
		this.wxlx = wxlx;
	}
	public String getShTime() {
		return shTime;
	}
	public void setShTime(String shTime) {
		this.shTime = shTime;
	}
	public String getSx() {
		return sx;
	}
	public void setSx(String sx) {
		this.sx = sx;
	}
	public Date getStatTime() {
		return statTime;
	}
	public void setStatTime(Date statTime) {
		this.statTime = statTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
}