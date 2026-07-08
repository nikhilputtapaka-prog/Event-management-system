package com.spring.nikhil.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.nikhil.entity.Registration;

@Repository
public interface RegistrationRepo extends JpaRepository<Registration,Long>{
	public List<Registration> findByEventEventId(long id);
}
