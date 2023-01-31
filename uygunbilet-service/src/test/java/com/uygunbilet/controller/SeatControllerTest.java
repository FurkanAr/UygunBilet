package com.uygunbilet.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.uygunbilet.request.SeatRequest;
import com.uygunbilet.response.SeatResponse;
import com.uygunbilet.service.SeatService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SeatController.class)
class SeatControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SeatService seatService;
	
	private ObjectMapper mapper = new ObjectMapper();


	@Test
	void it_should_get_all_seats() throws Exception {

		Mockito.when(seatService.getAll()).thenReturn(getAllSeatResponses());

		ResultActions resultActions = mockMvc.perform(get("/seats"));

		resultActions.andExpect(status().isOk()).andExpect(jsonPath("$[0].seatId").value(1))
				.andExpect(jsonPath("$[0].seatNo").value(11))
				.andExpect(jsonPath("$[0].type").value(SeatType.SINGLE.toString()))
				.andExpect(jsonPath("$[0].status").value(SeatStatus.AVAILABLE.toString()))
				.andExpect(jsonPath("$[0].tripId").value(2));

	}
	
	@Test
	void it_should_create() throws Exception{
		
		Mockito.when(seatService.create(Mockito.any(SeatRequest.class))).thenReturn(getSeatResponse(1));
		
		String request = mapper.writeValueAsString(getSeatRequest());
		
		ResultActions resultActions = mockMvc
				.perform(post("/seats").content(request).contentType(MediaType.APPLICATION_JSON));

		resultActions.andExpect(status().isCreated())
		.andExpect(jsonPath("$.seatId").value(1))
		.andExpect(jsonPath("$.seatNo").value(11))
		.andExpect(jsonPath("$.type").value(SeatType.SINGLE.toString()))
		.andExpect(jsonPath("$.status").value(SeatStatus.AVAILABLE.toString()))
		.andExpect(jsonPath("$.tripId").value(2));


}

	private SeatRequest getSeatRequest() {
		return new SeatRequest(101, SeatType.SINGLE, 2);
	}

	private List<SeatResponse> getAllSeatResponses() {
		return List.of(getSeatResponse(1));

	}

	private SeatResponse getSeatResponse(int seatId) {
		return new SeatResponse(seatId, 11, SeatType.SINGLE, SeatStatus.AVAILABLE, 2);

	}

}
