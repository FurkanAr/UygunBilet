package com.uygunbilet.service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uygunbilet.configuration.RabbitMQConfiguration;
import com.uygunbilet.converter.MailConverter;
import com.uygunbilet.converter.UserConverter;
import com.uygunbilet.exception.user.UserEmailAlreadyInUseException;
import com.uygunbilet.exception.user.UserNotFoundException;
import com.uygunbilet.model.User;
import com.uygunbilet.repository.UserRepository;
import com.uygunbilet.request.LoginRequest;
import com.uygunbilet.request.MailRequest;
import com.uygunbilet.request.UserRequest;
import com.uygunbilet.response.TicketResponse;
import com.uygunbilet.response.UserResponse;
import com.uygunbilet.util.PasswordUtil;

@Service
public class UserService {

	private static final String EMAIL_VEYA_ŞIFRE_YANLIŞ = "Email veya şifre yanlış";

	private static final String LOGIN_BAŞARILI = "Login Başarılı";

	Logger logger = Logger.getLogger(UserService.class.getName());

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserConverter userConverter;

	@Autowired
	private MailConverter mailConverter;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private RabbitMQConfiguration rabbitMQConfiguration;

	@Autowired
	private TicketService ticketService;

	// Creates new users
	public UserResponse create(UserRequest userRequest) {
		Optional<User> optionalUser = userRepository.findByEmail(userRequest.getEmail());

		if (optionalUser.isPresent()) {
			logger.log(Level.WARNING, "[UserService] -- User already has account by given email: {0}",
					userRequest.getEmail());
			throw new UserEmailAlreadyInUseException(
					"User already has account by given email: " + userRequest.getEmail());
		}

		String hash = PasswordUtil.preparePasswordHash(userRequest.getPassword(), userRequest.getEmail());

		logger.log(Level.INFO, "[UserSerivce] - password hash created: {0}", hash);

		User savedUser = userRepository.save(userConverter.convert(userRequest, hash));

		logger.log(Level.INFO, "[UserSerivce] - user created: {0}", savedUser.getId());

		MailRequest mailRequest = mailConverter.convert(userRequest);

		rabbitTemplate.convertAndSend(rabbitMQConfiguration.getQueueName(), mailRequest);

		logger.log(Level.WARNING, "[UserSerivce] - userRequest: {0}, sent to : {1}",
				new Object[] { mailRequest, rabbitMQConfiguration.getQueueName() });
		return userConverter.convert(savedUser);
	}
	
	// Returns created users 
	public List<UserResponse> getAll() {
		List<UserResponse> userResponses = userConverter.convert(userRepository.findAll());
		logger.log(Level.INFO, "[UserSerivce] - get all users, number of users: {0}", userResponses.size());
		return userResponses;
	}

	// If it matches the email and password in database, the user enters the system
	public String login(LoginRequest loginRequest) {

		User foundUser = userRepository.findByEmail(loginRequest.getEmail())
				.orElseThrow(() -> new UserNotFoundException("User cannot find"));

		logger.log(Level.INFO, "[UserSerivce] - found user: {0}", foundUser);

		String passwordHash = PasswordUtil.preparePasswordHash(loginRequest.getPassword(), loginRequest.getEmail());
		
		logger.log(Level.INFO, "[UserSerivce] - login request user hash: {0}", passwordHash);

		boolean isValid = PasswordUtil.validatePassword(passwordHash, foundUser.getPassword());
		
		logger.log(Level.INFO, "[UserSerivce] - login request user: {0}", isValid);

		return isValid ? LOGIN_BAŞARILI : EMAIL_VEYA_ŞIFRE_YANLIŞ;
	}

	// Returns user by given user id
	public Optional<User> findByUserId(Integer id) {
		Optional<User> foundUser =  userRepository.findById(id);
		logger.log(Level.INFO, "[UserSerivce] - find user by userId: {0}", foundUser);
		return foundUser;
	}
	
	// Returns user tickets with the help of ticket service
	public List<TicketResponse> getAllTickets(Integer userId) {
		List<TicketResponse> ticketResponses = ticketService.getAllByUserId(userId);
		logger.log(Level.INFO, "[UserSerivce] - user tickets: {0}", ticketResponses);
		return ticketResponses;
	}

}
