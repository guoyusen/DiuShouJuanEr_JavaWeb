package com.bili.diushoujuaner.service;

import com.bili.diushoujuaner.common.entity.ResponseDto;

public interface ContactService {

	ResponseDto getContactListByToken(String accessToken);
	
	ResponseDto getContactsSearch(String paramNo);
	
}
