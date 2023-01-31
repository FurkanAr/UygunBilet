package com.uygunbilet.request;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

public class CardPaymentRequest {

	private String cardNo;
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate expireDate;
	private Integer cvcNo;

	public CardPaymentRequest() {
		super();
	}

	public CardPaymentRequest(String cardNo, LocalDate expireDate, Integer cvcNo) {
		super();
		this.cardNo = cardNo;
		this.expireDate = expireDate;
		this.cvcNo = cvcNo;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public LocalDate getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(LocalDate expireDate) {
		this.expireDate = expireDate;
	}

	public Integer getCvcNo() {
		return cvcNo;
	}

	public void setCvcNo(Integer cvcNo) {
		this.cvcNo = cvcNo;
	}

	@Override
	public String toString() {
		return "CardPaymentRequest [cardNo=" + cardNo + ", expireDate=" + expireDate + ", cvcNo=" + cvcNo + "]";
	}

}
