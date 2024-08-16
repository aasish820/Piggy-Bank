package org.piggybank.credidcard.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class CreditCardDto {
	
	private long card_number;
	
	private int cvv;
	
	private String expiration_year;
	
	private String expiration_month;
	
	private int credit_limit;
	
	private int amount;
	
	@JsonIgnore
	private int user_id;
}
