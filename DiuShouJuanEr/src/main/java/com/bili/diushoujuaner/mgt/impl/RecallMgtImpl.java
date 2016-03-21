package com.bili.diushoujuaner.mgt.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bili.diushoujuaner.common.recallpic.RecallPicManager;
import com.bili.diushoujuaner.database.mapper.PictureMapper;
import com.bili.diushoujuaner.database.mapper.RecallMapper;
import com.bili.diushoujuaner.database.model.Picture;
import com.bili.diushoujuaner.database.model.Recall;
import com.bili.diushoujuaner.database.model.RecallExample;
import com.bili.diushoujuaner.database.param.RecallRequestParam;
import com.bili.diushoujuaner.mgt.RecallMgt;

@Repository
public class RecallMgtImpl implements RecallMgt {

	@Autowired
	RecallMapper recallMapper;
	@Autowired
	PictureMapper pictureMapper;
	
	@Override
	public List<Recall> getRecallListByPageParam(int pageIndex, int pageSize) {
		RecallRequestParam recallRequestParam = new RecallRequestParam();
		recallRequestParam.setPageStart((pageIndex - 1) * pageSize);
		recallRequestParam.setPageSize(pageSize);
		return recallMapper.getRecallListByPageParam(recallRequestParam);
	}
	
	@Override
	public List<Recall> getRecallListByPageParamAndRecall(int pageIndex, int pageSize,long lastRecall) {
		RecallRequestParam recallRequestParam = new RecallRequestParam();
		recallRequestParam.setPageStart((pageIndex - 1) * pageSize);
		recallRequestParam.setPageSize(pageSize);
		recallRequestParam.setLastRecall(lastRecall);
		return recallMapper.getRecallListByPageParamAndRecall(recallRequestParam);
	}

	@Override
	public List<Recall> getRecallListByUserNoAndPageParam(long userNo,
			int pageIndex, int pageSize) {
		RecallRequestParam recallRequestParam = new RecallRequestParam();
		recallRequestParam.setUserNo(userNo);
		recallRequestParam.setPageStart((pageIndex - 1) * pageSize);
		recallRequestParam.setPageSize(pageSize);
		return recallMapper.getRecallListByUserNoAndPageParam(recallRequestParam);
	}

	@Override
	public List<Recall> getRecallListByUserNoAndPageParamAndRecall(long userNo, 
			int pageIndex, int pageSize, long lastRecall) {
		RecallRequestParam recallRequestParam = new RecallRequestParam();
		recallRequestParam.setUserNo(userNo);
		recallRequestParam.setPageStart((pageIndex - 1) * pageSize);
		recallRequestParam.setPageSize(pageSize);
		recallRequestParam.setLastRecall(lastRecall);
		return recallMapper.getRecallListByUserNoAndPageParamAndRecall(recallRequestParam);
	}

	@Override
	public int addRecall(long userNo, String content, String time, int picCount, String deviceType) {
		Recall recall = new Recall();
		recall.setContent(content);
		recall.setUserNo(userNo);
		recall.setPublishTime(time);
		
		int effectLines = recallMapper.insertSelective(recall);
		if(effectLines > 0 && picCount > 0){
			Map<String, Picture> tmpMap = RecallPicManager.getPictureMap(userNo + deviceType);
			if(tmpMap != null){
				for(Picture picture : tmpMap.values()){
					picture.setRecallNo(recall.getRecallNo());
					pictureMapper.insertSelective(picture);
				}
			}
			RecallPicManager.clearUserPicture(userNo + deviceType, false);
		}
		return effectLines;
	}

	@Override
	public int deleteRecallByRecallNo(long recallNo) {
		RecallExample recallExample = new RecallExample();
		recallExample.createCriteria().andRecallNoEqualTo(recallNo);
		return recallMapper.deleteByExample(recallExample);
	}

	@Override
	public long getUserNoByRecallNo(long recallNo) {
		return recallMapper.getUserNoByRecallNo(recallNo);
	}

}
