package com.bili.diushoujuaner.chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.mina.core.session.IoSession;

import com.bili.diushoujuaner.chat.iosession.IOSessionManager;
import com.bili.diushoujuaner.common.CommonUtils;
import com.bili.diushoujuaner.common.ConstantUtils;
import com.bili.diushoujuaner.common.entity.MessageDto;
import com.bili.diushoujuaner.common.springcontext.SpringContextUtil;
import com.bili.diushoujuaner.mgt.MemberMgt;

public class MemberManager {
	
	private static HashMap<Long, List<Long>> memberSource = new HashMap<>();
	private static MemberMgt memberMgt;
	
	public static synchronized List<Long> getMemberNoList(long partyNo){
		if(memberSource.get(partyNo) == null){
			if(memberMgt == null){
				memberMgt = (MemberMgt)SpringContextUtil.getBean("memberMgtImpl");
			}
			List<Long> memberNoList = memberMgt.getMemberNoListByPartyNo(partyNo);
			memberSource.put(partyNo, memberNoList);
		}
		return memberSource.get(partyNo);
	}
	
	public static synchronized void clearMember(long partyNo){
		memberSource.remove(partyNo);
	}
	
	public static void broadCastToMember(long partyNo, long userNo, long exceptNo, String content, short msgType, boolean saveOff, boolean isSystem){
		MessageDto msg = new MessageDto(); 
		msg.setReceiverNo(partyNo);
		if(msgType == ConstantUtils.CHAT_PARTY_MEMBER_NAME
				|| msgType == ConstantUtils.CHAT_PARTY_APPLY_AGREE
				|| msgType == ConstantUtils.CHAT_PARTY_MEMBER_EXIT
				|| msgType == ConstantUtils.CHAT_MEMBER_BATCH_ADD){
			msg.setSenderNo(userNo);
		}else{
			msg.setSenderNo(ConstantUtils.SYSTEM_ID_LONG);
		}
		msg.setMsgContent(content);
		msg.setMsgTime(CommonUtils.getCurrentTime_YYYYMMDD_HHMMSS());
		msg.setMsgType(msgType);
		msg.setConType(ConstantUtils.CONTENT_TEXT);
		
		List<Long> memberList = getMemberNoList(partyNo);
		List<Long> offMemberNoList = new ArrayList<>();
		boolean isMobileAccept = false;
		boolean isBrowserAccept = false;
		
		IoSession sessionTmp = null;
		for(Long memberNo : memberList){
			if(memberNo == exceptNo){
				continue;
			}
			isMobileAccept = false;
			isBrowserAccept = false;
			if(isSystem){
				//如果是系统群发消息，接收id应该为对应的用户账号
				msg.setReceiverNo(memberNo);
			}
    		//1.不为空 
    		//2.账号不和当前session的账号相等，直接发送
    		//3.账号相等，发送给浏览器的不能是浏览器
			sessionTmp = IOSessionManager.getSessionBrowser(memberNo);
	    	if(sessionTmp != null){
	    		isBrowserAccept = true;
	    		Transceiver.getInstance().addSendTask(CommonUtils.getCloneMessageDto(msg), sessionTmp);
	    	}
	    	
	    	//1.不为空 
    		//2.账号不和当前session的账号相等，直接发送
    		//3.账号相等，发送给手机的不能是手机
	    	sessionTmp = IOSessionManager.getSessionMobile(memberNo);
	    	if(sessionTmp != null){
	    		isMobileAccept = true;
	    		Transceiver.getInstance().addSendTask(CommonUtils.getCloneMessageDto(msg), sessionTmp);
	    	}
	    	
	    	// 手机和浏览器都没有被接收，且不是当前发送者的账号，保存
	    	if(!isMobileAccept && !isBrowserAccept && saveOff){
	    		offMemberNoList.add(memberNo);
	    	}
    	}
		
		// 存储群消息，确保非在线用户下次上线可以看到离线信息
		if(!isSystem && saveOff && !offMemberNoList.isEmpty()){
			CommonUtils.processMessageStore(msg, offMemberNoList);
		}else if(isSystem && saveOff && !offMemberNoList.isEmpty()){
			CommonUtils.processSystemMessageStore(msg, offMemberNoList);
		}
	}

}
