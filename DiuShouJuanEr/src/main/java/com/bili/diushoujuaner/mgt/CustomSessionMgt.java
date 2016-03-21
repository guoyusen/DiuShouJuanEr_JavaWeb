package com.bili.diushoujuaner.mgt;

import com.bili.diushoujuaner.database.model.CustomSession;

public interface CustomSessionMgt {

	CustomSession updateCustomSession(String accessToken, long userNo, short deviceType);
	
	CustomSession updateCustomSession(String accessToken);
	
}
