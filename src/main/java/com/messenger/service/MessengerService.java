package com.messenger.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.messenger.dao.MessengerDao;
import com.messenger.logs.MessengerLogger;
import com.messenger.pojo.Message;
import com.messenger.pojo.Senders;
import com.messenger.validators.ValidateMessages;
@Service
public class MessengerService {
	
	@Autowired
	private MessengerDao msgrDao;
	
	@Autowired
	ValidateMessages validateMsg;
	
	@Transactional
	public boolean createCustomer(Senders sender) {
	MessengerLogger.logInfo("Inside MessengerService -- createCustomer() ");
	return msgrDao.createCustomer(sender);
	}
	
	public boolean checkUserExist(String userId,String type) {
		MessengerLogger.logInfo("Inside MessengerService -- checkUserExist() ");
		return msgrDao.checkUserExist(userId, type);
	}

	@Transactional
	public void mergeCustomer(Set<Message> message, String userId) {
		MessengerLogger.logInfo("Inside MessengerService -- mergeCustomer() ");
		 msgrDao.mergeCustomer(message,userId);
	}
	
	public Set<Message> populateSenderDetails(HttpServletRequest request,String type,String userId,String senderName){
	    ArrayList<String>  senderParams = null;
	    Message message = null;
	    Set<Message> msgSet = null;
	    List<Message> msgSetOrg = null;
	    int i = 0;
		try {
		MessengerLogger.logInfo("Inside MessengerService -- populateSenderDetails() ");
		senderParams = Collections.list((Enumeration<String>)  request.getParameterNames());
		message =  new Message();
		msgSet = new HashSet<Message>();
		
		 for(String param : senderParams) {
			 
			 String paramVal = request.getParameter(param);
			if(param.startsWith("phone"))
			{
				
					message.setReceiverPhone(paramVal);
					i++;
					System.out.println(param+" "+paramVal+" "+i);
					
				}else if(param.startsWith("msg")){
					message.setMsg(paramVal);
					i++;
				}
			else if(param.startsWith("date")){
				message.setDate(paramVal);
				if((paramVal.contains("/") && (paramVal.lastIndexOf("/")-paramVal.indexOf("/"))>0)){
				String dateVal[] = message.getDate().split("/");
				message.setDay(dateVal[0]);
				message.setMonth(dateVal[1]);
				}else {
					message.setDay("0");
					message.setMonth("0");
				}
				i++;
			}
			
			if(i%3==0 && i!=0) {
			if("message".equalsIgnoreCase(type)) {
				message.setUserId(userId);

			}	
			message.setUsername(senderName);
			msgSet.add(message);
			message =  new Message();
			i=0;
			}
			
		 }
		 msgSetOrg = new ArrayList<Message>(msgSet);
		 msgSet.clear();
		 for(int j = 0;j<msgSetOrg.size();j++) {
			
			 Message msg = msgSetOrg.get(j);
			 if(!StringUtils.hasText(msg.getReceiverPhone()) && !StringUtils.hasText(msg.getMsg()) && !StringUtils.hasText(msg.getDate())) {
				 MessengerLogger.logInfo("Dummy Feild Found and removed");
			 }
			 else {
				 msgSet.add(msg);
			 }
			 
		 }
		 
		 
		}finally {
			message = null;
		    senderParams = null;
		}
		return msgSet;
		
	}
	
	public List<Message> getMessageById(String userId){
		return msgrDao.getMessageById(userId);
		
	}
	

}
