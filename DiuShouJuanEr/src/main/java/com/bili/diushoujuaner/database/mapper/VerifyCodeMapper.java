package com.bili.diushoujuaner.database.mapper;

import com.bili.diushoujuaner.database.model.VerifyCode;
import com.bili.diushoujuaner.database.model.VerifyCodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VerifyCodeMapper {
    int countByExample(VerifyCodeExample example);

    int deleteByExample(VerifyCodeExample example);

    int deleteByPrimaryKey(Long verifyCodeNo);

    int insert(VerifyCode record);

    int insertSelective(VerifyCode record);

    List<VerifyCode> selectByExample(VerifyCodeExample example);

    VerifyCode selectByPrimaryKey(Long verifyCodeNo);

    int updateByExampleSelective(@Param("record") VerifyCode record, @Param("example") VerifyCodeExample example);

    int updateByExample(@Param("record") VerifyCode record, @Param("example") VerifyCodeExample example);

    int updateByPrimaryKeySelective(VerifyCode record);

    int updateByPrimaryKey(VerifyCode record);
}