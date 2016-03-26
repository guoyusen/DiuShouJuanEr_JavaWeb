package com.bili.diushoujuaner.mgt;

import java.util.List;

import com.bili.diushoujuaner.database.model.Member;

public interface MemberMgt {

	List<Member> getMemberListByPartyNo(long partyNo);
	
	List<Long> getMemberNoListByPartyNo(long partyNo);
	
}
