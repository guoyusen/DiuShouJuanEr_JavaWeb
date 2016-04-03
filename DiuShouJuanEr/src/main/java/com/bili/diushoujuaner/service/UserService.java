package com.bili.diushoujuaner.service;

import com.bili.diushoujuaner.common.dto.ResponseDto;

public interface UserService {

	ResponseDto getUserLogin(String mobile,String password, String deviceType);
	
	ResponseDto getUserLogout(String accessToken, String deviceType);
	
	ResponseDto getUserInfoByToken(String accessToken);
	
	ResponseDto getUserInfoByUserNo(long userNo);
	
	ResponseDto getUserRegister(String mobile,String password, String code) throws Exception;

	ResponseDto getPasswordReset(String mobile,String password, String code) throws Exception;

	ResponseDto modifyAutographByAutoAndToken(String autograph, String accessToken);
}
