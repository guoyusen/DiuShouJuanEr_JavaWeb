package com.bili.diushoujuaner.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bili.diushoujuaner.chat.MemberManager;
import com.bili.diushoujuaner.common.CommonUtils;
import com.bili.diushoujuaner.common.ConstantUtils;
import com.bili.diushoujuaner.common.entity.ResponseDto;
import com.bili.diushoujuaner.common.pinyin.PinyinComparator;
import com.bili.diushoujuaner.common.pinyin.PinyinUtil;
import com.bili.diushoujuaner.common.session.CustomSessionManager;
import com.bili.diushoujuaner.database.model.ContactVo;
import com.bili.diushoujuaner.mgt.ContactVoMgt;
import com.bili.diushoujuaner.mgt.MemberMgt;
import com.bili.diushoujuaner.mgt.PartyMgt;
import com.bili.diushoujuaner.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	MemberMgt memberMgt;
	@Autowired
	ContactVoMgt contactVoMgt;
	@Autowired
	private PartyMgt partyMgt;
	
	@Override
	public ResponseDto getContactsSearch(String paramNo) {
		List<ContactVo> contactList = new ArrayList<>();
		
		List<ContactVo> friendList = contactVoMgt.getNewFriendListByMobile(paramNo);
		List<ContactVo> partyList = contactVoMgt.getNewPartyListByPartyNo(paramNo);
		for(ContactVo party : partyList){
	    	party.setMemberList(memberMgt.getMemberListByPartyNo(party.getContNo()));
	    }
		
		contactList.addAll(friendList);
		contactList.addAll(partyList);
		
		return CommonUtils.getResponse(ConstantUtils.SUCCESS, "搜索童友成功", contactList);
	}

	@Override
	public ResponseDto getContactListByToken(String accessToken) {
		
		List<ContactVo> friendList = contactVoMgt.getFriendListByUserNo(CommonUtils.getUserNoFromAccessToken(accessToken));
		List<ContactVo> partyList = contactVoMgt.getPartyListByUserNo(CommonUtils.getUserNoFromAccessToken(accessToken));
	    
		for(ContactVo party : partyList){
	    	party.setMemberList(memberMgt.getMemberListByPartyNo(party.getContNo()));
	    }
		
		List<ContactVo> contactList = new ArrayList<>();
		contactList.addAll(friendList);
		contactList.addAll(partyList);
		
		if(contactList.size() == 1){
			contactList.get(0).setSortLetter(PinyinUtil.getHeadCapitalByChar(contactList.get(0).getDisplayName().charAt(0)) + "");
		}
		Collections.sort(contactList, new PinyinComparator());
		
		return CommonUtils.getResponse(ConstantUtils.SUCCESS, "获取通讯录成功", contactList);
		
	}

	@Override
	public ResponseDto modifyPartyName(long partyNo, String partyName, String accessToken) {
		if(!partyMgt.isPermitHeadModify(partyNo, CustomSessionManager.getCustomSession(accessToken).getUserNo())){
			CommonUtils.getResponse(ConstantUtils.FAIL, "暂无修改权限", null);
		}
		if(partyMgt.updateName(partyNo, partyName)){
			new Thread(){

				@Override
				public void run() {
					MemberManager.broadCastToMember(partyNo, CustomSessionManager.getCustomSession(accessToken).getUserNo(), partyName, ConstantUtils.CHAT_PARTY_NAME, true);
				}
				
			}.start();
			return CommonUtils.getResponse(ConstantUtils.SUCCESS, "修改群名称成功", partyName);
		}
		return CommonUtils.getResponse(ConstantUtils.FAIL, "修改群名称失败", null);
	}

	@Override
	public ResponseDto modifyPartyIntroduce(long partyNo, String introduce, String accessToken) {
		if(!partyMgt.isPermitHeadModify(partyNo, CustomSessionManager.getCustomSession(accessToken).getUserNo())){
			CommonUtils.getResponse(ConstantUtils.FAIL, "暂无修改权限", null);
		}
		if(partyMgt.updateIntroduce(partyNo, introduce)){
			return CommonUtils.getResponse(ConstantUtils.SUCCESS, "修改群介绍成功", introduce);
		}
		return CommonUtils.getResponse(ConstantUtils.FAIL, "修改群介绍失败", null);
	}
	
	

}
