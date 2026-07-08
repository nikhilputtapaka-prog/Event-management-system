package com.spring.nikhil.requestDto;

import com.spring.nikhil.enums.PaymentStatus;
import com.spring.nikhil.enums.RegistrationStatus;

import lombok.Data;

@Data
public class RegistrationDto {
    private Long eventId;
    private Long participantId;
    private Integer ticketCount;
    private PaymentStatus paymentStatus;
    private RegistrationStatus registrationStatus;
}
