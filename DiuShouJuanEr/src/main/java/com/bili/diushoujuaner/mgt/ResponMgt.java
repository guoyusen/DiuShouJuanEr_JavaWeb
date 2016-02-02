package com.bili.diushoujuaner.mgt;

import java.util.List;

import com.bili.diushoujuaner.database.model.Respon;

public interface ResponMgt {

	List<Respon> getResponListByCommentNo(long commentNo);
	
	int deleteResponByResponNo(long responNo);
	
	Respon addResponByRecord(long commentNo, long toNo, String content, long fromNo);
	
}
