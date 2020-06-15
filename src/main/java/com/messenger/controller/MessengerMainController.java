package com.messenger.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.messenger.dto.MessageTO;
import com.messenger.dto.SendersTO;
import com.messenger.logs.MessengerLogger;
import com.messenger.pojo.Message;
import com.messenger.pojo.Senders;
import com.messenger.pojo.UserDetails;
import com.messenger.service.MessengerService;
import com.messenger.service.RegisterService;
import com.messenger.validators.OTPGenerator;
import com.messenger.validators.ValidateMessages;

@Controller
public class MessengerMainController  {
	@Autowired
	private MessengerService msgrService;
	@Autowired
	ValidateMessages validateMsg;

	@Autowired
	RegisterService regService;
	

	
	@RequestMapping(value = "/login")
	public String test() {
		
		return "index";
	}

	
	
	@RequestMapping(value = "/loginValidate",method = RequestMethod.POST)
	public String validateLogin(HttpServletRequest request) {
		HttpSession session = null;
		try {
		Optional<UserDetails> userOptional = null;
		UserDetails user = null;
		String username = request.getParameter("username");
		String otp = request.getParameter("otp");
		session = request.getSession();
		session.setAttribute("userName", username);
		session.setAttribute("otp", otp);
		session.setMaxInactiveInterval(180);
		userOptional = regService.getUserById(username);
		
		if(userOptional.isPresent()) {
			user = userOptional.get();
			session.setAttribute("senderName", user.getUsername());
			if(otp.equals(user.getOtp())) {
				if(user!=null) {
					otp = OTPGenerator.generateOTP(6);
					user.setOtp(otp);
					session.setAttribute("otp", otp);
					user = regService.mergeCustomer(user);
					}
				if(user !=null && otp.equals(user.getOtp())) {
					return "addReceiver";
				}
				else {
					request.setAttribute("errorMsg", "Problem Occured");
					session.invalidate();
					return "index";
				}
			
			}
			else {
				request.setAttribute("errorMsg", "Please enter valid OTP");
				session.invalidate();
				return "index";
				}
		}
		else {
		request.setAttribute("errorMsg", "Please enter valid Username");
		session.invalidate();
		return "index";
		}
		
		
	}catch (Exception e) {
		// TODO: handle exception
		session.invalidate();
		return "index";
	}
		
	}
	

	@RequestMapping(value = "/home",method = RequestMethod.POST)
	public String home(HttpServletRequest request) {
		HttpSession session = null;
		Optional<Object> userOptional = null;
		Optional<Object> otpOptional = null;
		Optional<Object> senderNameOptional = null;
		String username = null;
		Set<MessageTO> setTo = null;
		Set<Message> msgSet = null;
		MessageTO messageTo = null;
		MessageTO messageTo1 = null;
		SendersTO sendersTO = null;
		Message msg1 = null;
		Message msg2 = null;
	    Senders senders = null;
	    ArrayList<String>  senderParams = null;
	    Message message = null;
	    String otp = null;
	    String validMsg = null;
	    String senderName = null;
	    List<Message> msgList = null;
	    int i = 0;
	   
		try {
		 session=request.getSession(false);
		 userOptional = Optional.ofNullable(session.getAttribute("userName"));
		 otpOptional = Optional.ofNullable(session.getAttribute("otp"));
		 senderNameOptional = Optional.ofNullable(session.getAttribute("senderName"));
		 MessengerLogger.logInfo("Inside the method -- home()");
		 if(userOptional.isPresent() && otpOptional.isPresent()) {
			 username=userOptional.get().toString();
			 otp = otpOptional.get().toString();
			 senderName = senderNameOptional.get().toString();
		 }
		 if(!StringUtils.hasText(username) || !StringUtils.hasText(otp) || !StringUtils.hasText(senderName)) {
			 session.invalidate();
			 return "index";
		 }

		
		if(!msgrService.checkUserExist(username, "sender")) {
		MessengerLogger.logInfo("User does not exist so creating new user");
		msgSet=msgrService.populateSenderDetails(request,"sender",username,senderName);
	    validMsg = validateMsg.validateMessages(msgSet);
	    if(!"Success".equals(validMsg)) {
	    	request.setAttribute("validMsg", validMsg);
	    	return "addReceiver";
	    }
		senders = new Senders(username,msgSet);
	    boolean status = msgrService.createCustomer(senders);
	    if(status) {
	    	msgList = msgrService.getMessageById(username);
	    	request.setAttribute("msgList", msgList);
	    	return "viewUser";
	    }
	    }
		else {
			MessengerLogger.logInfo("User exist so merging new user");
			msgSet=msgrService.populateSenderDetails(request,"message",username,senderName);
		    validMsg = validateMsg.validateMessages(msgSet);
		    if(!"Success".equals(validMsg)) {
		    	request.setAttribute("validMsg", validMsg);
		    	return "addReceiver";
		    }
			msgrService.mergeCustomer(msgSet,username);
		    if(true) {
		    	msgList = msgrService.getMessageById(username);
		    	request.setAttribute("msgList", msgList);
		    	return "viewUser";
		    }
		}
		}catch(Exception e) {
			if(session!=null ) {
				session.invalidate();
			}
			
			e.printStackTrace();
			return "index";
		}
		finally {
			msgSet = null;
			senders = null;
			userOptional = null;
			otpOptional = null;
			
		}
		return "addReceiver";
	}
	
	
	
