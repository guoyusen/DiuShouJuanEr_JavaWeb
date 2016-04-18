package com.bili.diushoujuaner.service;

import com.bili.diushoujuaner.common.entity.ResponseDto;

public interface OffMsgService {

	ResponseDto getOffMsgListByToken(String accessToken);
	
}
