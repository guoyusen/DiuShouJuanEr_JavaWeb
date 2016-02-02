package com.bili.diushoujuaner.service;

import com.bili.diushoujuaner.common.dto.ResponseDto;

public interface GoodService {

	ResponseDto addGoodByRecallNoAndToken(long recallNo, String accessToken);
	
	ResponseDto removeGoodByRecallNoAndToken(long recallNo, String accessToken);
	
}
