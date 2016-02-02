package com.bili.diushoujuaner.chat.message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 聊天的消息实体
 * @author BILI
 */
public class Message implements Serializable{

	private static final long serialVersionUID = 1L;
	private String senderAcc = "";
	private List<String> receiverAcc = new ArrayList<>();
	private short msgType;
	private short conType;
	private String msgContent = "";
	private String msgTime = "";
	public String getSenderAcc() {
		return senderAcc;
	}
	public void setSenderAcc(String senderAcc) {
		this.senderAcc = senderAcc;
	}
	public List<String> getReceiverAcc() {
		return receiverAcc;
	}
	public void setReceiverAcc(List<String> receiverAcc) {
		this.receiverAcc = receiverAcc;
	}
	public short getConType() {
		return conType;
	}
	public void setConType(short conType) {
		this.conType = conType;
	}
	public short getMsgType() {
		return msgType;
	}
	public void setMsgType(short msgType) {
		this.msgType = msgType;
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
		return "Message [senderAcc=" + senderAcc + ", receiverAcc="
				+ receiverAcc + ", msgType=" + msgType + ", msgContent="
				+ msgContent + ", msgTime=" + msgTime + "]";
	}
}