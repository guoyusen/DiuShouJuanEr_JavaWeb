package com.bili.diushoujuaner.common;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bili.diushoujuaner.common.entity.MessageDto;
import com.bili.diushoujuaner.common.entity.ResponseDto;
import com.bili.diushoujuaner.common.session.CustomSessionManager;
import com.bili.diushoujuaner.common.springcontext.SpringContextUtil;
import com.bili.diushoujuaner.database.model.CommonInfo;
import com.bili.diushoujuaner.database.model.CustomSession;
import com.bili.diushoujuaner.database.model.OffMsg;
import com.bili.diushoujuaner.database.model.Picture;
import com.bili.diushoujuaner.mgt.CommonInfoMgt;
import com.bili.diushoujuaner.mgt.OffMsgMgt;

public class CommonUtils {

	private static Random random = new Random();

	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
	private static SimpleDateFormat sdf_Full = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS", Locale.CHINA);

	public static String getCurrentTimeFull(){
        return sdf_Full.format(new Date());
    }
	
	public static int getMilliDifferenceBetweenTime(String start){
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try{
            c1.setTime(sdf_Full.parse(start));
            c2.setTime(sdf_Full.parse(getCurrentTimeFull()));
            return (int)Math.abs(c1.getTimeInMillis() - c2.getTimeInMillis());
        }catch(ParseException pe){
            return 0;
        }
    }
	
	/**
	 * 获取消息返回实体
	 * @param retCode
	 * @param message
	 * @param data
	 * @return ResponseDto
	 */
	public static ResponseDto getResponse(String retCode, String message,
			Object data) {
		
		ResponseDto res = new ResponseDto();
		res.setRetCode(retCode);
		res.setData(data);
		res.setMessage(message);
		return res;
		
	}
	
	/**
     * 判断字符串是否为空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
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
	public static MessageDto getObjectFromJSONString(String jsonString) {
		return JSONObject.toJavaObject(JSON.parseObject(jsonString),
				MessageDto.class);
	}

	/**
	 * 判断得到的消息是否是心跳请求包
	 * @param message
	 * @return boolean
	 */
	public static boolean isMessageForHeartBeat(Object message) {
		MessageDto msg = getObjectFromJSONString(message.toString());
		return msg.getMsgType() == ConstantUtils.CHAT_PONG;
	}
	
	public static String getSerialNo(){
        return UUID.randomUUID().toString();
    }
	
