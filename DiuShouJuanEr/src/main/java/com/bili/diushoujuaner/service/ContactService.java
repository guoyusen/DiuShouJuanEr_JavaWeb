package com.bili.diushoujuaner.service;

import com.bili.diushoujuaner.common.dto.ResponseDto;

public interface ContactService {

	ResponseDto getContactListByToken(String accessToken);
	
}
