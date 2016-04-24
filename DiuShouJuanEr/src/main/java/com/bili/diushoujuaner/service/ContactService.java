package com.bili.diushoujuaner.service;

import com.bili.diushoujuaner.common.entity.ResponseDto;

public interface ContactService {

	ResponseDto getContactListByToken(String accessToken);
	
	ResponseDto modifyPartyName(long partyNo, String partyName, String accessToken);
	
	ResponseDto modifyPartyIntroduce(long partyNo, String introduce, String accessToken);
	
	ResponseDto getContactsSearch(String paramNo);
	
	ResponseDto getFriendAdd(long friendNo, String content, String accessToken);
	
	ResponseDto getFriendAgree(long fromNo, long toNo, String accessToken);
	
}
