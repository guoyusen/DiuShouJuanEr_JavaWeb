package com.bili.diushoujuaner.mgt.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bili.diushoujuaner.common.CommonUtils;
import com.bili.diushoujuaner.database.mapper.CommentMapper;
import com.bili.diushoujuaner.database.model.Comment;
import com.bili.diushoujuaner.database.model.CommentExample;
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
	public int deleteCommentByCommentNo(long commentNo) {
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
		
		commentMapper.insertSelective(comment);
		List<Comment> commentList = commentMapper.getCommentListByCommentNo(comment.getCommentNo());
		
		if(commentList.size() > 0){
			return commentList.get(0);
		}
		return null;
	}

}
