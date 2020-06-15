package com.messenger.pojo;

public class Login {
	
	public Login() {}
	
	public Login(String username, String password, String otp) {
		super();
		this.username = username;
		this.password = password;
		this.otp = otp;
	}
	private String username;
	private String password;
	private String otp;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}

}
