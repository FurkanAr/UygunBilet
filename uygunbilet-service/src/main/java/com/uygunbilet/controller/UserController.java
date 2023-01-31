package com.uygunbilet.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uygunbilet.request.LoginRequest;
import com.uygunbilet.request.UserRequest;
import com.uygunbilet.response.TicketResponse;
import com.uygunbilet.response.UserResponse;
import com.uygunbilet.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	Logger logger = Logger.getLogger(UserController.class.getName());

	@Autowired
	private UserService userService;


	// Returns all users
	@GetMapping
	public ResponseEntity<List<UserResponse>> getAll() {
		List<UserResponse> userResponses = userService.getAll();
		logger.log(Level.INFO, "[UserController] -- returns all users, number of users: {0}" , userResponses.size());
		return new ResponseEntity<>(userResponses, HttpStatus.OK);
	}

	// Creates a new user
	@PostMapping
	public ResponseEntity<UserResponse> create(@RequestBody UserRequest userRequest) {
		UserResponse userResponse = userService.create(userRequest);
		logger.log(Level.INFO, "[UserController] -- user created: {0}" , userResponse);
		return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
	}
	
	// Login to uygunbilet application
	@PostMapping(value = "/login")
	public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
		return ResponseEntity.ok(userService.login(loginRequest));
	}
	
	// Returns user tickets
	@GetMapping(value = "/tickets")
	public ResponseEntity<List<TicketResponse>> getTickets(@RequestParam("userId") Integer userId) {
		List<TicketResponse> ticketResponses = userService.getAllTickets(userId);
		logger.log(Level.INFO, "[UserController] -- returns user tickets: {0}" , ticketResponses);
		return new ResponseEntity<>(ticketResponses, HttpStatus.OK);
	}

}
