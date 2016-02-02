package com.bili.diushoujuaner.chat.filter.keepAlive;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

import com.bili.diushoujuaner.common.CommonUtils;
import com.bili.diushoujuaner.common.ConstantUtils;

public class KeepAliveMessageFactoryImpl implements KeepAliveMessageFactory {

	@Override
	public boolean isRequest(IoSession session, Object message) {
		return false;
	}

	@Override
	public boolean isResponse(IoSession session, Object message) {
		return CommonUtils.isMessageForHeartBeat(message);
	}

	@Override
	public Object getRequest(IoSession session) {
		System.out.println("发送心跳包");
		return CommonUtils.getEmptyMessage(ConstantUtils.CHAT_PING);
	}

	@Override
	public Object getResponse(IoSession session, Object request) {
		return null;
	}

}