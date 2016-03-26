package com.bili.diushoujuaner.database.mapper;

import com.bili.diushoujuaner.database.model.Comment;
import com.bili.diushoujuaner.database.model.CommentExample;
import com.bili.diushoujuaner.database.param.CommentRemoveValidateParam;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommentMapper {
    int countByExample(CommentExample example);

    int deleteByExample(CommentExample example);

    int deleteByPrimaryKey(Long commentNo);

    int insert(Comment record);

    int insertSelective(Comment record);

    List<Comment> selectByExample(CommentExample example);

    Comment selectByPrimaryKey(Long commentNo);

    int updateByExampleSelective(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByExample(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);
    
    List<Comment> getCommentListByRecallNo(long recallNo);
    
    List<Comment> getCommentListByCommentNo(long commentNo);
    
    long getPermitionForRemove(CommentRemoveValidateParam commentRemoveValidateParam);
}