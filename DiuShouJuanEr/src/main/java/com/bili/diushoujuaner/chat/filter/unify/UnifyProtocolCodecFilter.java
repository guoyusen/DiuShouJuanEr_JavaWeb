package com.bili.diushoujuaner.chat.filter.unify;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.nio.ByteBuffer;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.WriteRequest;
import org.apache.mina.filter.codec.ProtocolCodecFilter;

import com.bili.diushoujuaner.common.ConstantUtils;

public class UnifyProtocolCodecFilter extends ProtocolCodecFilter {

	public UnifyProtocolCodecFilter(UnifyProtocolCodecFactory factory) {
		super(factory);
	}

	/**
	 * 父类获取待buffer消息后，再向下传递。 在这里去判断该消息来自于哪种客户端 每一个session连接之后只会执行一次
	 */
	@Override
	public void messageReceived(NextFilter nextFilter, IoSession session,
			Object message) throws Exception {
		// 初始请求，将请求的客户端类型存入session中
		if (!session.containsAttribute(ConstantUtils.DEVICE_TYPE)) {
			IoBuffer in = (IoBuffer) message;
			getClientType(in.buf(), session);
		}
		super.messageReceived(nextFilter, session, message);
	}

	private void getClientType(ByteBuffer buffer, IoSession session) {
		String requestInfo = new String(buffer.array(), 0, buffer.limit(),
				UTF_8);
		if (requestInfo.contains(ConstantUtils.ATTR_CLIENT_CODE)) {
			session.setAttribute(ConstantUtils.DEVICE_TYPE,
					ConstantUtils.DEVICE_BROWSER);
		} else {
			session.setAttribute(ConstantUtils.DEVICE_TYPE,
					ConstantUtils.DEVICE_ANDROID);
		}
	}

	@Override
	public void messageSent(NextFilter nextFilter, IoSession session,
			WriteRequest writeRequest) throws Exception {
		//在此屏蔽掉其他类型的数据回传，或者也可以全部屏蔽掉所有的已发送回传
		if (writeRequest.getMessage() instanceof String)
			super.messageSent(nextFilter, session, writeRequest);
	}

}
