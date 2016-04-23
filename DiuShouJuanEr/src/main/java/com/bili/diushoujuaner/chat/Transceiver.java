package com.bili.diushoujuaner.chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.mina.core.session.IoSession;

import com.bili.diushoujuaner.chat.iosession.IOSessionManager;
import com.bili.diushoujuaner.common.CommonUtils;
import com.bili.diushoujuaner.common.ConstantUtils;
import com.bili.diushoujuaner.common.entity.MessageDto;
import com.bili.diushoujuaner.common.entity.TransMessageBo;
import com.bili.diushoujuaner.common.springcontext.SpringContextUtil;
import com.bili.diushoujuaner.database.mapper.OffMsgMapper;
import com.bili.diushoujuaner.database.model.CommonInfo;
import com.bili.diushoujuaner.database.model.OffMsg;
import com.bili.diushoujuaner.database.model.OffMsgExample;
import com.bili.diushoujuaner.mgt.CommonInfoMgt;
import com.bili.diushoujuaner.mgt.OffMsgMgt;

public class Transceiver extends Thread {

	private HashMap<String, TransMessageBo> transMessageBoHashMap;
	private List<TransMessageBo> transMessageBoList;
	private OffMsgMgt offMsgMgt = null;
	private CommonInfoMgt commonInfoMgt;
	private OffMsgMapper offMsgMapper = null;
	private static Transceiver transceiver = null;
	
	public void init() {
		transMessageBoHashMap = new HashMap<>();
		transMessageBoList = new ArrayList<>();
		System.out.println("收发器启动");
		transceiver = this;
		this.start();
	}
	
	public static synchronized Transceiver getInstance(){
		if(transceiver == null){
			transceiver = (Transceiver)SpringContextUtil.getBean("transceiver");
		}
		return transceiver;
	}
	
	public void addSendTask(MessageDto messageDto, IoSession session){
		synchronized (transMessageBoList){
            TransMessageBo transMessageBo = new TransMessageBo(messageDto, session);
            transMessageBoHashMap.put(messageDto.getSerialNo(), transMessageBo);
            transMessageBoList.add(transMessageBo);
            
            session.write(CommonUtils.getJSONStringFromObject(messageDto));
            transMessageBoList.notify();
        }
	}
	
	public void updateStatusSuccess(String serialNo){
		synchronized (transMessageBoList) {
			System.out.println("更新状态");
			transMessageBoHashMap.get(serialNo).setStatus(ConstantUtils.MESSAGE_STATUS_SUCCESS);
		}
	}

	@Override
	public void run() {
		while(true){
			synchronized (transMessageBoList){
                if(transMessageBoList.isEmpty()){
                    try{
                    	System.out.println("收发器空了");
                        transMessageBoList.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                executeMessage();
            }
            try {
				Thread.sleep(transMessageBoList.size() > 0 ? (100 / transMessageBoList.size()) : 100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void executeMessage(){
		for(TransMessageBo transMessageBo : transMessageBoList){
        	if(transMessageBo.getStatus() == ConstantUtils.MESSAGE_STATUS_SUCCESS){
        		//发送成功，删除这条记录
        		System.out.println("发送成功");
        		transMessageBoList.remove(transMessageBo);
        		transMessageBoHashMap.remove(transMessageBo.getMessageDto().getSerialNo());
        		break;
        	}else if(transMessageBo.getSendCount() >= ConstantUtils.MAX_SAND_TIMES 
        			&& CommonUtils.getMilliDifferenceBetweenTime(transMessageBo.getLastTime()) > ConstantUtils.MAX_BETWEEN_TIME 
        			&& transMessageBo.getStatus() == ConstantUtils.MESSAGE_STATUS_SENDING){
        		//超过3次，处理为离线
        		System.out.println("存储离线");
        		System.out.println(transMessageBo.getMessageDto().toString());
        		transMessageBoList.remove(transMessageBo);
        		transMessageBoHashMap.remove(transMessageBo.getMessageDto().getSerialNo());
        		if(transMessageBo.getMessageDto().getMsgType() == ConstantUtils.CHAT_FRI
        				|| transMessageBo.getMessageDto().getMsgType() == ConstantUtils.CHAT_PARTY_HEAD
        				|| transMessageBo.getMessageDto().getMsgType() == ConstantUtils.CHAT_PARTY_NAME){
        			saveFriMessage(transMessageBo.getMessageDto());
        			break;
        		}else if(transMessageBo.getMessageDto().getMsgType() == ConstantUtils.CHAT_PAR){
        			//群消息在三次内没有收到，删除任务并插入数据库
        			saveCommonInfo(saveParMessage(transMessageBo.getMessageDto()), IOSessionManager.getUserNoFromIoSessionToLong(transMessageBo.getSession()));
        			break;
        		}
        	}else if(transMessageBo.getSendCount() < ConstantUtils.MAX_SAND_TIMES 
        			&& CommonUtils.getMilliDifferenceBetweenTime(transMessageBo.getLastTime()) > ConstantUtils.MAX_BETWEEN_TIME 
        			&& transMessageBo.getStatus() == ConstantUtils.MESSAGE_STATUS_SENDING){
        		//重新发送，更新时间和次数
        		System.out.println("重新发送");
        		transMessageBo.setSendCount(transMessageBo.getSendCount() + 1);
        		transMessageBo.reSetLastTime();
        		transMessageBo.getSession().write(CommonUtils.getJSONStringFromObject(transMessageBo.getMessageDto()));
        	}
        }
	}
	
	private void saveCommonInfo(long offMsgNo, long userNo){
		CommonInfo commonInfo = new CommonInfo();
		commonInfo.setIsRead(false);
		commonInfo.setOffMsgNo(offMsgNo);
		commonInfo.setToNo(userNo);
		
		if(commonInfoMgt == null){
			commonInfoMgt = (CommonInfoMgt)SpringContextUtil.getBean("commonInfoMgtImpl");
		}
		commonInfoMgt.addCommonInfoByRecord(commonInfo);
	}
	
	private long saveFriMessage(MessageDto messageDto){
		OffMsg offMsg = new OffMsg();
		
		offMsg.setToNo(messageDto.getReceiverNo());
		offMsg.setFromNo(messageDto.getSenderNo());
		offMsg.setContent(messageDto.getMsgContent());
		offMsg.setConType(messageDto.getConType());
		offMsg.setMsgType(messageDto.getMsgType());
		offMsg.setIsRead(false);
		offMsg.setTime(messageDto.getMsgTime());
		
		if(offMsgMgt == null){
			offMsgMgt = (OffMsgMgt)SpringContextUtil.getBean("offMsgMgtImpl");
		}
		
		offMsg = offMsgMgt.putOffMsgByRecord(offMsg);
		return offMsg.getOffMsgNo();
	}
	
	private long saveParMessage(MessageDto messageDto){
		OffMsgExample offMsgExample = new OffMsgExample();
		offMsgExample.createCriteria()
		.andFromNoEqualTo(messageDto.getSenderNo())
		.andToNoEqualTo(messageDto.getReceiverNo())
		.andTimeEqualTo(messageDto.getMsgTime())
		.andMsgTypeEqualTo(messageDto.getMsgType())
		.andConTypeEqualTo(messageDto.getConType());
		
		if(offMsgMapper == null){
			offMsgMapper = (OffMsgMapper)SpringContextUtil.getBean("offMsgMapper");
		}
		
		List<OffMsg> offMsgs = offMsgMapper.selectByExample(offMsgExample);
		if(offMsgs.isEmpty()){
			return saveFriMessage(messageDto);
		}else{
			return offMsgs.get(0).getOffMsgNo();
		}
	}

}
