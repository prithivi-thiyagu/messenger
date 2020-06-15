package com.messenger.dao;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.messenger.logs.MessengerLogger;
import com.messenger.pojo.Senders;
import com.messenger.pojo.UserDetails;
@Repository
public class RegisterDao {
	@Autowired
	private RegisterRepository regRepo;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public boolean createCustomer(UserDetails user) {
	MessengerLogger.logInfo("Inside RegisterDao -- createCustomer() ");
	 entityManager.persist(user);
	 return  checkUserExist(user.getUserId());
	 
	}
	
	
	public boolean checkUserExist(String userId) {
		MessengerLogger.logInfo("Inside RegisterDao -- checkUserExist() ");
		boolean result = false;
		
				result = regRepo.existsById(userId);
		
		return result;
		
	}
	
	public Optional<UserDetails> getUserById(String id) {
		return regRepo.findById(id);
		
		
	}


	public UserDetails mergeCustomer(UserDetails user) {
		// TODO Auto-generated method stub
		return entityManager.merge(user);
		
	}

}
