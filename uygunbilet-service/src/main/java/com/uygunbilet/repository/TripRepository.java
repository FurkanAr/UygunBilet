package com.uygunbilet.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uygunbilet.model.Trip;
import com.uygunbilet.model.enums.TripType;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {

	
	List<Trip> findAllByDepartureAndDestination(String departure, String destination);

	List<Trip> findAllByDepartureDate(LocalDate date);

	List<Trip> findAllByType(TripType search);

	List<Trip> findAllByDepartureAndDestinationAndTypeAndDepartureDate(String departure, String destination,
			TripType type, LocalDate date);

}
