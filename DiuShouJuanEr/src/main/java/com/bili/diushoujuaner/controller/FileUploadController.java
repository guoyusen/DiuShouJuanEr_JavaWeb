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

	@RequestMapping(value = "/1.0/file/recallpic", method = RequestMethod.POST)
	@ResponseBody
	public Object uploadPostPicByRecord(
			@RequestParam("file") MultipartFile file,
			@RequestHeader(value="AccessToken", required = true, defaultValue = "") String accessToken,
			@RequestHeader(value="Device-Type", required = true, defaultValue = "") String deviceType) throws IOException {
		return fileUploadService.uploadPostPicByRecord(file, accessToken, deviceType);
	}
	
	@RequestMapping(value = "/1.0/file/headpic", method = RequestMethod.POST)
	@ResponseBody
	public Object uploadHeadPic(
			@RequestParam("file") MultipartFile file,
			@RequestHeader(value="AccessToken", required = true, defaultValue = "") String accessToken){
		return fileUploadService.uploadHeadPic(file, accessToken);
	}
	
	@RequestMapping(value = "/1.0/file/wallpaper", method = RequestMethod.POST)
	@ResponseBody
	public Object uploadWallPaper(
			@RequestParam("file") MultipartFile file,
			@RequestHeader(value="AccessToken", required = true, defaultValue = "") String accessToken){
		return fileUploadService.uploadWallPaper(file, accessToken);
	}
	
	@RequestMapping(value = "/1.0/file/recallpic/remove", method = RequestMethod.POST)
	@ResponseBody
	public Object removePostPicByRecord(
			@RequestParam(value = "picId", required = true, defaultValue = "-1") long picId,
			@RequestHeader(value="AccessToken", required = true, defaultValue = "") String accessToken,
			@RequestHeader(value="Device-Type", required = true, defaultValue = "") String deviceType){
		
		return fileUploadService.removePostPicByRecord(picId, accessToken, deviceType);
	}
	
	@RequestMapping(value = "/1.0/file/recallpic/removeall", method = RequestMethod.POST)
	@ResponseBody
	public Object removePostPicListByRecord(
			@RequestHeader(value="AccessToken", required = true, defaultValue = "") String accessToken,
			@RequestHeader(value="Device-Type", required = true, defaultValue = "") String deviceType){
		
		return fileUploadService.removePostPicListByRecord(accessToken, deviceType);
	}

}
