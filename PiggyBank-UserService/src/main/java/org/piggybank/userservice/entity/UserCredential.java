package org.piggybank.userservice.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usercredential")
public class UserCredential {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String first_name;
    
    private String middle_name;
    
    private String last_name;

    private String address;
    
    private String email;
    
    private String username;

    private String password;
    
    private String phone_number;
    
    @JsonIgnore
    private LocalDate delete_at;
    
    @CreationTimestamp
    @JsonIgnore
    private LocalDate created_at;
    
    @UpdateTimestamp
    @JsonIgnore
    private LocalDate updated_at;
}
