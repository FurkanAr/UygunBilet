package com.uygunbilet.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uygunbilet.model.enums.SeatStatus;
import com.uygunbilet.model.enums.SeatType;
import com.uygunbilet.model.enums.TripType;
import com.uygunbilet.model.enums.UserGender;
import com.uygunbilet.model.enums.UserRole;
import com.uygunbilet.model.enums.UserType;
import com.uygunbilet.request.LoginRequest;
import com.uygunbilet.request.UserRequest;
import com.uygunbilet.response.TicketResponse;
import com.uygunbilet.response.UserResponse;
import com.uygunbilet.service.UserService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	private ObjectMapper mapper = new ObjectMapper();

	@Test
	void it_should_get_all_users() throws Exception {
		// given
		Mockito.when(userService.getAll()).thenReturn(getAllUserResponses());

		// when
		ResultActions resultActions = mockMvc.perform(get("/users"));

		// then

		//// @formatter:off
 
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].id").value(1))
		.andExpect(jsonPath("$[0].name").value("test"))
		.andExpect(jsonPath("$[0].email").value("test@gmail.com"))
		.andExpect(jsonPath("$[0].phone").value("5552228855"))
		.andExpect(jsonPath("$[0].type").value(UserType.INDIVIDUAL.toString()))
		.andExpect(jsonPath("$[0].role").value(UserRole.STANDART.toString()))
		.andExpect(jsonPath("$[0].gender").value(UserGender.MALE.toString()));

		// @formatter:on

	}

	@Test
	void it_should_create() throws Exception {

		Mockito.when(userService.create(Mockito.any(UserRequest.class))).thenReturn(getUserResponse(1));

		String request = mapper.writeValueAsString(getUserRequest());

		ResultActions resultActions = mockMvc
				.perform(post("/users").content(request).contentType(MediaType.APPLICATION_JSON));

		resultActions.andExpect(status().isCreated()).andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.name").value("test")).andExpect(jsonPath("$.email").value("test@gmail.com"))
				.andExpect(jsonPath("$.phone").value("5552228855"))
				.andExpect(jsonPath("$.type").value(UserType.INDIVIDUAL.toString()))
				.andExpect(jsonPath("$.role").value(UserRole.STANDART.toString()))
				.andExpect(jsonPath("$.gender").value(UserGender.MALE.toString()));
	}

	@Test
	void it_should_login() throws Exception {

		Mockito.when(userService.login(Mockito.any(LoginRequest.class))).thenReturn(getLoginResponse());

		String request = mapper.writeValueAsString(getLoginRequest());

		ResultActions resultActions = mockMvc
				.perform(post("/users/login").content(request).contentType(MediaType.APPLICATION_JSON));
		resultActions.andExpect(status().isOk()).andExpect(jsonPath("$").value("Login Başarılı"));

	}

	@Test
	void it_should_getTickets() throws Exception {

		Mockito.when(userService.getAllTickets(Mockito.any(Integer.class))).thenReturn(getUserTicketResponses(1));

		ResultActions resultActions = mockMvc
				.perform(get("/users/tickets?userId=1").contentType(MediaType.APPLICATION_JSON));

		resultActions.andExpect(status().isOk()).andExpect(jsonPath("$[0].ticketId").value(1))
				.andExpect(jsonPath("$[0].createDate").value(LocalDateTime.MAX.toString()))
				.andExpect(jsonPath("$[0].tripId").value(2))
				.andExpect(jsonPath("$[0].tripType").value(TripType.BUS.toString()))
				.andExpect(jsonPath("$[0].tripNo").value(101)).andExpect(jsonPath("$[0].departure").value("İstanbul"))
				.andExpect(jsonPath("$[0].destination").value("Ankara"))
				.andExpect(jsonPath("$[0].departureDate").value(
						LocalDate.now(Clock.systemDefaultZone()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))))
				.andExpect(jsonPath("$[0].departureTime")
						.value(LocalTime.MIDNIGHT.format(DateTimeFormatter.ofPattern("HH:mm:ss"))))
				.andExpect(jsonPath("$[0].travelAgency").value("Ulusoy"))
				.andExpect(jsonPath("$[0].firstName").value("test")).andExpect(jsonPath("$[0].lastName").value("test2"))
				.andExpect(jsonPath("$[0].gender").value(UserGender.MALE.toString()))
				.andExpect(jsonPath("$[0].identityNo").value("111111111")).andExpect(jsonPath("$[0].seatId").value(3))
				.andExpect(jsonPath("$[0].seatNo").value(4))
				.andExpect(jsonPath("$[0].seatStatus").value(SeatStatus.AVAILABLE.toString()))
				.andExpect(jsonPath("$[0].seatType").value(SeatType.SINGLE.toString()))
				.andExpect(jsonPath("$[0].userId").value(1)).andExpect(jsonPath("$[0].userName").value("tester"))
				.andExpect(jsonPath("$[0].userEmail").value("tester@gmail.com"))
				.andExpect(jsonPath("$[0].ticketPrice").value(30.0));
	}

	private List<TicketResponse> getUserTicketResponses(Integer userId) {
		return List.of(getUserTicketResponse(1));
	}

	private TicketResponse getUserTicketResponse(Integer userId) {
		return new TicketResponse(1, LocalDateTime.MAX, 2, TripType.BUS, 101, "İstanbul", "Ankara",
				LocalDate.now(Clock.systemDefaultZone()), LocalTime.MIDNIGHT, "Ulusoy", "test", "test2",
				UserGender.MALE, "111111111", 3, 4, SeatStatus.AVAILABLE, SeatType.SINGLE, userId, "tester",
				"tester@gmail.com", 30.0);
	}

	private String getLoginResponse() {
		return "Login Başarılı";
	}

	private LoginRequest getLoginRequest() {
		return new LoginRequest("test@gmail.com", "12345");
	}

	private UserRequest getUserRequest() {
		return new UserRequest("test", "test@gmail.com", "test123", "5552228855", UserType.INDIVIDUAL, UserGender.MALE);
	}

	private List<UserResponse> getAllUserResponses() {
		return List.of(getUserResponse(1));
	}

	private UserResponse getUserResponse(int id) {
		return new UserResponse(id, "test", "test@gmail.com", "5552228855", UserType.INDIVIDUAL, UserRole.STANDART,
				UserGender.MALE);
	}

}
