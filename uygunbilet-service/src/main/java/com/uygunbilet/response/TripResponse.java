package com.uygunbilet.response;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.uygunbilet.model.enums.TripType;

public class TripResponse {

	private Integer id;
	private TripType type;
	private Integer no;
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
	private Integer capacity;
	private List<SeatResponse> seatList;

	public TripResponse() {
		super();
	}

	public TripResponse(Integer id, TripType type, Integer no, String departure, String destination,
			LocalDate departureDate, LocalTime departureTime, String travelAgency, Double ticketPrice, Integer capacity,
			List<SeatResponse> seatList) {
		super();
		this.id = id;
		this.type = type;
		this.no = no;
		this.departure = departure;
		this.destination = destination;
		this.departureDate = departureDate;
		this.departureTime = departureTime;
		this.travelAgency = travelAgency;
		this.ticketPrice = ticketPrice;
		this.capacity = capacity;
		this.seatList = seatList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TripType getType() {
		return type;
	}

	public void setType(TripType type) {
		this.type = type;
	}

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
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

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public List<SeatResponse> getSeatList() {
		return seatList;
	}

	public void setSeatList(List<SeatResponse> seatList) {
		this.seatList = seatList;
	}

	@Override
	public String toString() {
		return "TripResponse [id=" + id + ", type=" + type + ", no=" + no + ", departure=" + departure
				+ ", destination=" + destination + ", departureDate=" + departureDate + ", departureTime="
				+ departureTime + ", travelAgency=" + travelAgency + ", ticketPrice=" + ticketPrice + ", capacity="
				+ capacity + "]";
	}

	
	
}
