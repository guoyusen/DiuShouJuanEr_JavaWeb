package com.bili.diushoujuaner.mgt.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bili.diushoujuaner.common.CommonUtils;
import com.bili.diushoujuaner.common.ConstantUtils;
import com.bili.diushoujuaner.database.mapper.UserMapper;
import com.bili.diushoujuaner.database.model.User;
import com.bili.diushoujuaner.database.model.UserExample;
import com.bili.diushoujuaner.mgt.UserMgt;

@Repository
public class UserMgtImpl implements UserMgt {

	@Autowired
	private UserMapper userMapper;

	@Override
	public boolean registerUserByMobile(String mobile, String password) {
		User user = new User();
		user.setRealName("diudiu");
		user.setNickName("diudiu");
		user.setMobile(mobile);
		user.setUserPsd(password);
		user.setGender((short) 1);
		user.setIsValid(true);
		user.setPicPath("images/head/head_default.png");
		user.setRegistTime(CommonUtils.getCurrentTime_YYYYMMDD_HHMMSS());
		user.setVisitType(ConstantUtils.USER_SPACE_VISIT_ALL);
		if(userMapper.insertSelective(user) > 0){
			return true;
		}else{
		    return false;
		}
	}

	@Override
	public boolean resetUserPsdByMobile(String mobile, String password) {
		UserExample userExample = new UserExample();
		userExample.createCriteria().andMobileEqualTo(mobile);
		User user = new User();
		user.setUserPsd(password);
		if(userMapper.updateByExampleSelective(user, userExample) > 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public User getUserByUserNo(long userNo) {
		UserExample userExample = new UserExample();
		userExample.createCriteria().andUserNoEqualTo(userNo);

		List<User> userList = userMapper.selectByExample(userExample);
		if (userList != null && userList.size() > 0){
			return userList.get(0);
		}
		else{
			return null;
		}
	}

	@Override
	public User getUserByMobile(String mobile) {
		UserExample userExample = new UserExample();
		userExample.createCriteria().andMobileEqualTo(mobile);

		List<User> userList = userMapper.selectByExample(userExample);
		if (userList != null && userList.size() > 0){
			return userList.get(0);
		}
		else{
			return null;
		}
	}

	@Override
	public boolean modifyAutographByUserNo(String autograph, long userNo) {
		User user = new User();
		user.setAutograph(autograph);
		
		UserExample userExample = new UserExample();
		userExample.createCriteria().andUserNoEqualTo(userNo);
		
		if(userMapper.updateByExampleSelective(user, userExample) > 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean updateUserOnlineStatus(long userNo, boolean isOnline) {
		User user = new User();
		user.setIsOnline(isOnline);
		
		UserExample userExample = new UserExample();
		userExample.createCriteria().andUserNoEqualTo(userNo);
		
		if(userMapper.updateByExampleSelective(user, userExample) > 0){
			return true;
		}else{
			return false;
		}
	}

}
