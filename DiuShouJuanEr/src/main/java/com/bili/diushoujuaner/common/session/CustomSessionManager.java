package com.bili.diushoujuaner.common.session;

import java.util.HashMap;
import java.util.Map;

import com.bili.diushoujuaner.database.model.CustomSession;

public class CustomSessionManager {

	private static Map<String, Object> customSessionFactory = new HashMap<String, Object>();
	
	public static CustomSession getCustomSession(String accessToken){
		return (CustomSession) customSessionFactory.get(accessToken);
	}
	
	public static void addCustomSession(CustomSession customSession){
		customSessionFactory.put(customSession.getAccessToken(), customSession);
	}
	
	public static void removeCustomSession(String accessToken){
		customSessionFactory.remove(accessToken);
	}
	
}
