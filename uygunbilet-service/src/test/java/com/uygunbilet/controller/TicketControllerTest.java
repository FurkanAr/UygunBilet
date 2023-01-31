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
import com.uygunbilet.client.response.PassengerResponse;
import com.uygunbilet.client.response.PaymentResponse;
import com.uygunbilet.model.enums.PaymentType;
import com.uygunbilet.model.enums.SeatStatus;
import com.uygunbilet.model.enums.SeatType;
import com.uygunbilet.model.enums.TripType;
import com.uygunbilet.model.enums.UserGender;
import com.uygunbilet.request.CardPaymentRequest;
import com.uygunbilet.request.PassengerRequest;
import com.uygunbilet.request.TicketRequest;
import com.uygunbilet.request.TransferPaymentRequest;
import com.uygunbilet.response.TicketResponse;
import com.uygunbilet.service.TicketService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TicketController.class)
class TicketControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TicketService ticketService;

	private ObjectMapper mapper = new ObjectMapper();

	@Test
	void it_should_get_all_tickets() throws Exception {
		Mockito.when(ticketService.getAll()).thenReturn(getTicketResponses(1));
		ResultActions resultActions = mockMvc.perform(get("/tickets"));

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
				.andExpect(jsonPath("$[0].ticketPrice").value(300.0));
	}

	@Test
	void it_should_create() throws Exception {

		Mockito.when(ticketService.create(Mockito.any(TicketRequest.class))).thenReturn(getPaymentResponse(1));

		String request = mapper.writeValueAsString(getTicketRequest());

		ResultActions resultActions = mockMvc
				.perform(post("/tickets").content(request).contentType(MediaType.APPLICATION_JSON));

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
	void it_should_get_all_tickets_by_user_id() throws Exception {

		Mockito.when(ticketService.getAllByUserId(Mockito.anyInt())).thenReturn(getTicketResponses(1));
		ResultActions resultActions = mockMvc.perform(get("/tickets/users/1").contentType(MediaType.APPLICATION_JSON));
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
				.andExpect(jsonPath("$[0].ticketPrice").value(300.0));
	}
	
	@Test
	void it_should_get_all_tickets_by_trip_id_and_user_id() throws Exception {

		Mockito.when(ticketService.getAllByTripId(Mockito.anyInt(), Mockito.anyInt())).thenReturn(getTicketResponses(1));
		ResultActions resultActions = mockMvc.perform(get("/tickets/sold/trips/2?userId=1").contentType(MediaType.APPLICATION_JSON));
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
				.andExpect(jsonPath("$[0].ticketPrice").value(300.0));
	}
	
	@Test
	void it_should_get_number_of_tickets_by_trip_id_and_user_id() throws Exception {
		
		Mockito.when(ticketService.countByTripId(Mockito.anyInt(), Mockito.anyInt())).thenReturn(1);
		ResultActions resultActions = mockMvc.perform(get("/tickets/sold/total/trips/2?userId=1").contentType(MediaType.APPLICATION_JSON));
		resultActions.andExpect(status().isOk()).andExpect(jsonPath("$").value(1));

	}
	
	@Test
	void it_should_get_total_price_of_a_trip_by_trip_id_and_user_id() throws Exception {
		
		Mockito.when(ticketService.sumPriceByTripId(Mockito.anyInt(), Mockito.anyInt())).thenReturn(300.0);
		ResultActions resultActions = mockMvc.perform(get("/tickets/sold/totalPrice/trips/2?userId=1").contentType(MediaType.APPLICATION_JSON));
		resultActions.andExpect(status().isOk()).andExpect(jsonPath("$").value(300.0));

	}
	
	

	private TicketRequest getTicketRequest() {
		return new TicketRequest(PaymentType.CARD, getTransferRequest(), getCardRequest(), 1, 2, getSeatRequests());
	}

	private List<PassengerRequest> getSeatRequests() {
		return List.of(getPassengerRequest());

	}

	private PassengerRequest getPassengerRequest() {
		return new PassengerRequest("test", "test2", UserGender.MALE, "111111111", 3);
	}

	private CardPaymentRequest getCardRequest() {
		// TODO Auto-generated method stub
		return new CardPaymentRequest("11111111111", LocalDate.now(Clock.systemDefaultZone()), 111);
	}

	private TransferPaymentRequest getTransferRequest() {
		return new TransferPaymentRequest("11111111111", "11111111111", "uygunbilet");
	}

	private List<TicketResponse> getTicketResponses(Integer ticketId) {
		return List.of(getTicketResponse(1));
	}

	private TicketResponse getTicketResponse(Integer id) {
		return new TicketResponse(id, LocalDateTime.MAX, 2, TripType.BUS, 101, "İstanbul", "Ankara",
				LocalDate.now(Clock.systemDefaultZone()), LocalTime.MIDNIGHT, "Ulusoy", "test", "test2",
				UserGender.MALE, "111111111", 3, 4, SeatStatus.AVAILABLE, SeatType.SINGLE, 1, "tester",
				"tester@gmail.com", 300.0);
	}


	private PaymentResponse getPaymentResponse(Integer id) {
		return new PaymentResponse(id, LocalDateTime.MAX, 1, "test", "5552223311", 2, TripType.BUS, 101, "İstanbul",
				"Ankara", LocalDate.now(Clock.systemDefaultZone()), LocalTime.MIDNIGHT, "Ulusoy", getPassengers(1),
				300.0);
	}

	private List<PassengerResponse> getPassengers(Integer id) {
		return List.of(getPassengerResponse(1));
	}

	private PassengerResponse getPassengerResponse(Integer id) {
		return new PassengerResponse(id, "test", "test2", UserGender.MALE, "111111111", 3, 4, SeatType.SINGLE);
	}

}
