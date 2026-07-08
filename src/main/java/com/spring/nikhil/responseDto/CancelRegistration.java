package com.spring.nikhil.responseDto;

import java.time.LocalDate;


import com.spring.nikhil.enums.PaymentStatus;

import lombok.Data;

@Data
public class CancelRegistration {
	private PaymentStatus paymentStatus;
	private LocalDate registeredDate;
	private LocalDate cancelledDate;
	private String eventName;
	private long tickets;
	private String email;
}
