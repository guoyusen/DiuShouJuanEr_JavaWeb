package com.bili.diushoujuaner.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bili.diushoujuaner.chat.MemberManager;
import com.bili.diushoujuaner.chat.Transceiver;
import com.bili.diushoujuaner.chat.iosession.IOSessionManager;
import com.bili.diushoujuaner.common.CommonUtils;
import com.bili.diushoujuaner.common.ConstantUtils;
import com.bili.diushoujuaner.common.CustomSessionUtil;
import com.bili.diushoujuaner.database.model.ContactVo;
import com.bili.diushoujuaner.database.model.Member;
import com.bili.diushoujuaner.database.model.OffMsg;
import com.bili.diushoujuaner.database.model.Party;
import com.bili.diushoujuaner.database.model.User;
import com.bili.diushoujuaner.entity.MessageDto;
import com.bili.diushoujuaner.entity.ResponseDto;
import com.bili.diushoujuaner.mgt.CommonInfoMgt;
import com.bili.diushoujuaner.mgt.ContactVoMgt;
import com.bili.diushoujuaner.mgt.MemberMgt;
import com.bili.diushoujuaner.mgt.OffMsgMgt;
import com.bili.diushoujuaner.mgt.PartyMgt;
import com.bili.diushoujuaner.mgt.UserMgt;
import com.bili.diushoujuaner.service.PartyService;

@Service
public class PartyServiceImpl implements PartyService {

	@Autowired
	private PartyMgt partyMgt;
	@Autowired
	private ContactVoMgt contactVoMgt;
	@Autowired
	private MemberMgt memberMgt;
	@Autowired
	private UserMgt userMgt;
	@Autowired
	private OffMsgMgt offMsgMgt;
	@Autowired
	private CommonInfoMgt commonInfoMgt;
	
	@Override
	public ResponseDto getPartyUnGroup(long partyNo, String accessToken) {
		long userNo = CustomSessionUtil.getCustomSession(accessToken).getUserNo();
		if(userNo != partyMgt.getUserNoByPartyNo(partyNo)){
			//非管理员，不能解散
			return CommonUtils.getResponse(ConstantUtils.FAIL, "非法操作", null);
		}
		Party party = partyMgt.getPartyByPartyNo(partyNo);
		CommonUtils.deleteFileFromPath(CommonUtils.getRootDirectory() + party.getPicPath());
		new Thread(){
			@Override
			public void run() {
				MemberManager.broadCastToMember(partyNo, userNo, userNo, String.valueOf(partyNo), ConstantUtils.CHAT_PARTY_UNGROUP, false, true);
				offMsgMgt.deletePartyOffMsg(partyNo);
				partyMgt.deleteParty(partyNo);
				MemberManager.clearMember(partyNo);
			}
			
		}.start();
		return CommonUtils.getResponse(ConstantUtils.SUCCESS, "群解散成功", null);
	}

	@Override
	public ResponseDto addMembersToParty(long partyNo, String members, String accessToken) {
		List<Long> memberNoList = JSON.parseObject(members, new TypeReference<List<Long>>(){}.getType());
		long userNo = CustomSessionUtil.getCustomSession(accessToken).getUserNo();
		if(!memberMgt.isMember(partyNo, userNo)){
			return CommonUtils.getResponse(ConstantUtils.FAIL, "非群成员不能执行添加操作", null);
		}
		List<Member> memberList = memberMgt.getMemberListByPartyNo(partyNo);
		HashMap<Long, Member> hashMap = new HashMap<>();
		for(Member member : memberList){
			hashMap.put(member.getUserNo(), member);
		}
		for(Long item : memberNoList){
			if(hashMap.get(item) != null){
				//已经有该成员
				continue;
			}
			User user = userMgt.getUserByUserNo(item);
			if(user != null){
				memberMgt.addMemberForParty(partyNo, user);
			}
		}
		ContactVo contactVo = contactVoMgt.getPartyByPartyNo(partyNo);
		contactVo.setMemberList(memberMgt.getMemberList(memberNoList, partyNo));
		new Thread(){
			public void run() {
				MemberManager.clearMember(partyNo);
				MemberManager.broadCastToMember(partyNo, userNo, userNo, CommonUtils.getJSONStringFromObject(contactVo), ConstantUtils.CHAT_MEMBER_BATCH_ADD, true, false);
			};
		}.start();
		return CommonUtils.getResponse(ConstantUtils.SUCCESS, "添加群成员成功", contactVo);
	}

