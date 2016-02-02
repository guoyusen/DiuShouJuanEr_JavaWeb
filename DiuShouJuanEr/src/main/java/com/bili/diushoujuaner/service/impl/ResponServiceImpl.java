package com.bili.diushoujuaner.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bili.diushoujuaner.common.CommonUtils;
import com.bili.diushoujuaner.common.ConstantUtils;
import com.bili.diushoujuaner.common.dto.ResponseDto;
import com.bili.diushoujuaner.database.model.Respon;
import com.bili.diushoujuaner.mgt.ResponMgt;
import com.bili.diushoujuaner.service.ResponService;

@Service
public class ResponServiceImpl implements ResponService {

	@Autowired
	ResponMgt responMgt;
	
	@Override
	public ResponseDto deleteResponByResponNo(long responNo) {
		int effectLines = responMgt.deleteResponByResponNo(responNo);
		if(effectLines > 0){
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("responNo", responNo);
			return CommonUtils.getResponse(ConstantUtils.SUCCESS, "删除Respon成功", data);
		}else{
			return CommonUtils.getResponse(ConstantUtils.FAIL, "删除Respon失败", null);
		}
	}

	@Override
	public ResponseDto addResponByRecord(long commentNo, long toNo, String content, String accessToken) {
		Respon respon = responMgt.addResponByRecord(commentNo, toNo, content, CommonUtils.getUserNoFromAccessToken(accessToken));
		if(respon != null){
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("respon", respon);
			return CommonUtils.getResponse(ConstantUtils.SUCCESS, "添加Respon成功", data);
		}else{
			return CommonUtils.getResponse(ConstantUtils.FAIL, "添加Respon失败", null);
		}
	}

}
