package com.bili.diushoujuaner.service.impl;

import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bili.diushoujuaner.chat.iosession.IOSessionManager;
import com.bili.diushoujuaner.chat.message.Message;
import com.bili.diushoujuaner.common.CommonUtils;
import com.bili.diushoujuaner.common.ConstantUtils;
import com.bili.diushoujuaner.common.dto.ResponseDto;
import com.bili.diushoujuaner.database.model.User;
import com.bili.diushoujuaner.mgt.RecallMgt;
import com.bili.diushoujuaner.mgt.GoodMgt;
import com.bili.diushoujuaner.mgt.UserMgt;
import com.bili.diushoujuaner.service.GoodService;

@Service
public class GoodServiceImpl implements GoodService {
	
	@Autowired
	GoodMgt goodMgt;
	@Autowired
	UserMgt userMgt;
	@Autowired
	RecallMgt recallMgt;

	@Override
	public ResponseDto addGoodByRecallNoAndToken(long recallNo, String accessToken) {
		if(recallNo == -1){
			return CommonUtils.getResponse(ConstantUtils.ERROR, "添加Good失败", null);
		}
		int effectLines = goodMgt.addGoodByRecallNoAndUserNo(recallNo,CommonUtils.getUserNoFromAccessToken(accessToken));
		if(effectLines > 0){
			sendGoodMsgToClient(userMgt.getUserByUserNo(CommonUtils.getUserNoFromAccessToken(accessToken)), recallMgt.getUserNoByRecallNo(recallNo));
			
			return CommonUtils.getResponse(ConstantUtils.SUCCESS, "添加Good成功", null);
		}else if(effectLines == -1){
			return CommonUtils.getResponse(ConstantUtils.FAIL, "该趣事儿已被删除", null);
		}else{
			return CommonUtils.getResponse(ConstantUtils.FAIL, "添加Good失败", null);
		}
	}
	
	private void sendGoodMsgToClient(User from, long to){
		
		if(from.getUserNo() == to){
			return;
		}
		
		Message msg = new Message();
		msg.setMsgType(ConstantUtils.CHAT_GOOD);
		msg.setConType(ConstantUtils.CONTENT_TEXT);
		msg.setMsgTime(from.getPicPath());
		msg.setSenderNo(ConstantUtils.SYSTEM_ID_LONG);
		msg.setReceiverNo(0);
		msg.setMsgContent(from.getNickName() + "赞了你");
		
		IoSession ioSession = IOSessionManager.getSessionBrowser(to);
		if(ioSession != null){
			ioSession.write(CommonUtils.getJSONStringFromObject(msg));
		}
	}

	@Override
	public ResponseDto removeGoodByRecallNoAndToken(long recallNo, String accessToken) {
		int effectLines = goodMgt.removeGoodByRecallNoAndUserNo(recallNo,CommonUtils.getUserNoFromAccessToken(accessToken));
		if(effectLines > 0){
			return CommonUtils.getResponse(ConstantUtils.SUCCESS, "取消Good成功", null);
		}else{
			return CommonUtils.getResponse(ConstantUtils.FAIL, "该趣事已删除", null);
		}
	}

}
