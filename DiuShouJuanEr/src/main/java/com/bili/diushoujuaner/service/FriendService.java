package com.bili.diushoujuaner.service;

import com.bili.diushoujuaner.common.entity.ResponseDto;

public interface FriendService {

	ResponseDto getFriendAdd(long friendNo, String content, String accessToken);
	
	ResponseDto getFriendAgree(long friendNo, String accessToken);
	
	ResponseDto getFriendDelete(long friendNo, String accessToken);
	
	ResponseDto modifyFriendRemark(long friendNo, String remark, String accessToken);
	
}
