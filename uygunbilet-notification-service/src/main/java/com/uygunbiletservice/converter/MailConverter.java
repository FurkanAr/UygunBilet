package com.uygunbiletservice.converter;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.uygunbiletservice.model.Mail;
import com.uygunbiletservice.request.MailRequest;

/*
 * The mail request from the uygunbilet service is saved here and sent to the users.
 */

@Component
public class MailConverter {

	private static final String COMPANY = "Uygunbilet";
	private static final String TITLE = "Welcome to " + COMPANY;
	private static final String CONTENT = "Your account created succesfully";

	public Mail convert(MailRequest mailRequest) {
		Mail mail = new Mail();
		mail.setCompany(COMPANY);
		mail.setTitle(TITLE);
		mail.setContent(CONTENT);
		mail.setEmail(mailRequest.getEmail());
		mail.setUserName(mailRequest.getUserName());
		mail.setCreateDate(LocalDateTime.now());
		return mail;

	}

}