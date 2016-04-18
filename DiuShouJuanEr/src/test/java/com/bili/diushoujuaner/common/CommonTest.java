package com.bili.diushoujuaner.common;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bili.diushoujuaner.common.CommonUtils;
import com.bili.diushoujuaner.common.entity.MessageDto;
import com.bili.diushoujuaner.common.pinyin.PinyinComparator;
import com.bili.diushoujuaner.database.model.ContactVo;

public class CommonTest {

	public static void main(String[] args) {
//		getcontactsVoListSort();
		testObjectJson();
	}
	
	
	public static void getcontactsVoListSort(){
		List<ContactVo> list = new ArrayList<>();
		ContactVo f = new ContactVo();
		f.setDisplayName("郭");
		list.add(f);
		f = new ContactVo();
		f.setDisplayName("a");
		list.add(f);
		f = new ContactVo();
		f.setDisplayName("不");
		list.add(f);
		f = new ContactVo();
		f.setDisplayName("森");
		list.add(f);
		f = new ContactVo();
		f.setDisplayName("*");
		list.add(f);
		f = new ContactVo();
		f.setDisplayName("那");
		list.add(f);
		
		for(ContactVo contactsVo:list){
			System.out.println(contactsVo.getDisplayName());
		}
		Collections.sort(list, new PinyinComparator());//new ToSort() 根据需求定义排序  
	    System.out.println("排序后！！！！！！！！！");  
	    for(ContactVo contactsVo:list){
			System.out.println(contactsVo.getDisplayName() + " " + contactsVo.getDisplayName());
		}
	}
	
	
	public static void getTimeDifference(){
		try {
			System.out.println(CommonUtils.getBetweenToSeconds("2015-12-14 12:12:12", "2015-12-14 12:14:13"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public static void getCurrentTime(){
		System.out.println(CommonUtils.getCurrentTime_YYYYMMDD_HHMMSS());
	}
	
	public static void testObjectJson(){
		MessageDto msg = new MessageDto();
		msg.setMsgContent("今天天气不错");
		msg.setMsgTime("2015-12-10 12:30:45");
		msg.setMsgType(ConstantUtils.CHAT_FRI);
		msg.setReceiverNo(0);;
		msg.setSenderNo(1);;
		
		Object o = msg;
		((MessageDto)o).getMsgType();
		String content = CommonUtils.getJSONStringFromObject(msg);
		System.out.println(content);
		
		MessageDto msg_1 = CommonUtils.getObjectFromJSONString(content);
		msg_1.setMsgContent("====");
		System.out.println(msg_1.toString());
	}

}
