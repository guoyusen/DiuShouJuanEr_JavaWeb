package com.bili.diushoujuaner.common;

public interface ConstantUtils {
	
	long SYSTEM_ID_LONG = 10000;
	String SYSTEM_ID_STRING= "10000";
	// 心跳频率 15s/次
	int HEARTBEATRATE = 15;
	int IDEL_TIMEOUT_FOR_INTERVAL = 12;
	int IDLE_TIMEOUT_FOR_REQUEST = 5;
	// 返回结果的retCode
	String SUCCESS = "success";
	String FAIL = "fail";
	String ERROR = "error";
	// 发送验证码的类型
	short VERIFY_CODE_FOR_REG = 1;
	short VERIFY_CODE_FOR_RET = 2;
	// 客户端的类型
	String DEVICE_TYPE = "deviceType";
	short DEVICE_UNKNOW = 0;
	short DEVICE_BROWSER = 1;
	short DEVICE_ANDROID = 2;
	// 判断客户端类型的CODE
	String ATTR_CLIENT_CODE = "GET";
	// session中存储的用户UserNo的键
	String ATTR_USERNO= "UserNo";
	// session接收数据的缓存,1M
	int SESSION_CACHE_SIZE = 1048576;
	// 聊天的消息类型
	short CHAT_INIT = -1;
	short CHAT_PING = 0;
	short CHAT_PONG = 1;
	short CHAT_FRI = 2;
	short CHAT_TIME = 3;
	short CHAT_CLOSE = 4;
	short CHAT_PAR = 5;
	short CHAT_GOOD = 6;
	// 聊天消息中content的类型
	short CONTENT_EMPTY = 0;
	short CONTENT_TEXT = 1;
	short CONTENT_IMG = 2;
	short CONTENT_VOICE = 3;
	// 用户空间的访问类型
	short USER_SPACE_VISIT_ALL = 1;
	short USER_SPACE_VISIT_SELF = 2;
	short USER_SPACE_VISIT_FRIEND = 3;
	// 验证码超时时间60s
	int MSG_TIME_LIMIT_60 = 60;
	// 通讯录身份类型
	int CONTACT_PARTY = 1;
	int CONTACT_FRIEND = 2;
	// 用户获取Recall的类型，所有的 || 单个用户所有的
	int RECALL_ALL = 1;
	int RECALL_USER = 2;
	
	float RECALL_PIC_WIDTH = 176;
	float RECALL_PIC_HEIGHT = 114;
}
