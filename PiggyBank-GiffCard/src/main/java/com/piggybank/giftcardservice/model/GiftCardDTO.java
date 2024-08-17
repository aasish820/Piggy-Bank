package com.piggybank.giftcardservice.model;

import java.time.LocalDate;

import lombok.Data;
@Data
public class GiftCardDTO {
	private long id;
	private String cardNumber;
	private double balance;
	private LocalDate expiryDate;
	private long userID;
	private boolean active;
	

}
