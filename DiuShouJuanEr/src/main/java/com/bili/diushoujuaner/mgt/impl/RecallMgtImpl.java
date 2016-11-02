package com.bili.diushoujuaner.mgt.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bili.diushoujuaner.common.CommonUtils;
import com.bili.diushoujuaner.common.recallpic.RecallPicManager;
import com.bili.diushoujuaner.database.mapper.PictureMapper;
import com.bili.diushoujuaner.database.mapper.RecallMapper;
import com.bili.diushoujuaner.database.model.Comment;
import com.bili.diushoujuaner.database.model.Good;
import com.bili.diushoujuaner.database.model.Picture;
import com.bili.diushoujuaner.database.model.PictureExample;
import com.bili.diushoujuaner.database.model.Recall;
import com.bili.diushoujuaner.database.model.RecallExample;
import com.bili.diushoujuaner.database.param.RecallRemoveValidateParam;
import com.bili.diushoujuaner.database.param.RecallRequestParam;
import com.bili.diushoujuaner.mgt.RecallMgt;

@Repository
public class RecallMgtImpl implements RecallMgt {

	@Autowired
	RecallMapper recallMapper;
	@Autowired
	PictureMapper pictureMapper;
	
	
	
	@Override
	public Recall getRecallByRecallNo(long recallNo) {
		List<Recall> recallList = new ArrayList<Recall>();
		Recall recall = null;
		
		recallList.addAll(recallMapper.getRecallByRecallNo(recallNo));
		if(!recallList.isEmpty()){
			recall = recallList.get(0);
			recall.setCommentList(new ArrayList<Comment>());
			recall.setGoodList(new ArrayList<Good>());
			PictureExample pictureExample = new PictureExample();
			pictureExample.createCriteria().andRecallNoEqualTo(recallNo);
			recall.setPictureList(pictureMapper.selectByExample(pictureExample));
		}
		return recall;
	}

	@Override
	public Recall getRecentRecallByUserNo(long userNo) {
		List<Recall> recallList = recallMapper.getRecentRecallByUserNo(userNo);
		return recallList.isEmpty() ? null : recallList.get(0);
	}

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
		recallRequestParam.setPageStart((pageIndex - 1) * pageSize - 1);
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
		recallRequestParam.setPageStart((pageIndex - 1) * pageSize - 1);
		recallRequestParam.setPageSize(pageSize);
		recallRequestParam.setLastRecall(lastRecall);
		return recallMapper.getRecallListByUserNoAndPageParamAndRecall(recallRequestParam);
	}

	@Override
	public long addRecall(long userNo, String content, String time, int picCount,String serial, String deviceType) {
		Recall recall = new Recall();
		recall.setContent(content);
		recall.setUserNo(userNo);
		recall.setPublishTime(time);
		
		try{
			int effectLines = recallMapper.insertSelective(recall);
			if(effectLines > 0 && picCount > 0){
				Map<String, Picture> tmpMap = RecallPicManager.getPictureMap(userNo + deviceType);
				if(tmpMap != null){
					for(Picture picture : tmpMap.values()){
						if(picture.getSerial().equals(serial)){
							picture.setRecallNo(recall.getRecallNo());
							pictureMapper.insertSelective(picture);
						}else{
							CommonUtils.deleteFileFromPath(picture.getRealPath());
						}
					}
				}
				RecallPicManager.clearUserPicture(userNo + deviceType, false);
			}
		}catch(Exception e){
			return -1;
		}
		return recall.getRecallNo();
	}

	@Override
	public int removeRecallByRecallNo(long recallNo) {
		RecallExample recallExample = new RecallExample();
		recallExample.createCriteria().andRecallNoEqualTo(recallNo);
		return recallMapper.deleteByExample(recallExample);
	}

	@Override
	public long getUserNoByRecallNo(long recallNo) {
		List<Long> resultList = recallMapper.getUserNoByRecallNo(recallNo);

		return resultList.isEmpty() ? 0 : resultList.get(0);
	}

	@Override
	public boolean getPermitionForRemove(long recallNo, long userNo) {
		RecallRemoveValidateParam recallRemoveValidateParam = new RecallRemoveValidateParam();
		recallRemoveValidateParam.setRecallNo(recallNo);
		recallRemoveValidateParam.setUserNo(userNo);
		
		long result = recallMapper.getPermitionForRemove(recallRemoveValidateParam);

		return result > 0;
	}

}
