package com.bili.diushoujuaner.mgt.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bili.diushoujuaner.common.CommonUtils;
import com.bili.diushoujuaner.database.mapper.FeedBackMapper;
import com.bili.diushoujuaner.database.model.FeedBack;
import com.bili.diushoujuaner.mgt.FeedBackMgt;

@Repository
public class FeedBackMgtImpl implements FeedBackMgt {
	
	
	@Autowired
	FeedBackMapper feedBackMapper;

	@Override
	public int addFeedBack(String content, long userNo) {
		FeedBack feedBack = new FeedBack();
		feedBack.setContent(content);
		feedBack.setTime(CommonUtils.getCurrentTime_YYYYMMDD_HHMMSS());
		feedBack.setUserNo(userNo);
		
		return feedBackMapper.insert(feedBack);
	}

}
