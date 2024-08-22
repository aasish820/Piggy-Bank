package org.piggybank.creditcard.entity;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="creditcard")
public class CreditCard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="Card number cannot be empty")
	private long card_number;
	
	@NotNull(message="CVV cannot be empty")
	private int cvv;
	
	@NotBlank(message="Expiration year cannot be empty")
	private String expiration_year;
	
	@NotBlank(message="Expiration month cannot be empty")
	private String expiration_month;
	
	@NotNull(message="Credit amount limit cannot be empty")
	private int credit_limit;
	
	@NotNull(message="Amount limit cannot be empty")
	private int amount;
	
	private int user_id;
	
	@CreationTimestamp
	@JsonIgnore
	private LocalDateTime created_at;
	
	@UpdateTimestamp
	@JsonIgnore
	private LocalDateTime updated_at;
	
	@JsonIgnore
	private LocalDateTime deleted_at;
	
}