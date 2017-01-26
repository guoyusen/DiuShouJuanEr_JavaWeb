package com.bili.diushoujuaner.service;

import com.bili.diushoujuaner.entity.ResponseDto;

public interface ContactService {

	ResponseDto getContactListByToken(String accessToken);
	
	ResponseDto getContactsSearch(String paramNo);
	
	ResponseDto getContactByPartyNo(long partyNo);
	
}
