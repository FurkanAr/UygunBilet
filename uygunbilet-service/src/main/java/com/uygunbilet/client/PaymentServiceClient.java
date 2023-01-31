package com.uygunbilet.client;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.uygunbilet.client.request.PaymentCardRequest;
import com.uygunbilet.client.request.PaymentTransferRequest;
import com.uygunbilet.client.response.PaymentResponse;

/*
 * We send payment request to payment service, this class is feign client 
 * and it help us to ticket payment steps.
 */

@FeignClient(value = "uygunbilet-payment", url = "http://localhost:8081")
public interface PaymentServiceClient {

	@PostMapping(value = "/payments/card")
	PaymentResponse create(@RequestBody PaymentCardRequest paymentRequest);
	
	@PostMapping(value = "/payments/transfer")
	PaymentResponse create(@RequestBody PaymentTransferRequest paymentRequest);

}
