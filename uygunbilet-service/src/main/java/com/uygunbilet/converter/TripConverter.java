package com.uygunbilet.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uygunbilet.model.Trip;
import com.uygunbilet.model.enums.TripType;
import com.uygunbilet.request.TripRequest;
import com.uygunbilet.response.TripResponse;

/*
 * Converts trip request to new trip and trip to  trip repsonse
 *  Bus and flight capacity given by instructor
 *  
 */

@Component
public class TripConverter {

	private static final Integer BUS_CAPACITY = 45;
	private static final Integer FLIGHT_CAPACITY = 189;

	@Autowired
	private SeatConverter seatConverter;

	public TripResponse convert(Trip trip) {
		TripResponse response = new TripResponse();
		response.setId(trip.getId());
		response.setType(trip.getType());
		response.setNo(trip.getNo());
		response.setDeparture(trip.getDeparture());
		response.setDestination(trip.getDestination());
		response.setDepartureDate(trip.getDepartureDate());
		response.setDepartureTime(trip.getDepartureTime());
		response.setTravelAgency(trip.getTravelAgency());
		response.setTicketPrice(trip.getTicketPrice());
		response.setCapacity(trip.getCapacity());
		response.setSeatList(seatConverter.convert(trip.getSeatList()));
		return response;
	}

	public Trip convert(TripRequest tripRequest) {
		Trip trip = new Trip();
		trip.setNo(tripRequest.getNo());
		trip.setType(tripRequest.getType());
		trip.setDeparture(tripRequest.getDeparture());
		trip.setDestination(tripRequest.getDestination());
		trip.setDepartureDate(tripRequest.getDepartureDate());
		trip.setDepartureTime(tripRequest.getDepartureTime());
		trip.setTravelAgency(tripRequest.getTravelAgency());
		trip.setTicketPrice(tripRequest.getTicketPrice());
		trip.setCapacity(FLIGHT_CAPACITY);
		if (TripType.BUS.equals(tripRequest.getType())) 
			trip.setCapacity(BUS_CAPACITY);
		return trip;
	}

	public List<TripResponse> convert(List<Trip> tripList) {
		List<TripResponse> tripResponses = new ArrayList<>();
		tripList.stream().forEach(trip -> tripResponses.add(convert(trip)));
		return tripResponses;
	}

}
