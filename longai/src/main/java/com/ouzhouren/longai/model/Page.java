package com.ouzhouren.longai.model;


import java.util.List;

/**
 * 分页
 * @author pengshizhong
 *
 */
public class Page {

	private int pages;
	private int currentPage;
	private int amount;
	private double total;
	private List list;
	private int min;
	private int max;
	public Page(int total,int currentPage,int amount){
		this.total = total;
		this.pages = total/amount+1;
		this.currentPage = currentPage;
		this.amount = amount;
		this.min = (currentPage-1)*amount;
		this.max = this.min+amount;
	}
	
	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	
	
	
}
