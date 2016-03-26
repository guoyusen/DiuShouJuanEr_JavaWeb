package com.bili.diushoujuaner.mgt.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bili.diushoujuaner.common.CommonUtils;
import com.bili.diushoujuaner.database.mapper.ResponMapper;
import com.bili.diushoujuaner.database.model.Respon;
import com.bili.diushoujuaner.database.model.ResponExample;
import com.bili.diushoujuaner.database.param.ResponRemoveValidateParam;
import com.bili.diushoujuaner.mgt.ResponMgt;

@Repository
public class ResponMgtImpl implements ResponMgt {

	@Autowired
	ResponMapper responMapper;
	
	@Override
	public List<Respon> getResponListByCommentNo(long commentNo) {
		return responMapper.getResponListByCommentNo(commentNo);
	}

	@Override
	public int removeResponByResponNo(long responNo) {
		ResponExample responExample = new ResponExample();
		responExample.createCriteria().andResponNoEqualTo(responNo);
		return responMapper.deleteByExample(responExample);
	}

	@Override
	public Respon addResponByRecord(long commentNo, long toNo, String content, long fromNo) {
		Respon respon = new Respon();
		respon.setCommentNo(commentNo);
		respon.setAddTime(CommonUtils.getCurrentTime_YYYYMMDD_HHMMSS());
		respon.setContent(content);
		respon.setFromNo(fromNo);
		respon.setToNo(toNo);
		
		responMapper.insertSelective(respon);
		
		List<Respon> responList = responMapper.getResponListByResponNo(respon.getResponNo());
		
		if(responList.size() > 0){
			return responList.get(0);
		}
		return null;
	}

	@Override
	public boolean getPermitionForRemove(long responNo, long userNo) {
		ResponRemoveValidateParam responRemoveValidateParam = new ResponRemoveValidateParam();
		responRemoveValidateParam.setResponNo(responNo);
		responRemoveValidateParam.setUserNo(userNo);
		long result = responMapper.getPermitionForRemove(responRemoveValidateParam);
		
		return result > 0 ? true : false;
	}

}
