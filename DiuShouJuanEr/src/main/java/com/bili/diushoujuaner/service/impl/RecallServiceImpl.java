package com.bili.diushoujuaner.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bili.diushoujuaner.common.CommonUtils;
import com.bili.diushoujuaner.common.ConstantUtils;
import com.bili.diushoujuaner.common.dto.ResponseDto;
import com.bili.diushoujuaner.common.session.CustomSessionManager;
import com.bili.diushoujuaner.database.model.Comment;
import com.bili.diushoujuaner.database.model.Picture;
import com.bili.diushoujuaner.database.model.Recall;
import com.bili.diushoujuaner.mgt.CommentMgt;
import com.bili.diushoujuaner.mgt.ResponMgt;
import com.bili.diushoujuaner.mgt.RecallMgt;
import com.bili.diushoujuaner.mgt.GoodMgt;
import com.bili.diushoujuaner.mgt.PictureMgt;
import com.bili.diushoujuaner.service.RecallService;

@Service
public class RecallServiceImpl implements RecallService {
	
	@Autowired
	RecallMgt recallMgt;
	@Autowired
	PictureMgt pictureMgt;
	@Autowired
	GoodMgt goodMgt;
	@Autowired
	CommentMgt commentMgt;
	@Autowired
	ResponMgt responMgt;

	@Override
	public ResponseDto getRecentRecallByUserNo(long userNo) {
		Recall recall = recallMgt.getRecentRecallByUserNo(userNo);
		if(recall != null){
			recall.setPictureList(pictureMgt.getPictureListByRecallNo(recall.getRecallNo()));
			return CommonUtils.getResponse(ConstantUtils.SUCCESS, "获取recentRecall成功", recall);
		}
		return CommonUtils.getResponse(ConstantUtils.SUCCESS, "获取recentRecall失败", null);
	}

	@Override
	public ResponseDto getRecallListByRecord(int type, int pageIndex, int pageSize, long userNo, long lastRecall) {
		
		List<Recall> recallList = new ArrayList<>();
		if(type == ConstantUtils.RECALL_ALL && pageIndex == 1){
			recallList.addAll(recallMgt.getRecallListByPageParam(pageIndex, pageSize));
		} else if (type == ConstantUtils.RECALL_ALL && pageIndex > 1){
			recallList.addAll(recallMgt.getRecallListByPageParamAndRecall(pageIndex, pageSize,lastRecall));
		} else if (type == ConstantUtils.RECALL_USER && pageIndex == 1){
			recallList.addAll(recallMgt.getRecallListByUserNoAndPageParam(userNo, pageIndex, pageSize));
		} else if (type == ConstantUtils.RECALL_USER && pageIndex > 1){
			recallList.addAll(recallMgt.getRecallListByUserNoAndPageParamAndRecall(userNo, pageIndex, pageSize, lastRecall));
		}
		
		for(Recall recall : recallList){
			recall.setPictureList(pictureMgt.getPictureListByRecallNo(recall.getRecallNo()));
			recall.setGoodList(goodMgt.getGoodListByRecallNo(recall.getRecallNo()));
			List<Comment> commentList = commentMgt.getCommentListByRecallNo(recall.getRecallNo());
			for(Comment comment:commentList){
				comment.setResponList(responMgt.getResponListByCommentNo(comment.getCommentNo()));
			}
			recall.setCommentList(commentList);
		}
		return CommonUtils.getResponse(ConstantUtils.SUCCESS, "获取recallList信息成功", recallList);
	}

	@Override
	public ResponseDto addRecallByContAndToken(String content, String accessToken, int picCount, String deviceType) {
		int effectLines = recallMgt.addRecall(CommonUtils.getUserNoFromAccessToken(accessToken), content, CommonUtils.getCurrentTime_YYYYMMDD_HHMMSS(), picCount, deviceType);
		if(effectLines > 0){
			return CommonUtils.getResponse(ConstantUtils.SUCCESS, "添加Recall成功", null);
		}else{
			return CommonUtils.getResponse(ConstantUtils.FAIL, "添加Recall失败", null);
		}
	}

	@Override
	public ResponseDto removeRecallByRecallNo(long recallNo, String accessToken) {
		if(!recallMgt.getPermitionForRemove(recallNo, CustomSessionManager.getCustomSession(accessToken).getUserNo())){
			return CommonUtils.getResponse(ConstantUtils.FAIL, "非法操作", null);
		}
		
		int effectLines = recallMgt.removeRecallByRecallNo(recallNo);
		if(effectLines > 0){
			List<Picture> pictureList = pictureMgt.getPictureListByRecallNo(recallNo);
			for(Picture picture : pictureList){
				CommonUtils.deleteFileFromPath(CommonUtils.getRootDirectory() + picture.getPicPath());
			}
			
			return CommonUtils.getResponse(ConstantUtils.SUCCESS, "删除Recall成功", recallNo);
		}else{
			return CommonUtils.getResponse(ConstantUtils.FAIL, "该趣事已经被删除", null);
		}
	}

}
