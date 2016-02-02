package com.bili.diushoujuaner.common.dto;

import java.util.Map;

public class ResponseDto {
	
	private String retCode = "";
	private String message = "";
	private Map<String, Object> data;
	
	public String getRetCode() {
		return retCode;
	}
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}

}
