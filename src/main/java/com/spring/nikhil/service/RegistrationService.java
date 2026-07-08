package com.spring.nikhil.service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.nikhil.entity.Events;
import com.spring.nikhil.entity.Participant;
import com.spring.nikhil.entity.Registration;
import com.spring.nikhil.enums.EventStatus;
import com.spring.nikhil.enums.PaymentStatus;
import com.spring.nikhil.enums.RegistrationStatus;
import com.spring.nikhil.repositories.EventsRepo;
import com.spring.nikhil.repositories.ParticipantRepo;
import com.spring.nikhil.repositories.RegistrationRepo;
import com.spring.nikhil.requestDto.CancelTicketsDto;
import com.spring.nikhil.requestDto.RegistrationDto;
import com.spring.nikhil.responseDto.CancelRegistration;
import com.spring.nikhil.responseDto.RegistrationResponse2;
import com.spring.nikhil.responseDto.RegistrationsResponse;

@Service
public class RegistrationService {
	
	
	@Autowired
	RegistrationRepo registrationRepo;
	@Autowired
	ParticipantRepo participantRepo;
	@Autowired
	EventsRepo eventsRepo;
	/*
	 * ========================================Registration Features=========================================================
	 * 
								registerForEvent()

								cancelRegistration()

								getRegistrationById()

								getAllRegistrations()

								getRegistrationsByEvent()

								getRegistrationsByParticipant()
	 */
	
	public String registerEvent(RegistrationDto registrationDto) {
		Registration registration=new Registration();
		Optional<Events> event=eventsRepo.findById(registrationDto.getEventId());
		event.get().setAvailableSeats(event.get().getAvailableSeats()-registrationDto.getTicketCount());
		registration.setEvent(event.get());
		Optional<Participant> participant=participantRepo.findById(registrationDto.getParticipantId());
		registration.setParticipant(participant.get());
		registration.setTicketCount(registrationDto.getTicketCount());
		registration.setRegistrationDate(LocalDate.now());
		registration.setPaymentStatus(PaymentStatus.PAID);
		registration.setRegistrationStatus(RegistrationStatus.REGISTERED);
		registrationRepo.save(registration);
		return "Registration Sucessfull";
	}
	
	public List<RegistrationsResponse> getRegistrationsByEvent(long id){
		Optional<Events> event=eventsRepo.findById(id);
		List<RegistrationsResponse> registrationsResponse=new ArrayList<RegistrationsResponse>();
		for(Registration i:event.get().getRegistrations()) {
			RegistrationsResponse registrationsResponse1=new RegistrationsResponse();
			registrationsResponse1.setRegistrationStatus(i.getRegistrationStatus());
			registrationsResponse1.setPaymentStatus(i.getPaymentStatus());
			registrationsResponse1.setRegistrationDate(i.getRegistrationDate());
			registrationsResponse1.setTickets(i.getTicketCount());
			registrationsResponse1.setParticipantEmail(i.getParticipant().getEmail());
			registrationsResponse1.setParticipantName(i.getParticipant().getName());
			registrationsResponse1.setRegistrationId(i.getRegistration_id());
			registrationsResponse.add(registrationsResponse1);
		}
		return registrationsResponse;
	}
	
	public ResponseEntity<?> getRegistrationById(long id){
		Optional<Registration> registration=registrationRepo.findById(id);
		if(registration.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registration not found");
		}
		Registration register=registration.get();
		Optional<Events> event=eventsRepo.findById(register.getEvent().getEventId());
		Optional<Participant> participant=participantRepo.findById(register.getParticipant().getParticipant_id());
		RegistrationResponse2 response=new RegistrationResponse2();
		response.setEventDate(event.get().getEventDate());
		response.setEventName(event.get().getVenue());
		response.setParticipantEmail(participant.get().getEmail());
		response.setPaymentStatus(register.getPaymentStatus());
		response.setParticipantName(participant.get().getName());
		response.setRegistrationDate(register.getRegistrationDate());
		response.setRegistrationStatus(register.getRegistrationStatus());
		response.setTickets(register.getTicketCount());
		return ResponseEntity.ok(response);
	}
	
	public List<RegistrationResponse2> getAllRegisrations(){
		List<Registration> registrations=registrationRepo.findAll();
		List<RegistrationResponse2> response=new ArrayList<RegistrationResponse2>();
		for(Registration i:registrations) {
			RegistrationResponse2 res=new RegistrationResponse2();
			Optional<Events> event=eventsRepo.findById(i.getEvent().getEventId());
			Optional<Participant> participant=participantRepo.findById(i.getParticipant().getParticipant_id());
			res.setEventDate(event.get().getEventDate());
			res.setEventName(event.get().getVenue());
			res.setParticipantEmail(participant.get().getEmail());
			res.setPaymentStatus(i.getPaymentStatus());
			res.setParticipantName(participant.get().getName());
			res.setRegistrationDate(i.getRegistrationDate());
			res.setRegistrationStatus(i.getRegistrationStatus());
			res.setTickets(i.getTicketCount());
			response.add(res);
		}
		return response;
	}
	public ResponseEntity<?> cancelRegistration(CancelTicketsDto cancelTickets) {
		Optional<Registration> register=registrationRepo.findById(cancelTickets.getId());
		if(register.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registration id is Invalid");
		}
		Registration registration=register.get();
		Optional<Events> event=eventsRepo.findById(registration.getEvent().getEventId());
		Optional<Participant> participant=participantRepo.findById(registration.getParticipant().getParticipant_id());
		if(event.get().getStatus().equals(EventStatus.COMPLETED) ||  event.get().getStatus().equals(EventStatus.ONGOING)) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Tickets Cannot be cancelled due the event may completed , ongoing ");
		}
		if(registration.getTicketCount()<cancelTickets.getTickets()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Your cancellation tickets are greater than registed");
		}
		CancelRegistration cancelRegistration=new CancelRegistration();
		event.get().setAvailableSeats(event.get().getAvailableSeats()+cancelTickets.getTickets());
		if(registration.getTicketCount()==cancelTickets.getTickets()) {
			registration.setPaymentStatus(PaymentStatus.REFUNDED);
			registration.setRegistrationStatus(RegistrationStatus.CANCELLED);
		}
		registration.setTicketCount(registration.getTicketCount()-cancelTickets.getTickets());
		cancelRegistration.setRegisteredDate(registration.getRegistrationDate());
		registration.setRegistrationDate(LocalDate.now());
		registrationRepo.save(registration);
		eventsRepo.save(event.get());
		cancelRegistration.setCancelledDate(registration.getRegistrationDate());
		cancelRegistration.setEmail(participant.get().getEmail());
		cancelRegistration.setEventName(event.get().getTitle());
		cancelRegistration.setPaymentStatus(registration.getPaymentStatus());
		cancelRegistration.setTickets(cancelTickets.getTickets());
		return ResponseEntity.ok(cancelRegistration);
	}
}
