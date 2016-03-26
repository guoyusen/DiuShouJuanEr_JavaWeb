package com.bili.diushoujuaner.chat.message;

import java.io.Serializable;

/**
 * 聊天的消息实体
 * @author BILI
 */
public class Message implements Serializable{

	private static final long serialVersionUID = 1L;
	private long senderNo;
	private long receiverNo;
	private short msgType;
	private short conType;
	private String msgContent = "";
	private String msgTime = "";
	public long getSenderNo() {
		return senderNo;
	}
	public void setSenderNo(long senderNo) {
		this.senderNo = senderNo;
	}
	public long getReceiverNo() {
		return receiverNo;
	}
	public void setReceiverNo(long receiverNo) {
		this.receiverNo = receiverNo;
	}
	public short getMsgType() {
		return msgType;
	}
	public void setMsgType(short msgType) {
		this.msgType = msgType;
	}
	public short getConType() {
		return conType;
	}
	public void setConType(short conType) {
		this.conType = conType;
	}
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	public String getMsgTime() {
		return msgTime;
	}
	public void setMsgTime(String msgTime) {
		this.msgTime = msgTime;
	}
	@Override
	public String toString() {
		return "Message [senderNo=" + senderNo + ", receiverNo=" + receiverNo + ", msgType=" + msgType + ", conType="
				+ conType + ", msgContent=" + msgContent + ", msgTime=" + msgTime + "]";
	}
	
	
}