package com.spring.nikhil.responseDto;

import java.time.LocalDate;

import com.spring.nikhil.enums.PaymentStatus;
import com.spring.nikhil.enums.RegistrationStatus;

import lombok.Data;

@Data
public class RegistrationsResponse {
	private long registrationId;
	private long tickets;
	private PaymentStatus paymentStatus;
	private RegistrationStatus registrationStatus;
	private LocalDate registrationDate;
	private String participantName;
	private String participantEmail;
	
}
