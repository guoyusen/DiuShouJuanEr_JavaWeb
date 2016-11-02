package com.bili.diushoujuaner.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.bili.diushoujuaner.common.entity.ResponseDto;

public interface FileUploadService {

	ResponseDto uploadPostPicByRecord(MultipartFile file, String serial, String accessToken, String deviceType) throws IOException;
	
	ResponseDto removePostPicByRecord(long picId, String accessToken, String deviceType);
	
	ResponseDto removePostPicListByRecord(String accessToken, String deviceType);
	
	ResponseDto uploadHeadPic(MultipartFile file, String accessToken);
	
	ResponseDto uploadPartyHeadPic(MultipartFile file, long partyNo, String accessToken);
	
	ResponseDto uploadWallPaper(MultipartFile file, String accessToken);
}
