package com.messenger.controller;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.messenger.dto.MessageTO;
import com.messenger.dto.SendersTO;
import com.messenger.logs.MessengerLogger;
import com.messenger.pojo.Message;
import com.messenger.pojo.Senders;
import com.messenger.pojo.UserDetails;
import com.messenger.service.RegisterService;
import com.messenger.validators.OTPGenerator;
import com.messenger.validators.SMSHandler;
import com.messenger.validators.ValidateMessages;

@Controller
public class UserRegisterController {
	@Autowired
	ValidateMessages validateMsg;
	
	@Autowired
	RegisterService regService;
	
	private SMSHandler smsHandler = null;
	
@RequestMapping(value = "/register", method = RequestMethod.GET)	
public String registerUser() {
	try {
		return "registerUser";
	}finally {
		
	}
	
}


@RequestMapping(value = "/registerUserDet",method = RequestMethod.POST)
public String registerUser(HttpServletRequest request) {
	//HttpSession session = null;
	Optional<Object> userOptional = null;
	Optional<Object> phoneOptional = null;
	String username = null;
	String phone = null;
    ArrayList<String>  senderParams = null;
    String otp = null;
    String validMsg = null;
    int i = 0;
    UserDetails user = null;
   
	try {
	 
	 user = new UserDetails();
	 userOptional = Optional.ofNullable(request.getParameter("username"));
	 phoneOptional = Optional.ofNullable(request.getParameter("phoneNum"));

	 MessengerLogger.logInfo("Inside the method -- registerUser()");
	 if(userOptional.isPresent() && phoneOptional.isPresent()) {
		 username=userOptional.get().toString();
		 phone = phoneOptional.get().toString();
	 }
	 
	 if(!StringUtils.hasText(username) || !StringUtils.hasText(phone) || !validateMsg.isValid(phone)) {
	    	request.setAttribute("errorMsg", "User Creation Failed. Enter Valid Data");
	    	return "registerUser";
	 }
	 user.setUserId(phone);
	 user.setUsername(username);
	 user.setOtp("1234");
	
	if(!regService.checkUserExist(username)) {
	MessengerLogger.logInfo("User does not exist so creating new user");

    if(regService.createCustomer(user)) {
    	request.setAttribute("errorMsg", "User Created Successfully. Please Login");
    	return "index";
    }
    else {
    	request.setAttribute("errorMsg", "User Creation Failed");
    	return "registerUser";
    }
    }
	else {
    	request.setAttribute("errorMsg", "User Already Exist");
    	return "registerUser";
	}
    }catch(Exception e) {}
	return "registerUser";
	}

@RequestMapping(value = "/generateOTP", method = RequestMethod.POST)
public String generateOTP(HttpServletRequest request) {
	Optional<UserDetails> userOptional = null;
	Optional<Object> phoneOptional = null;
	String username = null;
	String phone = null;
    ArrayList<String>  senderParams = null;
    String otp = null;
    String validMsg = null;
    int i = 0;
    UserDetails user = null;
    String message = null;
   
	try {
	 
	phoneOptional = Optional.ofNullable(request.getParameter("username"));

	smsHandler = new SMSHandler();
	 MessengerLogger.logInfo("Inside the method -- registerUser()");
	 if(phoneOptional.isPresent()) {
		 phone = phoneOptional.get().toString();
	 }
	 
	 if( !StringUtils.hasText(phone) && !validateMsg.isValid(phone)) {
	    	request.setAttribute("errorMsg", "User Creation Failed. Enter Valid Data");
	    	return "index";
	 }

	
	if(!regService.checkUserExist(phone)) {
	MessengerLogger.logInfo("User does not exist");
	request.setAttribute("errorMsg", "User Not Exist");
	return "index";
	}else {
		userOptional = regService.getUserById(phone);
		if(userOptional.isPresent()) {
			user = userOptional.get();
		}
		if(user!=null) {
			otp = OTPGenerator.generateOTP(6);
			user.setOtp(otp);
			user = regService.mergeCustomer(user);
			if(user !=null && otp.equals(user.getOtp())) {
				message = "&message="+ URLEncoder.encode("OTP for Prithivi Greetings \n"+otp,"UTF-8");
				  message = message+"&numbers="+phone;
				  smsHandler.sendSMS(message);
				request.setAttribute("errorMsg", "Please Enter OTP");
				return "index";
			}
			else {
				request.setAttribute("errorMsg", "Problem Occured");
				return "index";
			}
		}
	}
	}catch (Exception e) {
		// TODO: handle exception
	}
	return "index";
	

}
}
