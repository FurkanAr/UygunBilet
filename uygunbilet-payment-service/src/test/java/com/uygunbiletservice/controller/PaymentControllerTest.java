package com.uygunbiletservice.controller;

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
import com.uygunbiletservice.model.enums.SeatType;
import com.uygunbiletservice.model.enums.TripType;
import com.uygunbiletservice.model.enums.UserGender;
import com.uygunbiletservice.request.PassengerSeatRequest;
import com.uygunbiletservice.request.PaymentCardRequest;
import com.uygunbiletservice.request.PaymentTransferRequest;
import com.uygunbiletservice.response.PassengerResponse;
import com.uygunbiletservice.response.PaymentResponse;
import com.uygunbiletservice.service.PaymentService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PaymentController.class)
public class PaymentControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PaymentService paymentService;

	private ObjectMapper mapper = new ObjectMapper();
	
	@Test
	void it_should_get_all_payments_by_card_request() throws Exception{
		Mockito.when(paymentService.getAll()).thenReturn(getCardPaymentResponses(1));

		ResultActions resultActions = mockMvc.perform(get("/payments"));
		resultActions.andExpect(status().isOk()).andExpect(jsonPath("$[0].id").value(1))
		.andExpect(jsonPath("$[0].createdDate").value(LocalDateTime.MAX.toString()))
		.andExpect(jsonPath("$[0].userId").value(1)).andExpect(jsonPath("$[0].name").value("test"))
		.andExpect(jsonPath("$[0].phone").value("5552223311")).andExpect(jsonPath("$[0].tripId").value(2))
		.andExpect(jsonPath("$[0].tripType").value(TripType.BUS.toString()))
		.andExpect(jsonPath("$[0].tripNo").value(101)).andExpect(jsonPath("$[0].departure").value("İstanbul"))
		.andExpect(jsonPath("$[0].destination").value("Ankara"))
		.andExpect(jsonPath("$[0].departureDate").value(
				LocalDate.now(Clock.systemDefaultZone()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))))
		.andExpect(jsonPath("$[0].departureTime")
				.value(LocalTime.MIDNIGHT.format(DateTimeFormatter.ofPattern("HH:mm:ss"))))
		.andExpect(jsonPath("$[0].travelAgency").value("Ulusoy"))
		.andExpect(jsonPath("$[0].passengers[0].id").value(1))
		.andExpect(jsonPath("$[0].passengers[0].firstName").value("test"))
		.andExpect(jsonPath("$[0].passengers[0].lastName").value("test2"))
		.andExpect(jsonPath("$[0].passengers[0].gender").value(UserGender.MALE.toString()))
		.andExpect(jsonPath("$[0].passengers[0].identityNo").value("111111111"))
		.andExpect(jsonPath("$[0].passengers[0].seatId").value(3))
		.andExpect(jsonPath("$[0].passengers[0].seatNo").value(4))
		.andExpect(jsonPath("$[0].passengers[0].seatType").value(SeatType.SINGLE.toString()))
		.andExpect(jsonPath("$[0].price").value(300.0));

	}
	
	@Test
	void it_should_get_all_payments_by_transfer_request() throws Exception{
		Mockito.when(paymentService.getAll()).thenReturn(getTransferPaymentResponses(1));

		ResultActions resultActions = mockMvc.perform(get("/payments"));
		resultActions.andExpect(status().isOk()).andExpect(jsonPath("$[0].id").value(1))
		.andExpect(jsonPath("$[0].createdDate").value(LocalDateTime.MAX.toString()))
		.andExpect(jsonPath("$[0].userId").value(1)).andExpect(jsonPath("$[0].name").value("test"))
		.andExpect(jsonPath("$[0].phone").value("5552223311")).andExpect(jsonPath("$[0].tripId").value(2))
		.andExpect(jsonPath("$[0].tripType").value(TripType.FLIGHT.toString()))
		.andExpect(jsonPath("$[0].tripNo").value(201)).andExpect(jsonPath("$[0].departure").value("London"))
		.andExpect(jsonPath("$[0].destination").value("Chicago"))
		.andExpect(jsonPath("$[0].departureDate").value(
				LocalDate.now(Clock.systemDefaultZone()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))))
		.andExpect(jsonPath("$[0].departureTime")
				.value(LocalTime.MIDNIGHT.format(DateTimeFormatter.ofPattern("HH:mm:ss"))))
		.andExpect(jsonPath("$[0].travelAgency").value("Pegasus"))
		.andExpect(jsonPath("$[0].passengers[0].id").value(1))
		.andExpect(jsonPath("$[0].passengers[0].firstName").value("test"))
		.andExpect(jsonPath("$[0].passengers[0].lastName").value("test2"))
		.andExpect(jsonPath("$[0].passengers[0].gender").value(UserGender.MALE.toString()))
		.andExpect(jsonPath("$[0].passengers[0].identityNo").value("111111111"))
		.andExpect(jsonPath("$[0].passengers[0].seatId").value(3))
		.andExpect(jsonPath("$[0].passengers[0].seatNo").value(4))
		.andExpect(jsonPath("$[0].passengers[0].seatType").value(SeatType.SINGLE.toString()))
		.andExpect(jsonPath("$[0].price").value(1300.0));

	}


	@Test
	void it_should_create_by_card() throws Exception {

		Mockito.when(paymentService.create(Mockito.any(PaymentCardRequest.class))).thenReturn(getPaymentCardResponse(1));

		String request = mapper.writeValueAsString(getPaymentCardRequest());

		ResultActions resultActions = mockMvc
				.perform(post("/payments/card").content(request).contentType(MediaType.APPLICATION_JSON));

		resultActions.andExpect(status().isCreated()).andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.createdDate").value(LocalDateTime.MAX.toString()))
				.andExpect(jsonPath("$.userId").value(1)).andExpect(jsonPath("$.name").value("test"))
				.andExpect(jsonPath("$.phone").value("5552223311")).andExpect(jsonPath("$.tripId").value(2))
				.andExpect(jsonPath("$.tripType").value(TripType.BUS.toString()))
				.andExpect(jsonPath("$.tripNo").value(101)).andExpect(jsonPath("$.departure").value("İstanbul"))
				.andExpect(jsonPath("$.destination").value("Ankara"))
				.andExpect(jsonPath("$.departureDate").value(
						LocalDate.now(Clock.systemDefaultZone()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))))
				.andExpect(jsonPath("$.departureTime")
						.value(LocalTime.MIDNIGHT.format(DateTimeFormatter.ofPattern("HH:mm:ss"))))
				.andExpect(jsonPath("$.travelAgency").value("Ulusoy"))
				.andExpect(jsonPath("$.passengers[0].id").value(1))
				.andExpect(jsonPath("$.passengers[0].firstName").value("test"))
				.andExpect(jsonPath("$.passengers[0].lastName").value("test2"))
				.andExpect(jsonPath("$.passengers[0].gender").value(UserGender.MALE.toString()))
				.andExpect(jsonPath("$.passengers[0].identityNo").value("111111111"))
				.andExpect(jsonPath("$.passengers[0].seatId").value(3))
				.andExpect(jsonPath("$.passengers[0].seatNo").value(4))
				.andExpect(jsonPath("$.passengers[0].seatType").value(SeatType.SINGLE.toString()))
				.andExpect(jsonPath("$.price").value(300.0));

	}

	@Test
	void it_should_create_by_transfer() throws Exception {

		Mockito.when(paymentService.create(Mockito.any(PaymentTransferRequest.class)))
				.thenReturn(getPaymentTransferResponse(1));

		String request = mapper.writeValueAsString(getPaymentTransferdRequest());

		ResultActions resultActions = mockMvc
				.perform(post("/payments/transfer").content(request).contentType(MediaType.APPLICATION_JSON));

		resultActions.andExpect(status().isCreated()).andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.createdDate").value(LocalDateTime.MAX.toString()))
				.andExpect(jsonPath("$.userId").value(1)).andExpect(jsonPath("$.name").value("test"))
				.andExpect(jsonPath("$.phone").value("5552223311")).andExpect(jsonPath("$.tripId").value(2))
				.andExpect(jsonPath("$.tripType").value(TripType.FLIGHT.toString()))
				.andExpect(jsonPath("$.tripNo").value(201)).andExpect(jsonPath("$.departure").value("London"))
				.andExpect(jsonPath("$.destination").value("Chicago"))
				.andExpect(jsonPath("$.departureDate").value(
						LocalDate.now(Clock.systemDefaultZone()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))))
				.andExpect(jsonPath("$.departureTime")
						.value(LocalTime.MIDNIGHT.format(DateTimeFormatter.ofPattern("HH:mm:ss"))))
				.andExpect(jsonPath("$.travelAgency").value("Pegasus"))
				.andExpect(jsonPath("$.passengers[0].id").value(1))
				.andExpect(jsonPath("$.passengers[0].firstName").value("test"))
				.andExpect(jsonPath("$.passengers[0].lastName").value("test2"))
				.andExpect(jsonPath("$.passengers[0].gender").value(UserGender.MALE.toString()))
				.andExpect(jsonPath("$.passengers[0].identityNo").value("111111111"))
				.andExpect(jsonPath("$.passengers[0].seatId").value(3))
				.andExpect(jsonPath("$.passengers[0].seatNo").value(4))
				.andExpect(jsonPath("$.passengers[0].seatType").value(SeatType.SINGLE.toString()))
				.andExpect(jsonPath("$.price").value(1300.0));

	}

	private PaymentTransferRequest getPaymentTransferdRequest() {
		
		return new PaymentTransferRequest("111111111", "222222222", "uygunbilet", 1, "test", "5552223311",
				TripType.FLIGHT, 201, "İstanbul", "Ankara", LocalDate.now(Clock.systemDefaultZone()),
				LocalTime.MIDNIGHT, "Pegasus", 2, getPassengerSeats(), 1300.0);
	}

	private PaymentCardRequest getPaymentCardRequest() {

		return new PaymentCardRequest("111111111", LocalDate.now(Clock.systemDefaultZone()), 121, 1, "test",
				"5552223311", 2, TripType.BUS, 101, "İstanbul", "Ankara", LocalDate.now(Clock.systemDefaultZone()),
				LocalTime.MIDNIGHT, "Ulusoy", getPassengerSeats(), 300.0);
	}

	private List<PassengerSeatRequest> getPassengerSeats() {
		return List.of(getPassengerSeat());
	}

	private PassengerSeatRequest getPassengerSeat() {
		return new PassengerSeatRequest("test", "test2", UserGender.MALE, "111111111", 3, 4, SeatType.SINGLE);
	}

	private List<PaymentResponse> getCardPaymentResponses(Integer paymentId) {
		return List.of(getPaymentCardResponse(1));
	}
	
	private List<PaymentResponse> getTransferPaymentResponses(Integer paymentId) {
		return List.of(getPaymentTransferResponse(1));
	}

	private PaymentResponse getPaymentCardResponse(Integer id) {
		return new PaymentResponse(id, LocalDateTime.MAX, 1, "test", "5552223311", 2, TripType.BUS, 101, "İstanbul",
				"Ankara", LocalDate.now(Clock.systemDefaultZone()), LocalTime.MIDNIGHT, "Ulusoy", getPassengers(1),
				300.0);
	}
	private PaymentResponse getPaymentTransferResponse(Integer id) {
		return new PaymentResponse(id, LocalDateTime.MAX, 1, "test", "5552223311", 2, TripType.FLIGHT, 201, "London",
				"Chicago", LocalDate.now(Clock.systemDefaultZone()), LocalTime.MIDNIGHT, "Pegasus", getPassengers(1),
				1300.0);
	}

	private List<PassengerResponse> getPassengers(Integer id) {
		return List.of(getPassengerResponse(1));
	}

	private PassengerResponse getPassengerResponse(Integer id) {
		return new PassengerResponse(id, "test", "test2", UserGender.MALE, "111111111", 3, 4, SeatType.SINGLE);
	}
}
