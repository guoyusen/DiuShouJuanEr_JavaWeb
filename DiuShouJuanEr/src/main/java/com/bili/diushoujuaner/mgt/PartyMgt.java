package com.bili.diushoujuaner.mgt;

import com.bili.diushoujuaner.database.model.Party;

public interface PartyMgt {
	
	boolean isPermitHeadModify(long partyNo, long userNo);
	
	Party getPartyByPartyNo(long partyNo);
	
	boolean updateHead(String path, long partyNo, long userNo);
	
	boolean updateName(long partyNo, String partyName);
	
	boolean updateIntroduce(long partyNo, String introduce);
	
	Party addParty(long userNo, String partyName, String path);

}
