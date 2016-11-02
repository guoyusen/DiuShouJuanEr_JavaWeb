package com.bili.diushoujuaner.database.model;

public class Picture {
    private Long pictureNo;

    private Long recallNo;

    private String picPath;

    private Integer height;

    private Integer width;

    private Integer offSetTop;

    private Integer offSetLeft;
    
    private String realPath;
    
    private int picId;
    
    private String serial;

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getRealPath() {
		return realPath;
	}

	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}

	public int getPicId() {
		return picId;
	}

	public void setPicId(int picId) {
		this.picId = picId;
	}

	public Long getPictureNo() {
        return pictureNo;
    }

    public void setPictureNo(Long pictureNo) {
        this.pictureNo = pictureNo;
    }

    public Long getRecallNo() {
        return recallNo;
    }

    public void setRecallNo(Long recallNo) {
        this.recallNo = recallNo;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath == null ? null : picPath.trim();
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getOffSetTop() {
        return offSetTop;
    }

    public void setOffSetTop(Integer offSetTop) {
        this.offSetTop = offSetTop;
    }

    public Integer getOffSetLeft() {
        return offSetLeft;
    }

    public void setOffSetLeft(Integer offSetLeft) {
        this.offSetLeft = offSetLeft;
    }
}