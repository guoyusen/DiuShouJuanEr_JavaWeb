package com.bili.diushoujuaner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bili.diushoujuaner.database.model.User;
import com.bili.diushoujuaner.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/1.0/users/info", method = RequestMethod.GET)
	@ResponseBody
	public Object getUserInfoByToken(@RequestHeader(value="AccessToken", required = true, defaultValue = "") String accessToken){
		return userService.getUserInfoByToken(accessToken);
	}
	
	@RequestMapping(value = "/1.0/users/info/user", method = RequestMethod.GET)
	@ResponseBody
	public Object getUserInfoByUserNo(@RequestParam(value = "userNo", required = true, defaultValue = "-1") long userNo){
		return userService.getUserInfoByUserNo(userNo);
	}
	
	@RequestMapping(value = "/1.0/users/login", method = RequestMethod.POST)
	@ResponseBody
	public Object getUserLogin(
			@RequestParam(value = "mobile", required = true, defaultValue = "") String mobile,
			@RequestParam(value = "password", required = true, defaultValue = "") String password,
			@RequestHeader(value="Device-Type", required = true, defaultValue = "") String deviceType) {
		return userService.getUserLogin(mobile, password, deviceType);
	}
	
	@RequestMapping(value = "/1.0/users/logout", method = RequestMethod.GET)
	@ResponseBody
	public Object getUserLogout(
			@RequestHeader(value="AccessToken", required = true, defaultValue = "") String accessToken,
			@RequestHeader(value="Device-Type", required = true, defaultValue = "") String deviceType) {
		return userService.getUserLogout(accessToken, deviceType);
	}

	@RequestMapping(value = "/1.0/users/regist", method = RequestMethod.POST)
	@ResponseBody
	public Object getUserRegister(
			@RequestParam(value = "mobile", required = true, defaultValue = "") String mobile,
			@RequestParam(value = "password", required = true, defaultValue = "") String password,
			@RequestParam(value = "code", required = true, defaultValue = "") String code,
			@RequestHeader(value="Device-Type", required = true, defaultValue = "") String deviceType) throws Exception {
		return userService.getUserRegister(mobile, password, code, deviceType);
	}
	
	@RequestMapping(value = "/1.0/users/reset", method = RequestMethod.POST)
	@ResponseBody
	public Object getPasswordReset(
			@RequestParam(value = "mobile", required = true, defaultValue = "") String mobile,
			@RequestParam(value = "password", required = true, defaultValue = "") String password,
			@RequestParam(value = "code", required = true, defaultValue = "") String code,
			@RequestHeader(value="Device-Type", required = true, defaultValue = "") String deviceType) throws Exception {
		return userService.getPasswordReset(mobile, password, code, deviceType);
	}
	
	@RequestMapping(value = "/1.0/users/modify/autograph", method = RequestMethod.POST)
	@ResponseBody
	public Object modifyAutographByAutoAndToken(
			@RequestParam(value = "autograph", required = true, defaultValue = "") String autograph,
			@RequestHeader(value="AccessToken", required = true, defaultValue = "") String accessToken) throws Exception {
		return userService.modifyAutographByAutoAndToken(autograph, accessToken);
	}
	
	@RequestMapping(value = "/1.0/users/info/update", method = RequestMethod.POST)
	@ResponseBody
	public Object updateUserInfo(
			@RequestParam(value = "nickName", required = true, defaultValue = "") String nickName,
			@RequestParam(value = "gender", required = true, defaultValue = "1") short gender,
			@RequestParam(value = "email", required = true, defaultValue = "") String email,
			@RequestParam(value = "smallNick", required = true, defaultValue = "") String smallNick,
			@RequestParam(value = "birthday", required = true, defaultValue = "") String birthday,
			@RequestParam(value = "location", required = true, defaultValue = "") String location,
			@RequestParam(value = "homeTown", required = true, defaultValue = "") String homeTown,
			@RequestHeader(value="AccessToken", required = true, defaultValue = "") String accessToken){
		User user = new User();
		user.setNickName(nickName);
		user.setBirthday(birthday);
		user.setEmail(email);
		user.setGender(gender);
		user.setHomeTown(homeTown);
		user.setLocation(location);
		user.setSmallNick(smallNick);
		user.setLocation(location);

		return userService.updateUserInfo(user, accessToken);
	}
	
	@ExceptionHandler
	public void processException(Exception e){
		e.printStackTrace();
	}
	
}