	@Override
	public ResponseDto getMemberForceExit(long partyNo, long memberNo, String accessToken) {
		long userNo = CustomSessionUtil.getCustomSession(accessToken).getUserNo();
		if(userNo != partyMgt.getUserNoByPartyNo(partyNo)){
			//非管理员，不能强制推出用户
			return CommonUtils.getResponse(ConstantUtils.FAIL, "非法操作", null);
		}
		if(!memberMgt.isMember(partyNo, memberNo)){
			return CommonUtils.getResponse(ConstantUtils.FAIL, "该群没有这个成员", null);
		}
		//删除该成员的离线群消息
		commonInfoMgt.deleteCommonInfo(partyNo, memberNo);
		if(memberMgt.deleteMember(partyNo, memberNo)){
			commonInfoMgt.deleteCommonInfo(partyNo, memberNo);
			new Thread(){
				public void run() {
					MemberManager.broadCastToMember(partyNo, memberNo, userNo, "", ConstantUtils.CHAT_PARTY_MEMBER_EXIT, true, false);
					//发送出去之后再清除Member列表，否则会造成被删除成员无法收到删除信号
					MemberManager.clearMember(partyNo);
				};
			}.start();
			return CommonUtils.getResponse(ConstantUtils.SUCCESS, "踢成员操作成功", null);
		}
		return CommonUtils.getResponse(ConstantUtils.FAIL, "踢成员操作失败", null);
	}

	@Override
	public ResponseDto getMemberExit(long partyNo, String accessToken) {
		long userNo = CustomSessionUtil.getCustomSession(accessToken).getUserNo();
		if(userNo == partyMgt.getUserNoByPartyNo(partyNo)){
			// 成员退出，不允许管理员退出
			return CommonUtils.getResponse(ConstantUtils.FAIL, "非法操作", null);
		}
		if(!memberMgt.isMember(partyNo, userNo)){
			return CommonUtils.getResponse(ConstantUtils.FAIL, "该群没有这个成员", null);
		}
		if(memberMgt.deleteMember(partyNo, userNo)){
			MemberManager.clearMember(partyNo);
			commonInfoMgt.deleteCommonInfo(partyNo, userNo);
			new Thread(){
				public void run() {
					MemberManager.broadCastToMember(partyNo, userNo, -1, "", ConstantUtils.CHAT_PARTY_MEMBER_EXIT, true, false);
				};
			}.start();
			return CommonUtils.getResponse(ConstantUtils.SUCCESS, "退出成功", null);
		}
		return CommonUtils.getResponse(ConstantUtils.FAIL, "退出群失败", null);
	}

	@Override
	public ResponseDto getPartyApplyAgree(long partyNo, long memberNo, String accessToken) {
		//accessToken 作为群主，同意memberNo用户加入群
		long userNo = CustomSessionUtil.getCustomSession(accessToken).getUserNo();
		if(userNo != partyMgt.getUserNoByPartyNo(partyNo)){
			return CommonUtils.getResponse(ConstantUtils.FAIL, "您没有该权限", null);
		}
		if(memberMgt.isMember(partyNo, memberNo)){
			return CommonUtils.getResponse(ConstantUtils.FAIL, "不能重复添加", null);
		}
		User user = userMgt.getUserByUserNo(memberNo);
		if(memberMgt.addMemberForParty(partyNo, user)){
			//添加成功,清除对应的成员缓存
			MemberManager.clearMember(partyNo);
			//群发添加成功消息
			new Thread(){
				public void run() {
					MemberManager.broadCastToMember(partyNo, memberNo, -1, "", ConstantUtils.CHAT_PARTY_APPLY_AGREE, true, false);
				};
			}.start();
			return CommonUtils.getResponse(ConstantUtils.SUCCESS, "添加成功", null);
		}
		return CommonUtils.getResponse(ConstantUtils.FAIL, "添加失败", null);
	}

