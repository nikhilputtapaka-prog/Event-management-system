package com.spring.nikhil.requestDto;

import com.spring.nikhil.enums.Gender;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ParticipantDto {
	private String name;
	@Email
	private String email;
	@Pattern(
		    regexp = "^[6-9]\\d{9}$",
		    message = "Enter a valid 10-digit Indian mobile number"
		)
	private String phone;
	private int age;
	
    @Enumerated(EnumType.STRING)
	private Gender gender;
}
