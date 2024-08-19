package com.piggybank.user.model;

import java.time.LocalDate;
import java.util.Set;
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
    private String phoneNumber;
    private Set<String> roles;

}

