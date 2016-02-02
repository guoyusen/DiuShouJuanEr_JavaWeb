package com.bili.diushoujuaner.common;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bili.diushoujuaner.chat.message.Message;
import com.bili.diushoujuaner.common.dto.ResponseDto;
import com.bili.diushoujuaner.common.session.CustomSessionManager;
import com.bili.diushoujuaner.database.model.CustomSession;

public class CommonUtils {

	private static Random random = new Random();

	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	/**
	 * 获取消息返回实体
	 * @param retCode
	 * @param message
	 * @param data
	 * @return ResponseDto
	 */
	public static ResponseDto getResponse(String retCode, String message,
			Map<String, Object> data) {
		ResponseDto res = new ResponseDto();
		res.setRetCode(retCode);
		res.setData(data);
		res.setMessage(message);
		return res;
	}

	/**
	 * 获取随机验证码
	 * @return eg:1234
	 */
	public static String getverifycode() {
		String tmpCode = "";
		for (int i = 0; i < 4; i++) {
			tmpCode += random.nextInt(10);
		}
		return tmpCode;
	}

	/**
	 * 检查手机号格式
	 * @param mobile
	 * @return boolean
	 */
	public static boolean isMobile(String mobile) {
		Pattern p = Pattern.compile("^((13[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}$");  		  
	    Matcher m = p.matcher(mobile);  			  
		return m.matches();  
	}
	
	/**
	 * 根据AccessToken从Session工厂中得到对应的属性
	 * @param accessToken
	 * @return userNo(long)
	 */
	public static long getUserNoFromAccessToken(String accessToken){
		CustomSession customSession = CustomSessionManager.getCustomSession(accessToken);
		if(customSession != null){
			return customSession.getUserNo();
		}else{
			return 0;
		}
	}

	/**
	 * 将java对象转换成JSONString
	 * @param object
	 * @return JSONString
	 */
	public static String getJSONStringFromObject(Object object) {
		return JSONObject.toJSONString(object);
	}

	/**
	 * 将JSONString转成对应的Java对象
	 * @param JSONString
	 */
	public static Message getObjectFromJSONString(String jsonString) {
		return JSONObject.toJavaObject(JSON.parseObject(jsonString),
				Message.class);
	}

	/**
	 * 判断得到的消息是否是心跳包
	 * @param message
	 * @return boolean
	 */
	public static boolean isMessageForHeartBeat(Object message) {
		Message msg = getObjectFromJSONString(message.toString());
		return msg.getMsgType() == ConstantUtils.CHAT_PONG;
	}

	/**
	 * 获得空消息包。
	 * @param chatType 空消息的消息类型
	 * @return getJSONStringFromObject(message)
	 */
	public static String getEmptyMessage(short chatType) {
		Message message = new Message();
		message.setMsgContent("");
		message.setMsgTime("");
		message.setMsgType(chatType);
		message.setReceiverAcc(null);
		message.setSenderAcc("");
		return getJSONStringFromObject(message);
	}

	/**
	 * 获取当前的时间，日期格式为YYYY-MM-DD HH:MM:SS
	 */
	public static String getCurrentTime_YYYYMMDD_HHMMSS() {
		return simpleDateFormat.format(new Date());
	}
	
	/**
	 * 获得当前时间的路径
	 */
	public static String getCurrentTimeDirectory(){
		String tmpTime = getCurrentTime_YYYYMMDD_HHMMSS();
		tmpTime = tmpTime.substring(0,10);
		
		return tmpTime.substring(0,4) + "/" + tmpTime.substring(5,7) + "/" + tmpTime.substring(8);
	}
	
	/**
	 * 获得相册的路径
	 */
	public static String getAlbumDirectory(){
		return "images/album/" + getCurrentTimeDirectory();
	}
	
	/**
	 * 获得虚拟目录的父路径
	 * @return
	 */
	public static String getRootDirectory(){
		return "D:/ServerData/";
	}

	/**
	 * 获取两个时间的时间间隔，时间精确到秒
	 */
	public static int getBetweenToSeconds(String timeStart, String timeEnd)
			throws ParseException {
		int between = 0;
		Date begin, end;
		begin = simpleDateFormat.parse(timeStart);
		end = simpleDateFormat.parse(timeEnd);
		between = (int) (end.getTime() - begin.getTime()) / 1000;
		return between;
	}
	
	/**
	 * 生成对应于时间的相册路径
	 */
	public static boolean createAlbumDirectoryByTime(){
		File tmpFile = new File(getRootDirectory() + getAlbumDirectory());
		if(!tmpFile.exists()){
			return tmpFile.mkdirs();
		}
		return true;
	}
	
	/**
	 * 生成UUID作为AccessToken
	 * @return accessToken Eg:7484320b-aef8-4850-b798-30a6f4064ff3
	 */
	public static String getRandomAccessToken(){
		UUID accessToken = UUID.randomUUID();
		return accessToken.toString();
	}
	
	/**
	 * 通过request请求头中的Device-Type来判断客户端类型
	 * @param deviceType
	 * @return
	 */
	public static short getDeviceType(String deviceType){
		if(deviceType.contains("Browser")){
			return ConstantUtils.DEVICE_BROWSER;
		}else if(deviceType.contains("Android")){
			return ConstantUtils.DEVICE_ANDROID;
		}else{
			return ConstantUtils.DEVICE_UNKNOW;
		}
	}
	
	/**
	 * 得到文件名的后缀名
	 */
	public static String getSuffixFromFileName(String fileName){
		
		if(fileName.indexOf('.') < 0){
			return "";
		}else{
			return fileName.substring(fileName.lastIndexOf('.'));
		}
	}
	
	/**
	 * 根据文件路径进行删除
	 */
	public static void deleteFileFromPath(String filePath){
		
		File file = new File(filePath);
		if(file.exists()){
			file.delete();
		}
		
	}
	
}
