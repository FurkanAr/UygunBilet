package com.uygunbiletservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uygunbiletservice.model.Sms;

@Repository
public interface SmsRepository extends JpaRepository<Sms, Integer> {

}
