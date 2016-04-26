package com.bili.diushoujuaner.mgt.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bili.diushoujuaner.common.CommonUtils;
import com.bili.diushoujuaner.common.ConstantUtils;
import com.bili.diushoujuaner.database.mapper.MemberMapper;
import com.bili.diushoujuaner.database.model.Member;
import com.bili.diushoujuaner.database.model.MemberExample;
import com.bili.diushoujuaner.database.model.User;
import com.bili.diushoujuaner.mgt.MemberMgt;

@Repository
public class MemberMgtImpl implements MemberMgt {

	@Autowired
	MemberMapper memberMapper;
	
	@Override
	public boolean deleteMember(long partyNo, long memberNo) {
		MemberExample memberExample = new MemberExample();
		memberExample.createCriteria()
		.andPartyNoEqualTo(partyNo)
		.andUserNoEqualTo(memberNo);
		return memberMapper.deleteByExample(memberExample) > 0;
	}

	@Override
	public boolean isMember(long partyNo, long memberNo) {
		MemberExample memberExample = new MemberExample();
		memberExample.createCriteria().andPartyNoEqualTo(partyNo).andUserNoEqualTo(memberNo);

		return !memberMapper.selectByExample(memberExample).isEmpty();
	}

	@Override
	public boolean addMemberForParty(long partyNo, User user) {
		Member member = new Member();
		member.setPartyNo(partyNo);
		member.setUserNo(user.getUserNo());
		member.setType(ConstantUtils.MEMBER_MEMBER);
		member.setAddTime(CommonUtils.getCurrentTime_YYYYMMDD_HHMMSS());
		member.setMemberName(user.getNickName());
		return memberMapper.insertSelective(member) > 0;
	}

	@Override
	public boolean addOwnerForParty(long partyNo, User user) {
		Member member = new Member();
		member.setPartyNo(partyNo);
		member.setUserNo(user.getUserNo());
		member.setType(ConstantUtils.MEMBER_OWNER);
		member.setAddTime(CommonUtils.getCurrentTime_YYYYMMDD_HHMMSS());
		member.setMemberName(user.getNickName());
		return memberMapper.insertSelective(member) > 0;
	}

	@Override
	public List<Member> getMemberListByPartyNo(long partyNo) {
		return memberMapper.getMemberListByPartyNo(partyNo);
	}

	@Override
	public List<Long> getMemberNoListByPartyNo(long partyNo) {
		return memberMapper.getMemberNoListByPartyNo(partyNo);
	}

	@Override
	public boolean modifyMemberName(long partyNo, long memberNo, String memberName) {
		MemberExample memberExample = new MemberExample();
		memberExample.createCriteria()
		.andPartyNoEqualTo(partyNo)
		.andUserNoEqualTo(memberNo);
		
		Member member = new Member();
		member.setMemberName(memberName);
		return memberMapper.updateByExampleSelective(member, memberExample) > 0;
	}
	
	

}
