package com.bili.diushoujuaner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bili.diushoujuaner.service.GoodService;

@Controller
public class GoodController {
	
	@Autowired
	GoodService goodService;
	
	@RequestMapping(value = "/1.0/goods/add", method = RequestMethod.POST)
	@ResponseBody
	public Object addGoodByRecallNoAndToken(
			@RequestParam(value = "recallNo", required = true, defaultValue = "-1") long recallNo,
			@RequestHeader(value="AccessToken", required = true, defaultValue = "") String accessToken){
		return goodService.addGoodByRecallNoAndToken(recallNo, accessToken);
	}
	
	@RequestMapping(value = "/1.0/goods/remove", method = RequestMethod.POST)
	@ResponseBody
	public Object removeGoodByRecallNoAndToken(
			@RequestParam(value = "recallNo", required = true, defaultValue = "-1") long recallNo,
			@RequestHeader(value="AccessToken", required = true, defaultValue = "") String accessToken){
		return goodService.removeGoodByRecallNoAndToken(recallNo, accessToken);
	}
	
}
