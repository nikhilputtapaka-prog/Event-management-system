package com.spring.nikhil.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.nikhil.enums.PaymentStatus;
import com.spring.nikhil.enums.RegistrationStatus;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Registration {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long registration_id;


	private LocalDate registrationDate;

	private int ticketCount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    
    @Enumerated(EnumType.STRING)
	private RegistrationStatus registrationStatus;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Events event;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_id")
    private Participant participant;

}
