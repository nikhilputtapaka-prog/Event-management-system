package com.spring.nikhil.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.nikhil.entity.Events;
import com.spring.nikhil.enums.EventStatus;
import com.spring.nikhil.requestDto.EventsDto;

@Repository
public interface EventsRepo extends JpaRepository<Events,Long>{
	public List<Events> findByCity(String city);
	public List<Events> findByCategory(String category);
	
	public List<Events> findByStatus(EventStatus UPCOMING);
	
}
