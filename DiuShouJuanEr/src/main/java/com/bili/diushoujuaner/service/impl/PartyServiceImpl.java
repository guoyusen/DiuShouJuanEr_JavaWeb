package com.bili.diushoujuaner.service.impl;

import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bili.diushoujuaner.chat.MemberManager;
import com.bili.diushoujuaner.chat.Transceiver;
import com.bili.diushoujuaner.chat.iosession.IOSessionManager;
import com.bili.diushoujuaner.common.CommonUtils;
import com.bili.diushoujuaner.common.ConstantUtils;
import com.bili.diushoujuaner.common.entity.MessageDto;
import com.bili.diushoujuaner.common.entity.ResponseDto;
import com.bili.diushoujuaner.common.session.CustomSessionManager;
import com.bili.diushoujuaner.database.model.ContactVo;
import com.bili.diushoujuaner.database.model.OffMsg;
import com.bili.diushoujuaner.database.model.Party;
import com.bili.diushoujuaner.database.model.User;
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
	public ResponseDto getMemberExit(long partyNo, String accessToken) {
		long userNo = CustomSessionManager.getCustomSession(accessToken).getUserNo();
		if(userNo == partyMgt.getUserNoByPartyNo(partyNo)){
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
					MemberManager.broadCastToMember(partyNo, userNo, "", ConstantUtils.CHAT_PARTY_MEMBER_EXIT, true);
				};
			}.start();
			return CommonUtils.getResponse(ConstantUtils.SUCCESS, "退出成功", null);
		}
		return CommonUtils.getResponse(ConstantUtils.FAIL, "退出群失败", null);
	}

	@Override
	public ResponseDto getPartyApplyAgree(long partyNo, long memberNo, String accessToken) {
		//accessToken 作为群主，同意memberNo用户加入群
		long userNo = CustomSessionManager.getCustomSession(accessToken).getUserNo();
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
					MemberManager.broadCastToMember(partyNo, memberNo, "", ConstantUtils.CHAT_PARTY_APPLY_AGREE, true);
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
			messageDto.setSenderNo(CustomSessionManager.getCustomSession(accessToken).getUserNo());
			messageDto.setReceiverNo(userNo);
			messageDto.setSerialNo(CommonUtils.getSerialNo());
			messageDto.setMsgType(ConstantUtils.CHAT_PARTY_APPLY);
			messageDto.setConType(ConstantUtils.CONTENT_TEXT);
			messageDto.setMsgContent(content);
			messageDto.setMsgTime(partyNo + "");
			
			Transceiver.getInstance().addSendTask(messageDto, session);
		}else{
			OffMsg offMsg = new OffMsg();
			offMsg.setFromNo(CustomSessionManager.getCustomSession(accessToken).getUserNo());
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
		long userNo = CustomSessionManager.getCustomSession(accessToken).getUserNo();
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
		if(!partyMgt.isPermitHeadModify(partyNo, CustomSessionManager.getCustomSession(accessToken).getUserNo())){
			CommonUtils.getResponse(ConstantUtils.FAIL, "暂无修改权限", null);
		}
		String tmpName = CommonUtils.getLimitContent(partyName, ConstantUtils.CONTENT_LENGTH_PARTY_NAME);
		if(partyMgt.updateName(partyNo, tmpName)){
			new Thread(){

				@Override
				public void run() {
					MemberManager.broadCastToMember(partyNo, CustomSessionManager.getCustomSession(accessToken).getUserNo(), tmpName, ConstantUtils.CHAT_PARTY_NAME, true);
				}
				
			}.start();
			return CommonUtils.getResponse(ConstantUtils.SUCCESS, "修改群名称成功", partyName);
		}
		return CommonUtils.getResponse(ConstantUtils.FAIL, "修改群名称失败", null);
	}

	@Override
	public ResponseDto modifyPartyIntroduce(long partyNo, String introduce, String accessToken) {
		introduce = CommonUtils.getLimitContent(introduce, ConstantUtils.CONTENT_LENGTH_PARTY_INTRODUCE);
		if(!partyMgt.isPermitHeadModify(partyNo, CustomSessionManager.getCustomSession(accessToken).getUserNo())){
			CommonUtils.getResponse(ConstantUtils.FAIL, "暂无修改权限", null);
		}
		if(partyMgt.updateIntroduce(partyNo, introduce)){
			return CommonUtils.getResponse(ConstantUtils.SUCCESS, "修改群介绍成功", introduce);
		}
		return CommonUtils.getResponse(ConstantUtils.FAIL, "修改群介绍失败", null);
	}

}
