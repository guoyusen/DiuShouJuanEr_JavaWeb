package com.bili.diushoujuaner.database.model;

import java.util.HashMap;
import java.util.Map;

public class CustomSession {
    private String accessToken;

    private String lastTime;

    private Short deviceType;

    private Long userNo;
    
    private Map<String, Object> attribute = new HashMap<>();
    
    public Object getAttribute(String name){
    	return this.attribute.get(name);
    }
    
    public void setAttribute(String name, Object value){
    	this.attribute.put(name, value);
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken == null ? null : accessToken.trim();
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime == null ? null : lastTime.trim();
    }

    public Short getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Short deviceType) {
        this.deviceType = deviceType;
    }

    public Long getUserNo() {
        return userNo;
    }

    public void setUserNo(Long userNo) {
        this.userNo = userNo;
    }
}