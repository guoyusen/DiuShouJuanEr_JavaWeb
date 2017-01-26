package com.bili.diushoujuaner.service;

import com.bili.diushoujuaner.entity.ResponseDto;

public interface MemberService {
	
	ResponseDto modifyMemberName(long partyNo, String memberName, String accessToken);

}
