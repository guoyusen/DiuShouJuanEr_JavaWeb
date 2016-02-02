package com.bili.diushoujuaner.service;

import com.bili.diushoujuaner.common.dto.ResponseDto;

public interface OffMsgService {

	ResponseDto getOffMsgListByToken(String accessToken);
	
}
