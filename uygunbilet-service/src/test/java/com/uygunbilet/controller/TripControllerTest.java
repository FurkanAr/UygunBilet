package com.uygunbilet.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Clock;
import java.time.LocalDate;
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
import com.uygunbilet.request.TripRequest;
import com.uygunbilet.response.SeatResponse;
import com.uygunbilet.response.TripResponse;
import com.uygunbilet.service.TripService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TripController.class)
class TripControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TripService tripService;

	private ObjectMapper mapper = new ObjectMapper();

	@Test
	void it_should_get_all_trips() throws Exception {

		Mockito.when(tripService.getAll()).thenReturn(getAllTripResponses(1));

		ResultActions resultActions = mockMvc.perform(get("/trips"));

		resultActions.andExpect(status().isOk()).andExpect(jsonPath("$[0].id").value(1))
				.andExpect(jsonPath("$[0].type").value(TripType.BUS.toString()))
				.andExpect(jsonPath("$[0].no").value(101)).andExpect(jsonPath("$[0].departure").value("İstanbul"))
				.andExpect(jsonPath("$[0].destination").value("Ankara"))
				.andExpect(jsonPath("$[0].departureDate").value(
						LocalDate.now(Clock.systemDefaultZone()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))))
				.andExpect(
						jsonPath("$[0].departureTime")
						.value(LocalTime.MIDNIGHT.format(DateTimeFormatter.ofPattern("HH:mm:ss"))))
				.andExpect(jsonPath("$[0].travelAgency").value("Ulusoy"))
				.andExpect(jsonPath("$[0].ticketPrice").value(300.0)).andExpect(jsonPath("$[0].capacity").value(45))
				.andExpect(jsonPath("$[0].seatList[0].seatId").value(1))
				.andExpect(jsonPath("$[0].seatList[0].seatNo").value(11))
				.andExpect(jsonPath("$[0].seatList[0].type").value(SeatType.SINGLE.toString()))
				.andExpect(jsonPath("$[0].seatList[0].status").value(SeatStatus.AVAILABLE.toString()))
				.andExpect(jsonPath("$[0].seatList[0].tripId").value(2));
	}

	@Test
	void it_should_create() throws Exception {

		Mockito.when(tripService.create(Mockito.any(TripRequest.class))).thenReturn(getTripResponse(1));

		String request = mapper.writeValueAsString(getTripRequest());

		ResultActions resultActions = mockMvc
				.perform(post("/trips").content(request).contentType(MediaType.APPLICATION_JSON));

		resultActions.andExpect(status().isCreated()).andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.type").value(TripType.BUS.toString())).andExpect(jsonPath("$.no").value(101))
				.andExpect(jsonPath("$.departure").value("İstanbul"))
				.andExpect(jsonPath("$.destination").value("Ankara"))
				.andExpect(jsonPath("$.departureDate").value(
						LocalDate.now(Clock.systemDefaultZone()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))))
				.andExpect(jsonPath("$.departureTime")
						.value(LocalTime.MIDNIGHT.format(DateTimeFormatter.ofPattern("HH:mm:ss"))))
				.andExpect(jsonPath("$.travelAgency").value("Ulusoy")).andExpect(jsonPath("$.ticketPrice").value(300.0))
				.andExpect(jsonPath("$.capacity").value(45)).andExpect(jsonPath("$.seatList[0].seatId").value(1))
				.andExpect(jsonPath("$.seatList[0].seatNo").value(11))
				.andExpect(jsonPath("$.seatList[0].type").value(SeatType.SINGLE.toString()))
				.andExpect(jsonPath("$.seatList[0].status").value(SeatStatus.AVAILABLE.toString()))
				.andExpect(jsonPath("$.seatList[0].tripId").value(2));
	}

	@Test
	void it_should_get_all_trips_by_trip_id() throws Exception {

		Mockito.when(tripService.getById(Mockito.any(Integer.class))).thenReturn(getTripResponse(1));

		ResultActions resultActions = mockMvc.perform(get("/trips/1").contentType(MediaType.APPLICATION_JSON));

		resultActions.andExpect(status().isOk()).andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.type").value(TripType.BUS.toString())).andExpect(jsonPath("$.no").value(101))
				.andExpect(jsonPath("$.departure").value("İstanbul"))
				.andExpect(jsonPath("$.destination").value("Ankara"))
				.andExpect(jsonPath("$.departureDate").value(
						LocalDate.now(Clock.systemDefaultZone()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))))
				.andExpect(jsonPath("$.departureTime")
						.value(LocalTime.MIDNIGHT.format(DateTimeFormatter.ofPattern("HH:mm:ss"))))
				.andExpect(jsonPath("$.travelAgency").value("Ulusoy")).andExpect(jsonPath("$.ticketPrice").value(300.0))
				.andExpect(jsonPath("$.capacity").value(45)).andExpect(jsonPath("$.seatList[0].seatId").value(1))
				.andExpect(jsonPath("$.seatList[0].seatNo").value(11))
				.andExpect(jsonPath("$.seatList[0].type").value(SeatType.SINGLE.toString()))
				.andExpect(jsonPath("$.seatList[0].status").value(SeatStatus.AVAILABLE.toString()))
				.andExpect(jsonPath("$.seatList[0].tripId").value(2));
	}

	@Test
	void it_should_delete() throws Exception {
		Mockito.when(tripService.delete(Mockito.any(Integer.class), Mockito.any(Integer.class)))
				.thenReturn(getDeleteResponse());

		ResultActions resultActions = mockMvc
				.perform(delete("/trips/1?userId=1").contentType(MediaType.APPLICATION_JSON));
		resultActions.andExpect(status().isOk()).andExpect(jsonPath("$").value("Trip deleted successfully!!"));

	}

	@Test
	void it_should_get_all_trips_by_departure_and_destination() throws Exception {
		Mockito.when(tripService.findTripByProvinces(Mockito.any(String.class), Mockito.any(String.class)))
				.thenReturn(getAllTripResponses(1));

		ResultActions resultActions = mockMvc
				.perform(get("/trips/departures/Istanbul/destinations/Ankara").contentType(MediaType.APPLICATION_JSON));

		resultActions.andExpect(status().isOk()).andExpect(jsonPath("$[0].id").value(1))
				.andExpect(jsonPath("$[0].type").value(TripType.BUS.toString()))
				.andExpect(jsonPath("$[0].no").value(101)).andExpect(jsonPath("$[0].departure").value("İstanbul"))
				.andExpect(jsonPath("$[0].destination").value("Ankara"))
				.andExpect(jsonPath("$[0].departureDate").value(
						LocalDate.now(Clock.systemDefaultZone()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))))
				.andExpect(jsonPath("$[0].departureTime")
						.value(LocalTime.MIDNIGHT.format(DateTimeFormatter.ofPattern("HH:mm:ss"))))
				.andExpect(jsonPath("$[0].travelAgency").value("Ulusoy"))
				.andExpect(jsonPath("$[0].ticketPrice").value(300.0)).andExpect(jsonPath("$[0].capacity").value(45))
				.andExpect(jsonPath("$[0].seatList[0].seatId").value(1))
				.andExpect(jsonPath("$[0].seatList[0].seatNo").value(11))
				.andExpect(jsonPath("$[0].seatList[0].type").value(SeatType.SINGLE.toString()))
				.andExpect(jsonPath("$[0].seatList[0].status").value(SeatStatus.AVAILABLE.toString()))
				.andExpect(jsonPath("$[0].seatList[0].tripId").value(2));

	}

	@Test
	void it_should_get_all_trips_by_trip_type() throws Exception {
		Mockito.when(tripService.findTripByTripType(Mockito.any(String.class))).thenReturn(getAllTripResponses(1));

		ResultActions resultActions = mockMvc.perform(get("/trips/types/bus").contentType(MediaType.APPLICATION_JSON));

		resultActions.andExpect(status().isOk()).andExpect(jsonPath("$[0].id").value(1))
				.andExpect(jsonPath("$[0].type").value(TripType.BUS.toString()))
				.andExpect(jsonPath("$[0].no").value(101)).andExpect(jsonPath("$[0].departure").value("İstanbul"))
				.andExpect(jsonPath("$[0].destination").value("Ankara"))
				.andExpect(jsonPath("$[0].departureDate").value(
						LocalDate.now(Clock.systemDefaultZone()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))))
				.andExpect(jsonPath("$[0].departureTime")
						.value(LocalTime.MIDNIGHT.format(DateTimeFormatter.ofPattern("HH:mm:ss"))))
				.andExpect(jsonPath("$[0].travelAgency").value("Ulusoy"))
				.andExpect(jsonPath("$[0].ticketPrice").value(300.0)).andExpect(jsonPath("$[0].capacity").value(45))
				.andExpect(jsonPath("$[0].seatList[0].seatId").value(1))
				.andExpect(jsonPath("$[0].seatList[0].seatNo").value(11))
				.andExpect(jsonPath("$[0].seatList[0].type").value(SeatType.SINGLE.toString()))
				.andExpect(jsonPath("$[0].seatList[0].status").value(SeatStatus.AVAILABLE.toString()))
				.andExpect(jsonPath("$[0].seatList[0].tripId").value(2));

	}

	@Test
	void it_should_get_all_trips_by_date() throws Exception {
		Mockito.when(tripService.findTripByDate(Mockito.any(String.class))).thenReturn(getAllTripResponses(1));

		ResultActions resultActions = mockMvc
				.perform(get("/trips/dates/22-07-2023").contentType(MediaType.APPLICATION_JSON));

		resultActions.andExpect(status().isOk()).andExpect(jsonPath("$[0].id").value(1))
				.andExpect(jsonPath("$[0].type").value(TripType.BUS.toString()))
				.andExpect(jsonPath("$[0].no").value(101)).andExpect(jsonPath("$[0].departure").value("İstanbul"))
				.andExpect(jsonPath("$[0].destination").value("Ankara"))
				.andExpect(jsonPath("$[0].departureDate").value(
						LocalDate.now(Clock.systemDefaultZone()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))))
				.andExpect(jsonPath("$[0].departureTime")
						.value(LocalTime.MIDNIGHT.format(DateTimeFormatter.ofPattern("HH:mm:ss"))))
				.andExpect(jsonPath("$[0].travelAgency").value("Ulusoy"))
				.andExpect(jsonPath("$[0].ticketPrice").value(300.0)).andExpect(jsonPath("$[0].capacity").value(45))
				.andExpect(jsonPath("$[0].seatList[0].seatId").value(1))
				.andExpect(jsonPath("$[0].seatList[0].seatNo").value(11))
				.andExpect(jsonPath("$[0].seatList[0].type").value(SeatType.SINGLE.toString()))
				.andExpect(jsonPath("$[0].seatList[0].status").value(SeatStatus.AVAILABLE.toString()))
				.andExpect(jsonPath("$[0].seatList[0].tripId").value(2));

	}

	@Test
	void it_should_get_all_trips_by_departure_destination_type_and_date() throws Exception {

		Mockito.when(tripService.findTrip(Mockito.any(String.class), Mockito.any(String.class),
				Mockito.any(TripType.class), Mockito.any(LocalDate.class))).thenReturn(getAllTripResponses(1));

		ResultActions resultActions = mockMvc
				.perform(get("/trips/services?departure=İstanbul&destination=Ankara&type=FLIGHT&date=2023-02-02")
						.contentType(MediaType.APPLICATION_JSON));

		resultActions.andExpect(status().isOk()).andExpect(jsonPath("$[0].id").value(1))
				.andExpect(jsonPath("$[0].type").value(TripType.BUS.toString()))
				.andExpect(jsonPath("$[0].no").value(101)).andExpect(jsonPath("$[0].departure").value("İstanbul"))
				.andExpect(jsonPath("$[0].destination").value("Ankara"))
				.andExpect(jsonPath("$[0].departureDate").value(
						LocalDate.now(Clock.systemDefaultZone()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))))
				.andExpect(jsonPath("$[0].departureTime")
						.value(LocalTime.MIDNIGHT.format(DateTimeFormatter.ofPattern("HH:mm:ss"))))
				.andExpect(jsonPath("$[0].travelAgency").value("Ulusoy"))
				.andExpect(jsonPath("$[0].ticketPrice").value(300.0)).andExpect(jsonPath("$[0].capacity").value(45))
				.andExpect(jsonPath("$[0].seatList[0].seatId").value(1))
				.andExpect(jsonPath("$[0].seatList[0].seatNo").value(11))
				.andExpect(jsonPath("$[0].seatList[0].type").value(SeatType.SINGLE.toString()))
				.andExpect(jsonPath("$[0].seatList[0].status").value(SeatStatus.AVAILABLE.toString()))
				.andExpect(jsonPath("$[0].seatList[0].tripId").value(2));
	}

	private String getDeleteResponse() {
		return "Trip deleted successfully!!";
	}

	private TripRequest getTripRequest() {
		return new TripRequest(1, 101, TripType.BUS, "İstanbul", "Ankara", LocalDate.now(Clock.systemDefaultZone()),
				LocalTime.MIDNIGHT, "Ulusoy", 300.0);
	}

	private List<TripResponse> getAllTripResponses(Integer tripId) {
		return List.of(getTripResponse(1));
	}

	private TripResponse getTripResponse(int id) {
		return new TripResponse(id, TripType.BUS, 101, "İstanbul", "Ankara", LocalDate.now(Clock.systemDefaultZone()),
				LocalTime.MIDNIGHT, "Ulusoy", 300.0, 45, getAllSeatResponses());
	}

	private List<SeatResponse> getAllSeatResponses() {
		return List.of(getSeatResponse(1));

	}

	private SeatResponse getSeatResponse(int seatId) {
		return new SeatResponse(seatId, 11, SeatType.SINGLE, SeatStatus.AVAILABLE, 2);

	}

}
