package com.uygunbiletservice.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.uygunbiletservice.model.Passenger;
import com.uygunbiletservice.model.Payment;
import com.uygunbiletservice.request.PassengerSeatRequest;
import com.uygunbiletservice.response.PassengerResponse;

/*
 * Converts passenger requests to passenger and passenger response
 */

@Component
public class PassengerConverter {

	public Passenger convert(PassengerSeatRequest request, Payment payment) {

		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getFirstName());
		passenger.setLastName(request.getLastName());
		passenger.setGender(request.getGender());
		passenger.setIdentityNo(request.getIdentityNo());
		passenger.setSeatId(request.getSeatId());
		passenger.setSeatNo(request.getSeatNo());
		passenger.setSeatType(request.getSeatType());
		passenger.setPayment(payment);
		return passenger;
	}

	public PassengerResponse convert(Passenger passenger) {
		PassengerResponse passengerResponse = new PassengerResponse();
		passengerResponse.setId(passenger.getId());
		passengerResponse.setFirstName(passenger.getFirstName());
		passengerResponse.setLastName(passenger.getLastName());
		passengerResponse.setGender(passenger.getGender());
		passengerResponse.setIdentityNo(passenger.getIdentityNo());
		passengerResponse.setSeatId(passenger.getSeatId());
		passengerResponse.setSeatNo(passenger.getSeatNo());
		passengerResponse.setSeatType(passenger.getSeatType());
		return passengerResponse;
	}

	public List<Passenger> convert(List<PassengerSeatRequest> passengerRequestList, Payment payment) {
		List<Passenger> passengers = new ArrayList<>();
		passengerRequestList.stream().forEach(passenger -> passengers.add(convert(passenger, payment)));
		return passengers;

	}

	public List<PassengerResponse> convert(List<Passenger> passengertList) {
		List<PassengerResponse> passengerResponses = new ArrayList<>();
		passengertList.stream().forEach(passenger -> passengerResponses.add(convert(passenger)));
		return passengerResponses;
	}

}
