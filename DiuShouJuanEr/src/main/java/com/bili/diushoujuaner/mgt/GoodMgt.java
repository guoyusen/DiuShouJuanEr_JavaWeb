package com.bili.diushoujuaner.mgt;

import java.util.List;

import com.bili.diushoujuaner.database.model.Good;

public interface GoodMgt {
 
	List<Good> getGoodListByRecallNo(long recallNo);
	
	int addGoodByRecallNoAndUserNo(long recallNo, long userNo);
	
	int removeGoodByRecallNoAndUserNo(long recallNo, long userNo);
	
}
