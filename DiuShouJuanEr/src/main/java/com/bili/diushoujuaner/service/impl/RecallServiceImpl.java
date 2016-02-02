package com.bili.diushoujuaner.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public ResponseDto getRecallListByRecord(int type, int pageIndex, int pageSize, String accessToken) {
		
		List<Recall> recallList = null;
		if(type == ConstantUtils.RECALL_ALL){
			if(pageIndex == 1){
				recallList = recallMgt.getRecallListByPageParam(pageIndex, pageSize);
			}else{
				recallList = recallMgt.getRecallListByPageParamAndTime(pageIndex, pageSize,CustomSessionManager.getCustomSession(accessToken).getAttribute("lastTime").toString().trim());
			}
			
		}else{
			recallList = recallMgt.getRecallListByUserNoAndPageParam(CommonUtils.getUserNoFromAccessToken(accessToken), pageIndex, pageSize);
		}
		if(recallList.size() > 0 && pageIndex == 1){
			CustomSessionManager.getCustomSession(accessToken).setAttribute("lastTime", recallList.get(0).getPublishTime());
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
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("recallList", recallList);
		
		return CommonUtils.getResponse(ConstantUtils.SUCCESS, "获取recallList信息成功", data);
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
	public ResponseDto deleteRecallByRecallNo(long recallNo) {
		
		List<Picture> pictureList = pictureMgt.getPictureListByRecallNo(recallNo);
		
		for(Picture picture : pictureList){
			CommonUtils.deleteFileFromPath(CommonUtils.getRootDirectory() + picture.getPicPath());
		}
		
		int effectLines = recallMgt.deleteRecallByRecallNo(recallNo);
		if(effectLines > 0){
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("recallNo", recallNo);
			return CommonUtils.getResponse(ConstantUtils.SUCCESS, "删除Recall成功", data);
		}else{
			return CommonUtils.getResponse(ConstantUtils.FAIL, "删除Recall失败" + recallNo, null);
		}
	}

}
