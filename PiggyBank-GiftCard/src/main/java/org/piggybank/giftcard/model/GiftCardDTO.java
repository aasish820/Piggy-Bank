package org.piggybank.giftcard.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GiftCardDTO {
	private long id;
	private String cardNumber;
	private double balance;
	private LocalDate expireDate;
	private long userID;
	private boolean active;
	

}