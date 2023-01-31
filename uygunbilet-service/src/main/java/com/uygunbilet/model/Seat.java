package com.uygunbilet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.uygunbilet.model.enums.SeatStatus;
import com.uygunbilet.model.enums.SeatType;
import com.uygunbilet.model.enums.UserGender;

@Entity
@Table(name = "seat")
public class Seat {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Integer id;
	@Column(name = "seat_no", nullable = false)
	private Integer seatNo;
	@Column(name = "seat_type")
	@Enumerated(EnumType.STRING)
	private SeatType seatType;
	@Column(name = "seat_status")
	@Enumerated(EnumType.STRING)
	private SeatStatus seatStatus;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "gender")
	@Enumerated(EnumType.STRING)
	private UserGender gender;
	@Column(name = "identity_number")
	private String identityNo;
	@ManyToOne
	@JoinColumn(name = "trip_id", referencedColumnName = "id")
	private Trip trip;
	@OneToOne(fetch = FetchType.LAZY)
	private Ticket ticket;

	public Seat() {
		super();
	}

	public Seat(Integer seatNo, SeatType seatType, SeatStatus seatStatus, Trip trip) {
		super();
		this.seatNo = seatNo;
		this.seatType = seatType;
		this.seatStatus = seatStatus;
		this.trip = trip;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(Integer seatNo) {
		this.seatNo = seatNo;
	}

	public SeatType getSeatType() {
		return seatType;
	}

	public void setSeatType(SeatType seatType) {
		this.seatType = seatType;
	}

	public SeatStatus getSeatStatus() {
		return seatStatus;
	}

	public void setSeatStatus(SeatStatus seatStatus) {
		this.seatStatus = seatStatus;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public UserGender getGender() {
		return gender;
	}

	public void setGender(UserGender gender) {
		this.gender = gender;
	}

	public String getIdentityNo() {
		return identityNo;
	}

	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}

	@Override
	public String toString() {
		return "Seat [id=" + id + ", seatNo=" + seatNo + ", seatType=" + seatType + ", seatStatus=" + seatStatus
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender + ", identityNo="
				+ identityNo + ", trip=" + trip + ", ticket=" + ticket + "]";
	}

}
