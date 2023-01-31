package com.uygunbilet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uygunbilet.model.Ticket;
import com.uygunbilet.model.Trip;
import com.uygunbilet.model.User;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

	List<Ticket> findAllByUserId(Integer id);

	List<Ticket> findAllByTripId(Integer id);

	Integer countByTripId(Integer id);

	@Query(value = "SELECT SUM(ticket_price) FROM ticket where trip_id = :tripId", nativeQuery = true)
	Double sumTicketPriceByTripId(@Param("tripId") Integer tripId);

	List<Ticket> findAllByUser(User user);

	Integer countByUserAndTrip(User user, Trip trip);

}
