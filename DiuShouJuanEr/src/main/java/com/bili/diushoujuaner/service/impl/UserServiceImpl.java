package com.bili.diushoujuaner.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bili.diushoujuaner.mgt.CustomSessionMgt;
import com.bili.diushoujuaner.mgt.UserMgt;
import com.bili.diushoujuaner.mgt.VerifyCodeMgt;
import com.bili.diushoujuaner.common.ConstantUtils;
import com.bili.diushoujuaner.common.entity.ResponseDto;
import com.bili.diushoujuaner.chat.iosession.IOSessionManager;
import com.bili.diushoujuaner.common.CommonUtils;
import com.bili.diushoujuaner.common.recallpic.RecallPicManager;
import com.bili.diushoujuaner.common.session.CustomSessionManager;
import com.bili.diushoujuaner.database.model.CustomSession;
import com.bili.diushoujuaner.database.model.User;
import com.bili.diushoujuaner.database.model.VerifyCode;
import com.bili.diushoujuaner.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMgt userMgt;
	@Autowired
	private VerifyCodeMgt verifyCodeMgt;
	@Autowired
	private CustomSessionMgt customSessionMgt;

	@Override
	public ResponseDto updateUserInfo(User user, String accessToken) {
		User result = userMgt.updateUserInfo(user, CustomSessionManager.getCustomSession(accessToken).getUserNo());
		if(result != null){
			return CommonUtils.getResponse(ConstantUtils.SUCCESS, "用户信息更新成功", result);
		}
		return CommonUtils.getResponse(ConstantUtils.FAIL, "用户信息更新失败", null);
	}

	@Override
	public ResponseDto getUserLogin(String mobile, String password, String deviceType) {
		
		System.out.println("mobile = " + mobile);
		System.out.println("password = " + password);
		
		String accessToken = CommonUtils.getRandomAccessToken();
		
		User user = userMgt.getUserByMobile(mobile);
		if (user != null && user.getUserPsd().equals(password)) {
			userMgt.updateUserOnlineStatus(user.getUserNo(), true);
			RecallPicManager.clearUserPicture(user.getUserNo() + deviceType, true);
			IOSessionManager.closeSession(user.getUserNo(), CommonUtils.getDeviceType(deviceType));
			CustomSession customSession = customSessionMgt.updateCustomSession(accessToken, user.getUserNo(), CommonUtils.getDeviceType(deviceType));
			customSession.setPassWord(user.getUserPsd());
			CustomSessionManager.addCustomSession(customSession);
			return CommonUtils.getResponse(ConstantUtils.SUCCESS, "登录成功", customSession);
		}
		return CommonUtils.getResponse(ConstantUtils.FAIL, "账号或密码错误", null);
	
	}

	@Override
	public ResponseDto getUserInfoByUserNo(long userNo) {
		User user = userMgt.getUserByUserNo(userNo);
		
		if (user == null){
			return CommonUtils.getResponse(ConstantUtils.FAIL, "获取用户信息失败", null);
		}
		user.setUserPsd(null);
		return CommonUtils.getResponse(ConstantUtils.SUCCESS, "获取用户信息成功", user);
	}

	@Override
	public ResponseDto getUserInfoByToken(String accessToken) {
		
		long userNo = CommonUtils.getUserNoFromAccessToken(accessToken);
		
		if (userNo == 0){
			return CommonUtils.getResponse(ConstantUtils.FAIL, "获取用户信息失败", null);
		}
		
		User user = userMgt.getUserByUserNo(userNo);
		
		if (user == null){
			return CommonUtils.getResponse(ConstantUtils.FAIL, "获取用户信息失败", null);
		}
		user.setUserPsd(null);
		return CommonUtils.getResponse(ConstantUtils.SUCCESS, "获取用户信息成功", user);
	}

	@Override
	public ResponseDto getUserRegister(String mobile, String password, String code,String deviceType) throws Exception {
		
		VerifyCode verifycode = verifyCodeMgt.getVerifyCodeByMobileAndType(mobile,
				ConstantUtils.VERIFY_CODE_REGIST);
		if (!verifycode.getCode().equals(code)) {
			return CommonUtils.getResponse(ConstantUtils.FAIL, "验证码错误", null);
		}
		
		if(!(CommonUtils.getBetweenToSeconds(verifycode.getAddTime(),
				CommonUtils.getCurrentTime_YYYYMMDD_HHMMSS()) < ConstantUtils.MSG_TIME_LIMIT_60)){
			return CommonUtils.getResponse(ConstantUtils.FAIL, "验证码超时失效", null);
		}
		
		User user = userMgt.getUserByMobile(mobile);
		if(user != null){
			return CommonUtils.getResponse(ConstantUtils.ERROR, "该手机已注册", null);
		}
		
		user = userMgt.registerUserByMobile(mobile, password);
		if(user != null){
			verifyCodeMgt.updateVerifyCodeByMobileAndTypeAndCode(mobile, ConstantUtils.VERIFY_CODE_REGIST, code);
			
			String accessToken = CommonUtils.getRandomAccessToken();
            CustomSession customSession = customSessionMgt.updateCustomSession(accessToken, user.getUserNo(), CommonUtils.getDeviceType(deviceType));
			
			CustomSessionManager.addCustomSession(customSession);
			return CommonUtils.getResponse(ConstantUtils.SUCCESS, "您已注册成功", customSession);
		}else{
			return CommonUtils.getResponse(ConstantUtils.ERROR, "服务器贪玩去了...", null);
		}
		
	}

	@Override
	public ResponseDto getPasswordReset(String mobile, String password, String code, String deviceType) throws Exception {
		
		VerifyCode verifycode = verifyCodeMgt.getVerifyCodeByMobileAndType(mobile,
				ConstantUtils.VERIFY_CODE_RESET);
		if (!verifycode.getCode().equals(code)) {
			return CommonUtils.getResponse(ConstantUtils.FAIL, "验证码错误", null);
		}
		
		if(!(CommonUtils.getBetweenToSeconds(verifycode.getAddTime(),
				CommonUtils.getCurrentTime_YYYYMMDD_HHMMSS()) < ConstantUtils.MSG_TIME_LIMIT_60)){
			return CommonUtils.getResponse(ConstantUtils.FAIL, "验证码超时失效", null);
		}
		
		User user = userMgt.getUserByMobile(mobile);
		if(user == null){
			return CommonUtils.getResponse(ConstantUtils.ERROR, "您还没有注册", null);
		}
		
		user = userMgt.resetUserPsdByMobile(mobile, password);
		if(user != null){
			verifyCodeMgt.updateVerifyCodeByMobileAndTypeAndCode(mobile, ConstantUtils.VERIFY_CODE_RESET, code);
			String accessToken = CommonUtils.getRandomAccessToken();
            CustomSession customSession = customSessionMgt.updateCustomSession(accessToken, user.getUserNo(), CommonUtils.getDeviceType(deviceType));
			
			CustomSessionManager.addCustomSession(customSession);
			return CommonUtils.getResponse(ConstantUtils.SUCCESS, "密码重置成功", customSession);
		}else{
			return CommonUtils.getResponse(ConstantUtils.FAIL, "密码重置失败", null);
		}
	}

	@Override
	public ResponseDto modifyAutographByAutoAndToken(String autograph, String accessToken) {
		if(userMgt.modifyAutographByUserNo(autograph, CommonUtils.getUserNoFromAccessToken(accessToken))){
			return CommonUtils.getResponse(ConstantUtils.SUCCESS, "修改用户签名成功", autograph);
		}
		return CommonUtils.getResponse(ConstantUtils.FAIL, "修改用户签名失败", null);
	}

	@Override
	public ResponseDto getUserLogout(String accessToken, String deviceType) {
		if(userMgt.updateUserOnlineStatus(CommonUtils.getUserNoFromAccessToken(accessToken), false)){
			CustomSessionManager.removeCustomSession(accessToken);
			RecallPicManager.clearUserPicture(CommonUtils.getUserNoFromAccessToken(accessToken) + deviceType, true);
			return CommonUtils.getResponse(ConstantUtils.SUCCESS, "退出账号成功", null);
		}
		return CommonUtils.getResponse(ConstantUtils.FAIL, "退出账号失败", null);
	}

}
