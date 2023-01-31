package com.uygunbiletservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uygunbiletservice.model.Payment;

@Repository
public interface PaymentRepository  extends JpaRepository<Payment, Integer>{

}
