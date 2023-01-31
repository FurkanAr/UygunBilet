package com.uygunbilet.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class ExceptionResponse {

	private final String message;
	private final HttpStatus httpStatus;
	private final LocalDateTime timestamp;

	public ExceptionResponse(String message, HttpStatus httpStatus, LocalDateTime timestamp) {
		super();
		this.message = message;
		this.httpStatus = httpStatus;
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}


	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	
}
