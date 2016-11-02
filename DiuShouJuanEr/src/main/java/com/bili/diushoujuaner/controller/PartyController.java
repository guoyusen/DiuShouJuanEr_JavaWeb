package com.bili.diushoujuaner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bili.diushoujuaner.service.PartyService;

@Controller
public class PartyController {
	
	@Autowired
	PartyService partyService;
	
	@RequestMapping(value = "/1.0/party/modify/name", method = RequestMethod.POST)
	@ResponseBody
	public Object modifyPartyName(
			@RequestParam(value = "partyNo", required = true, defaultValue = "-1") long partyNo,
			@RequestParam(value = "partyName", required = true, defaultValue = "") String partyName,
			@RequestHeader(value = "AccessToken", required = true, defaultValue = "") String accessToken){
		
		return partyService.modifyPartyName(partyNo, partyName, accessToken);
	}
	
	@RequestMapping(value = "/1.0/party/modify/introduce", method = RequestMethod.POST)
	@ResponseBody
	public Object modifyPartyintroduce(
			@RequestParam(value = "partyNo", required = true, defaultValue = "-1") long partyNo,
			@RequestParam(value = "introduce", required = true, defaultValue = "") String introduce,
			@RequestHeader(value = "AccessToken", required = true, defaultValue = "") String accessToken){
		
		return partyService.modifyPartyIntroduce(partyNo, introduce, accessToken);
	}	
	
	@RequestMapping(value = "/1.0/party/add", method = RequestMethod.POST)
	@ResponseBody
	public Object addParty(
			@RequestParam("file") MultipartFile file,
			@RequestParam(value = "partyName", required = true, defaultValue = "") String partyName,
			@RequestHeader(value = "AccessToken", required = true, defaultValue = "") String accessToken){
		return partyService.addParty(file, partyName, accessToken);
	}
	
	@RequestMapping(value = "/1.0/party/apply", method = RequestMethod.POST)
	@ResponseBody
	public Object applyAddParty(
			@RequestParam(value = "partyNo", required = true, defaultValue = "-1") long partyNo,
			@RequestParam(value = "content", required = true, defaultValue = "") String content,
			@RequestHeader(value = "AccessToken", required = true, defaultValue = "") String accessToken){
		
		return partyService.applyAddParty(partyNo, content, accessToken);
	}
	
	@RequestMapping(value = "/1.0/party/exit", method = RequestMethod.POST)
	@ResponseBody
	public Object getMemberExit(
			@RequestParam(value = "partyNo", required = true, defaultValue = "-1") long partyNo,
			@RequestHeader(value = "AccessToken", required = true, defaultValue = "") String accessToken){
		return partyService.getMemberExit(partyNo, accessToken);
	}
	
	@RequestMapping(value = "/1.0/party/ungroup", method = RequestMethod.POST)
	@ResponseBody
	public Object getPartyUnGroup(
			@RequestParam(value = "partyNo", required = true, defaultValue = "-1") long partyNo,
			@RequestHeader(value = "AccessToken", required = true, defaultValue = "") String accessToken){
		return partyService.getPartyUnGroup(partyNo, accessToken);
	}
	
	@RequestMapping(value = "/1.0/party/exit/force", method = RequestMethod.POST)
	@ResponseBody
	public Object getMemberForceExit(
			@RequestParam(value = "partyNo", required = true, defaultValue = "-1") long partyNo,
			@RequestParam(value = "memberNo", required = true, defaultValue = "-1") long memberNo,
			@RequestHeader(value = "AccessToken", required = true, defaultValue = "") String accessToken){
		return partyService.getMemberForceExit(partyNo, memberNo, accessToken);
	}
	
	@RequestMapping(value = "/1.0/party/members/batch", method = RequestMethod.POST)
	@ResponseBody
	public Object addMembersToParty(
			@RequestParam(value = "partyNo", required = true, defaultValue = "-1") long partyNo,
			@RequestParam(value = "members", required = true, defaultValue = "-1") String members,
			@RequestHeader(value = "AccessToken", required = true, defaultValue = "") String accessToken){
		return partyService.addMembersToParty(partyNo, members, accessToken);
	}
	
	@RequestMapping(value = "/1.0/party/apply/agree", method = RequestMethod.POST)
	@ResponseBody
	public Object getPartyApplyAgree(
			@RequestParam(value = "partyNo", required = true, defaultValue = "-1") long partyNo,
			@RequestParam(value = "memberNo", required = true, defaultValue = "-1") long memberNo,
			@RequestHeader(value="AccessToken", required = true, defaultValue = "") String accessToken){
		
		return partyService.getPartyApplyAgree(partyNo, memberNo, accessToken);
	}

}