	public static MessageDto getCloneMessageDto(MessageDto msg){
		MessageDto messageDto = null;
		try {
		    messageDto = msg.clone();
			messageDto.setSerialNo(getSerialNo());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return messageDto;
	}

	/**
	 * 获得空消息包。
	 * @param chatType 空消息的消息类型
	 * @return getJSONStringFromObject(message)
	 */
	public static String getEmptyMessage(short chatType) {
		MessageDto message = new MessageDto();
		message.setMsgContent("");
		message.setMsgTime("");
		message.setMsgType(chatType);
		message.setReceiverNo(0);
		message.setSenderNo(0);
		return getJSONStringFromObject(message);
	}
	
	public static String getSerialMessage(String serialNo){
		MessageDto message = new MessageDto();
		message.setSerialNo(serialNo);
		message.setMsgContent("");
		message.setMsgTime("");
		message.setMsgType(ConstantUtils.CHAT_STATUS);
		message.setReceiverNo(0);
		message.setSenderNo(0);
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
	 * 获得头像的路径
	 */
	public static String getHeadDirectory(){
		return "images/head/" + getCurrentTimeDirectory();
	}
	
	/**
	 * 获得相册的路径
	 */
	public static String getAlbumDirectory(){
		return "images/album/" + getCurrentTimeDirectory();
	}
	
	/**
	 * 获得壁纸的路径
	 */
	public static String getWallPaperDirectory(){
		return "images/wallpaper/" + getCurrentTimeDirectory();
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
	 * 生成对应于相册的目录
	 */
	public static boolean createAlbumDirectoryByTime(){
		File tmpFile = new File(getRootDirectory() + getAlbumDirectory());
		if(!tmpFile.exists()){
			return tmpFile.mkdirs();
		}
		return true;
	}
	
	/**
	 * 生成对应于头像的目录
	 */
	public static boolean createHeadDirectoryByTime(){
		File tmpFile = new File(getRootDirectory() + getHeadDirectory());
		if(!tmpFile.exists()){
			return tmpFile.mkdirs();
		}
		return true;
	}
	
	/**
	 * 生成对应于壁纸的目录
	 */
	public static boolean createWallPaperDirectoryByTime(){
		File tmpFile = new File(getRootDirectory() + getWallPaperDirectory());
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
	
	public static String getFileTypeFromFileName(String fileName){
		if(fileName.indexOf('.') < 0){
			return "";
		}else{
			return fileName.substring(fileName.lastIndexOf('.')+1);
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
	
	public static long getLongFromString(String value){
		long result = 0;
		if(isEmpty(value)){
			result = 0;
		}
		try{
			result = Long.parseLong(value);
		}catch(Exception e){
			result = 0;
		}finally{
		}
		return result;
	}
	
	public static String processWallPaper(MultipartFile file){
		createWallPaperDirectoryByTime();
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(getWallPaperDirectory());
		stringBuilder.append("/");
		stringBuilder.append(System.currentTimeMillis() + "");
		stringBuilder.append(getSuffixFromFileName(file.getOriginalFilename()));
		
		return processSquareImage(stringBuilder.toString(), file);
	}
	
	public static String processHeadImage(MultipartFile file){
		createHeadDirectoryByTime();
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(getHeadDirectory());
		stringBuilder.append("/");
		stringBuilder.append(System.currentTimeMillis() + "");
		stringBuilder.append(getSuffixFromFileName(file.getOriginalFilename()));
		
		return processSquareImage(stringBuilder.toString(), file);
	}
	
	private static String processSquareImage(String filePath, MultipartFile file){
		ImageInputStream iis;
		try {
			Iterator<ImageReader> it = ImageIO  
                    .getImageReadersByFormatName(getFileTypeFromFileName(filePath));  
            ImageReader reader = it.next();  
            iis = ImageIO.createImageInputStream(file.getInputStream()); 
            reader.setInput(iis, true);  
            ImageReadParam param = reader.getDefaultReadParam();  
			
			BufferedImage src = ImageIO.read(file.getInputStream());
			int width = src.getWidth(); // 得到源图宽  
			int height = src.getHeight(); // 得到源图长  
			
			int tmpEageLength = width > height ? height : width;
			tmpEageLength = tmpEageLength > ConstantUtils.IMAGE_HEAD_EAGE ?  ConstantUtils.IMAGE_HEAD_EAGE : tmpEageLength;
			Rectangle rect = new Rectangle((width - tmpEageLength)/2, (height - tmpEageLength)/2, tmpEageLength, tmpEageLength);
			param.setSourceRegion(rect);  
			BufferedImage bi = reader.read(0, param);  
 
            ImageIO.write(bi, getFileTypeFromFileName(filePath), new File(getRootDirectory() + filePath));// 输出到文件流  
            
            if(iis != null){
				iis.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return filePath;
	}
	
	public static Picture processRecallImage(Picture picture, MultipartFile file){
		try {
			BufferedImage src = ImageIO.read(file.getInputStream());
			double width = src.getWidth(); // 得到源图宽  
			double height = src.getHeight(); // 得到源图长  
            
			double scale = (ConstantUtils.IMAGE_RECALL_HEIGHT * ConstantUtils.IMAGE_RECALL_WIDTH) / (width * height);
            scale = Math.sqrt(Double.valueOf(scale + ""));
            width = width * scale;  
            height = height * scale;    
            
            Image image = src.getScaledInstance((int)width, (int)height, Image.SCALE_DEFAULT);  
            BufferedImage tag = new BufferedImage((int)width, (int)height, BufferedImage.TYPE_INT_RGB);  
            Graphics g = tag.getGraphics();  
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图  
            g.dispose();
            
            ImageIO.write(tag, getFileTypeFromFileName(picture.getRealPath()), new File(picture.getRealPath()));// 输出到文件流  
            
            if(width / height <= ConstantUtils.RECALL_PIC_WIDTH / ConstantUtils.RECALL_PIC_HEIGHT){
				picture.setWidth((int) ConstantUtils.RECALL_PIC_WIDTH);
				picture.setOffSetTop((int) (-(height * ConstantUtils.RECALL_PIC_WIDTH / width - ConstantUtils.RECALL_PIC_HEIGHT) / 2));
			    
				picture.setHeight(0);
				picture.setOffSetLeft(0);
			}else{
				picture.setHeight((int) ConstantUtils.RECALL_PIC_HEIGHT);
				picture.setOffSetLeft((int) (-(width * ConstantUtils.RECALL_PIC_HEIGHT / height - ConstantUtils.RECALL_PIC_WIDTH) / 2));
			
				picture.setWidth(0);
				picture.setOffSetTop(0);
			}
            
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		
		return picture;
	}
	
	public static boolean isDeviceTypelegal(String deviceType){
		return deviceType.equals("Client/Browser") || deviceType.equals("Client/Android");
	}
	
	public static OffMsg saveOffMessage(MessageDto msg, long receiverNo){
		OffMsg offMsg = new OffMsg();
		
		offMsg.setToNo(receiverNo);
		offMsg.setFromNo(msg.getSenderNo());
		offMsg.setContent(msg.getMsgContent());
		offMsg.setConType(msg.getConType());
		offMsg.setMsgType(msg.getMsgType());
		offMsg.setIsRead(false);
		offMsg.setTime(msg.getMsgTime());
		
		OffMsgMgt offMsgMgt = (OffMsgMgt)SpringContextUtil.getBean("offMsgMgtImpl");
		
		offMsg = offMsgMgt.putOffMsgByRecord(offMsg);
		
		return offMsg;
	}
	
	public static void processSystemMessageStore(MessageDto msg, List<Long> offMemberList){
		for(Long item : offMemberList){
			saveOffMessage(msg, item);
		}
	}
	
	/**
	 * 处理离线消息存储
	 * @param session
	 * @param msg
	 */
	public static void processMessageStore(MessageDto msg, List<Long> offMemberList){
		
		OffMsg offMsg = saveOffMessage(msg, msg.getReceiverNo());
		
		if(offMemberList != null && offMsg != null){
			CommonInfoMgt commonInfoMgt = (CommonInfoMgt)SpringContextUtil.getBean("commonInfoMgtImpl");
			for(long memberNo : offMemberList){
				CommonInfo commonInfo = new CommonInfo();
				commonInfo.setIsRead(false);
				commonInfo.setOffMsgNo(offMsg.getOffMsgNo());
				commonInfo.setToNo(Long.valueOf(memberNo));
				
				commonInfoMgt.addCommonInfoByRecord(commonInfo);
			}
		}
	}
	
	public static String getLimitContent(String content, int length){
		if(content.length() <= length){
			return content;
		}
		return content.substring(0, length);
	}

}
