package com.bili.diushoujuaner.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bili.diushoujuaner.chat.MemberManager;
import com.bili.diushoujuaner.common.CommonUtils;
import com.bili.diushoujuaner.common.ConstantUtils;
import com.bili.diushoujuaner.common.entity.ResponseDto;
import com.bili.diushoujuaner.common.session.CustomSessionManager;
import com.bili.diushoujuaner.mgt.MemberMgt;
import com.bili.diushoujuaner.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberMgt memberMgt;

	@Override
	public ResponseDto modifyMemberName(long partyNo, String memberName, String accessToken) {
		if(memberMgt.modifyMemberName(partyNo, CustomSessionManager.getCustomSession(accessToken).getUserNo(), memberName) > 0){
			new Thread(){
				//开启线程，发送群通知，更改群名片了
				@Override
				public void run() {
					MemberManager.broadCastToMember(partyNo, CustomSessionManager.getCustomSession(accessToken).getUserNo(), memberName, ConstantUtils.CHAT_PARTY_MEMBER_NAME, false);
				}
				
			}.start();
			return CommonUtils.getResponse(ConstantUtils.SUCCESS, "修改群名片成功", memberName.length() >= 50 ? memberName.substring(0, 50) : memberName);
		}
		return CommonUtils.getResponse(ConstantUtils.FAIL, "修改群名片失败", null);
	}

}
