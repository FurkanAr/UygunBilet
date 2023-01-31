package com.uygunbilet.client.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.uygunbilet.client.request.PassengerSeatRequest;
import com.uygunbilet.client.request.PaymentCardRequest;
import com.uygunbilet.client.request.PaymentTransferRequest;
import com.uygunbilet.model.Seat;
import com.uygunbilet.model.Trip;
import com.uygunbilet.model.User;
import com.uygunbilet.request.CardPaymentRequest;
import com.uygunbilet.request.TransferPaymentRequest;

/* There is 2 two type of payment, first one card second is transfer.
 * This component converts payment request which we send it to uygun bilet payment service.
 */

@Component
public class PaymentConverter {

	public PaymentCardRequest convert(CardPaymentRequest cardPaymentRequest, User user, Trip trip, List<Seat> seats) {
		PaymentCardRequest paymentRequest = new PaymentCardRequest();
		paymentRequest.setCardNo(cardPaymentRequest.getCardNo());
		paymentRequest.setExpireDate(cardPaymentRequest.getExpireDate());
		paymentRequest.setCvcNo(cardPaymentRequest.getCvcNo());
		paymentRequest.setUserId(user.getId());
		paymentRequest.setName(user.getName());
		paymentRequest.setPhone(user.getPhone());
		paymentRequest.setTripId(trip.getId());
		paymentRequest.setPassengers(convert(seats));
		paymentRequest.setPrice(seats.size() * trip.getTicketPrice());
		paymentRequest.setTripType(trip.getType());
		paymentRequest.setTripNo(trip.getNo());
		paymentRequest.setDeparture(trip.getDeparture());
		paymentRequest.setDestination(trip.getDestination());
		paymentRequest.setDepartureDate(trip.getDepartureDate());
		paymentRequest.setDepartureTime(trip.getDepartureTime());
		paymentRequest.setTravelAgency(trip.getTravelAgency());
		return paymentRequest;
	}

	public PaymentTransferRequest convert(TransferPaymentRequest transferPaymentRequest, User user, Trip trip,
			List<Seat> seats) {
		PaymentTransferRequest paymentRequest = new PaymentTransferRequest();
		paymentRequest.setAccountNumberFrom(transferPaymentRequest.getAccountNumberFrom());
		paymentRequest.setAccountNumberTo(transferPaymentRequest.getAccountNumberTo());
		paymentRequest.setFirmName(transferPaymentRequest.getFirmName());
		paymentRequest.setUserId(user.getId());
		paymentRequest.setName(user.getName());
		paymentRequest.setPhone(user.getPhone());
		paymentRequest.setTripId(trip.getId());
		paymentRequest.setPassengers(convert(seats));
		paymentRequest.setPrice(seats.size() * trip.getTicketPrice());
		paymentRequest.setTripType(trip.getType());
		paymentRequest.setTripNo(trip.getNo());
		paymentRequest.setDeparture(trip.getDeparture());
		paymentRequest.setDestination(trip.getDestination());
		paymentRequest.setDepartureDate(trip.getDepartureDate());
		paymentRequest.setDepartureTime(trip.getDepartureTime());
		paymentRequest.setTravelAgency(trip.getTravelAgency());
		return paymentRequest;

	}

	public PassengerSeatRequest convert(Seat seat) {
		PassengerSeatRequest passengerSeatRequest = new PassengerSeatRequest();
		passengerSeatRequest.setFirstName(seat.getFirstName());
		passengerSeatRequest.setLastName(seat.getLastName());
		passengerSeatRequest.setGender(seat.getGender());
		passengerSeatRequest.setIdentityNo(seat.getIdentityNo());
		passengerSeatRequest.setSeatId(seat.getId());
		passengerSeatRequest.setSeatNo(seat.getSeatNo());
		passengerSeatRequest.setSeatType(seat.getSeatType());
		return passengerSeatRequest;

	}

	public List<PassengerSeatRequest> convert(List<Seat> seatList) {
		List<PassengerSeatRequest> assengerSeatRequests = new ArrayList<>();
		seatList.stream().forEach(seat -> assengerSeatRequests.add(convert(seat)));
		return assengerSeatRequests;
	}

}
