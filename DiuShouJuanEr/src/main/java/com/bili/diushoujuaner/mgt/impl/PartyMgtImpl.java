package com.bili.diushoujuaner.mgt.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bili.diushoujuaner.common.CommonUtils;
import com.bili.diushoujuaner.database.mapper.PartyMapper;
import com.bili.diushoujuaner.database.model.Party;
import com.bili.diushoujuaner.database.model.PartyExample;
import com.bili.diushoujuaner.mgt.PartyMgt;

@Repository
public class PartyMgtImpl implements PartyMgt {
	
	@Autowired
	PartyMapper partyMapper;

	@Override
	public boolean deleteParty(long partyNo) {
		PartyExample partyExample = new PartyExample();
		partyExample.createCriteria().andPartyNoEqualTo(partyNo);
		return partyMapper.deleteByExample(partyExample) > 0;
	}

	@Override
	public long getUserNoByPartyNo(long partyNo) {
		PartyExample partyExample = new PartyExample();
		partyExample.createCriteria().andPartyNoEqualTo(partyNo);
		
		List<Party> partyList = partyMapper.selectByExample(partyExample);
		
		return partyList.isEmpty() ? -1 : partyList.get(0).getUserNo();
	}

	@Override
	public Party addParty(long userNo, String partyName, String path) {
		Party party = new Party();
		party.setPartyName(partyName);
		party.setPicPath(path);
		party.setRegistTime(CommonUtils.getCurrentTime_YYYYMMDD_HHMMSS());
		party.setUserNo(userNo);
		
		if(partyMapper.insertSelective(party) > 0){
			return party;
		}
		return null;
	}

	@Override
	public boolean isPermitHeadModify(long partyNo, long userNo) {
		PartyExample partyExample = new PartyExample();
		partyExample.createCriteria().andPartyNoEqualTo(partyNo).andUserNoEqualTo(userNo);
		
		List<Party> partyList = partyMapper.selectByExample(partyExample);
		return !partyList.isEmpty();
	}

	@Override
	public Party getPartyByPartyNo(long partyNo) {
		PartyExample partyExample = new PartyExample();
		partyExample.createCriteria().andPartyNoEqualTo(partyNo);
		
		List<Party> partyList = partyMapper.selectByExample(partyExample);
		return partyList.isEmpty() ? null : partyList.get(0);
	}

	@Override
	public boolean updateHead(String path, long partyNo, long userNo) {
		Party party = new Party();
		party.setPicPath(path);
		
		PartyExample partyExample = new PartyExample();
		partyExample.createCriteria().andPartyNoEqualTo(partyNo).andUserNoEqualTo(userNo);
		return partyMapper.updateByExampleSelective(party, partyExample) > 0;
	}

	@Override
	public boolean updateName(long partyNo, String partyName) {
		Party party = new Party();
		party.setPartyName(partyName);
		
		PartyExample partyExample = new PartyExample();
		partyExample.createCriteria().andPartyNoEqualTo(partyNo);
		return partyMapper.updateByExampleSelective(party, partyExample) > 0;
	}

	@Override
	public boolean updateIntroduce(long partyNo, String introduce) {
		Party party = new Party();
		party.setInformation(introduce);;
		
		PartyExample partyExample = new PartyExample();
		partyExample.createCriteria().andPartyNoEqualTo(partyNo);
		return partyMapper.updateByExampleSelective(party, partyExample) > 0;
	}

}
