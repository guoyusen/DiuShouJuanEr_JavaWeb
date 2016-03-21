package com.bili.diushoujuaner.mgt;

import java.util.List;

import com.bili.diushoujuaner.database.model.Recall;

public interface RecallMgt {

	List<Recall> getRecallListByPageParam(int pageIndex,int pageSize);
	
	List<Recall> getRecallListByPageParamAndRecall(int pageIndex, int pageSize,long lastRecall);
	
	List<Recall> getRecallListByUserNoAndPageParam(long userNo, int pageIndex,int pageSize);
	
	List<Recall> getRecallListByUserNoAndPageParamAndRecall(long userNo, int pageIndex,int pageSize, long lastRecall);
	
	int addRecall(long userNo, String content, String time, int picCount, String deviceType);
	
	int deleteRecallByRecallNo(long recallNo);
	
	long getUserNoByRecallNo(long recallNo);
	
}
