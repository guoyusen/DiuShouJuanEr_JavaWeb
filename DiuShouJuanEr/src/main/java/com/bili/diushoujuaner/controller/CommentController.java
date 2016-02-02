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
	
	@RequestMapping(value = "/1.0/comments/delete", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteCommentByCommentNo(
			@RequestParam(value = "commentno", required = true, defaultValue = "-1") long commentNo){
		return commentService.deleteCommentByCommentNo(commentNo);
	}
	
	@RequestMapping(value = "/1.0/comments/comment", method = RequestMethod.POST)
	@ResponseBody
	public Object addCommentByRecord(
			@RequestParam(value = "recallno", required = true, defaultValue = "-1") long recallNo,
			@RequestParam(value = "content", required = true, defaultValue = "") String content,
			@RequestHeader(value="AccessToken", required = true, defaultValue = "") String accessToken){
		return commentService.addCommentByRecord(recallNo, content, accessToken);
	}
	
	
}
