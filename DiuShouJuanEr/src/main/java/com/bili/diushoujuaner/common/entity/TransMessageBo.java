package com.bili.diushoujuaner.common.entity;

import org.apache.mina.core.session.IoSession;

import com.bili.diushoujuaner.common.CommonUtils;
import com.bili.diushoujuaner.common.ConstantUtils;

public class TransMessageBo {
	
	private MessageDto messageDto;
    private String lastTime;
    private int sendCount;
    private int status;
    private IoSession session;
    
    public TransMessageBo(MessageDto messageDto, IoSession session) {
        this.lastTime = CommonUtils.getCurrentTimeFull();
        this.messageDto = messageDto;
        this.sendCount = 1;
        this.status = ConstantUtils.MESSAGE_STATUS_SENDING;
        this.session = session;
    }

	public IoSession getSession() {
		return session;
	}

	public void setSession(IoSession session) {
		this.session = session;
	}

	public MessageDto getMessageDto() {
		return messageDto;
	}

	public void setMessageDto(MessageDto messageDto) {
		this.messageDto = messageDto;
	}

	public String getLastTime() {
		return lastTime;
	}

	public void reSetLastTime() {
        this.lastTime = CommonUtils.getCurrentTimeFull();
    }

	public int getSendCount() {
		return sendCount;
	}

	public void setSendCount(int sendCount) {
		this.sendCount = sendCount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
