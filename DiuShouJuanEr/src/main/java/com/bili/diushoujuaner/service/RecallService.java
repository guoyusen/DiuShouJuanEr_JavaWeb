package com.bili.diushoujuaner.service;

import com.bili.diushoujuaner.common.dto.ResponseDto;

public interface RecallService {
	
	ResponseDto getRecallListByRecord(int type, int pageIndex, int pageSize, String accessToken);

	ResponseDto addRecallByContAndToken(String content, String accessToken, int picCount, String deviceType);
	
	ResponseDto deleteRecallByRecallNo(long recallNo);
	
}
