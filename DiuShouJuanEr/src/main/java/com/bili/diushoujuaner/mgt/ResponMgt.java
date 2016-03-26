package com.bili.diushoujuaner.mgt;

import java.util.List;

import com.bili.diushoujuaner.database.model.Respon;

public interface ResponMgt {

	List<Respon> getResponListByCommentNo(long commentNo);
	
	int removeResponByResponNo(long responNo);
	
	Respon addResponByRecord(long commentNo, long toNo, String content, long fromNo);
	
	boolean getPermitionForRemove(long responNo, long userNo);
	
}
