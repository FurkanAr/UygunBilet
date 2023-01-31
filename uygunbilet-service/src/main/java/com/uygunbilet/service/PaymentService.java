package com.uygunbilet.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uygunbilet.client.PaymentServiceClient;
import com.uygunbilet.client.converter.PaymentConverter;
import com.uygunbilet.client.request.PaymentCardRequest;
import com.uygunbilet.client.request.PaymentTransferRequest;
import com.uygunbilet.client.response.PaymentResponse;
import com.uygunbilet.exception.payment.PaymentRefusedException;
import com.uygunbilet.model.Seat;
import com.uygunbilet.model.Trip;
import com.uygunbilet.model.User;
import com.uygunbilet.model.enums.PaymentType;
import com.uygunbilet.request.TicketRequest;

@Service
public class PaymentService {

	Logger logger = Logger.getLogger(PaymentService.class.getName());

	@Autowired
	private PaymentConverter paymentConverter;

	@Autowired
	private PaymentServiceClient paymentServiceClient;

	/*
	 * If everything is okay for ticket request then this method called by ticket
	 * service There are 2 types of payment methods. The first is by card and the
	 * second is by transfer. The payment request goes to the payment service that
	 * provides a separate service, and if the payment is successful, the refund
	 * summary is returned to the user after purchasing the ticket on this screen.
	 */

	public PaymentResponse createPayment(TicketRequest request, User user, Trip trip, List<Seat> seatNumbers) {
		PaymentType paymentType = request.getType();
		logger.log(Level.INFO, "[PaymentService] - paymentType: {0}", paymentType);

		switch (paymentType) {
		case TRANSFER:
			PaymentTransferRequest paymentTransferRequest = paymentConverter.convert(request.getTransferRequest(), user,
					trip, seatNumbers);
			PaymentResponse paymentTransferResponse = paymentServiceClient.create(paymentTransferRequest);
			logger.log(Level.INFO, "[PaymentService] - transfer method payment response: {0}", paymentTransferResponse);
			return paymentTransferResponse;

		case CARD:
			PaymentCardRequest paymentCardRequest = paymentConverter.convert(request.getCardRequest(), user, trip,
					seatNumbers);
			PaymentResponse paymentCardResponse = paymentServiceClient.create(paymentCardRequest);
			logger.log(Level.INFO, "[PaymentService] - card method payment response: {0}", paymentCardResponse);
			return paymentCardResponse;
		}
		logger.log(Level.INFO, "[PaymentService] - Payment refused, user id: {0}", user.getId());
		throw new PaymentRefusedException("Payment refused!! ");
	}

}
