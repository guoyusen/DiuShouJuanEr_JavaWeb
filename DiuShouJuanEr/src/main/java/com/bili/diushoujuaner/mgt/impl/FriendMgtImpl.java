package com.bili.diushoujuaner.mgt.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bili.diushoujuaner.common.CommonUtils;
import com.bili.diushoujuaner.database.mapper.FriendMapper;
import com.bili.diushoujuaner.database.model.Friend;
import com.bili.diushoujuaner.database.model.FriendExample;
import com.bili.diushoujuaner.database.model.User;
import com.bili.diushoujuaner.mgt.FriendMgt;

@Repository
public class FriendMgtImpl implements FriendMgt {

	@Autowired
	FriendMapper friendMapper;
	
	@Override
	public boolean isFriend(long userANo, long userBNo) {
		FriendExample friendExample = new FriendExample();
		friendExample.createCriteria().andUserANoEqualTo(userANo).andUserBNoEqualTo(userBNo);
		
		
		return !friendMapper.selectByExample(friendExample).isEmpty();
	}

	@Override
	public boolean addFriendRelationShip(User userFrom, User userTo) {
		int effectLine = 0;
		Friend friendA = new Friend();
		friendA.setUserANo(userFrom.getUserNo());
		friendA.setUserBNo(userTo.getUserNo());
		friendA.setRemark(userTo.getNickName());
		friendA.setStartTime(CommonUtils.getCurrentTime_YYYYMMDD_HHMMSS());
		
		effectLine += friendMapper.insertSelective(friendA);
		
		Friend friendB = new Friend();
		friendB.setUserANo(userTo.getUserNo());
		friendB.setUserBNo(userFrom.getUserNo());
		friendB.setRemark(userFrom.getNickName());
		friendB.setStartTime(CommonUtils.getCurrentTime_YYYYMMDD_HHMMSS());
		
		effectLine += friendMapper.insertSelective(friendB);
		
		return effectLine >= 2;
	}

}
