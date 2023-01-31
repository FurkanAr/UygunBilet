package com.uygunbilet.request;

public class TransferPaymentRequest {

	private String accountNumberFrom;
	private String accountNumberTo;
	private String firmName;

	public TransferPaymentRequest() {
		super();
	}

	public TransferPaymentRequest(String accountNumberFrom, String accountNumberTo, String firmName) {
		super();
		this.accountNumberFrom = accountNumberFrom;
		this.accountNumberTo = accountNumberTo;
		this.firmName = firmName;
	}

	public String getAccountNumberFrom() {
		return accountNumberFrom;
	}

	public void setAccountNumberFrom(String accountNumberFrom) {
		this.accountNumberFrom = accountNumberFrom;
	}

	public String getAccountNumberTo() {
		return accountNumberTo;
	}

	public void setAccountNumberTo(String accountNumberTo) {
		this.accountNumberTo = accountNumberTo;
	}

	public String getFirmName() {
		return firmName;
	}

	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}

	@Override
	public String toString() {
		return "TransferPaymentRequest [accountNumberFrom=" + accountNumberFrom + ", accountNumberTo=" + accountNumberTo
				+ ", firmName=" + firmName + "]";
	}

}
