package com.bili.diushoujuaner.service;

import com.bili.diushoujuaner.common.entity.ResponseDto;

public interface VerifyCodeService {
	
	ResponseDto getVerifyCodeByMobileAndType(String mobile, short type);

}
