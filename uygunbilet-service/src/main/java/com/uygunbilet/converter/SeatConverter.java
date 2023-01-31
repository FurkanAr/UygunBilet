package com.uygunbilet.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.uygunbilet.model.Seat;
import com.uygunbilet.model.Trip;
import com.uygunbilet.model.enums.SeatStatus;
import com.uygunbilet.request.PassengerRequest;
import com.uygunbilet.request.SeatRequest;
import com.uygunbilet.response.SeatResponse;

// Converts seatrequest to new seat and seat to  seat repsonse

@Component
public class SeatConverter {

	public SeatResponse convert(Seat seat) {
		SeatResponse response = new SeatResponse();
		response.setSeatId(seat.getId());
		response.setSeatNo(seat.getSeatNo());
		response.setStatus(seat.getSeatStatus());
		response.setType(seat.getSeatType());
		response.setTripId(seat.getTrip().getId());
		return response;
	}
	
	public Seat convert(SeatRequest seatRequest, Trip trip) {
		Seat seat = new Seat();
		seat.setSeatNo(seatRequest.getSeatNo());
		seat.setSeatType(seatRequest.getType());
		seat.setSeatStatus(SeatStatus.AVAILABLE);
		seat.setTrip(trip);
		return seat;
	}
	
	public Seat convert(PassengerRequest request, Seat seat) {
		seat.setFirstName(request.getFirstName());
		seat.setLastName(request.getLastName());
		seat.setGender(request.getGender());
		seat.setIdentityNo(request.getIdentityNo());
		seat.setSeatStatus(SeatStatus.FULL);
		return seat;
	}
	
	public List<SeatResponse> convert(List<Seat> seatList){
		List<SeatResponse> seatResponses = new ArrayList<>();
		seatList.stream().forEach(seat -> seatResponses.add(convert(seat)));
		return seatResponses;
	}

}
