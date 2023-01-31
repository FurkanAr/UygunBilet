package com.uygunbiletservice.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.uygunbiletservice.model.Passenger;
import com.uygunbiletservice.model.Payment;
import com.uygunbiletservice.request.SmsRequest;

// If we payment is successful we send sms to users this component help us to
// convert and get sms requests.

@Component
public class SmsConverter {
	
	public SmsRequest convert(Payment payment) {
		SmsRequest smsRequest = new SmsRequest();
		smsRequest.setUserName(payment.getUserName());
		smsRequest.setUserPhone(payment.getUserPhone());
		smsRequest.setTripId(payment.getTripId());
		smsRequest.setTripType(payment.getTripType());
		smsRequest.setTripNo(payment.getTripNo());
		smsRequest.setDeparture(payment.getDeparture());
		smsRequest.setDestination(payment.getDestination());
		smsRequest.setDepartureDate(payment.getDepartureDate());
		smsRequest.setDepartureTime(payment.getDepartureTime());
		smsRequest.setTravelAgency(payment.getTravelAgency());		
		smsRequest.setSeatNo(convert(payment.getPassengerList()));
		smsRequest.setPrice(payment.getPrice());
		return smsRequest;
	}
	
	public List<Integer> convert(List<Passenger> passengerList){
		List<Integer> seatNumbers = new ArrayList<>();
		passengerList.stream().forEach(passenger -> seatNumbers.add(passenger.getSeatNo()));
		return seatNumbers;
	}
	

}
