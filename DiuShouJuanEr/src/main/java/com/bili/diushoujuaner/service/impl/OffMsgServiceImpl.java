package com.bili.diushoujuaner.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bili.diushoujuaner.common.CommonUtils;
import com.bili.diushoujuaner.common.ConstantUtils;
import com.bili.diushoujuaner.database.model.OffMsg;
import com.bili.diushoujuaner.entity.ResponseDto;
import com.bili.diushoujuaner.mgt.OffMsgMgt;
import com.bili.diushoujuaner.service.OffMsgService;

@Service
public class OffMsgServiceImpl implements OffMsgService {

	@Autowired
	OffMsgMgt offMsgMgt;
	
	@Override
	public ResponseDto getOffMsgListByToken(String accessToken, String deviceType) {
		List<OffMsg> offMsgList = offMsgMgt.getOffMsgListByUserNo(CommonUtils.getUserNoFromAccessToken(accessToken), deviceType);
		if(CommonUtils.getDeviceType(deviceType) == ConstantUtils.DEVICE_ANDROID){
			offMsgMgt.deleteMobileOffMsgByUserNo(CommonUtils.getUserNoFromAccessToken(accessToken));
		}else{
			offMsgMgt.deleteBrowserOffMsgByUserNo(CommonUtils.getUserNoFromAccessToken(accessToken));
		}
		return CommonUtils.getResponse(ConstantUtils.SUCCESS, "获取离线信息成功", offMsgList);
	}

}
