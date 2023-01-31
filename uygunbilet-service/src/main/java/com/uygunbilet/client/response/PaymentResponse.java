package com.uygunbilet.client.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.uygunbilet.model.enums.TripType;

public class PaymentResponse {

	private Integer id;
	private LocalDateTime createdDate;
	private Integer userId;
	private String name;
	private String phone;
	private Integer tripId;
	private TripType tripType;
	private Integer tripNo;
	private String departure;
	private String destination;
	private LocalDate departureDate;
	private LocalTime departureTime;
	private String travelAgency;
	private List<PassengerResponse> passengers;
	private Double price;

	
	
	public PaymentResponse(Integer id, LocalDateTime createdDate, Integer userId, String name, String phone,
			Integer tripId, TripType tripType, Integer tripNo, String departure, String destination,
			LocalDate departureDate, LocalTime departureTime, String travelAgency, List<PassengerResponse> passengers,
			Double price) {
		super();
		this.id = id;
		this.createdDate = createdDate;
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



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
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

	public List<PassengerResponse> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<PassengerResponse> passengers) {
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
		return "PaymentResponse [id=" + id + ", createdDate=" + createdDate + ", userId=" + userId + ", name=" + name
				+ ", phone=" + phone + ", tripId=" + tripId + ", tripType=" + tripType + ", tripNo=" + tripNo
				+ ", departure=" + departure + ", destination=" + destination + ", departureDate=" + departureDate
				+ ", departureTime=" + departureTime + ", travelAgency=" + travelAgency + ", price=" + price + "]";
	}

}
