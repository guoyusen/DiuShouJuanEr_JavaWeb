package com.bili.diushoujuaner.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bili.diushoujuaner.chat.MemberManager;
import com.bili.diushoujuaner.chat.Transceiver;
import com.bili.diushoujuaner.chat.iosession.IOSessionManager;
import com.bili.diushoujuaner.common.CommonUtils;
import com.bili.diushoujuaner.common.ConstantUtils;
import com.bili.diushoujuaner.common.entity.MessageDto;
import com.bili.diushoujuaner.common.entity.ResponseDto;
import com.bili.diushoujuaner.common.pinyin.PinyinComparator;
import com.bili.diushoujuaner.common.pinyin.PinyinUtil;
import com.bili.diushoujuaner.common.session.CustomSessionManager;
import com.bili.diushoujuaner.database.model.ContactVo;
import com.bili.diushoujuaner.database.model.OffMsg;
import com.bili.diushoujuaner.database.model.User;
import com.bili.diushoujuaner.mgt.ContactVoMgt;
import com.bili.diushoujuaner.mgt.FriendMgt;
import com.bili.diushoujuaner.mgt.MemberMgt;
import com.bili.diushoujuaner.mgt.OffMsgMgt;
import com.bili.diushoujuaner.mgt.PartyMgt;
import com.bili.diushoujuaner.mgt.UserMgt;
import com.bili.diushoujuaner.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private MemberMgt memberMgt;
	@Autowired
	private ContactVoMgt contactVoMgt;
	@Autowired
	private PartyMgt partyMgt;
	@Autowired
	private OffMsgMgt offMsgMgt;
	@Autowired
	private FriendMgt friendMgt;
	@Autowired
	private UserMgt userMgt;
	
	@Override
	public ResponseDto getFriendAgree(long fromNo, long toNo, String accessToken) {
		// fromNo 同意了 toNo 的请求
		if(fromNo != CustomSessionManager.getCustomSession(accessToken).getUserNo()){
			return CommonUtils.getResponse(ConstantUtils.FAIL, "您没有此权限", null);
		}
		if(friendMgt.isFriend(fromNo, toNo)){
			return CommonUtils.getResponse(ConstantUtils.FAIL, "不能重复添加", null);
		}
		User userFrom = userMgt.getUserByUserNo(fromNo);
		User userTo = userMgt.getUserByUserNo(toNo);
		if(userFrom == null || userTo == null){
			return CommonUtils.getResponse(ConstantUtils.FAIL, "添加失败", null);
		}
		if(friendMgt.addFriendRelationShip(userFrom, userTo)){
			IoSession session = IOSessionManager.getSessionMobile(toNo);
			if(session != null){
				MessageDto messageDto = new MessageDto();
				messageDto.setSenderNo(fromNo);
				messageDto.setReceiverNo(toNo);
				messageDto.setSerialNo(CommonUtils.getSerialNo());
				messageDto.setMsgType(ConstantUtils.CHAT_FRI);
				messageDto.setConType(ConstantUtils.CONTENT_FRIEND_AGREE);
				messageDto.setMsgContent("我同意了你的好友申请");
				messageDto.setMsgTime(CommonUtils.getCurrentTime_YYYYMMDD_HHMMSS());
				
				Transceiver.getInstance().addSendTask(messageDto, session);
			}else{
				OffMsg offMsg = new OffMsg();
				offMsg.setFromNo(fromNo);
				offMsg.setToNo(toNo);
				offMsg.setContent("我同意了你的好友申请");
				offMsg.setTime(CommonUtils.getCurrentTime_YYYYMMDD_HHMMSS());
				offMsg.setConType(ConstantUtils.CONTENT_FRIEND_AGREE);
				offMsg.setMsgType(ConstantUtils.CHAT_FRI);
				offMsg.setIsRead(false);
				offMsgMgt.putOffMsgByRecord(offMsg);
			}
			return CommonUtils.getResponse(ConstantUtils.SUCCESS, "添加成功", contactVoMgt.getFriendByParam(fromNo, toNo));
		}
		return CommonUtils.getResponse(ConstantUtils.FAIL, "添加失败", null);
	}

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

	@Override
	public ResponseDto getFriendAdd(long friendNo, String content, String accessToken) {
		// accessToken用户想添加friendNo为好友
		long userNo = CustomSessionManager.getCustomSession(accessToken).getUserNo();
		if(friendMgt.isFriend(friendNo, userNo)){
			return CommonUtils.getResponse(ConstantUtils.FAIL, "不能重复添加好友", null);
		}
		IoSession session = IOSessionManager.getSessionMobile(friendNo);
		if(session != null){
			MessageDto messageDto = new MessageDto();
			messageDto.setSenderNo(userNo);
			messageDto.setReceiverNo(friendNo);
			messageDto.setSerialNo(CommonUtils.getSerialNo());
			messageDto.setMsgType(ConstantUtils.CHAT_FRIEND_ADD);
			messageDto.setConType(ConstantUtils.CONTENT_TEXT);
			messageDto.setMsgContent(content);
			messageDto.setMsgTime(CommonUtils.getCurrentTime_YYYYMMDD_HHMMSS());
			
			Transceiver.getInstance().addSendTask(messageDto, session);
		}else{
			OffMsg offMsg = new OffMsg();
			offMsg.setFromNo(userNo);
			offMsg.setToNo(friendNo);
			offMsg.setContent(content);
			offMsg.setTime(CommonUtils.getCurrentTime_YYYYMMDD_HHMMSS());
			offMsg.setConType(ConstantUtils.CONTENT_TEXT);
			offMsg.setMsgType(ConstantUtils.CHAT_FRIEND_ADD);
			offMsg.setIsRead(false);
			offMsgMgt.putOffMsgByRecord(offMsg);
		}
		return CommonUtils.getResponse(ConstantUtils.SUCCESS, "发送成功", null);
	}

}
