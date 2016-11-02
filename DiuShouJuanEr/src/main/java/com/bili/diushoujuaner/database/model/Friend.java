package com.bili.diushoujuaner.database.model;

public class Friend {
    private Long friendNo;

    private Long userANo;

    private Long userBNo;

    private String remark;

    private String startTime;

    public Long getFriendNo() {
        return friendNo;
    }

    public void setFriendNo(Long friendNo) {
        this.friendNo = friendNo;
    }

    public Long getUserANo() {
        return userANo;
    }

    public void setUserANo(Long userANo) {
        this.userANo = userANo;
    }

    public Long getUserBNo() {
        return userBNo;
    }

    public void setUserBNo(Long userBNo) {
        this.userBNo = userBNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }
}