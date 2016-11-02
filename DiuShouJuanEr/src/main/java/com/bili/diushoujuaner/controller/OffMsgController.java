package com.bili.diushoujuaner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bili.diushoujuaner.service.OffMsgService;

@Controller
public class OffMsgController {
	
	@Autowired
	OffMsgService offMsgService;
	
	@RequestMapping(value = "/1.0/offmsgs", method = RequestMethod.GET)
	@ResponseBody
	public Object getOffMsgListByToken(
			@RequestHeader(value="AccessToken", required = true, defaultValue = "") String accessToken,
			@RequestHeader(value="Device-Type", required = true, defaultValue = "") String deviceType){
		return offMsgService.getOffMsgListByToken(accessToken, deviceType);
	}
	
}
