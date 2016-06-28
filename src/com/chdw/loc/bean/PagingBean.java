package com.chdw.loc.bean;

import java.util.ArrayList;
import java.util.List;

public class PagingBean<T> {
	
	private int pageSize; //页面尺寸
	private int currPage; //当前页码
	private int totalRows; //总行数
	private int totalPages; //总页数
	
	private List<T> list=new ArrayList<T>();
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrPage() {
		return currPage;
	}
	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}
	public int getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
	public List<T> getList() {
		return list;
	}
	
	public void setList(List<T> list) {
		this.list = list;
	}
	
	
	
}