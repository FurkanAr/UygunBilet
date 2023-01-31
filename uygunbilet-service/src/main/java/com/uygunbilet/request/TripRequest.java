package com.uygunbilet.request;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.uygunbilet.model.enums.TripType;

public class TripRequest {
	private Integer userId;
	private Integer no;
	private TripType type;
	private String departure;
	private String destination;
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate departureDate;
	@JsonSerialize(using = LocalTimeSerializer.class)
	@JsonDeserialize(using = LocalTimeDeserializer.class)
	private LocalTime departureTime;
	private String travelAgency;
	private Double ticketPrice;

	public TripRequest() {
		super();
	}

	public TripRequest(Integer userId, Integer no, TripType type, String departure, String destination,
			LocalDate departureDate, LocalTime departureTime, String travelAgency, Double ticketPrice) {
		super();
		this.userId = userId;
		this.no = no;
		this.type = type;
		this.departure = departure;
		this.destination = destination;
		this.departureDate = departureDate;
		this.departureTime = departureTime;
		this.travelAgency = travelAgency;
		this.ticketPrice = ticketPrice;
	}



	public TripRequest(int i, int j, TripType bus, String string, String string2, String string3, String string4,
			String string5, double d) {
		// TODO Auto-generated constructor stub
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public TripType getType() {
		return type;
	}

	public void setType(TripType type) {
		this.type = type;
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

	public Double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(Double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	@Override
	public String toString() {
		return "TripRequest [userId=" + userId + ", no=" + no + ", type=" + type + ", departure=" + departure
				+ ", destination=" + destination + ", departureDate=" + departureDate + ", departureTime="
				+ departureTime + ", travelAgency=" + travelAgency + ", ticketPrice=" + ticketPrice + "]";
	}

}
