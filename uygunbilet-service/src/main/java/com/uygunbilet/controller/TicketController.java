package com.uygunbilet.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uygunbilet.client.response.PaymentResponse;
import com.uygunbilet.request.TicketRequest;
import com.uygunbilet.response.TicketResponse;
import com.uygunbilet.service.TicketService;

@RestController
@RequestMapping(value = "/tickets")
public class TicketController {
	
	Logger logger = Logger.getLogger(TicketController.class.getName());

	@Autowired
	private TicketService ticketService;
	
	// Creates new tickets
	@PostMapping
	public ResponseEntity<PaymentResponse> create(@RequestBody TicketRequest request) {
		PaymentResponse paymentResponse = ticketService.create(request);
		logger.log(Level.INFO, "[TicketController] -- ticket created: {0}" , paymentResponse);
		return new ResponseEntity<>(paymentResponse, HttpStatus.CREATED);
	}

	// Returns created tickets
	@GetMapping
	public ResponseEntity<List<TicketResponse>> getAll() {
		List<TicketResponse> ticketResponses = ticketService.getAll();
		logger.log(Level.INFO, "[TicketController] -- returns all tickets, number of tickets: {0}" , ticketResponses.size());
		return new ResponseEntity<>(ticketResponses, HttpStatus.OK);
	}

	// Returns user tickets
	@GetMapping(value = "/users/{id}")
	public ResponseEntity<List<TicketResponse>> getAllByUserId(@PathVariable Integer id) {
		List<TicketResponse> ticketResponses = ticketService.getAllByUserId(id);
		logger.log(Level.INFO, "[TicketController] -- returns user tickets {0}" , ticketResponses);
		return new ResponseEntity<>(ticketResponses, HttpStatus.OK);
	}

	// Returns tickets by given trip id only admin has permission
	@GetMapping(value = "/sold/trips/{tripId}")
	public ResponseEntity<List<TicketResponse>> getAllByTripId(@RequestParam("userId") Integer userId,
			@PathVariable Integer tripId) {
		List<TicketResponse> ticketResponses = ticketService.getAllByTripId(userId, tripId);
		logger.log(Level.INFO, "[TicketController] -- returns trip tickets: {0}" , ticketResponses);
		return new ResponseEntity<>(ticketResponses, HttpStatus.OK);
	}
	
	// Returns number of tickets by given trip id  only admin has permission
	@GetMapping(value = "/sold/total/trips/{tripId}")
	public ResponseEntity<Integer> countByTripId(@RequestParam("userId") Integer userId,
			@PathVariable Integer tripId) {
		Integer count = ticketService.countByTripId(userId, tripId);
		logger.log(Level.INFO, "[TicketController] -- number of tickets by trip: {0}" , count);
		return new ResponseEntity<>(count, HttpStatus.OK);
	}

	// Return total price of ticket by given trip id  only admin has permission
	@GetMapping(value = "/sold/totalPrice/trips/{tripId}")
	public ResponseEntity<Double> sumPriceByTripId(@RequestParam("userId") Integer userId,
			@PathVariable Integer tripId) {
		Double total = ticketService.sumPriceByTripId(userId, tripId);
		logger.log(Level.INFO, "[TicketController] -- sum of tickets price by trip: {0}" , total);
		return new ResponseEntity<>(total, HttpStatus.OK);
	}


}
