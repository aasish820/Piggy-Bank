package com.piggybank.user.entity;

import java.time.LocalDate;
import java.util.Set;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {
    
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Name can't be empty")
    @Size(min = 2, max = 20)
    private String first_name;
    
    private String middle_name;
    
    @NotBlank(message = "Last name can't be empty")
    @Size(min = 2, max = 20)
    private String last_name;
    
    @NotBlank
    private String address;
    
    @NotBlank
    @Email
    private String email;
    
    @NotBlank
    @Column(unique = true)
    private String username;
    
    @NotBlank
    @Size(min = 6, max = 12)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,12}$")
    private String password;
    
    @NotNull
    private LocalDate dob;
    
    @NotBlank
    private String phone_number;
    
    @JsonIgnore
    private LocalDate delete_at;
    
    @CreationTimestamp
    @JsonIgnore
    private LocalDate created_at;
    
    @UpdateTimestamp
    @JsonIgnore
    private LocalDate updated_at;
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
               joinColumns = @JoinColumn(name = "user_id"),
               inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}

	
	
	
	
	


