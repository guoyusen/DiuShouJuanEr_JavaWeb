package com.bili.diushoujuaner.service.impl;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bili.diushoujuaner.chat.MemberManager;
import com.bili.diushoujuaner.common.CommonUtils;
import com.bili.diushoujuaner.common.ConstantUtils;
import com.bili.diushoujuaner.common.CustomSessionUtil;
import com.bili.diushoujuaner.common.RecallPicUtil;
import com.bili.diushoujuaner.database.model.Party;
import com.bili.diushoujuaner.database.model.Picture;
import com.bili.diushoujuaner.database.model.User;
import com.bili.diushoujuaner.entity.ResponseDto;
import com.bili.diushoujuaner.mgt.PartyMgt;
import com.bili.diushoujuaner.mgt.UserMgt;
import com.bili.diushoujuaner.service.FileUploadService;

@Service
public class FileUploadServiceImpl implements FileUploadService {
	
	@Autowired
	private UserMgt userMgt;
	@Autowired
	private PartyMgt partyMgt;

	@Override
	public ResponseDto uploadWallPaper(MultipartFile file, String accessToken) {
		String filePath = CommonUtils.processWallPaper(file);
		
		User user = userMgt.getUserByUserNo(CustomSessionUtil.getCustomSession(accessToken).getUserNo());
		CommonUtils.deleteFileFromPath(CommonUtils.getRootDirectory() + user.getWallPaper());
		
		if(userMgt.updateWallpaper(filePath, CustomSessionUtil.getCustomSession(accessToken).getUserNo())){
			return CommonUtils.getResponse(ConstantUtils.SUCCESS, "上传壁纸成功", filePath);
		}
		return CommonUtils.getResponse(ConstantUtils.FAIL, "上传壁纸失败", null);
	}
	
	@Override
	public ResponseDto uploadPartyHeadPic(MultipartFile file, long partyNo, String accessToken) {
		long userNo = CustomSessionUtil.getCustomSession(accessToken).getUserNo();
		if(!partyMgt.isPermitHeadModify(partyNo, userNo)){
			CommonUtils.getResponse(ConstantUtils.FAIL, "暂无修改群头像权限", null);
		}
		String filePath = CommonUtils.processHeadImage(file);
		
		Party party = partyMgt.getPartyByPartyNo(partyNo);
		if(party == null){
			return CommonUtils.getResponse(ConstantUtils.FAIL, "该群不存在", null);
		}
		CommonUtils.deleteFileFromPath(CommonUtils.getRootDirectory() + party.getPicPath());
		
		if(partyMgt.updateHead(filePath, partyNo, userNo)){
			new Thread(){
				//开启线程，发送群通知，更改头像了
				@Override
				public void run() {
					MemberManager.broadCastToMember(partyNo, userNo, userNo, filePath, ConstantUtils.CHAT_PARTY_HEAD, true, false);
				}
				
			}.start();
			return CommonUtils.getResponse(ConstantUtils.SUCCESS, "上传头像成功", filePath);
		}
		return CommonUtils.getResponse(ConstantUtils.FAIL, "上传头像失败", null);
	}

	@Override
	public ResponseDto uploadHeadPic(MultipartFile file, String accessToken) {
		String filePath = CommonUtils.processHeadImage(file);
		
		User user = userMgt.getUserByUserNo(CustomSessionUtil.getCustomSession(accessToken).getUserNo());
		CommonUtils.deleteFileFromPath(CommonUtils.getRootDirectory() + user.getPicPath());
		
		if(userMgt.updateHead(filePath, CustomSessionUtil.getCustomSession(accessToken).getUserNo())){
			return CommonUtils.getResponse(ConstantUtils.SUCCESS, "上传头像成功", filePath);
		}
		return CommonUtils.getResponse(ConstantUtils.FAIL, "上传头像失败", null);
	}

	@Override
	public ResponseDto uploadPostPicByRecord(MultipartFile file,String serial, String accessToken, String deviceType) throws IOException {
		if (file != null) {
			Picture picture = RecallPicUtil.storePictureToLocal(CustomSessionUtil.getCustomSession(accessToken).getUserNo(), file, serial, deviceType);
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
		if(RecallPicUtil.removePicture(CustomSessionUtil.getCustomSession(accessToken).getUserNo() + deviceType, picId)){
			return CommonUtils.getResponse(ConstantUtils.SUCCESS, "删除文件成功", null);
		}
		return CommonUtils.getResponse(ConstantUtils.FAIL, "删除文件失败", null);
	}

	@Override
	public ResponseDto removePostPicListByRecord(String accessToken,
			String deviceType) {
		RecallPicUtil.clearUserPicture(CustomSessionUtil.getCustomSession(accessToken).getUserNo() + deviceType, true);
		return CommonUtils.getResponse(ConstantUtils.SUCCESS, "删除文件组成功", null);
	}

}
