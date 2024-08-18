package com.vani.ams.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Account number cannot be blank")
    @Size(min = 10, max = 20, message = "Account number must be between 10 and 20 characters")
	private String accountNumber;
	
	@NotBlank(message = "Account holder name cannot be blank")
    @Size(max = 100, message = "Account holder name cannot exceed 100 characters")
	private String accountHolderName;
	
	private Double balance;
	
	private String phoneNumber;//allowing for flexible and accurate representation of the phone number, including country codes and formatting characters 
	
	@NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    @Size(max = 255, message = "Email cannot exceed 255 characters")
    private String email;
	
	@Enumerated(EnumType.STRING)//mapping an enum to a string column in the database
    private AccountType accountType;

	private Date creationDate;
	private Date lastUpdatedDate;
	private Date deleted_at;
	private Long userId;
}

