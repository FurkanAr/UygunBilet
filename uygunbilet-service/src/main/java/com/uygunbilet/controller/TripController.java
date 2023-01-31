package com.uygunbilet.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uygunbilet.model.enums.TripType;
import com.uygunbilet.request.TripRequest;
import com.uygunbilet.response.TripResponse;
import com.uygunbilet.service.TripService;

@RestController
@RequestMapping(value = "/trips")
public class TripController {

	Logger logger = Logger.getLogger(TripController.class.getName());

	@Autowired
	private TripService tripService;

	// Returns created trips
	@GetMapping
	public ResponseEntity<List<TripResponse>> getAll() {
		List<TripResponse> tripResponses = tripService.getAll();
		logger.log(Level.INFO, "[TripController] -- returns all trips, number of trips: {0}", tripResponses.size());
		return new ResponseEntity<>(tripResponses, HttpStatus.OK);
	}

	// Creates new trips
	@PostMapping
	public ResponseEntity<TripResponse> create(@RequestBody TripRequest tripRequest) {
		TripResponse tripResponse = tripService.create(tripRequest);
		logger.log(Level.INFO, "[TripController] -- ticket created: {0}", tripResponse);
		return new ResponseEntity<>(tripResponse, HttpStatus.CREATED);
	}

	// Returns trips by given trip id
	@GetMapping(value = "/{id}")
	public ResponseEntity<TripResponse> getById(@PathVariable Integer id) {
		TripResponse tripResponse = tripService.getById(id);
		logger.log(Level.INFO, "[TripController] -- returns trip: {0}", tripResponse);
		return new ResponseEntity<>(tripResponse, HttpStatus.OK);
	}

	// Deletes a trip by given trip id, Only admin can deletes trip
	@DeleteMapping(value = "/{tripId}")
	public ResponseEntity<String> delete(@RequestParam("userId") Integer userId, @PathVariable Integer tripId) {
		logger.log(Level.INFO, "[TripController] -- trip delted: {0}", tripId);
		return new ResponseEntity<>(tripService.delete(tripId, userId), HttpStatus.OK);
	}

	// Returns trips by given departure, destination, trip type and date information
	@GetMapping(value = "/services")
	public ResponseEntity<List<TripResponse>> findTrips(@RequestParam("departure") String departure,
			@RequestParam("destination") String destination, @RequestParam("type") TripType type,
			@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {

		List<TripResponse> tripResponses = tripService.findTrip(departure, destination, type, date);
		logger.log(Level.INFO, "[TripController] -- trips, number of trips: {0}", tripResponses.size());
		return new ResponseEntity<>(tripResponses, HttpStatus.OK);
	}

	// Returns trips by given departure and destination
	@GetMapping(value = "/departures/{departure}/destinations/{destination}")
	public ResponseEntity<List<TripResponse>> findTripByProvinces(@PathVariable String departure,
			@PathVariable String destination) {
		List<TripResponse> tripResponses = tripService.findTripByProvinces(departure, destination);
		logger.log(Level.INFO, "[TripController] -- trips by departure and destination, number of trips: {0}",
				tripResponses.size());

		return new ResponseEntity<>(tripResponses, HttpStatus.OK);
	}

	// Returns trips by given trip type(bus or flight)
	@GetMapping(value = "/types/{type}")
	public ResponseEntity<List<TripResponse>> findTripByTripType(@PathVariable String type) {
		List<TripResponse> tripResponses = tripService.findTripByTripType(type);
		logger.log(Level.INFO, "[TripController] -- trips by trip type, number of trips: {0}",
				tripResponses.size());
		return new ResponseEntity<>(tripResponses, HttpStatus.OK);
	}

	// Returns trips by given date
	@GetMapping(value = "/dates/{date}")
	public ResponseEntity<List<TripResponse>> findTripByDate(@PathVariable String date) {
		List<TripResponse> tripResponses = tripService.findTripByDate(date);
		logger.log(Level.INFO, "[TripController] -- trips by date, number of trips: {0}",
				tripResponses.size());
		return new ResponseEntity<>(tripResponses, HttpStatus.OK);
	}

}
