package com.uygunbilet.request;

import java.util.List;

import com.uygunbilet.model.enums.PaymentType;

public class TicketRequest {

	private PaymentType type;
	private TransferPaymentRequest transferRequest;
	private CardPaymentRequest cardRequest;
	private Integer userId;
	private Integer tripId;
	private List<PassengerRequest> seats;

	public TicketRequest() {
		super();
	}

	public TicketRequest(PaymentType type, TransferPaymentRequest transferRequest, CardPaymentRequest cardRequest,
			Integer userId, Integer tripId, List<PassengerRequest> seats) {
		super();
		this.type = type;
		this.transferRequest = transferRequest;
		this.cardRequest = cardRequest;
		this.userId = userId;
		this.tripId = tripId;
		this.seats = seats;
	}

	public PaymentType getType() {
		return type;
	}

	public void setType(PaymentType type) {
		this.type = type;
	}

	public TransferPaymentRequest getTransferRequest() {
		return transferRequest;
	}

	public void setTransferRequest(TransferPaymentRequest transferRequest) {
		this.transferRequest = transferRequest;
	}

	public CardPaymentRequest getCardRequest() {
		return cardRequest;
	}

	public void setCardRequest(CardPaymentRequest cardRequest) {
		this.cardRequest = cardRequest;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getTripId() {
		return tripId;
	}

	public void setTripId(Integer tripId) {
		this.tripId = tripId;
	}

	public List<PassengerRequest> getSeats() {
		return seats;
	}

	public void setSeats(List<PassengerRequest> seats) {
		this.seats = seats;
	}

	@Override
	public String toString() {
		return "TicketRequest [type=" + type + ", transferRequest=" + transferRequest + ", cardRequest=" + cardRequest
				+ ", userId=" + userId + ", tripId=" + tripId + "]";
	}

}
