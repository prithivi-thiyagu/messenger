package com.messenger.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MESSAGE")
public class Message {


	public Message(String receiverPhone, String msg) {
		super();
		this.receiverPhone = receiverPhone;
		this.msg = msg;
	}
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MSG_ID")
	private int msgId;
	@Column(name = "MSG_RECEIVER_PH")
	private String receiverPhone;
	@Column(name = "MSG_VALUE")
	private String msg;
	@Column(name = "MSG_USER_ID")
	private String userId;
	public String getUserId() {
		return userId;
	}
	@Column(name = "MSG_DATE")
	public String date;
	@Column(name = "MSG_DAY")
	public String day;
	@Column(name = "MSG_MONTH")
	public String month;
	@Column(name = "MSG_USERNAME")
	public String username;
	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Message() {}

	public int getMsgId() {
		return msgId;
	}

	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}



	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@Override
	public String toString() {
		return "Message [msgId=" + msgId + ", receiverPhone=" + receiverPhone + ", msg=" + msg + ", userId=" + userId
				+ ", date=" + date + ", day=" + day + ", month=" + month + ", username=" + username + "]";
	}

}
