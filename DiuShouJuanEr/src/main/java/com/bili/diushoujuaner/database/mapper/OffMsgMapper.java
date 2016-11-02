package com.bili.diushoujuaner.database.mapper;

import com.bili.diushoujuaner.database.model.OffMsg;
import com.bili.diushoujuaner.database.model.OffMsgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OffMsgMapper {
    int countByExample(OffMsgExample example);

    int deleteByExample(OffMsgExample example);

    int deleteByPrimaryKey(Long offMsgNo);

    int insert(OffMsg record);

    int insertSelective(OffMsg record);

    List<OffMsg> selectByExample(OffMsgExample example);

    OffMsg selectByPrimaryKey(Long offMsgNo);

    int updateByExampleSelective(@Param("record") OffMsg record, @Param("example") OffMsgExample example);

    int updateByExample(@Param("record") OffMsg record, @Param("example") OffMsgExample example);

    int updateByPrimaryKeySelective(OffMsg record);

    int updateByPrimaryKey(OffMsg record);
    
    List<OffMsg> getMobileOffMsgListByUserNo(long userNo);
    
    List<OffMsg> getBrowserOffMsgListByUserNo(long userNo);
}