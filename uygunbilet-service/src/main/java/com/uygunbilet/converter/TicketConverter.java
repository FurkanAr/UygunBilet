package com.uygunbilet.converter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.uygunbilet.model.Seat;
import com.uygunbilet.model.Ticket;
import com.uygunbilet.model.Trip;
import com.uygunbilet.model.User;
import com.uygunbilet.response.TicketResponse;

//Converts ticket request to new ticket and ticket to  ticket repsonse

@Component
public class TicketConverter {


	public TicketResponse convert(Ticket ticket) {
		TicketResponse response = new TicketResponse();
		response.setTicketId(ticket.getId());
		response.setCreateDate(ticket.getCreateDate());
		response.setTripId(ticket.getTrip().getId());
		response.setTripType(ticket.getTrip().getType());
		response.setTripNo(ticket.getTrip().getNo());
		response.setDeparture(ticket.getTrip().getDeparture());
		response.setDestination(ticket.getTrip().getDestination());
		response.setDepartureDate(ticket.getTrip().getDepartureDate());
		response.setDepartureTime(ticket.getTrip().getDepartureTime());
		response.setTravelAgency(ticket.getTrip().getTravelAgency());
		response.setUserName(ticket.getUser().getName());
		response.setUserEmail(ticket.getUser().getEmail());
		response.setFirstName(ticket.getSeat().getFirstName());
		response.setLastName(ticket.getSeat().getLastName());
		response.setGender(ticket.getSeat().getGender());
		response.setIdentityNo(ticket.getSeat().getIdentityNo());
		response.setSeatId(ticket.getSeat().getId());
		response.setSeatNo(ticket.getSeat().getSeatNo());
		response.setSeatStatus(ticket.getSeat().getSeatStatus());
		response.setSeatType(ticket.getSeat().getSeatType());
		response.setUserId(ticket.getUser().getId());
		response.setTicketPrice(ticket.getTicketPrice());
		return response;
	}

	public Ticket convert(Seat seat, User user, Trip trip) {
		Ticket ticket = new Ticket();
		ticket.setCreateDate(LocalDateTime.now());
		ticket.setTrip(trip);
		ticket.setSeat(seat);
		ticket.setUser(user);
		ticket.setTicketPrice(trip.getTicketPrice());
		return ticket;
	}

	public List<TicketResponse> convert(List<Ticket> ticketList) {
		List<TicketResponse> ticketResponses = new ArrayList<>();
		ticketList.stream().forEach(ticket -> ticketResponses.add(convert(ticket)));
		return ticketResponses;
	}

}
