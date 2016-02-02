package com.bili.diushoujuaner.mgt.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bili.diushoujuaner.common.CommonUtils;
import com.bili.diushoujuaner.database.mapper.GoodMapper;
import com.bili.diushoujuaner.database.model.Good;
import com.bili.diushoujuaner.database.model.GoodExample;
import com.bili.diushoujuaner.mgt.GoodMgt;

@Repository
public class GoodMgtImpl implements GoodMgt {
	
	@Autowired
	GoodMapper goodMapper;

	@Override
	public List<Good> getGoodListByRecallNo(long recallNo) {
		return goodMapper.getGoodListByRecallNo(recallNo);
	}

	@Override
	public int addGoodByRecallNoAndUserNo(long recallNo, long userNo) {
		Good good = new Good();
		good.setRecallNo(recallNo);
		good.setUserNo(userNo);
		good.setGoodTime(CommonUtils.getCurrentTime_YYYYMMDD_HHMMSS());
		return goodMapper.insertSelective(good);
	}

	@Override
	public int removeGoodByRecallNoAndUserNo(long recallNo, long userNo) {
		GoodExample goodExample = new GoodExample();
		goodExample.createCriteria().andRecallNoEqualTo(recallNo).andUserNoEqualTo(userNo);
		return goodMapper.deleteByExample(goodExample);
	}

}
