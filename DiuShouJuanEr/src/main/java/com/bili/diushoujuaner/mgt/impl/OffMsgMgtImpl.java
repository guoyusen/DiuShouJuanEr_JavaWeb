package com.bili.diushoujuaner.mgt.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bili.diushoujuaner.database.mapper.CommonInfoMapper;
import com.bili.diushoujuaner.database.mapper.OffMsgMapper;
import com.bili.diushoujuaner.database.model.CommonInfoExample;
import com.bili.diushoujuaner.database.model.OffMsg;
import com.bili.diushoujuaner.database.model.OffMsgExample;
import com.bili.diushoujuaner.mgt.OffMsgMgt;

@Repository
public class OffMsgMgtImpl implements OffMsgMgt {

	@Autowired
	OffMsgMapper offMsgMapper;
	@Autowired
	CommonInfoMapper commonInfoMapper;
	
	@Override
	public List<OffMsg> getOffMsgListByUserNo(long userNo) {
		return offMsgMapper.getOffMsgListByUserNo(userNo);
	}
	
	@Override
	public void deleteOffMsgByUserNo(long userNo){
		OffMsgExample offMsgExample = new OffMsgExample();
		offMsgExample.createCriteria().andToNoEqualTo(userNo).andIsReadEqualTo(false);
		offMsgMapper.deleteByExample(offMsgExample);
		
		CommonInfoExample commonInfoExample = new CommonInfoExample();
		commonInfoExample.createCriteria().andToNoEqualTo(userNo).andIsReadEqualTo(false);
		commonInfoMapper.deleteByExample(commonInfoExample);
	}

	@Override
	public OffMsg putOffMsgByRecord(OffMsg offMsg) {
		if(offMsg == null || offMsgMapper.insertSelective(offMsg) <= 0){
			return null;
		}else{
			return offMsg;
		}
	}

}
