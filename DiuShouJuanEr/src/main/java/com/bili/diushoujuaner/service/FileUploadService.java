package com.bili.diushoujuaner.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.bili.diushoujuaner.common.dto.ResponseDto;

public interface FileUploadService {

	ResponseDto uploadPostPicByRecord(MultipartFile file, String accessToken, String deviceType) throws IOException;
	
	ResponseDto deletePostPicByRecord(long picId, String accessToken, String deviceType);
	
	ResponseDto deletePostPicListByRecord(String accessToken, String deviceType);
}
