package com.bili.diushoujuaner.common;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class FileUtil {
	private FileUtil(){
		
	}
	
	public static List<MultipartFile> getMultipartFileList(HttpServletRequest request){
		
		List<MultipartFile> fileList = new ArrayList<MultipartFile>();
		
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		
		if(multipartResolver.isMultipart(request)){
			
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			
			Iterator<String> nameList = multipartRequest.getFileNames();
			while(nameList.hasNext()){
				String name = nameList.next();
				System.out.println(name+": "+multipartRequest.getFile(name).getOriginalFilename());
				fileList.add(multipartRequest.getFile(name));
			}
		}
		return fileList;
	}
	
}
