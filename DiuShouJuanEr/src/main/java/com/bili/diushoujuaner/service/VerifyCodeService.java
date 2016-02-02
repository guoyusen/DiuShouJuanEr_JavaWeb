package com.bili.diushoujuaner.service;

import com.bili.diushoujuaner.common.dto.ResponseDto;

public interface VerifyCodeService {
	
	ResponseDto getVerifyCodeByMobileAndType(String mobile, short type);

}
