package com.uygunbilet.client.request;

import com.uygunbilet.model.enums.SeatType;
import com.uygunbilet.model.enums.UserGender;

public class PassengerSeatRequest {

	private String firstName;
	private String lastName;
	private UserGender gender;
	private String identityNo;
	private Integer seatId;
	private Integer seatNo;
	private SeatType seatType;

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
		return "PassengerSeatRequest [firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", identityNo=" + identityNo + ", seatId=" + seatId + ", seatNo=" + seatNo + ", seatType=" + seatType
				+ "]";
	}

}
