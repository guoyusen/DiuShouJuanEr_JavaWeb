package com.bili.diushoujuaner.chat.iosession;

import java.util.HashMap;
import java.util.Map;

import org.apache.mina.core.session.IoSession;

import com.bili.diushoujuaner.common.ConstantUtils;
import com.bili.diushoujuaner.common.entity.MessageDto;

public class IOSessionManager {

	/***
	 * 所有用户 的session管理,具体分为浏览器和移动客户端两个mapSession 删除、添加、查询，在删除和添加要使用锁机制
	 */
	private static Map<Long, IoSession> mapSessionMobile = new HashMap<Long, IoSession>();
	private static Map<Long, IoSession> mapSessionBrowser = new HashMap<Long, IoSession>();

	public static void addSession(IoSession session) throws Exception {
		if (isSessionMobile(session)) {
			synchronized (mapSessionMobile) {
				mapSessionMobile.put(getUserNoFromIoSessionToLong(session), session);
			}
		} else {
			synchronized (mapSessionBrowser) {
				mapSessionBrowser.put(getUserNoFromIoSessionToLong(session), session);
			}
		}
	}
	
	public static IoSession getSessionMobile(long sessionId){
		return mapSessionMobile.get(sessionId);
	}
	
    public static IoSession getSessionBrowser(long sessionId){
    	return mapSessionBrowser.get(sessionId);
	}

	public static void removeSession(IoSession session) throws Exception {
		if(isSessionMobile(session)){
			mapSessionMobile.remove(getUserNoFromIoSessionToLong(session));
		}else{
			mapSessionBrowser.remove(getUserNoFromIoSessionToLong(session));
		}
	}
	
	public static long getUserNoFromIoSessionToLong(IoSession session){
		if (!session.containsAttribute(ConstantUtils.ATTR_USERNO)) {
			return 0;
		}else{
			return Long.valueOf(session.getAttribute(ConstantUtils.ATTR_USERNO).toString());
		}
	}
	
	public static String getUserNoFromIoSessionToString(IoSession session){
		if (!session.containsAttribute(ConstantUtils.ATTR_USERNO)) {
			return "";
		}else{
			return session.getAttribute(ConstantUtils.ATTR_USERNO).toString();
		}
	}

	public static boolean isSessionMobile(IoSession session) {
		if (!session.containsAttribute(ConstantUtils.DEVICE_TYPE)) {
			throw new RuntimeException("会话没有设置客户端类型");
		} else if (Short.valueOf(session.getAttribute(ConstantUtils.DEVICE_TYPE).toString()) == ConstantUtils.DEVICE_ANDROID) {
			return true;
		} else if (Short.valueOf(session.getAttribute(ConstantUtils.DEVICE_TYPE).toString()) == ConstantUtils.DEVICE_BROWSER) {
			return false;
		}
		return false;
	}
	
	public static void broadCastToAllSession(MessageDto message){
	
	}

}
