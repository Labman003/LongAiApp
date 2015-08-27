package com.ouzhouren.longai.model;

public class UploadFileInfo {
	private String name;
	private java.sql.Timestamp ts;
	private boolean status;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public java.sql.Timestamp getTs() {
		return ts;
	}
	public void setTs(java.sql.Timestamp ts) {
		this.ts = ts;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
}
