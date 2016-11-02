package com.bili.diushoujuaner.database.mapper;

import com.bili.diushoujuaner.database.model.CustomSession;
import com.bili.diushoujuaner.database.model.CustomSessionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CustomSessionMapper {
    int countByExample(CustomSessionExample example);

    int deleteByExample(CustomSessionExample example);

    int insert(CustomSession record);

    int insertSelective(CustomSession record);

    List<CustomSession> selectByExample(CustomSessionExample example);

    int updateByExampleSelective(@Param("record") CustomSession record, @Param("example") CustomSessionExample example);

    int updateByExample(@Param("record") CustomSession record, @Param("example") CustomSessionExample example);
}