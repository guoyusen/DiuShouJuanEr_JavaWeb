package com.bili.diushoujuaner.database.mapper;

import com.bili.diushoujuaner.database.model.Member;
import com.bili.diushoujuaner.database.model.MemberExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MemberMapper {
    int countByExample(MemberExample example);

    int deleteByExample(MemberExample example);

    int deleteByPrimaryKey(Long memberNo);

    int insert(Member record);

    int insertSelective(Member record);

    List<Member> selectByExample(MemberExample example);

    Member selectByPrimaryKey(Long memberNo);

    int updateByExampleSelective(@Param("record") Member record, @Param("example") MemberExample example);

    int updateByExample(@Param("record") Member record, @Param("example") MemberExample example);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);
    
    List<Member> getMemberListByPartyNo(long partyNo);
    
    List<Long> getMemberNoListByPartyNo(long partyNo);
}