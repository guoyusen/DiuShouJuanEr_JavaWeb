package com.bili.diushoujuaner.database.model;

public class VerifyCode {
    private Long verifyCodeNo;

    private String code;

    private String addTime;

    private String mobile;

    private Short type;

    private Boolean valid;

    public Long getVerifyCodeNo() {
        return verifyCodeNo;
    }

    public void setVerifyCodeNo(Long verifyCodeNo) {
        this.verifyCodeNo = verifyCodeNo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime == null ? null : addTime.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }
}