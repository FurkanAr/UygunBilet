package com.uygunbiletservice.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uygunbiletservice.configuration.RabbitMQConfiguration;
import com.uygunbiletservice.converter.PassengerConverter;
import com.uygunbiletservice.converter.PaymentConverter;
import com.uygunbiletservice.converter.SmsConverter;
import com.uygunbiletservice.model.Passenger;
import com.uygunbiletservice.model.Payment;
import com.uygunbiletservice.repository.PassengerRepository;
import com.uygunbiletservice.repository.PaymentRepository;
import com.uygunbiletservice.request.PaymentCardRequest;
import com.uygunbiletservice.request.PaymentTransferRequest;
import com.uygunbiletservice.request.SmsRequest;
import com.uygunbiletservice.response.PassengerResponse;
import com.uygunbiletservice.response.PaymentResponse;

@Service
public class PaymentService {

	Logger logger = Logger.getLogger(PaymentService.class.getName());

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private PassengerRepository passengerRepository;

	@Autowired
	private PaymentConverter paymentConverter;

	@Autowired
	private PassengerConverter passengerConverter;

	@Autowired
	private SmsConverter smsConverter;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private RabbitMQConfiguration rabbitMQConfiguration;

	/*
	 * CardPayment requests from the eligible ticket application go here, if the
	 * conditions are met, the payment is successfully recorded and a refund summary
	 * is returned.
	 */
	public PaymentResponse create(PaymentCardRequest paymentRequest) {
		Payment payment = paymentConverter.convert(paymentRequest);
		Payment savedPayment = paymentRepository.save(payment);
		
		List<Passenger> passengers = passengerConverter.convert(paymentRequest.getPassengers(), savedPayment);
		List<Passenger> savedPassengers = passengerRepository.saveAll(passengers);
		
		savedPayment.setPassengerList(savedPassengers);
	
		logger.log(Level.INFO, "[PaymentService] - payment: {0}, passengers: {1}",
				new Object[] { savedPayment, savedPassengers });
	
		PaymentResponse paymentResponse = paymentConverter.convert(savedPayment);
		List<PassengerResponse> passengerResponses = passengerConverter.convert(savedPassengers);
		
		logger.log(Level.INFO, "[PaymentService] -  card method payment respons: {0}, passenger responsess: {1}",
				new Object[] { paymentResponse, passengerResponses });
		
		rabbitSender(savedPayment);
		return paymentResponse;
	}

	/*
	 * TransferPayment requests from the eligible ticket application go here, if the
	 * conditions are met, the payment is successfully recorded and a refund summary
	 * is returned.
	 */
	public PaymentResponse create(PaymentTransferRequest paymentRequest) {
		Payment payment = paymentConverter.convert(paymentRequest);
		Payment savedPayment = paymentRepository.save(payment);
		
		List<Passenger> passengers = passengerConverter.convert(paymentRequest.getPassengers(), savedPayment);
		List<Passenger> savedPassengers = passengerRepository.saveAll(passengers);
		
		savedPayment.setPassengerList(savedPassengers);
		
		logger.log(Level.INFO, "[PaymentService] - payment: {0}, passengers: {1}",
				new Object[] { savedPayment, savedPassengers });
	
		PaymentResponse paymentResponse = paymentConverter.convert(savedPayment);
		List<PassengerResponse> passengerResponses = passengerConverter.convert(savedPassengers);
		
		logger.log(Level.INFO, "[PaymentService] -  card method payment respons: {0}, passenger responsess: {1}",
				new Object[] { paymentResponse, passengerResponses });
		
		rabbitSender(savedPayment);
		return paymentResponse;
	}

	// If the payment is successful, travel information is sent to notification
	// service.
	public void rabbitSender(Payment payment) {
		SmsRequest smsRequest = smsConverter.convert(payment);
		rabbitTemplate.convertAndSend(rabbitMQConfiguration.getQueueName(), smsRequest);
		logger.log(Level.WARNING, "[PaymentService] - smsRequest: {0}, sent to : {1}",
				new Object[] { smsRequest, rabbitMQConfiguration.getQueueName() });
	}

	// Returns successful payments
	public List<PaymentResponse> getAll() {
		List<Payment> payments = paymentRepository.findAll();
		logger.log(Level.INFO, "[PaymentService] - payment list number of payments: " + payments.size());
		return paymentConverter.convert(payments);
	}

}
