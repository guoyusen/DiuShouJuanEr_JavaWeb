package com.bili.diushoujuaner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bili.diushoujuaner.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping(value = "/1.0/member/modify/name", method = RequestMethod.POST)
	@ResponseBody
	public Object modifyMemberName(
			@RequestParam(value = "memberName", required = true, defaultValue = "") String memberName,
			@RequestParam(value = "partyNo", required = true, defaultValue = "") long partyNo,
			@RequestHeader(value="AccessToken", required = true, defaultValue = "") String accessToken){
		return memberService.modifyMemberName(partyNo, memberName, accessToken);
	}	

}
