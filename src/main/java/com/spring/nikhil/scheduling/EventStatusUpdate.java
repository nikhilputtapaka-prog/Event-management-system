package com.spring.nikhil.scheduling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.spring.nikhil.service.EventsService;

@Component
public class EventStatusUpdate {
	@Autowired
	EventsService eventsService;
	
	@Scheduled(fixedRate = 300000)
	public void updateEventStatus() {
		eventsService.updateEventStatus();
	}
}
