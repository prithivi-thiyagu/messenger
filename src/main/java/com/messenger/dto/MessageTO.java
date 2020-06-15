package com.messenger.dto;

public class MessageTO {

	private String userid;

	private String receiverPhone;
	
	private String msg;

	public MessageTO( String receiverPhone, String msg) {
		super();
		
		this.receiverPhone = receiverPhone;
		this.msg = msg;
	}

	public MessageTO() {}	
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
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

	
	
}
