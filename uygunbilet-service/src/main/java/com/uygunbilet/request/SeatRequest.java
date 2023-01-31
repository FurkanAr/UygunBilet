package com.uygunbilet.request;

import com.uygunbilet.model.enums.SeatType;

public class SeatRequest {
	private Integer seatNo;
	private SeatType type;
	private Integer tripId;

	public SeatRequest() {
		super();
	}

	public SeatRequest(Integer seatNo, SeatType type, Integer tripId) {
		super();
		this.seatNo = seatNo;
		this.type = type;
		this.tripId = tripId;
	}

	public Integer getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(Integer seatNo) {
		this.seatNo = seatNo;
	}

	public SeatType getType() {
		return type;
	}

	public void setType(SeatType type) {
		this.type = type;
	}

	public Integer getTripId() {
		return tripId;
	}

	public void setTripId(Integer tripId) {
		this.tripId = tripId;
	}

	@Override
	public String toString() {
		return "SeatRequest [seatNo=" + seatNo + ", type=" + type + ", tripId=" + tripId + "]";
	}

}
