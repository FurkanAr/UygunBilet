package com.uygunbilet.validator;

import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.uygunbilet.exception.ticket.CorporateUserTicketSizeException;
import com.uygunbilet.exception.ticket.IndividualUserTicketSizeException;
import com.uygunbilet.exception.ticket.IndıvıdualUserGenderException;
import com.uygunbilet.model.User;
import com.uygunbilet.model.enums.UserGender;
import com.uygunbilet.request.PassengerRequest;

/*
 * An individual user can purchase a maximum of 5 tickets for the same trip.
 * Individual users can purchase tickets for a maximum of 2 male passengers in a single order
 * Corporate user can buy a maximum of 20 tickets for the same trip.
 */

public class UserTicketValidator {

	private static final Integer MAX_INDIVIDUAL_USER_TICKET_SIZE = 5;
	private static final Integer MAX_CORPORATE_USER_TICKET_SIZE = 20;
	private static final Integer INDIVIDUAL_USER_MALE_TICKET_SIZE = 2;

	static Logger logger = Logger.getLogger(UserTicketValidator.class.getName());

	private UserTicketValidator() {

	}

	public static void validateIndividualUserTickets(User user, Integer totalTicketSize,
			List<PassengerRequest> passengerList) {

		Integer numberofMaleTicketSize = 0;

		if (totalTicketSize > MAX_INDIVIDUAL_USER_TICKET_SIZE) {
			logger.log(Level.WARNING,
					"[TicketService] - individual user can purchase a maximum of 5 tickets for the same trip, number of ticket: {0}",
					totalTicketSize);
			throw new IndividualUserTicketSizeException(
					"An individual user can purchase a maximum of 5 tickets for the same trip");
		}

		for (int i = 0; i < passengerList.size(); i++) {
			if (UserGender.MALE.equals(passengerList.get(i).getGender())) {
				logger.log(Level.INFO, "[TicketService] - number of male passenger: {0}", numberofMaleTicketSize);
				numberofMaleTicketSize++;
			}
		}
		if (numberofMaleTicketSize > INDIVIDUAL_USER_MALE_TICKET_SIZE) {
			logger.log(Level.WARNING,
					"[TicketService] - Individual users can purchase tickets for a maximum of 2 male passengers in a "
							+ "single order, number of ticket: {0}",
					numberofMaleTicketSize);
			throw new IndıvıdualUserGenderException(
					"An Individual users can purchase tickets for a maximum of 2 male passengers in a single order");
		}

	}

	public static void validateCorporateUserTickets(Integer totalTicketSize) {

		if (totalTicketSize > MAX_CORPORATE_USER_TICKET_SIZE) {
			logger.log(Level.WARNING,
					"[TicketService] - A corporate user can purchase a maximum of 20 tickets for the same trip, number of ticket: {0}",
					totalTicketSize);
			throw new CorporateUserTicketSizeException(
					"A corporate user can purchase a maximum of 20 tickets for the same trip");
		}

	}
}
