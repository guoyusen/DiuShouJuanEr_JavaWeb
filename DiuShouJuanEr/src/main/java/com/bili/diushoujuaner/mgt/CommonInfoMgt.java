package com.bili.diushoujuaner.mgt;

import com.bili.diushoujuaner.database.model.CommonInfo;

public interface CommonInfoMgt {

	int addCommonInfoByRecord(CommonInfo commonInfo);
	
	void deleteCommonInfo(long partyNo, long memberNo);
	
	int getCommonInfoCount(long offNo);
	
}
