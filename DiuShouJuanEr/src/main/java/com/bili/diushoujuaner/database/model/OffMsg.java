package com.bili.diushoujuaner.database.model;

public class OffMsg {
    private Long offMsgNo;

    private Long fromNo;

    private Long toNo;

    private String content;

    private String time;

    private Boolean isRead;

    private Short msgType;

    private Short conType;

    public Long getOffMsgNo() {
        return offMsgNo;
    }

    public void setOffMsgNo(Long offMsgNo) {
        this.offMsgNo = offMsgNo;
    }

    public Long getFromNo() {
        return fromNo;
    }

    public void setFromNo(Long fromNo) {
        this.fromNo = fromNo;
    }

    public Long getToNo() {
        return toNo;
    }

    public void setToNo(Long toNo) {
        this.toNo = toNo;
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

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public Short getMsgType() {
        return msgType;
    }

    public void setMsgType(Short msgType) {
        this.msgType = msgType;
    }

    public Short getConType() {
        return conType;
    }

    public void setConType(Short conType) {
        this.conType = conType;
    }
}