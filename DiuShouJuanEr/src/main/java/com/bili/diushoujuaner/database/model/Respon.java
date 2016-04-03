package com.bili.diushoujuaner.database.model;

public class Respon {
    private Long responNo;

    private Long commentNo;

    private String content;

    private String addTime;

    private Long fromNo;

    private Long toNo;
    
    private String fromPicPath;
    
    private String nickNameFrom;
    
    private String nickNameTo;
    
    private String timeStamp;

    public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getFromPicPath() {
		return fromPicPath;
	}

	public void setFromPicPath(String fromPicPath) {
		this.fromPicPath = fromPicPath;
	}

	public String getNickNameFrom() {
		return nickNameFrom;
	}

	public void setNickNameFrom(String nickNameFrom) {
		this.nickNameFrom = nickNameFrom;
	}

	public String getNickNameTo() {
		return nickNameTo;
	}

	public void setNickNameTo(String nickNameTo) {
		this.nickNameTo = nickNameTo;
	}

    public Long getResponNo() {
        return responNo;
    }

    public void setResponNo(Long responNo) {
        this.responNo = responNo;
    }

    public Long getCommentNo() {
        return commentNo;
    }

    public void setCommentNo(Long commentNo) {
        this.commentNo = commentNo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime == null ? null : addTime.trim();
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
}