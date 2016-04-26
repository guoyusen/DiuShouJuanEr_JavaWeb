package com.bili.diushoujuaner.service.impl;

import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bili.diushoujuaner.chat.Transceiver;
import com.bili.diushoujuaner.chat.iosession.IOSessionManager;
import com.bili.diushoujuaner.common.CommonUtils;
import com.bili.diushoujuaner.common.ConstantUtils;
import com.bili.diushoujuaner.common.entity.MessageDto;
import com.bili.diushoujuaner.common.entity.ResponseDto;
import com.bili.diushoujuaner.common.session.CustomSessionManager;
import com.bili.diushoujuaner.database.model.OffMsg;
import com.bili.diushoujuaner.database.model.User;
import com.bili.diushoujuaner.mgt.ContactVoMgt;
import com.bili.diushoujuaner.mgt.FriendMgt;
import com.bili.diushoujuaner.mgt.OffMsgMgt;
import com.bili.diushoujuaner.mgt.UserMgt;
import com.bili.diushoujuaner.service.FriendService;

@Service
public class FriendServiceImpl implements FriendService {
	
	@Autowired
	private OffMsgMgt offMsgMgt;
	@Autowired
	private FriendMgt friendMgt;
	@Autowired
	private UserMgt userMgt;
	@Autowired
	private ContactVoMgt contactVoMgt;
	
	@Override
	public ResponseDto getFriendAdd(long friendNo, String content, String accessToken) {
		// accessToken用户想添加friendNo为好友
		content = CommonUtils.getLimitContent(content, ConstantUtils.CONTENT_LENGTH_FRIEND_ADD);
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
			messageDto.setMsgType(ConstantUtils.CHAT_FRIEND_APPLY);
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
			offMsg.setMsgType(ConstantUtils.CHAT_FRIEND_APPLY);
			offMsg.setIsRead(false);
			offMsgMgt.putOffMsgByRecord(offMsg);
		}
		return CommonUtils.getResponse(ConstantUtils.SUCCESS, "发送成功", null);
	}

	@Override
	public ResponseDto getFriendDelete(long friendNo, String accessToken) {
		long userNo = CustomSessionManager.getCustomSession(accessToken).getUserNo();
		if(friendMgt.deleteFriendRelationShip(userNo, friendNo)){
			//删除相关离线数据，给friendNo发送及时通知消息，通知更新联系人
			offMsgMgt.deleteFriendOffMsg(userNo, friendNo);
			IoSession session = IOSessionManager.getSessionMobile(friendNo);
			if(session != null){
				MessageDto messageDto = new MessageDto();
				messageDto.setSenderNo(userNo);
				messageDto.setReceiverNo(friendNo);
				messageDto.setSerialNo(CommonUtils.getSerialNo());
				messageDto.setMsgType(ConstantUtils.CHAT_FRIEND_DELETE);
				messageDto.setConType(ConstantUtils.CONTENT_EMPTY);
				messageDto.setMsgContent("");
				messageDto.setMsgTime(CommonUtils.getCurrentTime_YYYYMMDD_HHMMSS());
				
				Transceiver.getInstance().addSendTask(messageDto, session);
			}else{
				OffMsg offMsg = new OffMsg();
				offMsg.setFromNo(userNo);
				offMsg.setToNo(friendNo);
				offMsg.setContent("删除好友");
				offMsg.setTime(CommonUtils.getCurrentTime_YYYYMMDD_HHMMSS());
				offMsg.setConType(ConstantUtils.CONTENT_EMPTY);
				offMsg.setMsgType(ConstantUtils.CHAT_FRIEND_DELETE);
				offMsg.setIsRead(false);
				offMsgMgt.putOffMsgByRecord(offMsg);
			}
			return CommonUtils.getResponse(ConstantUtils.SUCCESS, "删除好友成功", null);
		}
		return CommonUtils.getResponse(ConstantUtils.FAIL, "删除好友失败", null);
	}

	@Override
	public ResponseDto modifyFriendRemark(long friendNo, String remark, String accessToken) {
		//accessToken 用户要修改 friendNo的备注
		remark = CommonUtils.getLimitContent(remark, ConstantUtils.CONTENT_LENGTH_FRIEND_REMARK);
		long userNo = CustomSessionManager.getCustomSession(accessToken).getUserNo();
		if(!friendMgt.isFriend(userNo, friendNo)){
			return CommonUtils.getResponse(ConstantUtils.FAIL, "您没有该权限", null);
		}
		if(friendMgt.modifyFriendRemark(userNo, friendNo, remark)){
			return CommonUtils.getResponse(ConstantUtils.SUCCESS, "修改备注名成功", CommonUtils.getLimitContent(remark, 50));
		}
		return CommonUtils.getResponse(ConstantUtils.FAIL, "修改备注名失败", null);
	}

	@Override
	public ResponseDto getFriendAgree(long friendNo, String accessToken) {
		// userNo 同意了 friendNo 的请求
		long userNo = CustomSessionManager.getCustomSession(accessToken).getUserNo();
		if(friendMgt.isFriend(userNo, friendNo)){
			return CommonUtils.getResponse(ConstantUtils.FAIL, "不能重复添加", null);
		}
		User userFrom = userMgt.getUserByUserNo(userNo);
		User userTo = userMgt.getUserByUserNo(friendNo);
		if(userFrom == null || userTo == null){
			return CommonUtils.getResponse(ConstantUtils.FAIL, "添加失败", null);
		}
		if(friendMgt.addFriendRelationShip(userFrom, userTo)){
			IoSession session = IOSessionManager.getSessionMobile(friendNo);
			if(session != null){
				MessageDto messageDto = new MessageDto();
				messageDto.setSenderNo(userNo);
				messageDto.setReceiverNo(friendNo);
				messageDto.setSerialNo(CommonUtils.getSerialNo());
				messageDto.setMsgType(ConstantUtils.CHAT_FRIEND_APPLY_AGREE);
				messageDto.setConType(ConstantUtils.CONTENT_TEXT);
				messageDto.setMsgContent("我同意了你的好友申请");
				messageDto.setMsgTime(CommonUtils.getCurrentTime_YYYYMMDD_HHMMSS());
				
				Transceiver.getInstance().addSendTask(messageDto, session);
			}else{
				OffMsg offMsg = new OffMsg();
				offMsg.setFromNo(userNo);
				offMsg.setToNo(friendNo);
				offMsg.setContent("我同意了你的好友申请");
				offMsg.setTime(CommonUtils.getCurrentTime_YYYYMMDD_HHMMSS());
				offMsg.setConType(ConstantUtils.CONTENT_TEXT);
				offMsg.setMsgType(ConstantUtils.CHAT_FRIEND_APPLY_AGREE);
				offMsg.setIsRead(false);
				offMsgMgt.putOffMsgByRecord(offMsg);
			}
			return CommonUtils.getResponse(ConstantUtils.SUCCESS, "添加成功", contactVoMgt.getFriendByParam(userNo, friendNo));
		}
		return CommonUtils.getResponse(ConstantUtils.FAIL, "添加失败", null);
	}
	
}
