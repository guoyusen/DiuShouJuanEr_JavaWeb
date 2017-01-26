package com.bili.diushoujuaner.service;

import com.bili.diushoujuaner.entity.ResponseDto;

public interface FeedBackService {
	
	ResponseDto addFeedBack(String content, String accessToken);

}
