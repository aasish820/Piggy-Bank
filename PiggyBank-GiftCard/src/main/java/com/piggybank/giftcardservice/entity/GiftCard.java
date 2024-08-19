package com.piggybank.giftcardservice.entity;

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
	@NotBlank
	private String cardNumber;
	@NotBlank
	private double balance;
	@NotBlank
	private LocalDate expireDate;
	@NotBlank
	private long userId;
	@CreationTimestamp
	private LocalDate createAt;
	@NotBlank
	private boolean active = true;
	

}
