package com.uygunbiletservice.request;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.uygunbiletservice.model.TripType;

public class SmsRequest {

	private String userName;
	private String userPhone;
	private Integer tripId;
	private TripType tripType;
	private Integer tripNo;
	private String departure;
	private String destination;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate departureDate;
	@JsonDeserialize(using = LocalTimeDeserializer.class)
	@JsonSerialize(using = LocalTimeSerializer.class)
	private LocalTime departureTime;
	private String travelAgency;
	private List<Integer> seatNo;
	private Double price;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public Integer getTripId() {
		return tripId;
	}

	public void setTripId(Integer tripId) {
		this.tripId = tripId;
	}

	public TripType getTripType() {
		return tripType;
	}

	public void setTripType(TripType tripType) {
		this.tripType = tripType;
	}

	public Integer getTripNo() {
		return tripNo;
	}

	public void setTripNo(Integer tripNo) {
		this.tripNo = tripNo;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}

	public LocalTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}

	public String getTravelAgency() {
		return travelAgency;
	}

	public void setTravelAgency(String travelAgency) {
		this.travelAgency = travelAgency;
	}

	public List<Integer> getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(List<Integer> seatNo) {
		this.seatNo = seatNo;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "SmsRequest [userName=" + userName + ", userPhone=" + userPhone + ", tripId=" + tripId + ", tripType="
				+ tripType + ", tripNo=" + tripNo + ", departure=" + departure + ", destination=" + destination
				+ ", departureDate=" + departureDate + ", departureTime=" + departureTime + ", travelAgency="
				+ travelAgency + ", price=" + price + "]";
	}

}
