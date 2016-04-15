package com.bili.diushoujuaner.service;

import com.bili.diushoujuaner.common.dto.ResponseDto;

public interface RecallService {
	
	ResponseDto getRecallListByRecord(int type, int pageIndex, int pageSize, long userNo, long lastRecall);

	ResponseDto addRecallByContAndToken(String content, String accessToken, String serial, int picCount, String deviceType);
	
	ResponseDto removeRecallByRecallNo(long recallNo, String accessToken);
	
	ResponseDto getRecentRecallByUserNo(long userNo);
	
}
