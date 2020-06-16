package com.messenger.schedulers;

import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.messenger.dao.MessengerDao;
import com.messenger.pojo.Message;
import com.messenger.validators.SMSHandler;
@Component
public class SMSScheduler {
	
	@Autowired
	private MessengerDao msgrDAO;
	
	@Scheduled(cron =  "0 40 12 * * ?", zone ="Asia/Kolkata")
	public void scheduleMessenger() {
		Date date = null;
		Calendar calender = null;
		String currDate = null;
		String currMnth = null;
		List<Message> msgList = null;
		SMSHandler smsHandler = null;
		String message = null;
		try {
			System.out.println("Test scheduler*********************");
			date = new Date();
			smsHandler = new SMSHandler();
			calender = Calendar.getInstance();
			calender.setTime(date);
			currDate =  String.valueOf(calender.get(Calendar.DAY_OF_MONTH));
			currMnth = String.valueOf(calender.get(Calendar.MONTH)+1);
			
			  msgList = msgrDAO.getMessage(currDate, currMnth); 
			  for(Message msg : msgList)
			  { 
				  message = "&message="+ URLEncoder.encode(msg.getMsg()+"\n"+"by -> "+msg.getUsername(),"UTF-8");
				  message = message+"&numbers="+msg.getReceiverPhone();
				  smsHandler.sendSMS(message);
			  }
			 
			System.out.println(currDate+"-----"+currMnth);
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

}
