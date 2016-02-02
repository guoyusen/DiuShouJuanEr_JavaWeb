package com.bili.diushoujuaner.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bili.diushoujuaner.service.FileUploadService;

@Controller
public class FileUploadController {
	
	@Autowired
	private FileUploadService fileUploadService;

	@RequestMapping(value = "/1.0/file/postpic", method = RequestMethod.POST)
	@ResponseBody
	public Object uploadPostPicByRecord(
			@RequestParam("file") MultipartFile file,
			@RequestHeader(value="AccessToken", required = true, defaultValue = "") String accessToken,
			@RequestHeader(value="Device-Type", required = true, defaultValue = "") String deviceType) throws IOException {
		return fileUploadService.uploadPostPicByRecord(file, accessToken, deviceType);
	}
	
	@RequestMapping(value = "/1.0/file/postpic/delete", method = RequestMethod.POST)
	@ResponseBody
	public Object deletePostPicByRecord(
			@RequestParam(value = "picid", required = true, defaultValue = "-1") long picId,
			@RequestHeader(value="AccessToken", required = true, defaultValue = "") String accessToken,
			@RequestHeader(value="Device-Type", required = true, defaultValue = "") String deviceType){
		
		return fileUploadService.deletePostPicByRecord(picId, accessToken, deviceType);
	}
	
	@RequestMapping(value = "/1.0/file/postpic/delete/all", method = RequestMethod.POST)
	@ResponseBody
	public Object deletePostPicListByRecord(
			@RequestHeader(value="AccessToken", required = true, defaultValue = "") String accessToken,
			@RequestHeader(value="Device-Type", required = true, defaultValue = "") String deviceType){
		
		return fileUploadService.deletePostPicListByRecord(accessToken, deviceType);
	}

}
