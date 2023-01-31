package com.uygunbilet.response;

import com.uygunbilet.model.enums.UserGender;
import com.uygunbilet.model.enums.UserRole;
import com.uygunbilet.model.enums.UserType;

public class UserResponse {

	private Integer id;
	private String name;
	private String email;
	private String phone;
	private UserType type;
	private UserRole role;
	private UserGender gender;

	public UserResponse() {
		super();
	}


	public UserResponse(Integer id, String name, String email, String phone, UserType type, UserRole role,
			UserGender gender) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.type = type;
		this.role = role;
		this.gender = gender;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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


	public UserRole getRole() {
		return role;
	}


	public void setRole(UserRole role) {
		this.role = role;
	}


	public UserGender getGender() {
		return gender;
	}


	public void setGender(UserGender gender) {
		this.gender = gender;
	}


	@Override
	public String toString() {
		return "UserResponse [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", type=" + type
				+ ", role=" + role + ", gender=" + gender + "]";
	}

}
