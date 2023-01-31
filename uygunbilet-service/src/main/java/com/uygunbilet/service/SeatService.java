package com.uygunbilet.service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uygunbilet.converter.SeatConverter;
import com.uygunbilet.repository.SeatRepository;
import com.uygunbilet.exception.trip.TripNotFoundException;
import com.uygunbilet.model.Seat;
import com.uygunbilet.model.Trip;
import com.uygunbilet.model.enums.SeatType;
import com.uygunbilet.request.SeatRequest;
import com.uygunbilet.response.SeatResponse;

@Service
public class SeatService {

	Logger logger = Logger.getLogger(SeatService.class.getName());
	
	@Autowired
	private SeatRepository seatRepository;

	@Autowired
	private SeatConverter seatConverter;

	@Autowired
	private TripService tripService;

	// Returns created seats;
	public List<SeatResponse> getAll() {
		List<SeatResponse> seatResponses = seatConverter.convert(seatRepository.findAll());
		logger.log(Level.INFO, "[SeatService] - all seats number of seats: {0}", seatResponses.size());
		return seatResponses;
	}

	// Creates new seats
	public SeatResponse create(SeatRequest seatRequest) {
		Trip foundTrip = tripService.findById(seatRequest.getTripId()).orElseThrow(
				() -> new TripNotFoundException("Trip cannot find with this id: " + seatRequest.getTripId()));

		Seat savedSeat = seatRepository.save(seatConverter.convert(seatRequest, foundTrip));

		logger.log(Level.INFO, "[SeatService] - seat created trip: {0}, seat: {1}",
				new Object[] { foundTrip, savedSeat });

		return seatConverter.convert(savedSeat);
	}

	/*
	 * when the trip is created automatically this method is called from the trip
	 * service and the seats of the trip are created
	 */
	public List<Seat> createSeats(Trip trip) {
		SeatRequest seatRequest = new SeatRequest();
		seatRequest.setTripId(trip.getId());
		seatRequest.setType(SeatType.SINGLE);

		for (int i = 1; i <= trip.getCapacity(); i++) {
			seatRequest.setSeatNo(i);
			Seat seat = seatConverter.convert(seatRequest, trip);
			logger.log(Level.INFO, "[SeatService] - seat created for trip seat: {0}, trip: {1}",
					new Object[] { seat, trip });
			seatRepository.save(seat);
		}
		return findAllByTripId(trip.getId());
	}

	// Returns seats for given trip id
	public List<Seat> findAllByTripId(Integer id) {
		List<Seat> seats = seatRepository.findAllByTripId(id);
		logger.log(Level.INFO, "[SeatService] - seats for trip id: {0}", seats.size());
		return seats;
	}

	// Returns seat for given seats id
	public Optional<Seat> findBySeatId(Integer id) {
		Optional<Seat> seat = seatRepository.findById(id);
		logger.log(Level.INFO, "[SeatService] - seat found by seat id: {0}", seat);
		return seat;
	}

	// Returns seats for given seat id
	public List<Seat> findAllBySeatId(Integer id) {
		List<Seat> seats = seatRepository.findAllById(id);
		logger.log(Level.INFO, "[SeatService] - seats found by seat id: {0}", seats.size());
		return seats;

	}

}
