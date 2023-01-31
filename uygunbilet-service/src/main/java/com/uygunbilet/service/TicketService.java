package com.uygunbilet.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uygunbilet.client.response.PaymentResponse;
import com.uygunbilet.converter.SeatConverter;
import com.uygunbilet.converter.TicketConverter;
import com.uygunbilet.exception.seat.SeatNotEmptyException;
import com.uygunbilet.exception.seat.SeatNotFoundException;
import com.uygunbilet.exception.trip.TripNotFoundException;
import com.uygunbilet.exception.user.UserHasNotPermissionException;
import com.uygunbilet.exception.user.UserNotFoundException;
import com.uygunbilet.model.Seat;
import com.uygunbilet.model.Ticket;
import com.uygunbilet.model.Trip;
import com.uygunbilet.model.User;
import com.uygunbilet.model.enums.SeatStatus;
import com.uygunbilet.model.enums.UserType;
import com.uygunbilet.repository.SeatRepository;
import com.uygunbilet.repository.TicketRepository;
import com.uygunbilet.request.PassengerRequest;
import com.uygunbilet.request.TicketRequest;
import com.uygunbilet.response.TicketResponse;
import com.uygunbilet.util.UserRoleUtil;
import com.uygunbilet.validator.UserTicketValidator;

@Service
public class TicketService {

	Logger logger = Logger.getLogger(TicketService.class.getName());

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private SeatRepository seatRepository;

	@Autowired
	private TicketConverter ticketConverter;

	@Autowired
	private SeatConverter seatConverter;

	@Autowired
	private UserService userService;

	@Autowired
	private TripService tripService;

	@Autowired
	private SeatService seatService;

	@Autowired
	private PaymentService paymentService;

	/*
	 * Creates new tickets, if payments is succesfull it returns payment response.
	 * An individual user can purchase a maximum of 5 tickets for the same trip.
	 * Individual users can purchase tickets for a maximum of 2 male passengers in a
	 * single order. A corporate user can purchase a maximum of 20 tickets for the
	 * same trip.
	 */
	public PaymentResponse create(TicketRequest request) {

		User foundUser = userService.findByUserId(request.getUserId())
				.orElseThrow(() -> new UserNotFoundException("User cannot find with this id:" + request.getUserId()));

		Trip foundTrip = tripService.findById(request.getTripId())
				.orElseThrow(() -> new TripNotFoundException("Trip cannot find with this id: " + request.getTripId()));

		logger.log(Level.INFO, "[TicketService] - foundUser: {0}, foundTrip : {1}",
				new Object[] { foundUser, foundTrip });

		checkUser(foundUser, foundTrip, request.getSeats());

		List<Seat> seats = createTicket(request.getSeats(), foundUser, foundTrip);

		logger.log(Level.INFO, "[TicketService] - seat numbers:: {0}", seats);

		return paymentService.createPayment(request, foundUser, foundTrip, seats);
	}

	
	// checks users' membership coverage and the tickets they want to buy
	private void checkUser(User user, Trip trip, List<PassengerRequest> passengerList) {

		Integer userTicketSize = ticketRepository.countByUserAndTrip(user, trip);
		Integer requestTicketSize = passengerList.size();
		Integer totalTicketSize = Math.addExact(userTicketSize, requestTicketSize);
		
		logger.log(Level.INFO, "[TicketService] - number of ticket: {0}, user : {1}",
				new Object[] { totalTicketSize, user });

		if (UserType.INDIVIDUAL.equals(user.getType())) {

			UserTicketValidator.validateIndividualUserTickets(user, totalTicketSize, passengerList);

		}

		if (UserType.CORPORATE.equals(user.getType())) {
			
			UserTicketValidator.validateCorporateUserTickets(totalTicketSize);

		}

	}

	// Checks seats are avaliable, and creates tickets.
	private List<Seat> createTicket(List<PassengerRequest> passengerList, User user, Trip trip) {
		List<Seat> seats = new ArrayList<>();

		for (int i = 0; i < passengerList.size(); i++) {
			Seat foundSeat = seatService.findBySeatId(passengerList.get(i).getSeatId())
					.orElseThrow(() -> new SeatNotFoundException("Seat cannot find "));
			logger.log(Level.INFO, "[TicketService] - found seat: {0}", foundSeat);

			if (SeatStatus.FULL.equals(foundSeat.getSeatStatus())) {
				logger.log(Level.WARNING, "[TicketService] - Seat is full try another seat: {0}", foundSeat);
				throw new SeatNotEmptyException("Seat is full try another seat!!");
			}
			Seat seat = seatConverter.convert(passengerList.get(i), foundSeat);

			Seat updatedSeat = seatRepository.save(seat);
			logger.log(Level.INFO, "[TicketService] - updated seat: {0}", updatedSeat);

			Ticket ticket = ticketConverter.convert(updatedSeat, user, trip);

			Ticket savedTicket = ticketRepository.save(ticket);
			logger.log(Level.INFO, "[TicketService] - ticket Created: {0}", savedTicket);

			updatedSeat.setTicket(savedTicket);
			Seat seatTicket = seatRepository.save(updatedSeat);
			seats.add(seatTicket);
			logger.log(Level.INFO, "[TicketService] - updated seat: {0}", seatTicket);

		}
		logger.log(Level.INFO, "[TicketService] - seat numbers: {0}", seats);
		return seats;
	}

