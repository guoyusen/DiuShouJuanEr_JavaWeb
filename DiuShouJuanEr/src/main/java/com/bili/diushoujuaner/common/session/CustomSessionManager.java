package com.bili.diushoujuaner.common.session;

import java.util.HashMap;
import java.util.Map;

import com.bili.diushoujuaner.common.springcontext.SpringContextUtil;
import com.bili.diushoujuaner.database.model.CustomSession;
import com.bili.diushoujuaner.mgt.CustomSessionMgt;

public class CustomSessionManager {

	private static Map<String, Object> customSessionMap = new HashMap<String, Object>();
	
	public static CustomSession getCustomSession(String accessToken){
		CustomSession customSession = (CustomSession) customSessionMap.get(accessToken);
		CustomSessionMgt customSessionMgt;
		if(customSession == null){
			customSessionMgt = (CustomSessionMgt)SpringContextUtil.getBean("customSessionMgtImpl");
			customSession = customSessionMgt.updateCustomSession(accessToken);
			if(customSession != null){
				customSession = customSessionMgt.updateCustomSession(customSession.getAccessToken(), customSession.getUserNo(), customSession.getDeviceType());
				addCustomSession(customSession);
			}
		}
		return customSession;
	}
	
	public static void addCustomSession(CustomSession customSession){
		customSessionMap.put(customSession.getAccessToken(), customSession);
	}
	
	public static void removeCustomSession(String accessToken){
		customSessionMap.remove(accessToken);
	}
	
}
