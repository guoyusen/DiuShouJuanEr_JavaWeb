package com.bili.diushoujuaner.database.model;

public class CommonInfo {
    private Long commonInfoNo;

    private Long offMsgNo;

    private Long toNo;

    private Boolean isRead;

    public Long getCommonInfoNo() {
        return commonInfoNo;
    }

    public void setCommonInfoNo(Long commonInfoNo) {
        this.commonInfoNo = commonInfoNo;
    }

    public Long getOffMsgNo() {
        return offMsgNo;
    }

    public void setOffMsgNo(Long offMsgNo) {
        this.offMsgNo = offMsgNo;
    }

    public Long getToNo() {
        return toNo;
    }

    public void setToNo(Long toNo) {
        this.toNo = toNo;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }
}