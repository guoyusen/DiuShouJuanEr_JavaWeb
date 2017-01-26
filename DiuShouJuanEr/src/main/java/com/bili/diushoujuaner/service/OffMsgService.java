package com.bili.diushoujuaner.service;

import com.bili.diushoujuaner.entity.ResponseDto;

public interface OffMsgService {

	ResponseDto getOffMsgListByToken(String accessToken, String deviceType);
	
}
