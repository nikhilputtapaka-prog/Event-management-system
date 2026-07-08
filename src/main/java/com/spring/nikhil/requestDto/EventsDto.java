package com.spring.nikhil.requestDto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.spring.nikhil.enums.EventStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
@Data
public class EventsDto {
	private String title;
	private String description;
	private String category;
	private String venue;
	private String city;
	private LocalDate eventDate;
	private LocalTime startTime;
	private LocalTime endTime;
	private long capacity;
	private long availableSeats;
	private long ticketPrice;
	@Enumerated(EnumType.STRING)
	private EventStatus status;
}
