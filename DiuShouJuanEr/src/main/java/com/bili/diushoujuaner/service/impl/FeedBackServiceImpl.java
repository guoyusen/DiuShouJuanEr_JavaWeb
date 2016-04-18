package com.bili.diushoujuaner.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bili.diushoujuaner.common.CommonUtils;
import com.bili.diushoujuaner.common.ConstantUtils;
import com.bili.diushoujuaner.common.entity.ResponseDto;
import com.bili.diushoujuaner.common.session.CustomSessionManager;
import com.bili.diushoujuaner.mgt.FeedBackMgt;
import com.bili.diushoujuaner.service.FeedBackService;

@Service
public class FeedBackServiceImpl implements FeedBackService {
	
	@Autowired
	FeedBackMgt feedBackMgt;

	@Override
	public ResponseDto addFeedBack(String content, String accessToken) {
		if(feedBackMgt.addFeedBack(content, CustomSessionManager.getCustomSession(accessToken).getUserNo()) > 0){
			return CommonUtils.getResponse(ConstantUtils.SUCCESS, "意见反馈成功", null);
		}else{
			return CommonUtils.getResponse(ConstantUtils.FAIL, "意见反馈成功", null);
		}
	}

}
