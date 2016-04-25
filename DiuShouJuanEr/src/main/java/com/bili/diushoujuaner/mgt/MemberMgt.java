package com.bili.diushoujuaner.mgt;

import java.util.List;

import com.bili.diushoujuaner.database.model.Member;
import com.bili.diushoujuaner.database.model.User;

public interface MemberMgt {

	List<Member> getMemberListByPartyNo(long partyNo);
	
	List<Long> getMemberNoListByPartyNo(long partyNo);
	
	boolean modifyMemberName(long partyNo, long memberNo, String memberName);
	
	int addOwnerForParty(long partyNo, User user);
	
}
