package com.bili.diushoujuaner.database.model;

public class FeedBack {
    private Long feBaNo;

    private Long userNo;

    private String content;

    private String time;

    public Long getFeBaNo() {
        return feBaNo;
    }

    public void setFeBaNo(Long feBaNo) {
        this.feBaNo = feBaNo;
    }

    public Long getUserNo() {
        return userNo;
    }

    public void setUserNo(Long userNo) {
        this.userNo = userNo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }
}