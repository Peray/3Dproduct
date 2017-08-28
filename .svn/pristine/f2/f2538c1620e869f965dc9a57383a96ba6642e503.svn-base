package com.eastdawn.common;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class PageAction extends ActionSupport {
	
	protected Integer pageStart = 0;	//开始页码--------好像没用
	protected Integer totalPage = 1;	//总页数
	protected Integer gotoPage;	//跳转的页数--------好像没用
	protected Integer currentPage = 1;	//当前页
	
	protected Integer pageNum = 100000;	//一页显示的数量
	protected Integer numStart = 0;	//起始数量
	protected Integer numEnd;	//结束数量
	protected Long totalNum;	//总记录数
	
	public Long getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Long totalNum) {
		this.totalNum = totalNum;
	}


	public Integer getPageNum() {
		return pageNum;
	}


	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageStart() {
		return pageStart;
	}

	public void setPageStart(Integer pageStart) {
		this.pageStart = pageStart;
	}


	public Integer getTotalPage() {
		this.totalPage =  (int) (this.totalNum / this.pageNum);
		int temp = (int) (this.totalNum % this.pageNum);
		
		if (temp != 0) {
			this.totalPage++;
		}
		
		return totalPage;
	}


	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}


	public int getNumStart() {
		if (currentPage == null) {
			currentPage = 1;
		}
		if (this.currentPage > this.totalPage) {//末页
			currentPage = this.totalPage;
		}
		if (this.currentPage < 1) {//首页
			currentPage = 1;
		}
		
		this.numStart = this.pageNum * (this.currentPage - 1);
		
		return numStart;
	}


	public void setNumStart(int numStart) {
		this.numStart = numStart;
	}


	public int getNumEnd() {
		this.numEnd = this.numStart + this.pageNum;
		
		return this.numEnd;
	}


	public void setNumEnd(int numEnd) {
		this.numEnd = numEnd;
	}


	public Integer getGotoPage() {
		return gotoPage;
	}


	public void setGotoPage(Integer gotoPage) {
		this.gotoPage = gotoPage;
	}


	public Integer getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	
}
