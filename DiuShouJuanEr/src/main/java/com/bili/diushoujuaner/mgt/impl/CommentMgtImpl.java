package com.bili.diushoujuaner.mgt.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import com.bili.diushoujuaner.common.CommonUtils;
import com.bili.diushoujuaner.database.mapper.CommentMapper;
import com.bili.diushoujuaner.database.model.Comment;
import com.bili.diushoujuaner.database.model.CommentExample;
import com.bili.diushoujuaner.database.param.CommentRemoveValidateParam;
import com.bili.diushoujuaner.mgt.CommentMgt;

@Repository
public class CommentMgtImpl implements CommentMgt {
	
	@Autowired
	CommentMapper commentMapper;

	@Override
	public List<Comment> getCommentListByRecallNo(long recallNo) {
		return commentMapper.getCommentListByRecallNo(recallNo);
	}

	@Override
	public int removeCommentByCommentNo(long commentNo) {
		CommentExample commentExample = new CommentExample();
		commentExample.createCriteria().andCommentNoEqualTo(commentNo);
		return commentMapper.deleteByExample(commentExample);
	}

	@Override
	public Comment addCommentByRecord(long recallNo, String content, long fromNo) {
		Comment comment = new Comment();
		comment.setRecallNo(recallNo);
		comment.setAddTime(CommonUtils.getCurrentTime_YYYYMMDD_HHMMSS());
		comment.setFromNo(fromNo);
		comment.setContent(content);
		
		try{
			commentMapper.insertSelective(comment);
			List<Comment> commentList = commentMapper.getCommentListByCommentNo(comment.getCommentNo());
			
			return commentList.isEmpty() ? null : commentList.get(0);
		}catch(DataIntegrityViolationException e){
			//数据不完整，插入失败
			return null;
		}
	}

	@Override
	public boolean getPermitionForRemove(long commentNo, long userNo) {
		CommentRemoveValidateParam commentRemoveValidateParam = new CommentRemoveValidateParam();
		commentRemoveValidateParam.setCommentNo(commentNo);
		commentRemoveValidateParam.setUserNo(userNo);
		
		long result = commentMapper.getPermitionForRemove(commentRemoveValidateParam);
		return result > 0;
	}

}
