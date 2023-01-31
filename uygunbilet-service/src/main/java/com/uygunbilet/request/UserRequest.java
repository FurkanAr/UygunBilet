package com.uygunbilet.request;

import com.uygunbilet.model.enums.UserGender;
import com.uygunbilet.model.enums.UserType;

public class UserRequest {
	private String name;
	private String email;
	private String password;
	private String phone;
	private UserType type;
	private UserGender gender;

	public UserRequest() {
		super();
	}

	public UserRequest(String name, String email, String password, String phone, UserType type, UserGender gender) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.type = type;
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public UserGender getGender() {
		return gender;
	}

	public void setGender(UserGender gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "UserRequest [name=" + name + ", email=" + email + ", password=" + password + ", phone=" + phone
				+ ", type=" + type + ", gender=" + gender + "]";
	}

}
