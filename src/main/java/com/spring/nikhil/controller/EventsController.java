package com.spring.nikhil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.nikhil.requestDto.EventsDto;
import com.spring.nikhil.service.EventsService;

@RestController
@RequestMapping("/events")
public class EventsController {
	@Autowired
	EventsService eventsService;
	
	@PostMapping("/createevent")
	public String registerEvent(@RequestBody EventsDto eventsDto) {
		return eventsService.createEvent(eventsDto);
	}
	
	@DeleteMapping("/deleteevent/{id}")
	public String deleteEvent(@PathVariable long id) {
		return eventsService.deleteEvent(id);
	}
	
	@GetMapping("/allevents")
	public List<EventsDto> getAllEvents(){
		return eventsService.getAllEvents();
	}
	
	@GetMapping("/eventid/{id}")
	public EventsDto getEventById(@PathVariable long id) {
		return eventsService.getEventById(id);
	}
	
	@GetMapping("/searchbycity")
	public List<EventsDto> searchByCity(@RequestParam String city){
		return eventsService.searchByCity(city);
	}
	
	@GetMapping("/searchbycategory/{cato}")
	public List<EventsDto> searchByCategory(@PathVariable String cato){
		return eventsService.searchByCategory(cato);
	}
	
	@GetMapping("/upcomingevents")
	public ResponseEntity<?> getUpcomingEvents(){
		return eventsService.getUpcomingEvents();
	}
	
	@GetMapping("/ongoingevents")
	public ResponseEntity<?> getOngoingEvents(){
		return eventsService.getOngoingEvents();
	}
	
	@PatchMapping("/updateevents/{id}")
	public String updateEvent(@PathVariable long id,@RequestBody EventsDto eventsDto) {
		return eventsService.updateEvent(id, eventsDto);
	}
}
