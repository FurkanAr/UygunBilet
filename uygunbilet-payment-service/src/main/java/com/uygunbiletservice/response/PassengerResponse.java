package com.uygunbiletservice.response;

import com.uygunbiletservice.model.enums.SeatType;
import com.uygunbiletservice.model.enums.UserGender;

public class PassengerResponse {

	private Integer id;
	private String firstName;
	private String lastName;
	private UserGender gender;
	private String identityNo;
	private Integer seatId;
	private Integer seatNo;
	private SeatType seatType;

	public PassengerResponse() {
		super();
	}

	public PassengerResponse(Integer id, String firstName, String lastName, UserGender gender, String identityNo,
			Integer seatId, Integer seatNo, SeatType seatType) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.identityNo = identityNo;
		this.seatId = seatId;
		this.seatNo = seatNo;
		this.seatType = seatType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public SeatType getSeatType() {
		return seatType;
	}

	public void setSeatType(SeatType seatType) {
		this.seatType = seatType;
	}

	@Override
	public String toString() {
		return "PassengerResponse [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", gender="
				+ gender + ", identityNo=" + identityNo + ", seatId=" + seatId + ", seatNo=" + seatNo + ", seatType="
				+ seatType + "]";
	}

}
