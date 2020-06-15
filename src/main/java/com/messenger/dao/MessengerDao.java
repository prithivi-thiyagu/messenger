package com.messenger.dao;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import com.messenger.logs.MessengerLogger;
import com.messenger.pojo.Message;
import com.messenger.pojo.Senders;

@Repository
public class MessengerDao {
	@Autowired
	private MessengerRepository msgrRepo;
	
	@PersistenceContext
	private EntityManager entityManager;
	

	
	
	public boolean createCustomer(Senders sender) {
	MessengerLogger.logInfo("Inside MessengerDao -- createCustomer() ");
	 entityManager.persist(sender);
	 return  checkUserExist(sender.getUserId(),"sender");
	 
	}
	
	
	public boolean checkUserExist(String userId,String type) {
		MessengerLogger.logInfo("Inside MessengerDao -- checkUserExist() ");
		boolean result = false;
		if("sender".equalsIgnoreCase(type)) {
				result = msgrRepo.existsById(userId);
			
		}
		
		return result;
		
	}
	
	public void mergeCustomer(Set<Message> message, String userId) {
		MessengerLogger.logInfo("Inside MessengerDao -- mergeCustomer() ");
		for(Message msg:message)
		{
		msg.setUserId(userId);
		entityManager.persist(msg);
		}
	}
	
	public List<Message> getMessage(String currDate, String currMnth) {
		String sql =null;
		List<Message> msgList = null;
		try {
		sql = "SELECT * FROM MESSAGE WHERE MSG_DAY = :day AND MSG_MONTH = :mnth ";
		Query sqlQuery = entityManager.createNativeQuery(sql, Message.class);
		sqlQuery.setParameter("day", currDate);
		sqlQuery.setParameter("mnth", currMnth);
		
		msgList = sqlQuery.getResultList();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return msgList;
		
	}
	
	public List<Message> getMessageById(String userId){
		String sql =null;
		List<Message> msgList = null;
		try {
		sql = "SELECT * FROM MESSAGE WHERE MSG_USER_ID = :userId ";
		Query sqlQuery = entityManager.createNativeQuery(sql, Message.class);
		sqlQuery.setParameter("userId", userId);
		
		msgList = sqlQuery.getResultList();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return msgList;
		
	}

}
