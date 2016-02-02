package com.bili.diushoujuaner.database.mapper;

import com.bili.diushoujuaner.database.model.CommonInfo;
import com.bili.diushoujuaner.database.model.CommonInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommonInfoMapper {
    int countByExample(CommonInfoExample example);

    int deleteByExample(CommonInfoExample example);

    int deleteByPrimaryKey(Long commonInfoNo);

    int insert(CommonInfo record);

    int insertSelective(CommonInfo record);

    List<CommonInfo> selectByExample(CommonInfoExample example);

    CommonInfo selectByPrimaryKey(Long commonInfoNo);

    int updateByExampleSelective(@Param("record") CommonInfo record, @Param("example") CommonInfoExample example);

    int updateByExample(@Param("record") CommonInfo record, @Param("example") CommonInfoExample example);

    int updateByPrimaryKeySelective(CommonInfo record);

    int updateByPrimaryKey(CommonInfo record);
}