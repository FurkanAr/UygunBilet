package com.uygunbilet.response;

import com.uygunbilet.model.enums.SeatStatus;
import com.uygunbilet.model.enums.SeatType;

public class SeatResponse {

	private Integer seatId;
	private Integer seatNo;
	private SeatType type;
	private SeatStatus status;
	private Integer tripId;

	public SeatResponse() {
		super();
	}

	public SeatResponse(Integer seatId, Integer seatNo, SeatType type, SeatStatus status, Integer tripId) {
		super();
		this.seatId = seatId;
		this.seatNo = seatNo;
		this.type = type;
		this.status = status;
		this.tripId = tripId;
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

	public SeatType getType() {
		return type;
	}

	public void setType(SeatType type) {
		this.type = type;
	}

	public SeatStatus getStatus() {
		return status;
	}

	public void setStatus(SeatStatus status) {
		this.status = status;
	}

	public Integer getTripId() {
		return tripId;
	}

	public void setTripId(Integer tripId) {
		this.tripId = tripId;
	}

	@Override
	public String toString() {
		return "SeatResponse [seatId=" + seatId + ", seatNo=" + seatNo + ", type=" + type + ", status=" + status
				+ ", tripId=" + tripId + "]";
	}

}
