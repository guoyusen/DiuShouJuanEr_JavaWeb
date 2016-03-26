package com.bili.diushoujuaner.service;

import com.bili.diushoujuaner.common.dto.ResponseDto;

public interface CommentService {

	ResponseDto removeCommentByCommentNo(long commentNo, String accessToken);
	
	ResponseDto addCommentByRecord(long recallNo, String content, String accessToken);
	
}
