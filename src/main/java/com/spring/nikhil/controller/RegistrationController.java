package com.spring.nikhil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.nikhil.entity.Registration;
import com.spring.nikhil.requestDto.CancelTicketsDto;
import com.spring.nikhil.requestDto.RegistrationDto;
import com.spring.nikhil.responseDto.CancelRegistration;
import com.spring.nikhil.responseDto.RegistrationResponse2;
import com.spring.nikhil.responseDto.RegistrationsResponse;
import com.spring.nikhil.service.RegistrationService;

@RestController
@RequestMapping("/registration")
public class RegistrationController {
	@Autowired
	RegistrationService registrationService;
	
	@PostMapping("/registerevent")
	public String registerEvent(@RequestBody RegistrationDto registrationDto) {
		return registrationService.registerEvent(registrationDto);
	}
	
	@GetMapping("/getbyeventid/{id}")
	public List<RegistrationsResponse> getRegistrationsByEvent(@PathVariable long id){
		return registrationService.getRegistrationsByEvent(id);
	}
	
	@GetMapping("/getbyid/{id}")
	public ResponseEntity<?> getRegistrationById(@PathVariable long id){
		return registrationService.getRegistrationById(id);
	}
	
	@GetMapping("/getallregistrations")
	public List<RegistrationResponse2> getAllRegistrations(){
		return registrationService.getAllRegisrations();
	}
	
	@PostMapping("/cancelregistration")
	public ResponseEntity<?> cancelRegistration(@RequestBody CancelTicketsDto cancelTicketsDto){
		return registrationService.cancelRegistration(cancelTicketsDto);
	}
}
