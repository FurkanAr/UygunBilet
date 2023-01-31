package com.uygunbiletservice.converter;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.uygunbiletservice.model.Sms;
import com.uygunbiletservice.request.SmsRequest;

/*
 * The sms request from the uygunbilet-payment service is saved here and sent to the users.
 */

@Component
public class SmsConverter {

	private static final String COMPANY = "Uygunbilet";

	public Sms convert(SmsRequest smsRequest) {

		Sms sms = new Sms();
		sms.setCreateDate(LocalDateTime.now());
		sms.setUserName(smsRequest.getUserName());
		sms.setUserPhone(smsRequest.getUserPhone());
		sms.setCompany(COMPANY);
		sms.setTripId(smsRequest.getTripId());
		sms.setType(smsRequest.getTripType());
		sms.setTripNo(smsRequest.getTripNo());
		sms.setDeparture(smsRequest.getDeparture());
		sms.setDestination(smsRequest.getDestination());
		sms.setDepartureDate(smsRequest.getDepartureDate());
		sms.setDepartureTime(smsRequest.getDepartureTime());
		sms.setTravelAgency(smsRequest.getTravelAgency());
		sms.setPrice(smsRequest.getPrice());
		sms.setSeatNo(smsRequest.getSeatNo());
		return sms;
	}
}
