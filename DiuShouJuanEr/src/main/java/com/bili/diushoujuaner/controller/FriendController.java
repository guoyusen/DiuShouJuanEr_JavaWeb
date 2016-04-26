package com.bili.diushoujuaner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bili.diushoujuaner.service.FriendService;

@Controller
public class FriendController {
	
	@Autowired
	FriendService friendService;

	@RequestMapping(value = "/1.0/friend/apply", method = RequestMethod.POST)
	@ResponseBody
	public Object getFriendAdd(
			@RequestParam(value = "friendNo", required = true, defaultValue = "-1") long friendNo,
			@RequestParam(value = "content", required = true, defaultValue = "") String content,
			@RequestHeader(value = "AccessToken", required = true, defaultValue = "") String accessToken){
		return friendService.getFriendAdd(friendNo, content, accessToken);
	}
	
	@RequestMapping(value = "/1.0/friend/agree", method = RequestMethod.POST)
	@ResponseBody
	public Object getFriendAgree(
			@RequestParam(value = "friendNo", required = true, defaultValue = "-1") long friendNo,
			@RequestHeader(value="AccessToken", required = true, defaultValue = "") String accessToken){
		return friendService.getFriendAgree(friendNo, accessToken);
	}
	
	@RequestMapping(value = "/1.0/friend/delete", method = RequestMethod.POST)
	@ResponseBody
	public Object getFriendDelete(
			@RequestParam(value = "friendNo", required = true, defaultValue = "-1") long friendNo,
			@RequestHeader(value="AccessToken", required = true, defaultValue = "") String accessToken){
		return friendService.getFriendDelete(friendNo, accessToken);
	}
	
	@RequestMapping(value = "/1.0/friend/remark/modify", method = RequestMethod.POST)
	@ResponseBody
	public Object modifyFriendRemark(
			@RequestParam(value = "friendNo", required = true, defaultValue = "-1") long friendNo,
			@RequestParam(value = "remark", required = true, defaultValue = "") String remark,
			@RequestHeader(value="AccessToken", required = true, defaultValue = "") String accessToken){
		return friendService.modifyFriendRemark(friendNo, remark, accessToken);
	}
	
}
