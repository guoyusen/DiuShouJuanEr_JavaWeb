package com.bili.diushoujuaner.chat.filter.unify;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;

import com.bili.diushoujuaner.chat.filter.websocket.WebsocketServerCodecFactory;
import com.bili.diushoujuaner.chat.iosession.IOSessionManager;

public class UnifyProtocolCodecFactory implements ProtocolCodecFactory {

	ObjectSerializationCodecFactory objectSerializationCodecFactory;
	WebsocketServerCodecFactory websocketServerCodecFactory;

	public UnifyProtocolCodecFactory(ObjectSerializationCodecFactory objectSerializationCodecFactory, WebsocketServerCodecFactory websocketServerCodecFactory) {
		this.objectSerializationCodecFactory = objectSerializationCodecFactory;
		this.websocketServerCodecFactory = websocketServerCodecFactory;
	}

	@Override
	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		if (IOSessionManager.isSessionMobile(session)) {
			return objectSerializationCodecFactory.getEncoder(session);
		} else {
			return websocketServerCodecFactory.getEncoder(session);
		}
	}

	@Override
	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		if (IOSessionManager.isSessionMobile(session)) {
			return objectSerializationCodecFactory.getDecoder(session);
		} else {
			return websocketServerCodecFactory.getDecoder(session);
		}
	}

}
