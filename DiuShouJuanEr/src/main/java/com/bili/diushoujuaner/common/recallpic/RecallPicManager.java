package com.bili.diushoujuaner.common.recallpic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

import com.bili.diushoujuaner.common.CommonUtils;
import com.bili.diushoujuaner.common.ConstantUtils;
import com.bili.diushoujuaner.database.model.Picture;

public class RecallPicManager {

	private static Map<String,Map<String,Picture>> pictureMap = new HashMap<>();
	
	public static Picture storePictureToLocal(long userNo, MultipartFile file, String deviceType){
		
		CommonUtils.createAlbumDirectoryByTime();
		
		String fileName = file.getOriginalFilename();
		String filePath = CommonUtils.getAlbumDirectory() + "/" + System.currentTimeMillis() + CommonUtils.getSuffixFromFileName(fileName);
		
		Picture picture = new Picture();
		picture.setPicPath(filePath);
		picture.setRealPath(CommonUtils.getRootDirectory() + filePath);
		
		File localFile = new File(picture.getRealPath());
		
		try {
			file.transferTo(localFile);
		} catch (Exception e) {
			e.printStackTrace();
			return picture;
		} 
		
		setPictureSizeAndOffSet(picture);
		addPicture(userNo + deviceType, picture);
		
		return picture;
		
	}
	
	public static Map<String,Picture> getPictureMap(String userKey){
		return pictureMap.get(userKey);
	}
	
	public static void clearUserPicture(String userKey, boolean deleteFromLocal){
		if(pictureMap.get(userKey) == null){
			return;
		}
		
		if(deleteFromLocal){
			File localFile = null;
			for(Picture picture : pictureMap.get(userKey).values()){
				localFile = new File(picture.getRealPath());
				if(localFile.exists()){
					localFile.delete();
				}
			}	
		}
		
		pictureMap.remove(userKey);
	}
	
	public static boolean removePicture(String userKey, long picId){
		if(pictureMap.get(userKey) != null){
			Picture picture = pictureMap.get(userKey).remove(picId + "");
			File file = new File(picture.getRealPath());
			if(file.exists()){
				file.delete();
				return true;
			}
		}
		return false;
	}
	
	private static void addPicture(String userKey, Picture picture){
		if(pictureMap.get(userKey) != null){
			
			picture.setPicId(pictureMap.get(userKey).size());
			pictureMap.get(userKey).put(picture.getPicId() + "", picture);
			
		}else{
			Map<String,Picture> tmpMap = new HashMap<>();
			picture.setPicId(0);
			tmpMap.put(picture.getPicId() + "", picture);
			
			pictureMap.put(userKey, tmpMap);
		}
	}
	
	private static void setPictureSizeAndOffSet(Picture picture){
		InputStream is = null;
		try {
			is = new FileInputStream(picture.getRealPath());
			BufferedImage sourceImg = ImageIO.read(is);
			
			float tmpHeight = sourceImg.getHeight(), tmpWidth = sourceImg.getWidth();
			
			if(tmpWidth / tmpHeight <= ConstantUtils.RECALL_PIC_WIDTH / ConstantUtils.RECALL_PIC_HEIGHT){
				picture.setWidth((int) ConstantUtils.RECALL_PIC_WIDTH);
				picture.setOffSetTop((int) (-(tmpHeight * ConstantUtils.RECALL_PIC_WIDTH / tmpWidth - ConstantUtils.RECALL_PIC_HEIGHT) / 2));
			    
				picture.setHeight(0);
				picture.setOffSetLeft(0);
			}else{
				picture.setHeight((int) ConstantUtils.RECALL_PIC_HEIGHT);
				picture.setOffSetLeft((int) (-(tmpWidth * ConstantUtils.RECALL_PIC_HEIGHT / tmpHeight - ConstantUtils.RECALL_PIC_WIDTH) / 2));
			
				picture.setWidth(0);
				picture.setOffSetTop(0);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Picture picture = new Picture();
		picture.setRealPath("D:/ServerData/album/2016/01/18/1.jpg");
		setPictureSizeAndOffSet(picture);
		addPicture("10001Client/Borwser", picture);
		System.out.println(picture);
	}

}
