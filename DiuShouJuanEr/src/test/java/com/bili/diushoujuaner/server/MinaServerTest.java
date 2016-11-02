package com.bili.diushoujuaner.server;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.springframework.stereotype.Service;

import com.bili.diushoujuaner.chat.handler.MinaServerHandler;
import com.bili.diushoujuaner.common.ConstantUtils;

@Service
public class MinaServerTest {

	public static void main(String[] args) throws IOException {
		SocketAcceptor acceptor = new NioSocketAcceptor();

		FilterFactory.addAllFilter(acceptor.getFilterChain());
		
		SocketSessionConfig sessionConfig = acceptor.getSessionConfig();
		sessionConfig.setReceiveBufferSize(ConstantUtils.SESSION_CACHE_SIZE);
		sessionConfig.setKeepAlive(true);

		acceptor.setReuseAddress(true);
		acceptor.setHandler(new MinaServerHandler());
		acceptor.bind(new InetSocketAddress(1314));
	}

}
