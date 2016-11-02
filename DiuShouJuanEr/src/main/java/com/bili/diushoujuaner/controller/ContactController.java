package com.bili.diushoujuaner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bili.diushoujuaner.service.ContactService;

@Controller
public class ContactController {
	
	@Autowired
	ContactService contactService;

	@RequestMapping(value = "/1.0/contacts", method = RequestMethod.GET)
	@ResponseBody
	public Object getContactListByToken(@RequestHeader(value="AccessToken", required = true, defaultValue = "") String accessToken){
		return contactService.getContactListByToken(accessToken);
	}
	
	@RequestMapping(value = "/1.0/contacts/search", method = RequestMethod.GET)
	@ResponseBody
	public Object getContactsSearch(
			@RequestParam(value = "paramNo", required = true, defaultValue = "-1") String paramNo){
		return contactService.getContactsSearch(paramNo);
	}
	
	@RequestMapping(value = "/1.0/contact/party", method = RequestMethod.GET)
	@ResponseBody
	public Object getContactByPartyNo(@RequestParam(value = "partyNo", required = true, defaultValue = "-1") long partyNo){
		return contactService.getContactByPartyNo(partyNo);
	}
	
}
