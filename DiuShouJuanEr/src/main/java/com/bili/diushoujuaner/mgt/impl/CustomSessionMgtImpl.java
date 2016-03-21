package com.bili.diushoujuaner.mgt.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bili.diushoujuaner.common.CommonUtils;
import com.bili.diushoujuaner.database.mapper.CustomSessionMapper;
import com.bili.diushoujuaner.database.model.CustomSession;
import com.bili.diushoujuaner.database.model.CustomSessionExample;
import com.bili.diushoujuaner.mgt.CustomSessionMgt;

@Repository
public class CustomSessionMgtImpl implements CustomSessionMgt {

	@Autowired
	private CustomSessionMapper customSessionMapper;
	
	@Override
	public CustomSession updateCustomSession(String accessToken, long userNo,
			short deviceType) {
		
		CustomSessionExample customSessionExample = new CustomSessionExample();
		customSessionExample.createCriteria().andUserNoEqualTo(userNo).andDeviceTypeEqualTo(deviceType);
		
		CustomSession customSession = new CustomSession();
		customSession.setAccessToken(accessToken);
		customSession.setDeviceType(deviceType);
		customSession.setLastTime(CommonUtils.getCurrentTime_YYYYMMDD_HHMMSS());
		customSession.setUserNo(userNo);
		
		List<CustomSession> list = customSessionMapper.selectByExample(customSessionExample);
		if(list.size() > 0){
			customSessionMapper.updateByExampleSelective(customSession, customSessionExample);
		}else{
			customSessionMapper.insertSelective(customSession);
		}
		
		return customSession;
	}

	@Override
	public CustomSession updateCustomSession(String accessToken) {
		CustomSessionExample customSessionExample = new CustomSessionExample();
		customSessionExample.createCriteria().andAccessTokenEqualTo(accessToken);
		
		List<CustomSession> list = customSessionMapper.selectByExample(customSessionExample);
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

}
