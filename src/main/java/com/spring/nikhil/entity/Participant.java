package com.spring.nikhil.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.spring.nikhil.enums.Gender;

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
public class Participant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long participant_id;
	private String name;
	private String email;
	private String phone;
	private int age;
	
    @Enumerated(EnumType.STRING)
	private Gender gender;
    
	private LocalDateTime registeredAt;
    @OneToMany(mappedBy = "participant",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Registration> registrations = new ArrayList<>();
}
