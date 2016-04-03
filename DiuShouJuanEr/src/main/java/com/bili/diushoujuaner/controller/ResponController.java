package com.bili.diushoujuaner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bili.diushoujuaner.service.ResponService;

@Controller
public class ResponController {
	
	@Autowired
	ResponService responService;
	
	@RequestMapping(value = "/1.0/respons/remove", method = RequestMethod.POST)
	@ResponseBody
	public Object removeResponByResponNo(
			@RequestParam(value = "responNo", required = true, defaultValue = "-1") long responNo,
			@RequestHeader(value="AccessToken", required = true, defaultValue = "") String accessToken){
		return responService.removeResponByResponNo(responNo, accessToken);
	}
	
	@RequestMapping(value = "/1.0/respons/add", method = RequestMethod.POST)
	@ResponseBody
	public Object addResponByRecord(
			@RequestParam(value = "timeStamp", required = true, defaultValue = "") String timeStamp,
			@RequestParam(value = "commentNo", required = true, defaultValue = "-1") long commentNo,
			@RequestParam(value = "toNo", required = true, defaultValue = "-1") long toNo,
			@RequestParam(value = "content", required = true, defaultValue = "0") String content,
			@RequestHeader(value="AccessToken", required = true, defaultValue = "") String accessToken){
		return responService.addResponByRecord(timeStamp, commentNo, toNo, content, accessToken);
	}
	
}
