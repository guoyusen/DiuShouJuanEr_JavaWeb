package com.bili.diushoujuaner.database.model;

public class Good {
    private Long goodNo;

    private Long recallNo;

    private Long userNo;

    private String goodTime;
    
    private String userPicPath;

    public String getUserPicPath() {
		return userPicPath;
	}

	public void setUserPicPath(String userPicPath) {
		this.userPicPath = userPicPath;
	}

    public Long getGoodNo() {
        return goodNo;
    }

    public void setGoodNo(Long goodNo) {
        this.goodNo = goodNo;
    }

    public Long getRecallNo() {
        return recallNo;
    }

    public void setRecallNo(Long recallNo) {
        this.recallNo = recallNo;
    }

    public Long getUserNo() {
        return userNo;
    }

    public void setUserNo(Long userNo) {
        this.userNo = userNo;
    }

    public String getGoodTime() {
        return goodTime;
    }

    public void setGoodTime(String goodTime) {
        this.goodTime = goodTime == null ? null : goodTime.trim();
    }
}