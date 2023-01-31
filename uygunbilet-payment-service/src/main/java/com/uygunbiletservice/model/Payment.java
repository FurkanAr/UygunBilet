package com.uygunbiletservice.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

import com.uygunbiletservice.model.enums.PaymentType;
import com.uygunbiletservice.model.enums.TripType;

@Entity
@Table(name = "payment")
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Integer id;
	@Column(name = "create_date")
	private LocalDateTime createdDate;
	@Column(name = "user_id")
	private Integer userId;
	@Column(name = "user_name")
	private String userName;
	@Column(name = "user_phone")
	private String userPhone;
	@Column(name = "trip_type")
	@Enumerated(EnumType.STRING)
	private TripType tripType;
	@Column(name = "payment_type")
	@Enumerated(EnumType.STRING)
	private PaymentType paymentType;
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
	@Column(name = "trip_id")
	private Integer tripId;
	@OneToMany(mappedBy = "payment")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Passenger> passengerList;
	@Column(name = "price")
	private Double price;

	public Payment() {
		super();
	}

	public Payment(LocalDateTime createdDate, Integer userId, String userName, String userPhone, TripType tripType,
			PaymentType paymentType, Integer tripNo, String departure, String destination, LocalDate departureDate,
			LocalTime departureTime, String travelAgency, Integer tripId, Double price) {
		super();
		this.createdDate = createdDate;
		this.userId = userId;
		this.userName = userName;
		this.userPhone = userPhone;
		this.tripType = tripType;
		this.paymentType = paymentType;
		this.tripNo = tripNo;
		this.departure = departure;
		this.destination = destination;
		this.departureDate = departureDate;
		this.departureTime = departureTime;
		this.travelAgency = travelAgency;
		this.tripId = tripId;
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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

	public List<Passenger> getPassengerList() {
		return passengerList;
	}

	public void setPassengerList(List<Passenger> passengerList) {
		this.passengerList = passengerList;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", createdDate=" + createdDate + ", userId=" + userId + ", userName=" + userName
				+ ", userPhone=" + userPhone + ", tripType=" + tripType + ", paymentType=" + paymentType + ", tripNo="
				+ tripNo + ", departure=" + departure + ", destination=" + destination + ", departureDate="
				+ departureDate + ", departureTime=" + departureTime + ", travelAgency=" + travelAgency + ", tripId="
				+ tripId + ", price=" + price + "]";
	}

}
