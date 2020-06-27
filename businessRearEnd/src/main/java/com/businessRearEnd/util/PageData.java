package com.businessRearEnd.util;

import java.util.List;

/**
 * 分页有关的通用类
 * @author Administrator
 *
 * @param <T>
 */
public class PageData<T> {
	// 当前页
	private int page;
	// 每页记录数
	private int pageSize;
	// 总页数
	private int totalPage;
	// 总记录数
	private int total;
	// 当前页的数据List
	private List<T> data;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	/**
	 * 带参数的构造
	 * @param page 当前页  
	 * @param pageSize 每页记录数
	 * @param totalPage 总页数
	 * @param total 总记录数
	 * @param data  当前页的数据集合
	 */
	public PageData(int page, int pageSize, int totalPage, int total, List<T> data) {
		super();
		this.page = page;
		this.pageSize = pageSize;
		this.totalPage = totalPage;
		this.total = total;
		this.data = data;
	}
	
	

}