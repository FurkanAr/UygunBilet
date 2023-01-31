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
import org.springframework.web.bind.annotation.RestController;

import com.uygunbilet.request.SeatRequest;
import com.uygunbilet.response.SeatResponse;
import com.uygunbilet.service.SeatService;

@RestController
@RequestMapping(value = "/seats")
public class SeatController {
	
	Logger logger = Logger.getLogger(SeatController.class.getName());
	
	@Autowired
	private SeatService seatService;

	// Returns created seaets
	@GetMapping
	public ResponseEntity<List<SeatResponse>> getAll() {
		List<SeatResponse> seatResponses = seatService.getAll();
		logger.log(Level.INFO, "[SeatController] -- returns all seats, number of seats: {0}" , seatResponses.size());
		return new ResponseEntity<>(seatResponses, HttpStatus.OK);
	}

	//Creates new seats but i diddnt created seats this way
	@PostMapping
	public ResponseEntity<SeatResponse> create(@RequestBody SeatRequest seatRequest) {
		SeatResponse seatResponse = seatService.create(seatRequest);
		logger.log(Level.INFO, "[SeatController] -- seat created: {0}" , seatResponse);
		return new ResponseEntity<>(seatResponse, HttpStatus.CREATED);
	}
}
