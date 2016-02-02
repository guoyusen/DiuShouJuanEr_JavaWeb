package com.bili.diushoujuaner.chat.filter.keepAlive;

import org.apache.mina.core.future.CloseFuture;
import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;

import com.bili.diushoujuaner.chat.iosession.IOSessionManager;

/**
 * 心跳超时处理器
 */
public class KeepAliveRequestTimeoutHandlerImpl implements
		KeepAliveRequestTimeoutHandler {
	@Override
	public void keepAliveRequestTimedOut(KeepAliveFilter filter,IoSession session) throws Exception {
		IOSessionManager.removeSession(session);
		CloseFuture closeFuture = session.close(true);
		closeFuture.addListener(new IoFutureListener<IoFuture>() {
			@Override
			public void operationComplete(IoFuture future) {
				if (future instanceof CloseFuture) {
					((CloseFuture) future).setClosed();
					System.out.println("用户退出");
				}
			}
		});
	}

}
