package com.bili.diushoujuaner.chat;

import java.util.HashMap;
import java.util.List;

import com.bili.diushoujuaner.common.springcontext.SpringContextUtil;
import com.bili.diushoujuaner.mgt.MemberMgt;

public class MemberManager {
	
	private static HashMap<Long, List<Long>> memberSource = new HashMap<>();
	
	public static synchronized List<Long> getMemberNoList(long partyNo){
		if(memberSource.get(partyNo) == null){
			MemberMgt memberMgt = (MemberMgt)SpringContextUtil.getBean("memberMgtImpl");
			
			List<Long> memberNoList = memberMgt.getMemberNoListByPartyNo(partyNo);
			memberSource.put(partyNo, memberNoList);
		}
		return memberSource.get(partyNo);
	}

}
