package com.bili.diushoujuaner.database.mapper;

import java.util.List;

import com.bili.diushoujuaner.database.model.ContactVo;
import com.bili.diushoujuaner.database.param.FriendParam;

public interface ContactVoMapper {
	
	List<ContactVo> getFriendListByUserNo(long userNo);
	
	List<ContactVo> getPartyListByUserNo(long userNo);
	
	List<ContactVo> getNewFriendListByMobile(String mobile);

	List<ContactVo> getNewPartyListByPartyNo(String partyNo);
	
	List<ContactVo> getFriendByParam(FriendParam friendParam);
	
}
