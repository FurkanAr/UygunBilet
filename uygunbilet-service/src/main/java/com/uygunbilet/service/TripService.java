package com.uygunbilet.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uygunbilet.converter.TripConverter;
import com.uygunbilet.exception.trip.TripNotFoundException;
import com.uygunbilet.exception.user.UserHasNotPermissionException;
import com.uygunbilet.exception.user.UserNotFoundException;
import com.uygunbilet.model.Trip;
import com.uygunbilet.model.User;
import com.uygunbilet.model.enums.TripType;
import com.uygunbilet.repository.TripRepository;
import com.uygunbilet.request.TripRequest;
import com.uygunbilet.response.TripResponse;
import com.uygunbilet.util.UserRoleUtil;

@Service
public class TripService {

	Logger logger = Logger.getLogger(TripService.class.getName());

	private static final String TRIP_DELETED = "Trip deleted successfully!!";
	private static final String TRIP_CANT_DELETED = "Trip cannot deleted!! You are not authorized to delete a trip";

	@Autowired
	private TripRepository tripRepository;

	@Autowired
	private TripConverter tripConverter;

	@Autowired
	private SeatService seatService;;

	@Autowired
	private UserService userService;

	// If user role is admin then user can create new trips
	public TripResponse create(TripRequest tripRequest) {
		User foundUser = userService.findByUserId(tripRequest.getUserId()).orElseThrow(
				() -> new UserNotFoundException("User cannot find with this id:" + tripRequest.getUserId()));

		logger.log(Level.INFO, "[TripService] - found user: {0}", foundUser);

		boolean hasPermission = UserRoleUtil.validateUserRole(foundUser);

		logger.log(Level.INFO, "[TripService] - found user role permission: {0}", hasPermission);

		if (hasPermission) {
			Trip savedTrip = tripRepository.save(tripConverter.convert(tripRequest));
			savedTrip.setSeatList(seatService.createSeats(savedTrip));
			logger.log(Level.INFO, "[TripService] - trip created: {0}", savedTrip);
			return tripConverter.convert(savedTrip);
		}
		logger.log(Level.WARNING, "[TripService] - user has not permission to create trip: {0}", foundUser);
		throw new UserHasNotPermissionException("You are not authorized to create trips: " + foundUser.getName());
	}

	// Returns created trips
	public List<TripResponse> getAll() {
		List<TripResponse> tripResponses = tripConverter.convert(tripRepository.findAll());
		logger.log(Level.INFO, "[TripService] - trips, number of trips: {0}", tripResponses.size());
		return tripResponses;
	}

	// Returns a trip by given trip id
	public TripResponse getById(Integer id) {
		Trip trip = findById(id).orElseThrow(() -> new TripNotFoundException("Trip cannot find with this id: " + id));
		logger.log(Level.INFO, "[TripService] - trip: {0}", trip);
		return tripConverter.convert(trip);
	}

	// Only Admin can deletes a trip
	public String delete(Integer tripId, Integer userId) {
		User foundUser = userService.findByUserId(userId)
				.orElseThrow(() -> new UserNotFoundException("User cannot find with this id:" + userId));
		Trip foundTrip = findById(tripId)
				.orElseThrow(() -> new TripNotFoundException("Trip cannot find with this id: " + tripId));

		logger.log(Level.INFO, "[TripService] - foundUser: {0}, foundTrip : {1}",
				new Object[] { foundUser, foundTrip });

		boolean hasPermission = UserRoleUtil.validateUserRole(foundUser);

		logger.log(Level.INFO, "[TripService] - found user role permission: {0}", hasPermission);

		if (hasPermission) {
			tripRepository.delete(foundTrip);
			logger.log(Level.INFO, "[TripService] - trip deleted trip id: {0}", tripId);
			return TRIP_DELETED;
		}
		logger.log(Level.WARNING, "[TripService] - trip cannot deleted trip: {0}", foundTrip);
		return TRIP_CANT_DELETED;
	}

	// Returns trips by given departure and destination
	public List<TripResponse> findTripByProvinces(String departure, String destination) {
		List<TripResponse> tripResponses = tripConverter
				.convert(tripRepository.findAllByDepartureAndDestination(departure, destination));
		logger.log(Level.INFO, "[TripService] - tripResponses by departure: {0}, destination : {1}",
				new Object[] { departure, destination });
		return tripResponses;
	}

	// Returns trips by given trip type(flight or bus)
	public List<TripResponse> findTripByTripType(String type) {
		TripType search = TripType.valueOf(type.toUpperCase());
		List<TripResponse> tripResponses = tripConverter.convert(tripRepository.findAllByType(search));
		logger.log(Level.INFO, "[TripService] - tripResponses by type: {0}, number of tripResponses : {1}",
				new Object[] { type, tripResponses.size() });
		return tripResponses;

	}

	// Returns trips by given trip date
	public List<TripResponse> findTripByDate(String date) {
		LocalDate localDate = LocalDate.parse(date);
		List<TripResponse> tripResponses = tripConverter.convert(tripRepository.findAllByDepartureDate(localDate));
		logger.log(Level.INFO, "[TripService] - tripResponses by date: {0}, number of tripResponses : {1}",
				new Object[] { date, tripResponses.size() });
		return tripResponses;
	}

	// Returns trips by given date, trip type, departure and destination
	public List<TripResponse> findTrip(String departure, String destination, TripType type, LocalDate date) {
		List<TripResponse> tripResponses = tripConverter.convert(tripRepository
				.findAllByDepartureAndDestinationAndTypeAndDepartureDate(departure, destination, type, date));
		logger.log(Level.INFO, "[TripService] - trips by date, type, destination and departure, number of trips: {0}",
				tripResponses.size());
		return tripResponses;
	}

	// Returns trip it doesnt matter exist or doesnt exist
	public Optional<Trip> findById(Integer id) {
		Optional<Trip> trip = tripRepository.findById(id);
		logger.log(Level.INFO, "[TripService] - find by trip id trip : {0}", trip);
		return trip;
	}

}
