package com.bili.diushoujuaner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bili.diushoujuaner.service.CommentService;

@Controller
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	@RequestMapping(value = "/1.0/comments/remove", method = RequestMethod.POST)
	@ResponseBody
	public Object removeCommentByCommentNo(
			@RequestParam(value = "commentNo", required = true, defaultValue = "-1") long commentNo,
			@RequestHeader(value="AccessToken", required = true, defaultValue = "") String accessToken){
		return commentService.removeCommentByCommentNo(commentNo, accessToken);
	}
	
	@RequestMapping(value = "/1.0/comments/add", method = RequestMethod.POST)
	@ResponseBody
	public Object addCommentByRecord(
			@RequestParam(value = "timeStamp", required = true, defaultValue = "") String timeStamp,
			@RequestParam(value = "recallNo", required = true, defaultValue = "-1") long recallNo,
			@RequestParam(value = "content", required = true, defaultValue = "") String content,
			@RequestHeader(value="AccessToken", required = true, defaultValue = "") String accessToken){
		return commentService.addCommentByRecord(timeStamp, recallNo, content, accessToken);
	}
	
	@RequestMapping(value = "/1.0/guoyusen", method = RequestMethod.GET)
	@ResponseBody
	public Object guoyusen(
			@RequestHeader(value="AccessToken", required = true, defaultValue = "") String accessToken) throws Exception{
	    throw new Exception("dfhsfdsafds");
	}
	
}
