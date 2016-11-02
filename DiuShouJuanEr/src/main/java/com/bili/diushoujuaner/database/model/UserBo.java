package com.bili.diushoujuaner.database.model;

public class UserBo {

	private long userNo;
	private String homeTown;
	private String nickName;
	
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public long getUserNo() {
		return userNo;
	}
	public void setUserNo(long userNo) {
		this.userNo = userNo;
	}
	public String getHomeTown() {
		return homeTown;
	}
	public void setHomeTown(String homeTown) {
		this.homeTown = homeTown;
	}
	@Override
	public String toString() {
		return "UserBo [userNo=" + userNo + ", homeTown=" + homeTown + ", nickName=" + nickName + "]";
	}
	
}
