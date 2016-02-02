package com.bili.diushoujuaner.database.model;

import java.util.List;

public class ContactVo {

	private Long contNo;
	private String displayName;
	private String picPath;
	private int type;
	private String sortLetter;
	private String startTime;
	// friend
	private String nickName;
	private String autograph;
	private short gender;
	private String homeTown;
	private String smallNick;
	// party
	private Long ownerNo;
	private String information;
	private List<Member> memberList;
	public Long getContNo() {
		return contNo;
	}
	public void setContNo(Long contNo) {
		this.contNo = contNo;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getSortLetter() {
		return sortLetter;
	}
	public void setSortLetter(String sortLetter) {
		this.sortLetter = sortLetter;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getAutograph() {
		return autograph;
	}
	public void setAutograph(String autograph) {
		this.autograph = autograph;
	}
	public short getGender() {
		return gender;
	}
	public void setGender(short gender) {
		this.gender = gender;
	}
	public String getHomeTown() {
		return homeTown;
	}
	public void setHomeTown(String homeTown) {
		this.homeTown = homeTown;
	}
	public String getSmallNick() {
		return smallNick;
	}
	public void setSmallNick(String smallNick) {
		this.smallNick = smallNick;
	}
	public Long getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(Long ownerNo) {
		this.ownerNo = ownerNo;
	}
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	public List<Member> getMemberList() {
		return memberList;
	}
	public void setMemberList(List<Member> memberList) {
		this.memberList = memberList;
	}
	
}
