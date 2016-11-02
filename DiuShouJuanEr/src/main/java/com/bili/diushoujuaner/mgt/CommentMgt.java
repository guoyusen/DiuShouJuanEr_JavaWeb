package com.bili.diushoujuaner.mgt;

import java.util.List;

import com.bili.diushoujuaner.database.model.Comment;

public interface CommentMgt {

	List<Comment> getCommentListByRecallNo(long recallNo);
	
	int removeCommentByCommentNo(long commentNo);
	
	Comment addCommentByRecord(long recallNo, String content, long fromNo);
	
	boolean getPermitionForRemove(long commentNo, long userNo);
	
}
