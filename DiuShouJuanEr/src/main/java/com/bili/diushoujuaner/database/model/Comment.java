package com.bili.diushoujuaner.database.model;

import java.util.List;

public class Comment {
	
	private String timeStamp;
	
    private Long commentNo;

    private Long recallNo;

    private String content;

    private String addTime;

    private Long fromNo;
    
    private String fromPicPath;
    
    private String nickName;
    
    private List<Respon> responList;

    public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public List<Respon> getResponList() {
		return responList;
	}

	public void setResponList(List<Respon> responList) {
		this.responList = responList;
	}

	public String getFromPicPath() {
		return fromPicPath;
	}

	public void setFromPicPath(String fromPicPath) {
		this.fromPicPath = fromPicPath;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

    public Long getCommentNo() {
        return commentNo;
    }

    public void setCommentNo(Long commentNo) {
        this.commentNo = commentNo;
    }

    public Long getRecallNo() {
        return recallNo;
    }

    public void setRecallNo(Long recallNo) {
        this.recallNo = recallNo;
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
}