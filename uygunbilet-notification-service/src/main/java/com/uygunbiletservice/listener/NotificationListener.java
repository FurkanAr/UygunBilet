package com.uygunbiletservice.listener;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uygunbiletservice.converter.MailConverter;
import com.uygunbiletservice.converter.SmsConverter;
import com.uygunbiletservice.model.Mail;
import com.uygunbiletservice.model.Sms;
import com.uygunbiletservice.repository.MailRepository;
import com.uygunbiletservice.repository.SmsRepository;
import com.uygunbiletservice.request.MailRequest;
import com.uygunbiletservice.request.SmsRequest;

@Component
public class NotificationListener {

	Logger logger = Logger.getLogger(NotificationListener.class.getName());

	@Autowired
	private MailRepository mailRepository;

	@Autowired
	private MailConverter mailConverter;

	@Autowired
	private SmsRepository smsRepository;

	@Autowired
	private SmsConverter smsConverter;

	/*
	 * It listens uygunbilet service when a new user created it sends to rabbitmq
	 * and notification service listener listens rabbitmq, and saves the mail to
	 * database
	 */
	@RabbitListener(queues = "uygunbilet.confirmation.notification")
	public void notificationListener(MailRequest mailRequest) {
		Mail mail = mailConverter.convert(mailRequest);
		mailRepository.save(mail);
		logger.log(Level.INFO, "Notification listener invoked - Consuming Message with EmailRequest Email: {0}",
				mailRequest.getEmail());
	}

	/*
	 * It listens uygunbilet-payment service when a new payment is successfull and
	 * created it sends to rabbitmq and notification service listener listens
	 * rabbitmq, and saves the sms to database
	 */
	@RabbitListener(queues = "uygunbilet.payment.notification")
	public void notificationListener(SmsRequest smsRequest) {
		Sms sms = smsConverter.convert(smsRequest);
		smsRepository.save(sms);
		System.out.println("sms: " + smsRequest);
		logger.log(Level.INFO, "Notification listener invoked - Consuming Message with SmsRequest Sms to user: {0}",
				smsRequest.getUserName());
	}

}
