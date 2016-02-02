package com.bili.diushoujuaner.chat.filter.keepAlive;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.WriteRequest;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;


public class CustomKeepAliveFilter extends
		org.apache.mina.filter.keepalive.KeepAliveFilter {

	public CustomKeepAliveFilter(KeepAliveMessageFactory messageFactory,
			IdleStatus interestedIdleStatus,
			KeepAliveRequestTimeoutHandler polic) {
		super(messageFactory, interestedIdleStatus, polic);
	}

	@Override
	public void messageReceived(NextFilter nextFilter, IoSession session,
			Object message) throws Exception {
		// 过滤非String的数据
		if (getPermitForward(message))
			super.messageReceived(nextFilter, session, message);
	}

	@Override
	public void messageSent(NextFilter nextFilter, IoSession session,
			WriteRequest writeRequest) throws Exception {
		if (getPermitForward(writeRequest.getMessage()))
			super.messageSent(nextFilter, session, writeRequest);
	}

	/**
	 * 过滤掉上层不需要的数据
	 * @param message
	 * @return
	 */
	private boolean getPermitForward(Object message) {
		String messageString = message.toString();
		if (messageString.substring(0, 1).equals("{") && messageString.substring(messageString.length() - 1,messageString.length()).equals("}")) {
			return true;
		} else {
			return false;
		}
	}

}
