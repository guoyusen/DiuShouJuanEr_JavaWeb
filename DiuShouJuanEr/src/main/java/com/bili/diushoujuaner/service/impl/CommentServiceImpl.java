package com.bili.diushoujuaner.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bili.diushoujuaner.common.CommonUtils;
import com.bili.diushoujuaner.common.ConstantUtils;
import com.bili.diushoujuaner.common.dto.ResponseDto;
import com.bili.diushoujuaner.database.model.Comment;
import com.bili.diushoujuaner.mgt.CommentMgt;
import com.bili.diushoujuaner.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentMgt commentMgt;

	@Override
	public ResponseDto deleteCommentByCommentNo(long commentNo) {
		int effectLines = commentMgt.deleteCommentByCommentNo(commentNo);
		if(effectLines > 0){
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("commentNo", commentNo);
			return CommonUtils.getResponse(ConstantUtils.SUCCESS, "删除Comment成功", data);
		}else{
			return CommonUtils.getResponse(ConstantUtils.FAIL, "删除Comment失败", null);
		}
	}

	@Override
	public ResponseDto addCommentByRecord(long recallNo, String content,
			String accessToken) {
		Comment comment = commentMgt.addCommentByRecord(recallNo, content, CommonUtils.getUserNoFromAccessToken(accessToken));
		if(comment != null){
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("comment", comment);
			return CommonUtils.getResponse(ConstantUtils.SUCCESS, "添加Comment成功", data);
		}else{
			return CommonUtils.getResponse(ConstantUtils.FAIL, "添加Comment失败", null);
		}
	}

}
