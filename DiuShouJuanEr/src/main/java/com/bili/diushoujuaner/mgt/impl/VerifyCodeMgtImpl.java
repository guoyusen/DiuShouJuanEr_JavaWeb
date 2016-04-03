package com.bili.diushoujuaner.mgt.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bili.diushoujuaner.common.CommonUtils;
import com.bili.diushoujuaner.database.mapper.VerifyCodeMapper;
import com.bili.diushoujuaner.database.model.VerifyCode;
import com.bili.diushoujuaner.database.model.VerifyCodeExample;
import com.bili.diushoujuaner.mgt.VerifyCodeMgt;

@Repository
public class VerifyCodeMgtImpl implements VerifyCodeMgt {

	@Autowired
	VerifyCodeMapper verifyCodeMapper;
	
	@Override
	public void addVerifyCodeByMobileAndTypeAndCode(String mobile, short type, String code) {
		VerifyCode verifyCode = new VerifyCode();
		verifyCode.setAddTime(CommonUtils.getCurrentTime_YYYYMMDD_HHMMSS());
		verifyCode.setCode(code);
		verifyCode.setMobile(mobile);
		verifyCode.setType(type);
		verifyCode.setValid(true);
		verifyCodeMapper.insertSelective(verifyCode);
	}

	@Override
	public VerifyCode getVerifyCodeByMobileAndType(String mobile, short type) {
		
		VerifyCodeExample verifyCodeExample = new VerifyCodeExample();
		verifyCodeExample.createCriteria().andMobileEqualTo(mobile).andTypeEqualTo(type).andValidEqualTo(true);
		
		List<VerifyCode> verifyCodeList = verifyCodeMapper.selectByExample(verifyCodeExample);
		return verifyCodeList.isEmpty() ? null : verifyCodeList.get(0);
	}

	@Override
	public void updateVerifyCodeByMobileAndTypeAndCode(String mobile, short type, String code) {
		VerifyCode verifyCode = new VerifyCode();
		verifyCode.setValid(false);
		VerifyCodeExample verifyCodeExample = new VerifyCodeExample();
		verifyCodeExample.createCriteria().andMobileEqualTo(mobile).andTypeEqualTo(type).andCodeEqualTo(code);
		verifyCodeMapper.updateByExampleSelective(verifyCode, verifyCodeExample);
	}

}
