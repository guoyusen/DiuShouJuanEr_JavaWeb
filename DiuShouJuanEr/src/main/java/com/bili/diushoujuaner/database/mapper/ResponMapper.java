package com.bili.diushoujuaner.database.mapper;

import com.bili.diushoujuaner.database.model.Respon;
import com.bili.diushoujuaner.database.model.ResponExample;
import com.bili.diushoujuaner.database.param.ResponRemoveParam;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResponMapper {
    int countByExample(ResponExample example);

    int deleteByExample(ResponExample example);

    int deleteByPrimaryKey(Long responNo);

    int insert(Respon record);

    int insertSelective(Respon record);

    List<Respon> selectByExample(ResponExample example);

    Respon selectByPrimaryKey(Long responNo);

    int updateByExampleSelective(@Param("record") Respon record, @Param("example") ResponExample example);

    int updateByExample(@Param("record") Respon record, @Param("example") ResponExample example);

    int updateByPrimaryKeySelective(Respon record);

    int updateByPrimaryKey(Respon record);
    
    List<Respon> getResponListByCommentNo(long commentNo);
    
    List<Respon> getResponListByResponNo(long responNo);
    
    long getPermitionForRemove(ResponRemoveParam responRemoveParam);
}