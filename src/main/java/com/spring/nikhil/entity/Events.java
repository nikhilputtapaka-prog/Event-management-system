package com.spring.nikhil.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.spring.nikhil.enums.EventStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Events {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long eventId;
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
    
	private LocalDateTime  createdAt;
    @OneToMany(mappedBy = "event",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Registration> registrations = new ArrayList<>();
}
