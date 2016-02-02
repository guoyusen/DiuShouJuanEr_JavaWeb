package com.bili.diushoujuaner.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bili.diushoujuaner.common.ConstantUtils;
import com.bili.diushoujuaner.common.CommonUtils;
import com.bili.diushoujuaner.common.dto.ResponseDto;
import com.bili.diushoujuaner.mgt.VerifyCodeMgt;
import com.bili.diushoujuaner.service.VerifyCodeService;

@Service
public class VerifyCodeServiceImpl implements VerifyCodeService {
	
	@Autowired
	VerifyCodeMgt verifyCodeMgt;

	@Override
	public ResponseDto getVerifyCodeByMobileAndType(String mobile, short type) {
		if(CommonUtils.isMobile(mobile)){
			String verifyCode = CommonUtils.getverifycode();
			System.out.println(verifyCode);
			verifyCodeMgt.addVerifyCodeByMobileAndTypeAndCode(mobile, type, verifyCode);
			return CommonUtils.getResponse(ConstantUtils.SUCCESS, "发送验证码成功", null);
		}else{
			return CommonUtils.getResponse(ConstantUtils.FAIL, "手机号格式错误", null);
		}
	}

}
