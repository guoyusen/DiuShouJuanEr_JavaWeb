package com.bili.diushoujuaner.server;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.logging.LoggingFilter;

import com.bili.diushoujuaner.chat.filter.keepAlive.CustomKeepAliveFilter;
import com.bili.diushoujuaner.chat.filter.keepAlive.KeepAliveMessageFactoryImpl;
import com.bili.diushoujuaner.chat.filter.keepAlive.KeepAliveRequestTimeoutHandlerImpl;
import com.bili.diushoujuaner.chat.filter.unify.UnifyProtocolCodecFactory;
import com.bili.diushoujuaner.chat.filter.unify.UnifyProtocolCodecFilter;
import com.bili.diushoujuaner.chat.filter.websocket.WebsocketServerCodecFactory;
import com.bili.diushoujuaner.chat.filter.websocket.WebsocketServerDecoder;
import com.bili.diushoujuaner.chat.filter.websocket.WebsocketServerEncoder;
import com.bili.diushoujuaner.common.ConstantUtils;

public class FilterFactory {

	public static void addAllFilter(DefaultIoFilterChainBuilder chain) {
		chain.addLast("unifyFilter", getCustomProtocolCodecFilter());
		chain.addLast("heartBeatFilter", getKeepAliveFilter());
		chain.addLast("executorFilter", getExecutorFilter());
		chain.addLast("loggingFilter", getLoggingFilter());
	}

	/**
	 * 自定义数据解析Filter，包含Object和Text两种
	 */
	private static UnifyProtocolCodecFilter getCustomProtocolCodecFilter() {
		return new UnifyProtocolCodecFilter(new UnifyProtocolCodecFactory(
				new ObjectSerializationCodecFactory(),
				new WebsocketServerCodecFactory(
						new WebsocketServerEncoder(),
						new WebsocketServerDecoder()
					)
				));
	}

	/**
	 * 心跳Filter
	 */
	private static CustomKeepAliveFilter getKeepAliveFilter() {
		CustomKeepAliveFilter heartBeat = new CustomKeepAliveFilter(
				new KeepAliveMessageFactoryImpl(), IdleStatus.BOTH_IDLE,
				new KeepAliveRequestTimeoutHandlerImpl());
		heartBeat.setRequestInterval(60);//每12秒发送一次ping包
		heartBeat.setRequestTimeout(ConstantUtils.IDLE_TIMEOUT_FOR_REQUEST);//5秒内没有收到pong包，出发TimeOutHandler去处理
		return heartBeat;
	}

	private static ExecutorFilter getExecutorFilter() {
		return new ExecutorFilter();
	}

	private static LoggingFilter getLoggingFilter() {
		return new LoggingFilter();
	}

}
