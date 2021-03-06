package com.bili.diushoujuaner.database.param;

public class RecallRequestParam {

	private long userNo;
	private int pageStart;
	private int pageSize;
	private long lastRecall;
	
	private String prefix = "<'";
	private String surfix = "'";
	
	public String getPrefix() {
		return prefix;
	}
	public String getSurfix() {
		return surfix;
	}
	public long getUserNo() {
		return userNo;
	}
	public void setUserNo(long userNo) {
		this.userNo = userNo;
	}
	public int getPageStart() {
		return pageStart;
	}
	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getLastRecall() {
		return lastRecall;
	}
	public void setLastRecall(long lastRecall) {
		this.lastRecall = lastRecall;
	}
	
}
