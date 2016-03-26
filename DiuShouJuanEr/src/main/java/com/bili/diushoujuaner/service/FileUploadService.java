package com.bili.diushoujuaner.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.bili.diushoujuaner.common.dto.ResponseDto;

public interface FileUploadService {

	ResponseDto uploadPostPicByRecord(MultipartFile file, String accessToken, String deviceType) throws IOException;
	
	ResponseDto removePostPicByRecord(long picId, String accessToken, String deviceType);
	
	ResponseDto removePostPicListByRecord(String accessToken, String deviceType);
}
