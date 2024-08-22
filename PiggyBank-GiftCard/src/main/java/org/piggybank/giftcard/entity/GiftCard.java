package org.piggybank.giftcard.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="gift_card")
@Data
public class GiftCard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String cardNumber;

	private double balance;

	private LocalDate expireDate;

	private long userId;
	
	@CreationTimestamp
	private LocalDate createAt;

	private boolean active = true;
	

}