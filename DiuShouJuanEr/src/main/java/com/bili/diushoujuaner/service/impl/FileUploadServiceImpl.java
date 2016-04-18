package com.bili.diushoujuaner.service.impl;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bili.diushoujuaner.common.CommonUtils;
import com.bili.diushoujuaner.common.ConstantUtils;
import com.bili.diushoujuaner.common.entity.ResponseDto;
import com.bili.diushoujuaner.common.recallpic.RecallPicManager;
import com.bili.diushoujuaner.common.session.CustomSessionManager;
import com.bili.diushoujuaner.database.model.Picture;
import com.bili.diushoujuaner.database.model.User;
import com.bili.diushoujuaner.mgt.UserMgt;
import com.bili.diushoujuaner.service.FileUploadService;

@Service
public class FileUploadServiceImpl implements FileUploadService {
	
	@Autowired
	private UserMgt userMgt;

	@Override
	public ResponseDto uploadWallPaper(MultipartFile file, String accessToken) {
		String filePath = CommonUtils.processWallPaper(file);
		
		User user = userMgt.getUserByUserNo(CustomSessionManager.getCustomSession(accessToken).getUserNo());
		CommonUtils.deleteFileFromPath(CommonUtils.getRootDirectory() + user.getWallPaper());
		
		if(userMgt.updateWallpaper(filePath, CustomSessionManager.getCustomSession(accessToken).getUserNo())){
			return CommonUtils.getResponse(ConstantUtils.SUCCESS, "上传壁纸成功", filePath);
		}
		return CommonUtils.getResponse(ConstantUtils.FAIL, "上传壁纸失败", null);
	}

	@Override
	public ResponseDto uploadHeadPic(MultipartFile file, String accessToken) {
		String filePath = CommonUtils.processHeadImage(file);
		
		User user = userMgt.getUserByUserNo(CustomSessionManager.getCustomSession(accessToken).getUserNo());
		CommonUtils.deleteFileFromPath(CommonUtils.getRootDirectory() + user.getPicPath());
		
		if(userMgt.updateHead(filePath, CustomSessionManager.getCustomSession(accessToken).getUserNo())){
			return CommonUtils.getResponse(ConstantUtils.SUCCESS, "上传头像成功", filePath);
		}
		return CommonUtils.getResponse(ConstantUtils.FAIL, "上传头像失败", null);
	}

	@Override
	public ResponseDto uploadPostPicByRecord(MultipartFile file,String serial, String accessToken, String deviceType) throws IOException {
		if (file != null) {
			Picture picture = RecallPicManager.storePictureToLocal(CustomSessionManager.getCustomSession(accessToken).getUserNo(), file, serial, deviceType);
			if(CommonUtils.getDeviceType(deviceType) == ConstantUtils.DEVICE_ANDROID){
				return CommonUtils.getResponse(ConstantUtils.SUCCESS, "上传图片成功", null);
			}else{
				return CommonUtils.getResponse(ConstantUtils.SUCCESS, "上传图片成功", picture);
			}
		}
		return CommonUtils.getResponse(ConstantUtils.FAIL, "不能传输空文件", null);
	}

	@Override
	public ResponseDto removePostPicByRecord(long picId, String accessToken,
			String deviceType) {
		if(RecallPicManager.removePicture(CustomSessionManager.getCustomSession(accessToken).getUserNo() + deviceType, picId)){
			return CommonUtils.getResponse(ConstantUtils.SUCCESS, "删除文件成功", null);
		}
		return CommonUtils.getResponse(ConstantUtils.FAIL, "删除文件失败", null);
	}

	@Override
	public ResponseDto removePostPicListByRecord(String accessToken,
			String deviceType) {
		RecallPicManager.clearUserPicture(CustomSessionManager.getCustomSession(accessToken).getUserNo() + deviceType, true);
		return CommonUtils.getResponse(ConstantUtils.SUCCESS, "删除文件组成功", null);
	}

}
