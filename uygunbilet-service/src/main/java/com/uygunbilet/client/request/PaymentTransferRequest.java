package com.uygunbilet.client.request;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.uygunbilet.model.enums.TripType;

public class PaymentTransferRequest {

	private String accountNumberFrom;
	private String accountNumberTo;
	private String firmName;
	private Integer userId;
	private String name;
	private String phone;
	private TripType tripType;
	private Integer tripNo;
	private String departure;
	private String destination;
	private LocalDate departureDate;
	private LocalTime departureTime;
	private String travelAgency;
	private Integer tripId;
	private List<PassengerSeatRequest> passengers;
	private Double price;

	public String getAccountNumberFrom() {
		return accountNumberFrom;
	}

	public void setAccountNumberFrom(String accountNumberFrom) {
		this.accountNumberFrom = accountNumberFrom;
	}

	public String getAccountNumberTo() {
		return accountNumberTo;
	}

	public void setAccountNumberTo(String accountNumberTo) {
		this.accountNumberTo = accountNumberTo;
	}

	public String getFirmName() {
		return firmName;
	}

	public void setFirmName(String firmName) {
		this.firmName = firmName;
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
		return "PaymentTransferRequest [accountNumberFrom=" + accountNumberFrom + ", accountNumberTo=" + accountNumberTo
				+ ", firmName=" + firmName + ", userId=" + userId + ", name=" + name + ", phone=" + phone
				+ ", tripType=" + tripType + ", tripNo=" + tripNo + ", departure=" + departure + ", destination="
				+ destination + ", departureDate=" + departureDate + ", departureTime=" + departureTime
				+ ", travelAgency=" + travelAgency + ", tripId=" + tripId + ", price=" + price + "]";
	}

}
