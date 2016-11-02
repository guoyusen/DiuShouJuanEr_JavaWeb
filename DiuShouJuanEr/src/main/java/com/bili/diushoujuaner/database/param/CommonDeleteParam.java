package com.bili.diushoujuaner.database.param;

public class CommonDeleteParam {
	
	private long partyNo;
	private long memberNo;
	
	public CommonDeleteParam(long partyNo, long memberNo) {
		super();
		this.partyNo = partyNo;
		this.memberNo = memberNo;
	}
	
	public long getPartyNo() {
		return partyNo;
	}
	public void setPartyNo(long partyNo) {
		this.partyNo = partyNo;
	}
	public long getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(long memberNo) {
		this.memberNo = memberNo;
	}
	
	

}
