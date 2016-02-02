package com.bili.diushoujuaner.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bili.diushoujuaner.common.CommonUtils;
import com.bili.diushoujuaner.common.ConstantUtils;
import com.bili.diushoujuaner.common.dto.ResponseDto;
import com.bili.diushoujuaner.common.recallpic.RecallPicManager;
import com.bili.diushoujuaner.common.session.CustomSessionManager;
import com.bili.diushoujuaner.database.model.Picture;
import com.bili.diushoujuaner.service.FileUploadService;

@Service
public class FileUploadServiceImpl implements FileUploadService {

	@Override
	public ResponseDto uploadPostPicByRecord(MultipartFile file, String accessToken, String deviceType) throws IOException {
		if (file != null) {
			Picture picture = RecallPicManager.storePictureToLocal(CustomSessionManager.getCustomSession(accessToken).getUserNo(), file, deviceType);
				
			Map<String, Object> data = new HashMap<>();
			data.put("picture", picture);
				
			return CommonUtils.getResponse(ConstantUtils.SUCCESS, "上传图片成功", data);
		}
		return CommonUtils.getResponse(ConstantUtils.FAIL, "不能传输空文件", null);
	}

	@Override
	public ResponseDto deletePostPicByRecord(long picId, String accessToken,
			String deviceType) {
		if(RecallPicManager.removePicture(CustomSessionManager.getCustomSession(accessToken).getUserNo() + deviceType, picId)){
			return CommonUtils.getResponse(ConstantUtils.SUCCESS, "删除文件成功", null);
		}
		return CommonUtils.getResponse(ConstantUtils.FAIL, "删除文件失败", null);
	}

	@Override
	public ResponseDto deletePostPicListByRecord(String accessToken,
			String deviceType) {
		RecallPicManager.clearUserPicture(CustomSessionManager.getCustomSession(accessToken).getUserNo() + deviceType, true);
		return CommonUtils.getResponse(ConstantUtils.SUCCESS, "删除文件组成功", null);
	}

}
