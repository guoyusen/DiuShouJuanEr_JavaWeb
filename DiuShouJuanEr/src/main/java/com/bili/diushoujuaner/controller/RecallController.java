package com.bili.diushoujuaner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bili.diushoujuaner.service.RecallService;

@Controller
public class RecallController {
	
	@Autowired
	RecallService recallService;

	@RequestMapping(value = "/1.0/recalls", method = RequestMethod.GET)
	@ResponseBody
	public Object getRecallListByRecord(
			@RequestParam(value = "type", required = true, defaultValue = "0") int type,
			@RequestParam(value = "pageIndex", required = true, defaultValue = "1") int pageIndex,
			@RequestParam(value = "pageSize", required = true, defaultValue = "20") int pageSize,
			@RequestParam(value = "userNo", required = true, defaultValue = "-1") long userNo,
			@RequestParam(value = "lastRecall", required = true, defaultValue = "-1") long lastRecall){
		return recallService.getRecallListByRecord(type, pageIndex, pageSize, userNo, lastRecall);
	}
	
	@RequestMapping(value = "/1.0/recalls/add", method = RequestMethod.POST)
	@ResponseBody
	public Object addRecallByContAndToken(
			@RequestParam(value = "content", required = true, defaultValue = "") String content,
			@RequestParam(value = "picount", required = true, defaultValue = "") int picCount,
			@RequestHeader(value="AccessToken", required = true, defaultValue = "") String accessToken,
			@RequestHeader(value="Device-Type", required = true, defaultValue = "") String deviceType){
		return recallService.addRecallByContAndToken(content, accessToken, picCount, deviceType);
	}
	
	@RequestMapping(value = "/1.0/recalls/remove", method = RequestMethod.POST)
	@ResponseBody
	public Object removeRecallByRecallNo(
			@RequestParam(value = "recallno", required = true, defaultValue = "-1") long recallNo,
			@RequestHeader(value="AccessToken", required = true, defaultValue = "") String accessToken){
		return recallService.removeRecallByRecallNo(recallNo, accessToken);
	}
	
}
