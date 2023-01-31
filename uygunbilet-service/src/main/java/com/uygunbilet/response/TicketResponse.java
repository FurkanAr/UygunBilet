package com.uygunbilet.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.uygunbilet.model.enums.SeatStatus;
import com.uygunbilet.model.enums.SeatType;
import com.uygunbilet.model.enums.TripType;
import com.uygunbilet.model.enums.UserGender;

public class TicketResponse {

	private Integer ticketId;
	private LocalDateTime createDate;
	private Integer tripId;
	private TripType tripType;
	private Integer tripNo;
	private String departure;
	private String destination;
	private LocalDate departureDate;
	private LocalTime departureTime;
	private String travelAgency;
	private String firstName;
	private String lastName;
	private UserGender gender;
	private String identityNo;
	private Integer seatId;
	private Integer seatNo;
	private SeatStatus seatStatus;
	private SeatType seatType;
	private Integer userId;
	private String userName;
	private String userEmail;
	private Double ticketPrice;

	public TicketResponse() {
		super();
	}

	public TicketResponse(Integer ticketId, LocalDateTime createDate, Integer tripId, TripType tripType, Integer tripNo,
			String departure, String destination, LocalDate departureDate, LocalTime departureTime, String travelAgency,
			String firstName, String lastName, UserGender gender, String identityNo, Integer seatId, Integer seatNo,
			SeatStatus seatStatus, SeatType seatType, Integer userId, String userName, String userEmail,
			Double ticketPrice) {
		super();
		this.ticketId = ticketId;
		this.createDate = createDate;
		this.tripId = tripId;
		this.tripType = tripType;
		this.tripNo = tripNo;
		this.departure = departure;
		this.destination = destination;
		this.departureDate = departureDate;
		this.departureTime = departureTime;
		this.travelAgency = travelAgency;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.identityNo = identityNo;
		this.seatId = seatId;
		this.seatNo = seatNo;
		this.seatStatus = seatStatus;
		this.seatType = seatType;
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.ticketPrice = ticketPrice;
	}


	public Integer getTicketId() {
		return ticketId;
	}

	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
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

	public Integer getSeatId() {
		return seatId;
	}

	public void setSeatId(Integer seatId) {
		this.seatId = seatId;
	}

	public Integer getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(Integer seatNo) {
		this.seatNo = seatNo;
	}

	public SeatStatus getSeatStatus() {
		return seatStatus;
	}

	public void setSeatStatus(SeatStatus seatStatus) {
		this.seatStatus = seatStatus;
	}

	public SeatType getSeatType() {
		return seatType;
	}

	public void setSeatType(SeatType seatType) {
		this.seatType = seatType;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(Double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	@Override
	public String toString() {
		return "TicketResponse [ticketId=" + ticketId + ", createDate=" + createDate + ", tripId=" + tripId
				+ ", tripType=" + tripType + ", tripNo=" + tripNo + ", departure=" + departure + ", destination="
				+ destination + ", departureDate=" + departureDate + ", departureTime=" + departureTime
				+ ", travelAgency=" + travelAgency + ", firstName=" + firstName + ", lastName=" + lastName + ", gender="
				+ gender + ", identityNo=" + identityNo + ", seatId=" + seatId + ", seatNo=" + seatNo + ", seatStatus="
				+ seatStatus + ", seatType=" + seatType + ", userId=" + userId + ", userName=" + userName
				+ ", userEmail=" + userEmail + ", ticketPrice=" + ticketPrice + "]";
	}

}
