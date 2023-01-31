package com.uygunbilet.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.uygunbilet.model.enums.TripType;

@Entity
@Table(name = "trip")
public class Trip {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Integer id;
	@Column(name = "trip_type", nullable = false)
	@Enumerated(EnumType.STRING)
	private TripType type;
	@Column(name = "trip_no", nullable = false)
	private Integer no;
	@Column(name = "departure", nullable = false)
	private String departure;
	@Column(name = "destination", nullable = false)
	private String destination;
	@Column(name = "departure_date", nullable = false)
	private LocalDate departureDate;
	@Column(name = "departure_time", nullable = false)
	private LocalTime departureTime;
	@Column(name = "travel_agency", nullable = false)
	private String travelAgency;
	@Column(name = "ticket_price", nullable = false)
	private Double ticketPrice;
	@Column(name = "capacity")
	private Integer capacity;
	@OneToMany(mappedBy = "trip")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Seat> seatList;
	@OneToMany(mappedBy = "trip")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Ticket> ticketList;

	public Trip() {
		super();
	}

	public Trip(TripType type, Integer no, String departure, String destination, LocalDate departureDate,
			LocalTime departureTime, String travelAgency, Double ticketPrice) {
		super();
		this.type = type;
		this.no = no;
		this.departure = departure;
		this.destination = destination;
		this.departureDate = departureDate;
		this.departureTime = departureTime;
		this.travelAgency = travelAgency;
		this.ticketPrice = ticketPrice;
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

	public List<Seat> getSeatList() {
		return seatList;
	}

	public void setSeatList(List<Seat> seatList) {
		this.seatList = seatList;
	}

	public List<Ticket> getTicketList() {
		return ticketList;
	}

	public void setTicketList(List<Ticket> ticketList) {
		this.ticketList = ticketList;
	}

	@Override
	public String toString() {
		return "Trip [id=" + id + ", type=" + type + ", no=" + no + ", departure=" + departure + ", destination="
				+ destination + ", departureDate=" + departureDate + ", departureTime=" + departureTime
				+ ", travelAgency=" + travelAgency + ", ticketPrice=" + ticketPrice + ", capacity=" + capacity + "]";
	}

}
