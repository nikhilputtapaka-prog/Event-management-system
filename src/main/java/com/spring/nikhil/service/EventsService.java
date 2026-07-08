package com.spring.nikhil.service;



import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.nikhil.entity.Events;
import com.spring.nikhil.enums.EventStatus;
import com.spring.nikhil.repositories.EventsRepo;
import com.spring.nikhil.requestDto.EventsDto;


@Service
public class EventsService {
	@Autowired
	EventsRepo eventsRepo;
	
	private static final Logger logger=LoggerFactory.getLogger(Events.class);
	
/*
 -------------------------------------------Features of Event Service------------------------------------------------
createEvent()

getAllEvents()

getEventById()

updateEvent()

deleteEvent()

searchByCategory()

searchByCity()

getUpcomingEvents()
 */
	
	public String createEvent(EventsDto events) {
		if (events.getEventDate().isBefore(LocalDate.now())) {
		    logger.info("Event date is in the past.");
		    return "Event date cannot be in the past.";
		}

		if (!events.getStartTime().isBefore(events.getEndTime())) {
		    logger.info("Invalid event time.");
		    return "Start time must be before end time.";
		}
		Events event=new Events();
		event.setAvailableSeats(events.getAvailableSeats());
		event.setCapacity(events.getCapacity());
		event.setCategory(events.getCategory());
		event.setCity(events.getCity());
		event.setCreatedAt(LocalDateTime.now());
		event.setDescription(events.getDescription());
		event.setEndTime(events.getEndTime());
		event.setEventDate(events.getEventDate());
		event.setVenue(events.getVenue());
		event.setTitle(events.getTitle());
		event.setTicketPrice(events.getTicketPrice());
		event.setStatus(EventStatus.UPCOMING);
		event.setStartTime(events.getStartTime());
		eventsRepo.save(event);
		updateEventStatus();
		logger.info("Event Created Sucessfully");
		return "Event Registered Sucessfully";
	}
	
	//Event Status Scheduling using Scheduler 
	
	public void updateEventStatus() {
		List<Events> events = eventsRepo.findAll();
		LocalDateTime now = LocalDateTime.now();
        for (Events event : events) {
            if (event.getStatus() == EventStatus.CANCELLED) {
                continue;
            }
            LocalDateTime startDateTime =
                    LocalDateTime.of(event.getEventDate(), event.getStartTime());

            LocalDateTime endDateTime =
                    LocalDateTime.of(event.getEventDate(), event.getEndTime());

            if (now.isBefore(startDateTime)) {
                event.setStatus(EventStatus.UPCOMING);
            }
            else if (!now.isBefore(startDateTime) && !now.isAfter(endDateTime)) {
                event.setStatus(EventStatus.ONGOING);
            }
            else {
                event.setStatus(EventStatus.COMPLETED);
            }
        }
        logger.info("Event status Updated Sucessfully");
        eventsRepo.saveAll(events);
    }
	
	public List<EventsDto> getAllEvents(){
		List<Events> events=eventsRepo.findAll();
		List<EventsDto> response=new ArrayList<EventsDto>();
		for(Events i:events) {
			EventsDto event=new EventsDto();
			event.setAvailableSeats(i.getAvailableSeats());
			event.setCapacity(i.getCapacity());
			event.setCategory(i.getCategory());
			event.setCity(i.getCity());
			event.setDescription(i.getDescription());
			event.setEndTime(i.getEndTime());
			event.setEventDate(i.getEventDate());
			event.setStartTime(i.getStartTime());
			event.setStatus(i.getStatus());
			event.setTicketPrice(i.getTicketPrice());
			event.setTitle(i.getTitle());
			event.setVenue(i.getVenue());
			response.add(event);
		}
		logger.info("All the events Returned");
		return response;
	}
	
	public EventsDto getEventById(long id) {
		Optional<Events> event=eventsRepo.findById(id);
		EventsDto response=new EventsDto();
		response.setAvailableSeats(event.get().getAvailableSeats());
		response.setCapacity(event.get().getCapacity());
		response.setCategory(event.get().getCategory());
		response.setCity(event.get().getCity());
		response.setDescription(event.get().getDescription());
		response.setEndTime(event.get().getEndTime());
		response.setEventDate(event.get().getEventDate());
		response.setStartTime(event.get().getStartTime());
		response.setStatus(event.get().getStatus());
		response.setTicketPrice(event.get().getTicketPrice());
		response.setTitle(event.get().getTitle());
		response.setVenue(event.get().getVenue());
		logger.info("Events Returned with id"+id);
		return response;
		
	}
	
	public String deleteEvent(long id) {
		Optional<Events> event=eventsRepo.findById(id);
		if(event.isPresent()) {
			eventsRepo.deleteById(id);
			return "Event Deleted Sucessfully";
		}
		logger.info("Event deleted sucessfully id:"+id);
		return "Event with this id not found";
	}
	
