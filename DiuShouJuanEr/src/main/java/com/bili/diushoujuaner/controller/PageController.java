package com.bili.diushoujuaner.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(HttpSession httpSession) {
		return "login";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String homePage(HttpSession httpSession) {
		return "home";
	}

}
