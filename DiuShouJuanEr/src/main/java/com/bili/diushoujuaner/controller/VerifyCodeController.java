package com.bili.diushoujuaner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bili.diushoujuaner.service.VerifyCodeService;

@Controller
public class VerifyCodeController {

	@Autowired
	private VerifyCodeService verifyCodeService;

	@RequestMapping(value = "/1.0/verify/code", method = RequestMethod.POST)
	@ResponseBody
	public Object getVerifyCodeByMobileAndType(
			@RequestParam(value = "mobile", required = true, defaultValue = "") String mobile,
			@RequestParam(value = "type", required = true, defaultValue = "0") short type) {
		return verifyCodeService.getVerifyCodeByMobileAndType(mobile, type);
	}

}
