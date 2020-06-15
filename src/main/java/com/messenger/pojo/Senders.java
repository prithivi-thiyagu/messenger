package com.messenger.pojo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "MSG_SENDER")
public class Senders {
	
	public Senders(String userId, Set<Message> messenger) {
		super();
		this.userId = userId;
		this.messenger = messenger;
	}

	@Id
	@Column(name = "MSG_USER_ID")
	private String userId;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="MSG_USER_ID")
	private Set<Message> messenger;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Set<Message> getMessenger() {
		return messenger;
	}
	public void setMessenger(Set<Message> messenger) {
		this.messenger = messenger;
	}
	
	public Senders() {}

}
