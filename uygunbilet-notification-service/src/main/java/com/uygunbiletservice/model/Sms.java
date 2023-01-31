package com.uygunbiletservice.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sms")
public class Sms {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Integer id;
	@Column(name = "create_date")
	private LocalDateTime createDate;
	@Column(name = "user_name")
	private String userName;
	@Column(name = "user_phone")
	private String userPhone;
	@Column(name = "company")
	private String company;
	@Column(name = "trip_id")
	private Integer tripId;
	@Column(name = "trip_type")
	@Enumerated(EnumType.STRING)
	private TripType type;
	@Column(name = "trip_no")
	private Integer tripNo;
	@Column(name = "departure")
	private String departure;
	@Column(name = "destination")
	private String destination;
	@Column(name = "departure_date")
	private LocalDate departureDate;
	@Column(name = "departure_time")
	private LocalTime departureTime;
	@Column(name = "travel_agency")
	private String travelAgency;
	@Column(name = "price")
	private Double price;
	@Column(name = "seat_no")
	@ElementCollection(targetClass = Integer.class)
	private List<Integer> seatNo;

	public Sms() {
		super();
	}

	public Sms(LocalDateTime createDate, String userName, String userPhone, String company, Integer tripId,
			TripType type, Integer tripNo, String departure, String destination, LocalDate departureDate,
			LocalTime departureTime, String travelAgency, Double price, List<Integer> seatNo) {
		super();
		this.createDate = createDate;
		this.userName = userName;
		this.userPhone = userPhone;
		this.company = company;
		this.tripId = tripId;
		this.type = type;
		this.tripNo = tripNo;
		this.departure = departure;
		this.destination = destination;
		this.departureDate = departureDate;
		this.departureTime = departureTime;
		this.travelAgency = travelAgency;
		this.price = price;
		this.seatNo = seatNo;
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

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Integer getTripId() {
		return tripId;
	}

	public void setTripId(Integer tripId) {
		this.tripId = tripId;
	}

	public TripType getType() {
		return type;
	}

	public void setType(TripType type) {
		this.type = type;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public List<Integer> getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(List<Integer> seatNo) {
		this.seatNo = seatNo;
	}

	@Override
	public String toString() {
		return "Sms [id=" + id + ", createDate=" + createDate + ", userName=" + userName + ", userPhone=" + userPhone
				+ ", company=" + company + ", tripId=" + tripId + ", type=" + type + ", tripNo=" + tripNo
				+ ", departure=" + departure + ", destination=" + destination + ", departureDate=" + departureDate
				+ ", departureTime=" + departureTime + ", travelAgency=" + travelAgency + ", price=" + price + "]";
	}

}
