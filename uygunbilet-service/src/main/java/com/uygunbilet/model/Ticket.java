package com.uygunbilet.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ticket")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Integer id;
	@Column(name = "create_date")
	private LocalDateTime createDate;
	@ManyToOne
	@JoinColumn(name = "trip_id", referencedColumnName = "id")
	private Trip trip;
	@OneToOne
	@JoinColumn(name = "seat_id", referencedColumnName = "id")
	private Seat seat;
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	@Column(name = "ticket_price")
	private Double ticketPrice;

	public Ticket() {
		super();
	}

	public Ticket(LocalDateTime createDate, Trip trip, Seat seat, User user, Double ticketPrice) {
		super();
		this.createDate = createDate;
		this.trip = trip;
		this.seat = seat;
		this.user = user;
		this.ticketPrice = ticketPrice;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(Double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

}
