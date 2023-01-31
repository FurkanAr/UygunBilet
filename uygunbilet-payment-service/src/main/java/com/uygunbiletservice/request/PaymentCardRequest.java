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
import com.uygunbiletservice.model.enums.TripType;

public class PaymentCardRequest {

	private String cardNo;
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate expireDate;
	private Integer cvcNo;
	private Integer userId;
	private String name;
	private String phone;
	private Integer tripId;
	private TripType tripType;
	private Integer tripNo;
	private String departure;
	private String destination;
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate departureDate;
	@JsonSerialize(using = LocalTimeSerializer.class)
	@JsonDeserialize(using = LocalTimeDeserializer.class)
	private LocalTime departureTime;
	private String travelAgency;
	private List<PassengerSeatRequest> passengers;
	private Double price;

	public PaymentCardRequest() {
		super();
	}

	public PaymentCardRequest(String cardNo, LocalDate expireDate, Integer cvcNo, Integer userId, String name,
			String phone, Integer tripId, TripType tripType, Integer tripNo, String departure, String destination,
			LocalDate departureDate, LocalTime departureTime, String travelAgency,
			List<PassengerSeatRequest> passengers, Double price) {
		super();
		this.cardNo = cardNo;
		this.expireDate = expireDate;
		this.cvcNo = cvcNo;
		this.userId = userId;
		this.name = name;
		this.phone = phone;
		this.tripId = tripId;
		this.tripType = tripType;
		this.tripNo = tripNo;
		this.departure = departure;
		this.destination = destination;
		this.departureDate = departureDate;
		this.departureTime = departureTime;
		this.travelAgency = travelAgency;
		this.passengers = passengers;
		this.price = price;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public LocalDate getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(LocalDate expireDate) {
		this.expireDate = expireDate;
	}

	public Integer getCvcNo() {
		return cvcNo;
	}

	public void setCvcNo(Integer cvcNo) {
		this.cvcNo = cvcNo;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public List<PassengerSeatRequest> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<PassengerSeatRequest> passengers) {
		this.passengers = passengers;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "PaymentCardRequest [cardNo=" + cardNo + ", expireDate=" + expireDate + ", cvcNo=" + cvcNo + ", userId="
				+ userId + ", name=" + name + ", phone=" + phone + ", tripId=" + tripId + ", tripType=" + tripType
				+ ", tripNo=" + tripNo + ", departure=" + departure + ", destination=" + destination
				+ ", departureDate=" + departureDate + ", departureTime=" + departureTime + ", travelAgency="
				+ travelAgency + ", price=" + price + "]";
	}

}
