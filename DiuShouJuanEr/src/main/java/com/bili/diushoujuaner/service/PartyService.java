package com.bili.diushoujuaner.service;

import org.springframework.web.multipart.MultipartFile;

import com.bili.diushoujuaner.common.entity.ResponseDto;

public interface PartyService {

	ResponseDto modifyPartyName(long partyNo, String partyName, String accessToken);
	
	ResponseDto modifyPartyIntroduce(long partyNo, String introduce, String accessToken);
	
	ResponseDto addParty(MultipartFile file, String partyName, String accessToken);
	
}
