package com.bili.diushoujuaner.database.mapper;

import com.bili.diushoujuaner.database.model.Recall;
import com.bili.diushoujuaner.database.model.RecallExample;
import com.bili.diushoujuaner.database.param.RecallRemoveValidateParam;
import com.bili.diushoujuaner.database.param.RecallRequestParam;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface RecallMapper {
    int countByExample(RecallExample example);

    int deleteByExample(RecallExample example);

    int deleteByPrimaryKey(Long recallNo);

    int insert(Recall record);

    int insertSelective(Recall record);

    List<Recall> selectByExample(RecallExample example);

    Recall selectByPrimaryKey(Long recallNo);

    int updateByExampleSelective(@Param("record") Recall record, @Param("example") RecallExample example);

    int updateByExample(@Param("record") Recall record, @Param("example") RecallExample example);

    int updateByPrimaryKeySelective(Recall record);

    int updateByPrimaryKey(Recall record);
    
    List<Recall> getRecallListByPageParam(RecallRequestParam recallRequestParam);
    
    List<Recall> getRecallListByPageParamAndRecall(RecallRequestParam recallRequestParam);
    
    List<Recall> getRecallListByUserNoAndPageParam(RecallRequestParam recallRequestParam);
    
    List<Recall> getRecallListByUserNoAndPageParamAndRecall(RecallRequestParam recallRequestParam);
    
    List<Long> getUserNoByRecallNo(long recallNo);
    
    long getPermitionForRemove(RecallRemoveValidateParam recallRemoveValidateParam);
    
    List<Recall> getRecentRecallByUserNo(long userNo);
    
    List<Recall> getRecallByRecallNo(long recallNo);
}