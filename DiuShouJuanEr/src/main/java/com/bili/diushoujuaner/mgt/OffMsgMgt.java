package com.bili.diushoujuaner.mgt;

import java.util.List;

import com.bili.diushoujuaner.database.model.OffMsg;

public interface OffMsgMgt {

	List<OffMsg> getOffMsgListByUserNo(long userNo, String deviceType);
	
	OffMsg putOffMsgByRecord(OffMsg offMsg);
	
	void deleteMobileOffMsgByUserNo(long userNo);
	
	void deleteBrowserOffMsgByUserNo(long userNo);
	
	void deleteFriendOffMsg(long fromNo, long toNo);
	
	void deletePartyOffMsg(long partyNo);
	
	List<OffMsg> getCommonOffMsg();
	
	void deleteByOffMsgNo(long offMsgNo);
	
}