	// Returns created tickets
	public List<TicketResponse> getAll() {
		List<TicketResponse> ticketResponses = ticketConverter.convert(ticketRepository.findAll());
		logger.log(Level.INFO, "[TicketService] - all tickets number of tickets: {0}", ticketResponses.size());
		return ticketResponses;
	}

	// Returns tickets by user id
	public List<TicketResponse> getAllByUserId(Integer id) {
		User foundUser = userService.findByUserId(id)
				.orElseThrow(() -> new UserNotFoundException("User cannot find with this id:" + id));

		List<TicketResponse> ticketResponses = ticketConverter.convert(ticketRepository.findAllByUser(foundUser));

		logger.log(Level.INFO, "[TicketService] - found user: {0}, user tickets : {1}",
				new Object[] { foundUser, ticketResponses });
		return ticketResponses;
	}

	// Admin displays sold tickets on a trip
	public List<TicketResponse> getAllByTripId(Integer userId, Integer tripId) {
		boolean hasPermission = checkUserAndTrip(userId, tripId);

		if (hasPermission) {
			List<TicketResponse> ticketResponses = ticketConverter.convert(ticketRepository.findAllByTripId(tripId));

			logger.log(Level.INFO, "[TicketService] - user has permission user id: {0}, number of tickets : {1}",
					new Object[] { userId, ticketResponses.size() });

			return ticketResponses;
		}
		logger.log(Level.WARNING, "[TripService] - user has not permission user id: {0}", userId);
		throw new UserHasNotPermissionException("You are not authorized to show tickets userId: " + userId);
	}

	// Admin displays the number of tickets sold on a trip
	public Integer countByTripId(Integer userId, Integer tripId) {
		boolean hasPermission = checkUserAndTrip(userId, tripId);

		if (hasPermission) {
			Integer numberOfTickets = ticketRepository.countByTripId(tripId);

			logger.log(Level.INFO, "[TicketService] - user has permission user id: {0}, number of tickets : {1}",
					new Object[] { userId, numberOfTickets });

			return numberOfTickets;
		}
		logger.log(Level.WARNING, "[TripService] - user has not permission user id: {0}", userId);
		throw new UserHasNotPermissionException("You are not authorized to show tickets userId: " + userId);
	}

	// Admin displays the total price of tickets sold on a trip
	public Double sumPriceByTripId(Integer userId, Integer tripId) {
		boolean hasPermission = checkUserAndTrip(userId, tripId);

		if (hasPermission) {
			Double totalTicketPrice = ticketRepository.sumTicketPriceByTripId(tripId);

			logger.log(Level.INFO, "[TicketService] - user has permission user id: {0}, total ticket price : {1}",
					new Object[] { userId, totalTicketPrice });

			return totalTicketPrice;
		}
		logger.log(Level.WARNING, "[TripService] - user has not permission user id: {0}", userId);
		throw new UserHasNotPermissionException("You are not authorized to show tickets userId: " + userId);
	}

	// Checks if user and trip exist. Also checks the user's role
	private boolean checkUserAndTrip(Integer userId, Integer tripId) {
		User foundUser = userService.findByUserId(userId)
				.orElseThrow(() -> new UserNotFoundException("User cannot find with this id:" + userId));

		Trip foundTrip = tripService.findById(tripId)
				.orElseThrow(() -> new TripNotFoundException("Trip cannot find with this id: " + tripId));

		logger.log(Level.INFO, "[TicketService] - foundUser: {0}, foundTrip : {1}",
				new Object[] { foundUser, foundTrip });

		boolean hasPermission = UserRoleUtil.validateUserRole(foundUser);

		logger.log(Level.INFO, "[TicketService] - user has permission userId: {0}, permission : {1}",
				new Object[] { userId, hasPermission });

		return hasPermission ? true : false;
	}

}