	@Override
	public ResponseDto applyAddParty(long partyNo, String content, String accessToken) {
		long userNo = partyMgt.getUserNoByPartyNo(partyNo);
		IoSession session = IOSessionManager.getSessionMobile(userNo);
		if(session != null){
			MessageDto messageDto = new MessageDto();
			messageDto.setSenderNo(CustomSessionUtil.getCustomSession(accessToken).getUserNo());
			messageDto.setReceiverNo(userNo);
			messageDto.setSerialNo(CommonUtils.getSerialNo());
			messageDto.setMsgType(ConstantUtils.CHAT_PARTY_APPLY);
			messageDto.setConType(ConstantUtils.CONTENT_TEXT);
			messageDto.setMsgContent(content);
			messageDto.setMsgTime(partyNo + "");
			
			Transceiver.getInstance().addSendTask(messageDto, session);
		}else{
			OffMsg offMsg = new OffMsg();
			offMsg.setFromNo(CustomSessionUtil.getCustomSession(accessToken).getUserNo());
			offMsg.setToNo(userNo);
			offMsg.setContent(content);
			offMsg.setTime(partyNo + "");
			offMsg.setConType(ConstantUtils.CONTENT_TEXT);
			offMsg.setMsgType(ConstantUtils.CHAT_PARTY_APPLY);
			offMsg.setIsRead(false);
			offMsgMgt.putOffMsgByRecord(offMsg);
		}
		return CommonUtils.getResponse(ConstantUtils.SUCCESS, "发送成功", null);
	}

	@Override
	public ResponseDto addParty(MultipartFile file, String partyName, String accessToken) {
		long userNo = CustomSessionUtil.getCustomSession(accessToken).getUserNo();
		String filePath = CommonUtils.processHeadImage(file);
		Party party = partyMgt.addParty(userNo, partyName, filePath);
		User user = userMgt.getUserByUserNo(userNo);
		//向成员表添加管理员
		memberMgt.addOwnerForParty(party.getPartyNo(), user);
		
		ContactVo contactVo = contactVoMgt.getPartyByPartyNo(party.getPartyNo());
		if(contactVo == null){
			return CommonUtils.getResponse(ConstantUtils.FAIL, "创建群失败", null);
		}
		contactVo.setMemberList(memberMgt.getMemberListByPartyNo(contactVo.getContNo()));
		return CommonUtils.getResponse(ConstantUtils.SUCCESS, "创建群成功", contactVo);
	}

	@Override
	public ResponseDto modifyPartyName(long partyNo, String partyName, String accessToken) {
		long userNo = CustomSessionUtil.getCustomSession(accessToken).getUserNo();
		if(!partyMgt.isPermitHeadModify(partyNo, userNo)){
			CommonUtils.getResponse(ConstantUtils.FAIL, "暂无修改权限", null);
		}
		String tmpName = CommonUtils.getLimitContent(partyName, ConstantUtils.CONTENT_LENGTH_PARTY_NAME);
		if(partyMgt.updateName(partyNo, tmpName)){
			new Thread(){

				@Override
				public void run() {
					MemberManager.broadCastToMember(partyNo, userNo, userNo, tmpName, ConstantUtils.CHAT_PARTY_NAME, true, false);
				}
				
			}.start();
			return CommonUtils.getResponse(ConstantUtils.SUCCESS, "修改群名称成功", partyName);
		}
		return CommonUtils.getResponse(ConstantUtils.FAIL, "修改群名称失败", null);
	}

	@Override
	public ResponseDto modifyPartyIntroduce(long partyNo, String introduce, String accessToken) {
		introduce = CommonUtils.getLimitContent(introduce, ConstantUtils.CONTENT_LENGTH_PARTY_INTRODUCE);
		if(!partyMgt.isPermitHeadModify(partyNo, CustomSessionUtil.getCustomSession(accessToken).getUserNo())){
			CommonUtils.getResponse(ConstantUtils.FAIL, "暂无修改权限", null);
		}
		if(partyMgt.updateIntroduce(partyNo, introduce)){
			return CommonUtils.getResponse(ConstantUtils.SUCCESS, "修改群介绍成功", introduce);
		}
		return CommonUtils.getResponse(ConstantUtils.FAIL, "修改群介绍失败", null);
	}

}
