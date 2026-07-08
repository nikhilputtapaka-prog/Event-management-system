package com.spring.nikhil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.nikhil.requestDto.ParticipantDto;
import com.spring.nikhil.service.ParticipantService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/participant")
public class ParticipantController {
	@Autowired
	ParticipantService participantService;
	
	@PostMapping("/registerparticipant")
	public String registerParticipant(@Valid @RequestBody ParticipantDto participantDto) {
		return participantService.registerParticpant(participantDto);
	}
	
	@GetMapping("/getbyid/{id}")
	public ResponseEntity<?> getByid(@PathVariable long id){
		return participantService.getParticipantById(id);
	}
	
	@GetMapping("/getallparticipants")
	public List<ParticipantDto> getAllParticipants(){
		return participantService.getAllParticipants();
	}
	
	@DeleteMapping("/deleteparticipant/{id}")
	public String deleteParticipant(@PathVariable long id) {
		return participantService.deleteParticipant(id);
	}
	
	@PatchMapping("/updatebyid/{id}")
	public String updateParticipant(@PathVariable long id,@RequestBody ParticipantDto participantDto) {
		return participantService.updateParticipant(id, participantDto);
	}
}
