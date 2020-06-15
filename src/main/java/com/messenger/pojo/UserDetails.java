package com.messenger.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MSG_USERDETAILS")
public class UserDetails {
	@Column(name = "MSG_USERNAME")
	private String username;
	@Column(name = "MSG_OTP")
	private String otp;
	@Id
	@Column(name = "MSG_USERID")
	private String userId;
		public UserDetails() {}
	public UserDetails(String username, String otp, String userId) {
		super();
		this.username = username;
		this.otp = otp;
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

}
