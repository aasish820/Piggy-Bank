package com.piggybank.user.model;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.piggybank.user.entity.User;

import lombok.Data;
@Data
public class UserDTO {
	private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String address;
    private String email;
    private String username;
    private String password;
    private LocalDate dob;
    private long phoneNumber;
    private Set<String> roles;
    
	@JsonIgnore
	private User user;

	


}
