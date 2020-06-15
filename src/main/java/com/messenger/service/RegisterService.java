package com.messenger.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.messenger.dao.MessengerDao;
import com.messenger.dao.RegisterDao;
import com.messenger.logs.MessengerLogger;
import com.messenger.pojo.Message;
import com.messenger.pojo.Senders;
import com.messenger.pojo.UserDetails;
import com.messenger.validators.ValidateMessages;

@Service
public class RegisterService {
	
	@Autowired
	private RegisterDao regDao;
	
	@Autowired
	ValidateMessages validateMsg;
	
	@Transactional
	public boolean createCustomer(UserDetails user) {
	MessengerLogger.logInfo("Inside RegisterService -- createCustomer() ");
	return regDao.createCustomer(user);
	}
	
	public boolean checkUserExist(String userId) {
		MessengerLogger.logInfo("Inside RegisterService -- checkUserExist() ");
		return regDao.checkUserExist(userId);
	}
	
	public Optional<UserDetails> getUserById(String id) {
		return regDao.getUserById(id);
		
	}
	
	@Transactional
	public UserDetails mergeCustomer(UserDetails user) {
		MessengerLogger.logInfo("Inside RegisterService -- mergeCustomer() ");
		return regDao.mergeCustomer(user);
	}
}
