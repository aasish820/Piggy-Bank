package org.piggybank.creditcard.exception;

public class CardNotFoundException extends RuntimeException {

	public CardNotFoundException(String message)
	{
		super(message);
	}
}

