package com.uygunbilet.converter;

import org.springframework.stereotype.Component;

import com.uygunbilet.request.MailRequest;
import com.uygunbilet.request.UserRequest;

/* 
 * This component creates us new mail request whic we used for
 * sending hi welcome mail to new users.
 */

@Component
public class MailConverter {

	public MailRequest convert(UserRequest userRequest) {
		MailRequest mailRequest = new MailRequest();
		mailRequest.setEmail(userRequest.getEmail());
		mailRequest.setUserName(userRequest.getName());
		return mailRequest;
	}

}
