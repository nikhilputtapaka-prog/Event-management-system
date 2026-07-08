package com.spring.nikhil.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.nikhil.entity.Participant;
import com.spring.nikhil.repositories.ParticipantRepo;
import com.spring.nikhil.requestDto.ParticipantDto;

@Service
public class ParticipantService {
	@Autowired
	ParticipantRepo participantRepo;
	
	/*
	 *-----------------------------------Participant Features---------------------------------------------------------------
					registerParticipant()

					getAllParticipants()

					getParticipantById()

					updateParticipant()

					deleteParticipant()
	 */
	public String registerParticpant(ParticipantDto participantDto) {
		Participant participant=new Participant();
		participant.setAge(participantDto.getAge());
		participant.setEmail(participantDto.getEmail());
		participant.setGender(participantDto.getGender());
		participant.setName(participantDto.getName());
		participant.setPhone(participantDto.getPhone());
		participant.setRegisteredAt(LocalDateTime.now());
		participantRepo.save(participant);
		return "Created Account Sucessfully, Now you can eligible to register for any Event,\nNOTE: TERMS AND CONDITIONS APPLY";
	}
	
	public List<ParticipantDto> getAllParticipants(){
		List<Participant> participants=participantRepo.findAll();
		List<ParticipantDto> response=new ArrayList<ParticipantDto>();
		for(Participant i:participants) {
			ParticipantDto participant=new ParticipantDto();
			participant.setAge(i.getAge());
			participant.setEmail(i.getEmail());
			participant.setGender(i.getGender());
			participant.setName(i.getName());
			participant.setPhone(i.getPhone());
			response.add(participant);
		}
		return response;
	}
	
	public ResponseEntity<?> getParticipantById(long id) {
		Optional<Participant> participant=participantRepo.findById(id);
		if(participant.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Participant Not Found");
		}
		ParticipantDto response=new ParticipantDto();
		response.setAge(participant.get().getAge());
		response.setEmail(participant.get().getEmail());
		response.setGender(participant.get().getGender());
		response.setName(participant.get().getName());
		response.setPhone(participant.get().getPhone());
		return ResponseEntity.ok(response);
	}
	
	public String deleteParticipant(long id){
		Optional<Participant> participant=participantRepo.findById(id);
		if(participant.isPresent()) {
			participantRepo.deleteById(id);
			return "Participant Deleted Sucessfully";
		}
		return "Partcipant not found";
	}
	
	public String updateParticipant(long id,ParticipantDto participantDto) {
		Optional<Participant> participant=participantRepo.findById(id);
		if(participant.isEmpty()) {
			return "Participant Not Found";
		}
		if(participantDto.getPhone()!=null) {
			participant.get().setPhone(participantDto.getPhone());
		}
		if(participantDto.getAge()!=0) {
			participant.get().setAge(participantDto.getAge());
		}
		if(participantDto.getEmail()!=null) {
			participant.get().setEmail(participantDto.getEmail());
		}
		if(participantDto.getGender()!=null) {
			participant.get().setGender(participantDto.getGender());
		}
		if(participantDto.getName()!=null) {
			participant.get().setName(participantDto.getName());
		}
		participantRepo.save(participant.get());
		return "Updated Sucessfully";
	}
	
}
