package com.bili.diushoujuaner.mgt;

import java.util.List;

import com.bili.diushoujuaner.database.model.ContactVo;

public interface ContactVoMgt {

	List<ContactVo> getFriendListByUserNo(long userNo);

	List<ContactVo> getPartyListByUserNo(long UserNo);

}
