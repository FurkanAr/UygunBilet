package com.uygunbiletservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uygunbiletservice.model.Mail;

@Repository
public interface MailRepository extends JpaRepository <Mail, Integer> {

}
