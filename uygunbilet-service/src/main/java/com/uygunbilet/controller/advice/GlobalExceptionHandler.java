package com.uygunbilet.controller.advice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.uygunbilet.exception.ExceptionResponse;
import com.uygunbilet.exception.payment.PaymentRefusedException;
import com.uygunbilet.exception.seat.SeatNotEmptyException;
import com.uygunbilet.exception.seat.SeatNotFoundException;
import com.uygunbilet.exception.ticket.CorporateUserTicketSizeException;
import com.uygunbilet.exception.ticket.IndividualUserTicketSizeException;
import com.uygunbilet.exception.ticket.IndıvıdualUserGenderException;
import com.uygunbilet.exception.trip.TripNotFoundException;
import com.uygunbilet.exception.user.UserEmailAlreadyInUseException;
import com.uygunbilet.exception.user.UserHasNotPermissionException;
import com.uygunbilet.exception.user.UserNotFoundException;

// Handles custom exceptions

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handle(UserNotFoundException exception) {
		return ResponseEntity
				.ok(new ExceptionResponse(exception.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()));
	}

	@ExceptionHandler(UserEmailAlreadyInUseException.class)
	public ResponseEntity<ExceptionResponse> handle(UserEmailAlreadyInUseException exception) {
		return ResponseEntity
				.ok(new ExceptionResponse(exception.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()));
	}

	@ExceptionHandler(UserHasNotPermissionException.class)
	public ResponseEntity<ExceptionResponse> handle(UserHasNotPermissionException exception) {
		return ResponseEntity
				.ok(new ExceptionResponse(exception.getMessage(), HttpStatus.UNAUTHORIZED, LocalDateTime.now()));
	}
	
	@ExceptionHandler(TripNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handle(TripNotFoundException exception) {
		return ResponseEntity
				.ok(new ExceptionResponse(exception.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()));
	}
	
	@ExceptionHandler(PaymentRefusedException.class)
	public ResponseEntity<ExceptionResponse> handle(PaymentRefusedException exception) {
		return ResponseEntity
				.ok(new ExceptionResponse(exception.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()));
	}

	@ExceptionHandler(SeatNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handle(SeatNotFoundException exception) {
		return ResponseEntity
				.ok(new ExceptionResponse(exception.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()));
	}
	
	@ExceptionHandler(SeatNotEmptyException.class)
	public ResponseEntity<ExceptionResponse> handle(SeatNotEmptyException exception) {
		return ResponseEntity
				.ok(new ExceptionResponse(exception.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()));
	}
	
	
	@ExceptionHandler(IndıvıdualUserGenderException.class)
	public ResponseEntity<ExceptionResponse> handle(IndıvıdualUserGenderException exception) {
		return ResponseEntity
				.ok(new ExceptionResponse(exception.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()));
	}

	@ExceptionHandler(IndividualUserTicketSizeException.class)
	public ResponseEntity<ExceptionResponse> handle(IndividualUserTicketSizeException exception) {
		return ResponseEntity
				.ok(new ExceptionResponse(exception.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()));
	}
	
	@ExceptionHandler(CorporateUserTicketSizeException.class)
	public ResponseEntity<ExceptionResponse> handle(CorporateUserTicketSizeException exception) {
		return ResponseEntity
				.ok(new ExceptionResponse(exception.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()));
	}


	
}