	@RequestMapping(value = "/viewUsers",method = RequestMethod.GET)
	public String viewDetails(HttpServletRequest request) {
		HttpSession session = null;
		Optional<Object> userOptional = null;
		Optional<Object> otpOptional = null;
		Optional<Object> senderNameOptional = null;
	    String otp = null;
	    String validMsg = null;
	    String senderName = null;
	    List<Message> msgList = null;
		 String username = null;
		 Optional<UserDetails> userOptionalVal = null;
		try {
			 session=request.getSession(false);
			 userOptional = Optional.ofNullable(session.getAttribute("userName"));
			 otpOptional = Optional.ofNullable(session.getAttribute("otp"));
			 senderNameOptional = Optional.ofNullable(session.getAttribute("senderName"));
			 MessengerLogger.logInfo("Inside the method -- home()");
		
			if(userOptional.isPresent() && otpOptional.isPresent()) {
				 username=userOptional.get().toString();
				 otp = otpOptional.get().toString();
				 senderName = senderNameOptional.get().toString();
			 }
			 if(!StringUtils.hasText(username) || !StringUtils.hasText(otp) || !StringUtils.hasText(senderName)) {
				 session.invalidate();
				 return "index";
			 }
			 userOptionalVal = regService.getUserById(username);
				
				if(userOptionalVal.isPresent()) {
					UserDetails user = userOptionalVal.get();
					if(!otp.equals(user.getOtp())) {
						session.invalidate();
						return "index";
					}
				}
			 	msgList = msgrService.getMessageById(username);
			 	if(msgList!=null && !msgList.isEmpty()) {
		    	request.setAttribute("msgList", msgList);
		    	return "viewUser";}
	
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return "viewUser";
		
		
	}
	
	@RequestMapping(value = "/logout",method = RequestMethod.GET)
	public String logOut(HttpServletRequest request) {
		HttpSession session = null;
		try {
			session = request.getSession(false);
			session.invalidate();
		}catch (Exception e) {
			e.printStackTrace();
			return "index";
			// TODO: handle exception
		}
		
		return "index";
		}
	
	@RequestMapping(value = "/addReceiver",method = RequestMethod.GET)
	public String addRedirect(HttpServletRequest request) {
		HttpSession session = null;
		String result = null;
		try {
		Optional<UserDetails> userOptional = null;
		UserDetails user = null;
	
		session = request.getSession(false);
		String username = (String) session.getAttribute("userName");
		String otp = (String) session.getAttribute("otp");
		 if(!StringUtils.hasText(username) || !StringUtils.hasText(otp)) {
			 session.invalidate();
			 return "index";
		 }
		userOptional = regService.getUserById(username);
		
		if(userOptional.isPresent()) {
			user = userOptional.get();
			if(otp.equals(user.getOtp())) {
				result = "addReceiver";
			}else {
				result = "index";
			}
	}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	
	@RequestMapping(value = "/rules",method = RequestMethod.GET)
	public String rules(HttpServletRequest request) {
		try {
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "rules";
	}
}
