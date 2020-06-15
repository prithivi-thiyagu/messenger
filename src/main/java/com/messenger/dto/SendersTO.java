package com.messenger.dto;

import java.util.Set;

import com.messenger.pojo.Message;

public class SendersTO {
	public SendersTO(String userId, Set<MessageTO> messenger) {
		super();
		this.userId = userId;
		this.messenger = messenger;
	}
	private String userId;
	private Set<MessageTO> messenger;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Set<MessageTO> getMessenger() {
		return messenger;
	}
	public void setMessenger(Set<MessageTO> messenger) {
		this.messenger = messenger;
	}

	
}
