package com.bili.diushoujuaner.service;

import org.springframework.web.multipart.MultipartFile;

import com.bili.diushoujuaner.entity.ResponseDto;

public interface PartyService {

	ResponseDto modifyPartyName(long partyNo, String partyName, String accessToken);
	
	ResponseDto modifyPartyIntroduce(long partyNo, String introduce, String accessToken);
	
	ResponseDto addParty(MultipartFile file, String partyName, String accessToken);
	
	ResponseDto applyAddParty(long partyNo, String content, String accessToken);
	
	ResponseDto getPartyApplyAgree(long partyNo, long memberNo, String accessToken);
	
	ResponseDto getMemberExit(long partyNo, String accessToken);
	
	ResponseDto getPartyUnGroup(long partyNo, String accessToken);
	
	ResponseDto getMemberForceExit(long partyNo, long memberNo, String accessToken);
	
	ResponseDto addMembersToParty(long partyNo, String members, String accessToken);
	
}
