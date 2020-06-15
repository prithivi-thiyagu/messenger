package com.messenger.validators;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.messenger.logs.MessengerLogger;
import com.messenger.pojo.Message;

@Component
public class ValidateMessages {
	
	public String validateMessages(Set<Message> msgSet) {
		boolean phoneStatus = true;
		boolean msgStatus = true;
		boolean dateStatus = true;
		String msgVal = "";
		List<Message> list = new ArrayList<Message>(msgSet);
		for(int i = 0;i<list.size();i++) {
		Message msg = list.get(i);
		if(!isValid(msg.getReceiverPhone())) {
			phoneStatus = false;
			MessengerLogger.logInfo("Invalid Phone Number "+msg.getReceiverPhone());
			
		}
		if(!isValidString(msg.getMsg())) {
			msgStatus = false;
			MessengerLogger.logInfo("Invalid Message "+msg.getMsg());
		}
		if(!isValidateDate(msg.getDate())) {
			dateStatus = false;
			MessengerLogger.logInfo("Invalid Date "+msg.getDate());
		}
		}
		
		if(!phoneStatus || !msgStatus ||!dateStatus) {
			msgVal = "Please Enter Valid data in all feilds";
		}
		else {
			msgVal = "Success";
		}
		return msgVal;
		
	}
	
	
	public static boolean isValid(String s) 
    { 

		if(!StringUtils.hasText(s)) {
			return false;
		}
        Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}"); 
   
        Matcher m = p.matcher(s); 
        return (m.find() && m.group().equals(s)); 
    } 
	
	public static boolean isValidString(String s) 
    { 
		return StringUtils.hasText(s);
    } 

	public static boolean isValidateDate(String strDate)
	   {
		if (StringUtils.hasText(strDate))
		{
			boolean result = false;
		    /*
		     * Set preferred date format,
		     * For example MM-dd-yyyy, MM.dd.yyyy,dd.MM.yyyy etc.*/
		    SimpleDateFormat sdfrmt = new SimpleDateFormat("dd/MM/yyyy");
		    sdfrmt.setLenient(false);

		    try
		    {
		        Date javaDate = sdfrmt.parse(strDate); 
		        System.out.println(strDate+" is valid date format");
		        result = true;
		    }
		    catch (Exception e)
		    {
		    	
		    	result = false;
		        System.out.println(strDate+" is Invalid Date format");
		        return result;
		    }
		    
		    return result;
		}
		else {
		return false;
	   
}
}
}
