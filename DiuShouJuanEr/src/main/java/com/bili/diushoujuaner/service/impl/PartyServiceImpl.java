package com.bili.diushoujuaner.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bili.diushoujuaner.chat.MemberManager;
import com.bili.diushoujuaner.common.CommonUtils;
import com.bili.diushoujuaner.common.ConstantUtils;
import com.bili.diushoujuaner.common.entity.ResponseDto;
import com.bili.diushoujuaner.common.session.CustomSessionManager;
import com.bili.diushoujuaner.database.model.ContactVo;
import com.bili.diushoujuaner.database.model.Party;
import com.bili.diushoujuaner.database.model.User;
import com.bili.diushoujuaner.mgt.ContactVoMgt;
import com.bili.diushoujuaner.mgt.MemberMgt;
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
