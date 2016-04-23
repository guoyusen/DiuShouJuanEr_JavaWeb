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
	short VERIFY_CODE_REGIST = 1;
	short VERIFY_CODE_RESET = 2;
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
	short CHAT_STATUS = 7;//消息的状态
	short CHAT_PARTY_NAME = 8;//群广播-群名称
	short CHAT_PARTY_HEAD = 9;//群广播-头像
	short CHAT_PARTY_MEMBER_UPDATE = 10;//群成员增删
	short CHAT_PARTY_UNGROUP = 11;//群解散
	short CHAT_PARTY_INTRODUCE = 12;//修改群介绍
	short CHAT_PARTY_MEMBER_NAME = 13;//群成员修改自己的群名片
	// 聊天消息中content的类型
	short CONTENT_EMPTY = 0;
	short CONTENT_TEXT = 1;
	short CONTENT_IMG = 2;
	short CONTENT_VOICE = 3;
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
	
	int IMAGE_RECALL_WIDTH = 800;
	int IMAGE_RECALL_HEIGHT = 500;
	int IMAGE_HEAD_EAGE = 800;
	
	//消息的发送状态
	int MESSAGE_STATUS_SENDING = 0;
    int MESSAGE_STATUS_FAIL = 1;
    int MESSAGE_STATUS_SUCCESS = 2;
    
    int MAX_SAND_TIMES = 3;// 最大重发次数
    int MAX_BETWEEN_TIME = 5000;// 超过5000毫秒没有接收到则认为发送失败
}
