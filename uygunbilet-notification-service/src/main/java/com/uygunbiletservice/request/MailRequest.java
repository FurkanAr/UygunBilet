package com.uygunbiletservice.request;


public class MailRequest {

	private String userName;
	private String email;


	public MailRequest() {
		super();
	}


	public MailRequest(String userName, String email) {
		super();
		this.userName = userName;
		this.email = email;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String toString() {
		return "MailRequest [userName=" + userName + ", email=" + email + "]";
	}


}
