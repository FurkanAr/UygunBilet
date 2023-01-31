package com.uygunbiletservice.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uygunbiletservice.request.PaymentCardRequest;
import com.uygunbiletservice.request.PaymentTransferRequest;
import com.uygunbiletservice.response.PaymentResponse;
import com.uygunbiletservice.service.PaymentService;

@RestController
@RequestMapping(value = "/payments")
public class PaymentController {

	Logger logger = Logger.getLogger(PaymentController.class.getName());

	@Autowired
	private PaymentService paymentService;

	// Returns payment response paid by card method
	@PostMapping(value = "/card")
	public ResponseEntity<PaymentResponse> payByCard(@RequestBody PaymentCardRequest paymentRequest) {
		PaymentResponse paymentResponse = paymentService.create(paymentRequest);
		logger.log(Level.INFO, "[PaymentController] -- card payment successful : {0}", paymentResponse);
		return new ResponseEntity<>(paymentResponse, HttpStatus.CREATED);
	}

	// Returns payment response paid by transfer method
	@PostMapping(value = "/transfer")
	public ResponseEntity<PaymentResponse> payByTransfer(@RequestBody PaymentTransferRequest paymentRequest) {
		PaymentResponse paymentResponse = paymentService.create(paymentRequest);
		logger.log(Level.INFO, "[PaymentController] -- transfer payment successful : {0}", paymentResponse);
		return new ResponseEntity<>(paymentResponse, HttpStatus.CREATED);
	}

	// Returns payments
	@GetMapping
	public ResponseEntity<List<PaymentResponse>> getAll() {
		List<PaymentResponse> paymentResponses = paymentService.getAll();
		logger.log(Level.INFO, "[PaymentController] -- all payments number of payments: {0}", paymentResponses.size());
		return new ResponseEntity<>(paymentResponses, HttpStatus.OK);

	}

}
