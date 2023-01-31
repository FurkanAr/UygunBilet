package com.uygunbiletservice.converter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uygunbiletservice.model.Payment;
import com.uygunbiletservice.model.enums.PaymentType;
import com.uygunbiletservice.request.PaymentCardRequest;
import com.uygunbiletservice.request.PaymentTransferRequest;
import com.uygunbiletservice.response.PaymentResponse;

/* There is 2 two type of payment, first one card second is transfer.
 * This component converts payment request which we send it to uygun bilet payment service.
 */

@Component
public class PaymentConverter {
	
	@Autowired
	PassengerConverter passengerConverter;

	public Payment convert(PaymentCardRequest paymentRequest) {
		Payment payment = new Payment();
		payment.setCreatedDate(LocalDateTime.now());
		payment.setUserId(paymentRequest.getUserId());
		payment.setUserName(paymentRequest.getName());
		payment.setUserPhone(paymentRequest.getPhone());
		payment.setTripType(paymentRequest.getTripType());
		payment.setPaymentType(PaymentType.CARD);
		payment.setTripNo(paymentRequest.getTripNo());
		payment.setDeparture(paymentRequest.getDeparture());
		payment.setDestination(paymentRequest.getDestination());
		payment.setDepartureDate(paymentRequest.getDepartureDate());
		payment.setDepartureTime(paymentRequest.getDepartureTime());
		payment.setTravelAgency(paymentRequest.getTravelAgency());
		payment.setTripId(paymentRequest.getTripId());
		payment.setPrice(paymentRequest.getPrice());
		return payment;
	}
	
	public Payment convert(PaymentTransferRequest paymentRequest) {
		Payment payment = new Payment();
		payment.setCreatedDate(LocalDateTime.now());
		payment.setUserId(paymentRequest.getUserId());
		payment.setUserName(paymentRequest.getName());
		payment.setUserPhone(paymentRequest.getPhone());
		payment.setTripType(paymentRequest.getTripType());
		payment.setPaymentType(PaymentType.TRANSFER);
		payment.setTripNo(paymentRequest.getTripNo());
		payment.setDeparture(paymentRequest.getDeparture());
		payment.setDestination(paymentRequest.getDestination());
		payment.setDepartureDate(paymentRequest.getDepartureDate());
		payment.setDepartureTime(paymentRequest.getDepartureTime());
		payment.setTravelAgency(paymentRequest.getTravelAgency());
		payment.setTripId(paymentRequest.getTripId());
		payment.setPrice(paymentRequest.getPrice());
		return payment;
	}
	
	public PaymentResponse convert(Payment payment) {
		PaymentResponse paymentResponse = new PaymentResponse();
		paymentResponse.setId(payment.getId());
		paymentResponse.setCreatedDate(payment.getCreatedDate());
		paymentResponse.setUserId(payment.getUserId());
		paymentResponse.setName(payment.getUserName());
		paymentResponse.setPhone(payment.getUserPhone());
		paymentResponse.setTripId(payment.getTripId());
		paymentResponse.setTripType(payment.getTripType());
		paymentResponse.setTripNo(payment.getTripNo());
		paymentResponse.setDeparture(payment.getDeparture());
		paymentResponse.setDestination(payment.getDestination());
		paymentResponse.setDepartureDate(payment.getDepartureDate());
		paymentResponse.setDepartureTime(payment.getDepartureTime());
		paymentResponse.setTravelAgency(payment.getTravelAgency());
		paymentResponse.setPassengers(passengerConverter.convert(payment.getPassengerList()));
		paymentResponse.setPrice(payment.getPrice());
		return paymentResponse;
	}
	
	
	public List<PaymentResponse> convert(List<Payment> paymentList) {
		List<PaymentResponse> paymentResponses = new ArrayList<>();
		paymentList.stream().forEach(payment -> paymentResponses.add(convert(payment)));
		return paymentResponses;
	}

}
