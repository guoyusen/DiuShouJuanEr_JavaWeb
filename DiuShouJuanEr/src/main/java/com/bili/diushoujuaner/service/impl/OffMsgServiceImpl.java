package com.bili.diushoujuaner.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bili.diushoujuaner.common.CommonUtils;
import com.bili.diushoujuaner.common.ConstantUtils;
import com.bili.diushoujuaner.common.entity.ResponseDto;
import com.bili.diushoujuaner.database.model.OffMsg;
import com.bili.diushoujuaner.mgt.OffMsgMgt;
import com.bili.diushoujuaner.service.OffMsgService;

@Service
public class OffMsgServiceImpl implements OffMsgService {

	@Autowired
	OffMsgMgt offMsgMgt;
	
	@Override
	public ResponseDto getOffMsgListByToken(String accessToken) {
		List<OffMsg> offMsgList = offMsgMgt.getOffMsgListByUserNo(CommonUtils.getUserNoFromAccessToken(accessToken));
		//offMsgMgt.deleteOffMsgByUserNo(CommonUtils.getUserNoFromAccessToken(accessToken));
		return CommonUtils.getResponse(ConstantUtils.SUCCESS, "获取离线信息成功", offMsgList);
	}

}
