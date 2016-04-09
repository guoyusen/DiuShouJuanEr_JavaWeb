package com.bili.diushoujuaner.mgt.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bili.diushoujuaner.common.CommonUtils;
import com.bili.diushoujuaner.database.mapper.UserMapper;
import com.bili.diushoujuaner.database.model.User;
import com.bili.diushoujuaner.database.model.UserExample;
import com.bili.diushoujuaner.mgt.UserMgt;

@Repository
public class UserMgtImpl implements UserMgt {

	@Autowired
	private UserMapper userMapper;

	@Override
	public User updateUserInfo(User user, long userNo) {
		UserExample userExample = new UserExample();
		userExample.createCriteria().andUserNoEqualTo(userNo);
		
		int result = userMapper.updateByExampleSelective(user, userExample);
		if(result > 0){
			return getUserByUserNo(userNo);
		}
		return null;
	}

	@Override
	public User registerUserByMobile(String mobile, String password) {
		User user = new User();
		user.setNickName(System.currentTimeMillis() + "");
		user.setMobile(mobile);
		user.setUserPsd(password);
		user.setGender((short) 1);
		user.setIsValid(true);
		user.setPicPath("images/head/head_default.png");
		user.setRegistTime(CommonUtils.getCurrentTime_YYYYMMDD_HHMMSS());

		return userMapper.insertSelective(user) > 0 ? user : null;
	}

	@Override
	public User resetUserPsdByMobile(String mobile, String password) {
		UserExample userExample = new UserExample();
		userExample.createCriteria().andMobileEqualTo(mobile);
		User user = new User();
		user.setUserPsd(password);
		
		if(userMapper.updateByExampleSelective(user, userExample) > 0){
			List<User> list = userMapper.selectByExample(userExample);
			return list.isEmpty() ? null : list.get(0);
		}
		return null;
	}

	@Override
	public User getUserByUserNo(long userNo) {
		UserExample userExample = new UserExample();
		userExample.createCriteria().andUserNoEqualTo(userNo);

		List<User> userList = userMapper.selectByExample(userExample);
		return userList.isEmpty() ? null : userList.get(0);
	}

	@Override
	public User getUserByMobile(String mobile) {
		UserExample userExample = new UserExample();
		userExample.createCriteria().andMobileEqualTo(mobile);

		List<User> userList = userMapper.selectByExample(userExample);
		
		return userList.isEmpty() ? null : userList.get(0);
	}

	@Override
	public boolean modifyAutographByUserNo(String autograph, long userNo) {
		User user = new User();
		user.setAutograph(autograph);
		
		UserExample userExample = new UserExample();
		userExample.createCriteria().andUserNoEqualTo(userNo);
		
		return userMapper.updateByExampleSelective(user, userExample) > 0;
	}

	@Override
	public boolean updateUserOnlineStatus(long userNo, boolean isOnline) {
		User user = new User();
		user.setIsOnline(isOnline);
		
		UserExample userExample = new UserExample();
		userExample.createCriteria().andUserNoEqualTo(userNo);
		
		return userMapper.updateByExampleSelective(user, userExample) > 0;
	}

}
