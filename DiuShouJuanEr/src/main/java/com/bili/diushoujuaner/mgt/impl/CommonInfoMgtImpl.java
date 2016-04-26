package com.bili.diushoujuaner.mgt.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bili.diushoujuaner.database.mapper.CommonInfoMapper;
import com.bili.diushoujuaner.database.model.CommonInfo;
import com.bili.diushoujuaner.database.param.CommonDeleteParam;
import com.bili.diushoujuaner.mgt.CommonInfoMgt;

@Repository
public class CommonInfoMgtImpl implements CommonInfoMgt {

	@Autowired
	CommonInfoMapper commonInfoMapper;
	
	@Override
	public int addCommonInfoByRecord(CommonInfo commonInfo) {
		return commonInfoMapper.insertSelective(commonInfo);
	}

	@Override
	public void deleteCommonInfo(long partyNo, long memberNo) {
		commonInfoMapper.deleteCommonInfo(new CommonDeleteParam(partyNo, memberNo));
	}

}
