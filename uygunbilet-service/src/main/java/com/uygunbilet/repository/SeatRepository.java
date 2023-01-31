package com.uygunbilet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uygunbilet.model.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {

	List<Seat> findAllByTripId(Integer id);

	List<Seat> findAllById(Integer id);

}
