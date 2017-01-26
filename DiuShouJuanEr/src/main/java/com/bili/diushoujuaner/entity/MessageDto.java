package com.bili.diushoujuaner.entity;

import java.io.Serializable;

/**
 * 聊天的消息实体
 * @author BILI
 */
public class MessageDto implements Serializable, Cloneable{

	private static final long serialVersionUID = 1L;
	
	private String serialNo;//标志唯一一个消息；在一个消息发送之后，通过反馈的序列号值判断消息是否发送成功；如果三次没有发送成功，存入离线信息表
	private long senderNo;
	private long receiverNo;
	private short msgType;
	private short conType;
	private String msgContent = "";
	private String msgTime = "";
	
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
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
	public MessageDto clone() throws CloneNotSupportedException {
		MessageDto messageDto =  (MessageDto) super.clone();
		return messageDto;
	}
	@Override
	public String toString() {
		return "Message [serialNo=" + serialNo + ", senderNo=" + senderNo + ", receiverNo=" + receiverNo + ", msgType="
				+ msgType + ", conType=" + conType + ", msgContent=" + msgContent + ", msgTime=" + msgTime + "]";
	}
	
}