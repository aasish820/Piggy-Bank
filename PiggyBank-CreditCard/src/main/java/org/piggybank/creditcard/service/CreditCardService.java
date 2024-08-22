package org.piggybank.creditcard.service;

import org.piggybank.creditcard.model.CreditCardDto;

public interface CreditCardService {
	
	CreditCardDto create(CreditCardDto creditCardDto);

	CreditCardDto update(int id, CreditCardDto creditCardDto);
	
	CreditCardDto getByUserID(int userId);
	
	CreditCardDto pay(int userId, int amount);
	
	String delete(int id);
}