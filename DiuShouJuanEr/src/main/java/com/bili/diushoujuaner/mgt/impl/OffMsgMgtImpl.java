package com.bili.diushoujuaner.mgt.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bili.diushoujuaner.common.CommonUtils;
import com.bili.diushoujuaner.common.ConstantUtils;
import com.bili.diushoujuaner.database.mapper.CommonInfoMapper;
import com.bili.diushoujuaner.database.mapper.OffMsgMapper;
import com.bili.diushoujuaner.database.model.CommonInfoExample;
import com.bili.diushoujuaner.database.model.OffMsg;
import com.bili.diushoujuaner.database.model.OffMsgExample;
import com.bili.diushoujuaner.mgt.OffMsgMgt;

@Repository
public class OffMsgMgtImpl implements OffMsgMgt {

	@Autowired
	OffMsgMapper offMsgMapper;
	@Autowired
	CommonInfoMapper commonInfoMapper;
	
	@Override
	public void deleteByOffMsgNo(long offMsgNo) {
		OffMsgExample offMsgExample = new OffMsgExample();
		offMsgExample.createCriteria().andOffMsgNoEqualTo(offMsgNo);
		
		offMsgMapper.deleteByExample(offMsgExample);
	}

	@Override
	public List<OffMsg> getCommonOffMsg() {
		List<Short> list = new ArrayList<Short>();
		list.add(ConstantUtils.CHAT_PAR);
		list.add(ConstantUtils.CHAT_PARTY_MEMBER_NAME);
		list.add(ConstantUtils.CHAT_PARTY_APPLY_AGREE);
		list.add(ConstantUtils.CHAT_PARTY_MEMBER_EXIT);
		list.add(ConstantUtils.CHAT_MEMBER_BATCH_ADD);
		list.add(ConstantUtils.CHAT_PARTY_UNGROUP);
		list.add(ConstantUtils.CHAT_PARTY_NAME);
		OffMsgExample offMsgExample = new OffMsgExample();
		offMsgExample.createCriteria().andMsgTypeIn(list);
		
		return offMsgMapper.selectByExample(offMsgExample);
	}

	@Override
	public void deletePartyOffMsg(long partyNo) {
		OffMsgExample offMsgExample = new OffMsgExample();
		offMsgExample.createCriteria().andToNoEqualTo(partyNo);
		
		offMsgMapper.deleteByExample(offMsgExample);
	}

	@Override
	public void deleteFriendOffMsg(long fromNo, long toNo) {
		OffMsgExample offMsgExample = new OffMsgExample();
		offMsgExample.createCriteria().andFromNoEqualTo(fromNo).andToNoEqualTo(toNo)
		.andMsgTypeEqualTo(ConstantUtils.CHAT_FRI);
		offMsgMapper.deleteByExample(offMsgExample);
		
		offMsgExample.clear();
		offMsgExample.createCriteria().andFromNoEqualTo(toNo).andToNoEqualTo(fromNo)
		.andMsgTypeEqualTo(ConstantUtils.CHAT_FRI);
		offMsgMapper.deleteByExample(offMsgExample);
	}
	
	@Override
	public List<OffMsg> getOffMsgListByUserNo(long userNo, String deviceType) {
		if(CommonUtils.getDeviceType(deviceType) == ConstantUtils.DEVICE_ANDROID){
			return offMsgMapper.getMobileOffMsgListByUserNo(userNo);
		}else{
			return offMsgMapper.getBrowserOffMsgListByUserNo(userNo);
		}
	}
	
	@Override
	public void deleteMobileOffMsgByUserNo(long userNo){
		OffMsgExample offMsgExample = new OffMsgExample();
		offMsgExample.createCriteria().andToNoEqualTo(userNo).andIsReadEqualTo(false);
		offMsgMapper.deleteByExample(offMsgExample);
		
		CommonInfoExample commonInfoExample = new CommonInfoExample();
		commonInfoExample.createCriteria().andToNoEqualTo(userNo).andIsReadEqualTo(false);
		commonInfoMapper.deleteByExample(commonInfoExample);
	}
	
	@Override
	public void deleteBrowserOffMsgByUserNo(long userNo){
		OffMsgExample offMsgExample = new OffMsgExample();
		offMsgExample.createCriteria()
		.andToNoEqualTo(userNo)
		.andIsReadEqualTo(false)
		.andMsgTypeEqualTo(ConstantUtils.CHAT_FRI);
		
		offMsgMapper.deleteByExample(offMsgExample);
		
		CommonInfoExample commonInfoExample = new CommonInfoExample();
		commonInfoExample.createCriteria().andToNoEqualTo(userNo).andIsReadEqualTo(false);
		commonInfoMapper.deleteByExample(commonInfoExample);
	}

	@Override
	public OffMsg putOffMsgByRecord(OffMsg offMsg) {
		if(offMsg == null || offMsgMapper.insertSelective(offMsg) <= 0){
			return null;
		}else{
			return offMsg;
		}
	}

}
