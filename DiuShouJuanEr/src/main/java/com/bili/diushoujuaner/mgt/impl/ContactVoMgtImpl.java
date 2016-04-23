package com.bili.diushoujuaner.mgt.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bili.diushoujuaner.database.mapper.ContactVoMapper;
import com.bili.diushoujuaner.database.model.ContactVo;
import com.bili.diushoujuaner.mgt.ContactVoMgt;

@Repository
public class ContactVoMgtImpl implements ContactVoMgt {

	@Autowired
	ContactVoMapper contactVoMapper;
	
	@Override
	public List<ContactVo> getFriendListByUserNo(long userNo) {
		return contactVoMapper.getFriendListByUserNo(userNo);
	}

	@Override
	public List<ContactVo> getPartyListByUserNo(long UserNo) {
		return contactVoMapper.getPartyListByUserNo(UserNo);
	}

	@Override
	public List<ContactVo> getNewFriendListByMobile(String mobile) {
		return contactVoMapper.getNewFriendListByMobile(mobile);
	}

	@Override
	public List<ContactVo> getNewPartyListByPartyNo(String partyNo) {
		return contactVoMapper.getNewPartyListByPartyNo(partyNo);
	}

}
