package com.bili.diushoujuaner.database.mapper;

import java.util.List;

import com.bili.diushoujuaner.database.model.ContactVo;

public interface ContactVoMapper {
	
	public List<ContactVo> getFriendListByUserNo(long userNo);
	
	public List<ContactVo> getPartyListByUserNo(long userNo);

}
