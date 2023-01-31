package com.uygunbilet.request;

import com.uygunbilet.model.enums.UserGender;

public class PassengerRequest {

	private String firstName;
	private String lastName;
	private UserGender gender;
	private String identityNo;
	private Integer seatId;

	public PassengerRequest() {
		super();
	}

	public PassengerRequest(String firstName, String lastName, UserGender gender, String identityNo, Integer seatId
			) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.identityNo = identityNo;
		this.seatId = seatId;
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

	@Override
	public String toString() {
		return "PassengerRequest [firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", identityNo=" + identityNo + ", seatId=" + seatId + "]";
	}



}
