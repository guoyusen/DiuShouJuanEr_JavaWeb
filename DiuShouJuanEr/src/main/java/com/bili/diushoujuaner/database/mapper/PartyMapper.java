package com.bili.diushoujuaner.database.mapper;

import com.bili.diushoujuaner.database.model.Party;
import com.bili.diushoujuaner.database.model.PartyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PartyMapper {
    int countByExample(PartyExample example);

    int deleteByExample(PartyExample example);

    int deleteByPrimaryKey(Long partyNo);

    int insert(Party record);

    int insertSelective(Party record);

    List<Party> selectByExample(PartyExample example);

    Party selectByPrimaryKey(Long partyNo);

    int updateByExampleSelective(@Param("record") Party record, @Param("example") PartyExample example);

    int updateByExample(@Param("record") Party record, @Param("example") PartyExample example);

    int updateByPrimaryKeySelective(Party record);

    int updateByPrimaryKey(Party record);
}