	public List<EventsDto> searchByCity(String city){
		List<Events> events=eventsRepo.findByCity(city);
		List<EventsDto> response=new ArrayList<EventsDto>();
		for(Events i:events) {
			EventsDto event=new EventsDto();
			event.setTitle(i.getTitle());
			event.setDescription(i.getDescription());
			event.setAvailableSeats(i.getAvailableSeats());
			event.setCapacity(i.getCapacity());
			event.setCategory(i.getCategory());
			event.setCity(i.getCity());
			event.setEventDate(i.getEventDate());
			event.setStartTime(i.getStartTime());
			event.setEndTime(i.getEndTime());
			event.setStatus(i.getStatus());
			event.setTicketPrice(i.getTicketPrice());
			event.setVenue(i.getVenue());
			response.add(event);
		}
		logger.info("Events Searched with city:"+city);
		return response;
	}
	
	public List<EventsDto> searchByCategory(String category){
		List<Events> events=eventsRepo.findByCategory(category);
		List<EventsDto> response=new ArrayList<EventsDto>();
		for(Events i:events) {
			EventsDto event=new EventsDto();
			event.setTitle(i.getTitle());
			event.setDescription(i.getDescription());
			event.setAvailableSeats(i.getAvailableSeats());
			event.setCapacity(i.getCapacity());
			event.setCategory(i.getCategory());
			event.setCity(i.getCity());
			event.setEventDate(i.getEventDate());
			event.setStartTime(i.getStartTime());
			event.setEndTime(i.getEndTime());
			event.setStatus(i.getStatus());
			event.setTicketPrice(i.getTicketPrice());
			event.setVenue(i.getVenue());
			response.add(event);
		}
		logger.info("Events Searched with category:"+category);
		return response;
	}
	
	public ResponseEntity<?> getUpcomingEvents(){
		List<Events> events=eventsRepo.findByStatus(EventStatus.UPCOMING);
		if(events.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Upcoming Events");
		}
		List<EventsDto> response=new ArrayList<EventsDto>();
		for(Events i:events) {
			EventsDto event=new EventsDto();
			event.setTitle(i.getTitle());
			event.setDescription(i.getDescription());
			event.setAvailableSeats(i.getAvailableSeats());
			event.setCapacity(i.getCapacity());
			event.setCategory(i.getCategory());
			event.setCity(i.getCity());
			event.setEventDate(i.getEventDate());
			event.setStartTime(i.getStartTime());
			event.setEndTime(i.getEndTime());
			event.setStatus(i.getStatus());
			event.setTicketPrice(i.getTicketPrice());
			event.setVenue(i.getVenue());
			response.add(event);
		}
		logger.info("Returned all upcoming events");
		return ResponseEntity.ok(response);
	}
	
	public ResponseEntity<?> getOngoingEvents(){
		List<Events> events=eventsRepo.findByStatus(EventStatus.ONGOING);
		if(events.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Ongoing Events");
		}
		List<EventsDto> response=new ArrayList<EventsDto>();
		for(Events i:events) {
			EventsDto event=new EventsDto();
			event.setTitle(i.getTitle());
			event.setDescription(i.getDescription());
			event.setAvailableSeats(i.getAvailableSeats());
			event.setCapacity(i.getCapacity());
			event.setCategory(i.getCategory());
			event.setCity(i.getCity());
			event.setEventDate(i.getEventDate());
			event.setStartTime(i.getStartTime());
			event.setEndTime(i.getEndTime());
			event.setStatus(i.getStatus());
			event.setTicketPrice(i.getTicketPrice());
			event.setVenue(i.getVenue());
			response.add(event);
		}
		logger.info("Returned all Ongoing events");
		return ResponseEntity.ok(response);
	}
	
	public String updateEvent(long id,EventsDto eventsDto){
		Optional<Events> event=eventsRepo.findById(id);
		if(event.isEmpty()) {
			return "Event not Found";
		}
		Events obj=event.get();
		if(eventsDto.getAvailableSeats()!=0) {
			obj.setAvailableSeats(eventsDto.getAvailableSeats());
		}
		if(eventsDto.getCapacity()!=0) {
			obj.setCapacity(eventsDto.getCapacity());
		}
		if(eventsDto.getTicketPrice()!=0) {
			obj.setTicketPrice(eventsDto.getTicketPrice());
		}
		if(eventsDto.getCategory()!=null) {
			obj.setCategory(eventsDto.getCategory());
		}
		if(eventsDto.getCity()!=null) {
			obj.setCity(eventsDto.getCity());
		}
		if(eventsDto.getDescription()!=null) {
			obj.setDescription(eventsDto.getDescription());
		}
		if(eventsDto.getEventDate()!=null) {
			obj.setEventDate(eventsDto.getEventDate());
		}
		if(eventsDto.getStartTime()!=null) {
			obj.setStartTime(eventsDto.getStartTime());
		}
		if(eventsDto.getEndTime()!=null) {
			obj.setEndTime(eventsDto.getEndTime());
		}
		if(eventsDto.getTitle()!=null) {
			obj.setTitle(eventsDto.getTitle());
		}
		if(eventsDto.getVenue()!=null) {
			obj.setVenue(eventsDto.getVenue());
		}
		eventsRepo.save(obj);
		logger.info("Event updated Sucessfully");
		return "Updated Sucessfully";
	}
	
	
}
