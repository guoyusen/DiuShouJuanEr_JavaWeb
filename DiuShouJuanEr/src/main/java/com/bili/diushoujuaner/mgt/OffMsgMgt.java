package com.bili.diushoujuaner.mgt;

import java.util.List;

import com.bili.diushoujuaner.database.model.OffMsg;

public interface OffMsgMgt {

	List<OffMsg> getOffMsgListByUserNo(long userNo);
	
	OffMsg putOffMsgByRecord(OffMsg offMsg);
	
	void deleteOffMsgByUserNo(long userNo);
	
}
