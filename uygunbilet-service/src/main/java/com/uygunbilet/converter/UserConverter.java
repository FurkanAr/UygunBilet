package com.uygunbilet.converter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.uygunbilet.model.User;
import com.uygunbilet.model.enums.UserRole;
import com.uygunbilet.model.enums.UserType;
import com.uygunbilet.request.UserRequest;
import com.uygunbilet.response.UserResponse;

/*
 * Converts user request to new user and user to user response
 * If a user enroll system with the admin@gmail.com, user role 
 * updated to Admin
 */

@Component
public class UserConverter {
	
	private static final String EMAIL = "admin@gmail.com";

	public UserResponse convert(User user) {
		UserResponse response = new UserResponse();
		response.setEmail(user.getEmail());
		response.setId(user.getId());
		response.setName(user.getName());
		response.setPhone(user.getPhone());
		response.setType(user.getType());
		response.setRole(user.getRole());
		response.setGender(user.getGender());
		return response;
	}

	public User convert(UserRequest userRequest, String hash) {
		User user = new User();
		user.setEmail(userRequest.getEmail());
		user.setName(userRequest.getName());
		user.setPassword(hash);
		user.setCreateDate(LocalDateTime.now());
		user.setGender(userRequest.getGender());
		user.setPhone(userRequest.getPhone());
		user.setRole(UserRole.STANDART);
		user.setType(userRequest.getType());
		if (EMAIL.equals(user.getEmail())) {
			user.setRole(UserRole.ADMIN);
			user.setType(UserType.CORPORATE);
		}
		return user;
	}

	public List<UserResponse> convert(List<User> userList) {
		List<UserResponse> userResponses = new ArrayList<>();
		userList.stream().forEach(user -> userResponses.add(convert(user)));
		return userResponses;
	}

}
