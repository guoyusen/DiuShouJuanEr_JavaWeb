package com.bili.diushoujuaner.service;

import com.bili.diushoujuaner.common.dto.ResponseDto;

public interface CommentService {

	ResponseDto deleteCommentByCommentNo(long commentNo);
	
	ResponseDto addCommentByRecord(long recallNo, String content, String accessToken);
	
}
