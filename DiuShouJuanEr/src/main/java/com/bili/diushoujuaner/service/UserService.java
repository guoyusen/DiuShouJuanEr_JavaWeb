package com.bili.diushoujuaner.service;

import com.bili.diushoujuaner.database.model.User;
import com.bili.diushoujuaner.entity.ResponseDto;

public interface UserService {

	ResponseDto getUserLogin(String mobile,String password, String deviceType);
	
	ResponseDto getUserLogout(String accessToken, String deviceType);
	
	ResponseDto getUserInfoByToken(String accessToken);
	
	ResponseDto getUserInfoByUserNo(long userNo);
	
	ResponseDto getUserRegister(String mobile,String password, String code, String deviceType) throws Exception;

	ResponseDto getPasswordReset(String mobile,String password, String code, String deviceType) throws Exception;

	ResponseDto modifyAutographByAutoAndToken(String autograph, String accessToken);
	
	ResponseDto updateUserInfo(User user, String accessToken);
}
