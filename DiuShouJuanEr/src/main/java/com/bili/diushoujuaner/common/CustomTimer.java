package com.bili.diushoujuaner.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.mina.core.session.IoSession;
import org.springframework.scheduling.annotation.Scheduled;

import com.bili.diushoujuaner.chat.Transceiver;
import com.bili.diushoujuaner.chat.iosession.IOSessionManager;
import com.bili.diushoujuaner.database.model.OffMsg;
import com.bili.diushoujuaner.database.model.UserBo;
import com.bili.diushoujuaner.entity.MessageDto;
import com.bili.diushoujuaner.mgt.CommonInfoMgt;
import com.bili.diushoujuaner.mgt.FriendMgt;
import com.bili.diushoujuaner.mgt.OffMsgMgt;
import com.bili.diushoujuaner.mgt.UserMgt;

public class CustomTimer {
	
	OffMsgMgt offMsgMgt;
	CommonInfoMgt commonInfoMgt;
	UserMgt userMgt;
	
	@Scheduled(cron="0 0 2 * * *") 
	public void deleteCommonInfo(){
		System.out.println("执行删除");
		if(offMsgMgt == null){
			offMsgMgt = (OffMsgMgt)SpringContextUtil.getBean("offMsgMgtImpl");
		}
		if(commonInfoMgt == null){
			commonInfoMgt = (CommonInfoMgt)SpringContextUtil.getBean("commonInfoMgtImpl");
		}
		if(userMgt == null){
			userMgt = (UserMgt)SpringContextUtil.getBean("userMgtImpl");
		}
		List<OffMsg> offMsgs = offMsgMgt.getCommonOffMsg();
		for(OffMsg offMsg : offMsgs){
			if(commonInfoMgt.getCommonInfoCount(offMsg.getOffMsgNo()) == 0){
				offMsgMgt.deleteByOffMsgNo(offMsg.getOffMsgNo());
			}
		}
	}
	
	@Scheduled(cron="0 0 2 * * *") 
	public void recommendFriend(){
		if(offMsgMgt == null){
			offMsgMgt = (OffMsgMgt)SpringContextUtil.getBean("offMsgMgtImpl");
		}
		if(commonInfoMgt == null){
			commonInfoMgt = (CommonInfoMgt)SpringContextUtil.getBean("commonInfoMgtImpl");
		}
		if(userMgt == null){
			userMgt = (UserMgt)SpringContextUtil.getBean("userMgtImpl");
		}
		System.out.println("执行推荐");
		List<UserBo> userBos = userMgt.selectUserBoList();
		System.out.println(userBos.toString());
		System.out.println("=======================");
		HashMap<String, List<UserBo>> hashMap = new HashMap<>();
		for(UserBo userBo : userBos){
			if(userBo.getHomeTown().contains("-") && userBo.getHomeTown().indexOf("-") != userBo.getHomeTown().lastIndexOf("-")){
				if(hashMap.get(userBo.getHomeTown()) == null){
					List<UserBo> list = new ArrayList<UserBo>();
					list.add(userBo);
					hashMap.put(userBo.getHomeTown(), list);
				}else{
					hashMap.get(userBo.getHomeTown()).add(userBo);
				}
			}
		}
		for(List<UserBo> list : hashMap.values()){
			sendRecommend(list);
		}
		
	}
	
	private void sendRecommend(List<UserBo> list){
		FriendMgt friendMgt = (FriendMgt)SpringContextUtil.getBean("friendMgtImpl");
		for(int i = 0, iLen = list.size(); i < iLen; i ++){
			for(int j = 0, jLen = list.size(); j < jLen; j++){
				if(i != j && !friendMgt.isFriend(list.get(i).getUserNo(), list.get(j).getUserNo())){
					IoSession session = IOSessionManager.getSessionMobile(list.get(j).getUserNo());
					if(session != null){
						Transceiver.getInstance().addSendTask(
								getRecommendMessageDto(list.get(i).getUserNo(), list.get(j).getUserNo(),list.get(i).getNickName()), session);
					}else{
						saveRecommendOffMsg(list.get(i).getUserNo(), list.get(j).getUserNo(),list.get(i).getNickName());
					}
					
				    session = IOSessionManager.getSessionMobile(list.get(i).getUserNo());
					if(session != null){
						Transceiver.getInstance().addSendTask(
								getRecommendMessageDto(list.get(j).getUserNo(), list.get(i).getUserNo(),list.get(j).getNickName()), session);
					}else{
						saveRecommendOffMsg(list.get(j).getUserNo(), list.get(i).getUserNo(),list.get(j).getNickName());
					}
				}
			}
		}
	}
	
	private void saveRecommendOffMsg(long sender, long receiver, String nickName){
		OffMsg offMsg = new OffMsg();
		offMsg.setFromNo(sender);
		offMsg.setToNo(receiver);
		offMsg.setContent("我是" + nickName);
		offMsg.setTime(CommonUtils.getCurrentTime_YYYYMMDD_HHMMSS());
		offMsg.setConType(ConstantUtils.CONTENT_TEXT);
		offMsg.setMsgType(ConstantUtils.CHAT_FRIEND_RECOMMEND);
		offMsg.setIsRead(false);
		offMsgMgt.putOffMsgByRecord(offMsg);
	}
	
	private MessageDto getRecommendMessageDto(long sender, long receiver, String nickName){
		MessageDto messageDto = new MessageDto();
		messageDto.setSenderNo(sender);
		messageDto.setReceiverNo(receiver);
		messageDto.setSerialNo(CommonUtils.getSerialNo());
		messageDto.setMsgType(ConstantUtils.CHAT_FRIEND_RECOMMEND);
		messageDto.setConType(ConstantUtils.CONTENT_TEXT);
		messageDto.setMsgContent("我是" + nickName);
		messageDto.setMsgTime(CommonUtils.getCurrentTime_YYYYMMDD_HHMMSS());
		
		return messageDto;
	}

}
